package ru.job4j.sql.sqllite;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Data manipulation
 */
public class StoreSQL implements AutoCloseable {
    private final Config config;
    private Connection connect;
    private final String tempPath = System.getProperty("java.io.tmpdir");
    private final File xmlFirstFile = new File(tempPath, "xmlFirst.xml");
    private final File xmlSecondFile = new File(tempPath, "xmlSecond.xml");

    public StoreSQL(Config config) {
        this.config = config;
        connectToDB();
    }

    private void connectToDB() {
        String url = config.get("url");
        try {
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                connect = conn;
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Put to base n records
     * @param size records amount
     */
    public void generate(int size) {
        createTableIfNotExist();
        if (!load().isEmpty()) {
            deleteAllRows();
        }
        var sql = new StringBuilder("INSERT INTO store VALUES ");
        for (int i = 0; i < size; i++) {
            sql.append(String.format(" (%d),", i));
        }
        sql.replace(sql.length() - 1, sql.length(), ";");
        try (PreparedStatement insertStatement = connect.prepareStatement(sql.toString())) {
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get entries from db, and write it to xml file
     * @throws JAXBException
     */
    public void listToXml() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(new Entries(load()), xmlFirstFile);
    }

    /**
     * convert xml from format :
     * <entries>
     * <entry>
     * <field>value of field</field>
     * </entry>
     * ...
     * <entry>
     * <field>value of field</field>
     * </entry>
     * </entries>
     *
     * to format:
     * <entries>
     * <entry field="value of field "/>
     * ...
     * <entry field="value of field "/>
     * </entries>
     *
     * @throws TransformerException
     */
    public void xmlToXmlByXstl() throws TransformerException {
        String xsl = "<?xml version=\"1.0\"?>" +
                "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">" +
                "<xsl:output indent=\"yes\"/>" +
                "<xsl:template match=\"/\">" +
                "<entries>" +
                "   <xsl:for-each select=\"entries/entry\">" +
                "       <entry>" +
                "           <xsl:attribute name=\"field\">" +
                "               <xsl:value-of select=\"field\"/>" +
                "           </xsl:attribute>" +
                "       </entry>" +
                "   </xsl:for-each>" +
                " </entries>" +
                "</xsl:template>" +
                "</xsl:stylesheet>";
        try {
            byte[] xml = Files.readAllBytes(xmlFirstFile.toPath());
            TransformerFactory factory = TransformerFactory.newInstance();

            Transformer transformer = factory.newTransformer(
                    new StreamSource(
                            new ByteArrayInputStream(xsl.getBytes()))
            );
            transformer.transform(new StreamSource(
                            new ByteArrayInputStream(xml)),
                    new StreamResult(xmlSecondFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * parse xml file and evaluate sum of fields value
     * @return sum
     */
    public int parseXmlAndEvaluateSum() {
        SAXContentHandler contentHandler = new SAXContentHandler();
        try {
            byte[] xml = Files.readAllBytes(xmlSecondFile.toPath());
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setNamespaceAware(true);
            SAXParser saxParser = spf.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(contentHandler);
            xmlReader.parse(new InputSource(new ByteArrayInputStream(xml)));
        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        int result = -1;
        Optional<Integer> sum = contentHandler.getFields()
                .stream()
                .reduce(Integer::sum);
        if (sum.isPresent()) {
            result = sum.get();
        }
        return result;
    }

    private void deleteAllRows() {
        String sql = "DELETE FROM store";
        try (PreparedStatement insertStatement = connect.prepareStatement(sql)) {
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTableIfNotExist() {
        try (PreparedStatement createTableStatement = connect.prepareStatement(
                "CREATE TABLE IF NOT EXISTS store(field INTEGER);")) {
            createTableStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable() {
        try (var statement = connect.createStatement()) {
            statement.execute("DROP TABLE store");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * get add records from db
     * @return List of records
     */
    public List<Entry> load() {
        List<Entry> list = new ArrayList<>(100);
        try (Statement statement = connect.createStatement()) {
            var resultSet = statement.executeQuery("SELECT * FROM store");
            while (resultSet.next()) {
                list.add(new Entry(resultSet.getInt("field")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }
}