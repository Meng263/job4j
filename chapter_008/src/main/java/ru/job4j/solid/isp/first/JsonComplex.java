package ru.job4j.solid.isp.first;

public class JsonComplex implements IJsonConvertibles {
    private Object jsonObject;

    @Override
    public String asString() {
        throw new UnsupportedOperationException("not implemented yet!");
    }

    @Override
    public int asInt() {
        throw new UnsupportedOperationException("not implemented yet!");
    }

    @Override
    public long asLong() {
        throw new UnsupportedOperationException("not implemented yet!");
    }

    @Override
    public Object[] asArray() {
        return (Object[]) jsonObject;
    }

    @Override
    public boolean asBoolean() {
        throw new UnsupportedOperationException("not implemented yet!");
    }
}
