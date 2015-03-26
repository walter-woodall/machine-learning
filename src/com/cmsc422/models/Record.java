package com.cmsc422.models;

import com.cmsc422.models.Attribute;

import java.util.ArrayList;

/**
 * Created by walterwoodall on 2/23/15.
 */
public class Record {
    private String id;
    private ArrayList<Attribute> attributes;

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<Attribute> attributes) {
        this.attributes = attributes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean contains(Attribute a){
        return attributes.contains(a);
    }

    @Override
    public String toString() {
        return "Record{" +
                "id='" + id + '\'' +
                ", attributes=" + attributes +
                "}\n";
    }
}
