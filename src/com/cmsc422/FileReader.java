package com.cmsc422;

import com.cmsc422.models.Attribute;
import com.cmsc422.models.Record;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by walterwoodall on 2/23/15.
 */
public class FileReader {

    public static ArrayList<Record> buildRecords(String filepath){
        BufferedReader reader = null;
        ArrayList<Record> records = new ArrayList<Record>();

        try{
            File f = new File(filepath);
            FileInputStream fis = new FileInputStream(f);
            reader = new BufferedReader(new InputStreamReader(fis));

            //Read the header line
            reader.readLine();
            //Process the data now
            String line;
            Record r = null;
            ArrayList<Attribute> attributes;

            while((line = reader.readLine()) != null){
                StringTokenizer st = new StringTokenizer(line, ",");
                attributes = new ArrayList<Attribute>();
                r = new Record();

                String id = st.nextToken();
                String ct = st.nextToken();
                String ucsz = st.nextToken();
                String ucsh = st.nextToken();
                String ma = st.nextToken();
                String sec = st.nextToken();
                String bn = st.nextToken();
                String bc = st.nextToken();
                String nn = st.nextToken();
                String m = st.nextToken();
                String ok = "-1";
                if(st.hasMoreElements()){
                    ok = st.nextToken();
                }

                attributes.add(new Attribute("CT", ct));
                attributes.add(new Attribute("UCSZ", ucsz));
                attributes.add(new Attribute("UCSH", ucsh));
                attributes.add(new Attribute("MA", ma));
                attributes.add(new Attribute("SEC", sec));
                attributes.add(new Attribute("BN", bn));
                attributes.add(new Attribute("BC", bc));
                attributes.add(new Attribute("NN", nn));
                attributes.add(new Attribute("M", m));
                attributes.add(new Attribute("OK", ok));

                r.setId(id);
                r.setAttributes(attributes);
                records.add(r);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Could not close reader");
                }
            }
        }
        return records;
    }
}
