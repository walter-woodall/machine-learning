package com.cmsc422.test;

import com.cmsc422.models.Attribute;
import com.cmsc422.models.ItemSet;
import com.cmsc422.models.Record;
import com.cmsc422.statistics.Apriori;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by walterwoodall on 3/6/15.
 */
public class ItemSetTest {

    //@Test
    public void testEquals(){
        ItemSet i1 = new ItemSet();
        ItemSet i2 = new ItemSet();

        i1.add(new Attribute("B", 1));
        i2.add(new Attribute("B", 1));
        assertEquals(i1, i2);

        i1.add(new Attribute("A", 2));
        assertNotEquals(i1, i2);

        i1.add(new Attribute("C", 1));
        i2.add(new Attribute("C", 1));
        i2.add(new Attribute("A", 2));
        assertEquals(i1, i2);

    }

    //@Test
    public void testContains(){
        ArrayList<ItemSet> canidates = new ArrayList<ItemSet>();
        ItemSet i1 = new ItemSet();
        ItemSet i2 = new ItemSet();

        i1.add(new Attribute("B", 1));
        i2.add(new Attribute("B", 1));
        i1.add(new Attribute("A", 2));
        i1.add(new Attribute("C", 1));
        i2.add(new Attribute("C", 1));
        i2.add(new Attribute("A", 2));

        canidates.add(i1);
        assertEquals(canidates.size(), 1);
        assertTrue(canidates.contains(i2));
    }

    //@Test
    public void testBuildCanidates(){
        ArrayList<ItemSet> frequentCanidates = new ArrayList<ItemSet>();
        ArrayList<ItemSet> canidates = new ArrayList<ItemSet>();

        ItemSet i1 = new ItemSet();
        ItemSet i2 = new ItemSet();
        ItemSet i3 = new ItemSet();

        Attribute a = new Attribute("A", 1);
        Attribute b = new Attribute("B", 1);
        Attribute c = new Attribute("C", 1);

        i1.add(a);
        i1.add(b);

        i2.add(a);
        i2.add(c);

        i3.add(b);
        i3.add(c);

        canidates.add(i1);
        canidates.add(i2);
        canidates.add(i3);

        frequentCanidates.add(i1);
        frequentCanidates.add(i3);
        //frequentCanidates.add(i2);

        //frequentCanidates = Apriori.buildCanidates(frequentCanidates, canidates);
        System.out.println(frequentCanidates);

    }

    @Test
    public void testRun(){
        Attribute a1 = new Attribute("A", 1);
        Attribute a2 = new Attribute("A", 2);
        Attribute b1 = new Attribute("B", 1);
        Attribute b2 = new Attribute("B", 2);

        Attribute ok0 = new Attribute("OK", 0);
        Attribute ok1 = new Attribute("OK", 1);

        Record r1 = new Record();
        ArrayList<Attribute> ra1 = new ArrayList<Attribute>();
        ra1.add(a2);
        ra1.add(b1);
        ra1.add(ok0);
        r1.setAttributes(ra1);

        Record r2 = new Record();
        ArrayList<Attribute> ra2 = new ArrayList<Attribute>();
        ra2.add(a2);
        ra2.add(b2);
        ra2.add(ok1);
        r2.setAttributes(ra2);

        Record r3 = new Record();
        ArrayList<Attribute> ra3 = new ArrayList<Attribute>();
        ra3.add(a1);
        ra3.add(b2);
        ra3.add(ok1);
        r3.setAttributes(ra3);

        ArrayList<Record> records = new ArrayList<Record>();
        records.add(r1);
        records.add(r2);
        records.add(r3);
        Apriori a = new Apriori(records);
        a.run();
    }

}
