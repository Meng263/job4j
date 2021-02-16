package ru.job4j.solid.isp.first;

public class JsonPrimitive implements IJsonConvertibles {
    private Object jsonObject;

    @Override
    public String asString() {
        return (String) jsonObject;
    }

    @Override
    public int asInt() {
        return (Integer) jsonObject;
    }

    @Override
    public long asLong() {
        return (Long) jsonObject;
    }

    @Override
    public Object[] asArray() {
        throw new UnsupportedOperationException("not implemented yet!");
    }

    @Override
    public boolean asBoolean() {
        return (Boolean) jsonObject;
    }
}
