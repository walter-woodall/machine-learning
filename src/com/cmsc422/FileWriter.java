package com.cmsc422;

import com.cmsc422.models.Record;
import com.cmsc422.models.Rule;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by walterwoodall on 2/24/15.
 */
public class FileWriter {
    public static void writeRecords(ArrayList<Record> records){
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("/Users/walterwoodall/Development/umd/cmsc422/submit.csv"), "utf-8"));
            writer.write("ID,OK\n");
            for(Record r : records){
                String id = r.getId();
                String ok = Integer.toString(r.getAttributes().get(9).getValue());

                writer.write(id + "," + ok + "\n");
            }
        } catch (IOException ex) {
            System.out.println("Error writing to file");
        } finally {
            try {writer.close();} catch (Exception ex) {}
        }
    }

    public static void writeRules(ArrayList<Rule> rules){
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("/Users/walterwoodall/Development/umd/cmsc422/rules.csv"), "utf-8"));
            writer.write("Rule,Support,Confidence,Lift\n");
            for(Rule r : rules){
                writer.write(r.toString() + "\n");
            }
        } catch (IOException ex) {
            System.out.println("Error writing to file");
        } finally {
            try {writer.close();} catch (Exception ex) {}
        }
    }

}
