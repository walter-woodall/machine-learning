package com.cmsc422;

import com.cmsc422.models.Record;
import com.cmsc422.statistics.Apriori;

import java.io.File;
import java.util.ArrayList;

public class Main {
    public static ArrayList<String> attrMap;
    public static ArrayList<Integer> usedAttributes = new ArrayList<Integer>();
    public static final String PATH_TO_TRAIN = "/Users/walterwoodall/Development/umd/cmsc422/training.csv";
    public static final String PATH_TO_TEST = "/Users/walterwoodall/Development/umd/cmsc422/test.csv";
    public static void main(String[] args) {
        populateAttrMap();

        Tree t = new Tree();
        ArrayList<Record> records;

        //read in test data
        records = FileReader.buildRecords(PATH_TO_TRAIN);
        ArrayList<Record> data = FileReader.buildRecords(PATH_TO_TEST);
        //System.out.println(data);

        
        Node root = new Node();
        root.setData(records);

        Node r = t.buildTree(records, root);

        for(Record re : data){
           t.traverseTree(re, root);
        }

        FileWriter.writeRecords(data);


        /*
        Apriori a = new Apriori(records);
        a.run();

        for(Record re: data){
            a.predict(re);
        }
        FileWriter.writeRecords(data);
        FileWriter.writeRules(a.rules);
        */
    }

    public static void populateAttrMap(){
        attrMap = new ArrayList<String>();
        attrMap.add("CT");
        attrMap.add("UCSZ");
        attrMap.add("UCSH");
        attrMap.add("MA");
        attrMap.add("SEC");
        attrMap.add("BN");
        attrMap.add("BC");
        attrMap.add("NN");
        attrMap.add("M");
        attrMap.add("OK");
    }

    public static boolean isAttributeUsed(int attribute) {
        if(usedAttributes.contains(attribute)) {
            return true;
        }
        else {
            return false;
        }
    }

}
