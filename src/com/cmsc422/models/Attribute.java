package com.cmsc422.models;

/**
 * Created by walterwoodall on 2/23/15.
 */
public class Attribute {
    private String name;
    private int value;
    private boolean isUnknown;
    private double surrogate;

    public Attribute(String name, int value) {
        this.name = name;
        this.value = value;
        surrogate = -1;
        isUnknown = false;
    }

    public Attribute(String name, String value){
        this.name = name;
        this.value = Integer.valueOf(value);
        surrogate = -1;
        isUnknown = false;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setSurrogate(double surrogate) {
        this.surrogate = surrogate;
    }

    public double getSurrogate() {
        return surrogate;
    }

    public void setUnknown(boolean isUnknown) {
        this.isUnknown = isUnknown;
    }

    public boolean isUnknown() {
        return isUnknown;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attribute attribute = (Attribute) o;

        if (value != attribute.value) return false;
        if (name != null ? !name.equals(attribute.name) : attribute.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + value;
        return result;
    }

    @Override
    public String toString() {
        return name + "=" + value;
    }
}
