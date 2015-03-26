package com.cmsc422.models;

import com.cmsc422.statistics.Apriori;

/**
 * Created by walterwoodall on 3/7/15.
 */
public class Rule {
    public ItemSet left;
    public ItemSet right;
    double conf;
    double lift;
    double support;

    public Rule(ItemSet i1, ItemSet i2){
        this.left = i1;
        this.right = i2;
    }

    public Rule(ItemSet i1, ItemSet i2, double conf, double lift, double support){
        this.left = i1;
        this.conf = conf;
        this.lift = lift;
        this.right = i2;
        this.support = support;
    }
    @Override
    public String toString() {
        return "\""+left +
                "--->" + right + "\"" +
                ',' + support + "," + conf + "," + lift;
    }
}
