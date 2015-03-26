package com.cmsc422;

import com.cmsc422.models.Attribute;
import com.cmsc422.models.Record;

import java.util.ArrayList;

/**
 * Created by walterwoodall on 2/23/15.
 */
public class Node {
    private Node parent;
    public Node[] children;
    private ArrayList<Record> data;
    private double entropy;
    private boolean isUsed;
    private Attribute testAttribute;

    public Node(){
        this.data = new ArrayList<Record>();
        setEntropy(0.0);
        setParent(null);
        setChildren(null);
        setUsed(false);
        setTestAttribute(new Attribute("", 0));
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    public void setData(ArrayList<Record> data) {
        this.data = data;
    }

    public ArrayList<Record> getData() {
        return data;
    }

    public void setEntropy(double entropy) {
        this.entropy = entropy;
    }

    public double getEntropy() {
        return entropy;
    }

    public void setChildren(Node[] children) {
        this.children = children;
    }

    public Node[] getChildren() {
        return children;
    }

    public void setUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setTestAttribute(Attribute testAttribute) {
        this.testAttribute = testAttribute;
    }

    public Attribute getTestAttribute() {
        return testAttribute;
    }

    public int getPrediction(){
        int yesCount = 0;
        int noCount = 0;
        for(int i = 0; i < data.size(); i ++){
            Record r = data.get(i);
            if(r.getAttributes().get(9).getValue() == 1){
                yesCount++;
            }else{
                noCount++;
            }
        }

        if(yesCount > noCount){
            return 1;
        }else{
            return 0;
        }
    }
}
