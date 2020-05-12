package ru.job4j.sql.sqllite;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SAXContentHandler extends DefaultHandler {
    private final List<Integer> fields = new ArrayList<>();

    public List<Integer> getFields() {
        return fields;
    }

    @Override
    public void startDocument() {
        System.out.println("Start parse XML...");
    }

    @Override
    public void startPrefixMapping(String prefix, String uri) {
        System.out.println(prefix);
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) {
        if (qName.equals("entry")) {
            fields.add(Integer.valueOf(atts.getValue("field")));
        }
    }

    @Override
    public void endDocument() {
        System.out.println("Stop parse XML...");
    }
}
