package com.cmsc422.statistics;

import com.cmsc422.models.Attribute;
import com.cmsc422.models.ItemSet;
import com.cmsc422.models.Record;
import com.cmsc422.models.Rule;

import java.util.ArrayList;

/**
 * Created by walterwoodall on 3/6/15.
 */
public class Apriori {
    private static final double MIN_SUP = 0.2;
    private static final double MIN_CONF = 0.60;
    ArrayList<Record> records;
    public ArrayList<Rule> rules;

    public Apriori(ArrayList<Record> records){
        this.records = records;
        this.rules = new ArrayList<Rule>();
    }

    public void run(){
        ArrayList<ItemSet> canidates;
        ArrayList<ItemSet> frequentCanidates = new ArrayList<ItemSet>();

        canidates = initializeCanidateItemSets();
        //System.out.println(canidates);

        while(canidates.size() > 0){

            frequentCanidates = getFrequentItemSets(canidates);

            if(frequentCanidates.size() > 0) {
                ArrayList<ItemSet> potentialCanidates = buildCanidates(frequentCanidates);
                canidates = prune(potentialCanidates, frequentCanidates);
            }
        }
        //System.out.println(frequentCanidates);

        Attribute ok = new Attribute("OK", 0);

        rules = getRules(frequentCanidates, ok);

        rules.addAll(getRules(frequentCanidates, new Attribute("OK", 1)));

        System.out.println(rules);
    }

    /*
    Build the inital canidate ItemSets of size 1
     */
    public ArrayList<ItemSet> initializeCanidateItemSets(){
        ArrayList<ItemSet> canidates = new ArrayList<ItemSet>();
        for(Record r : records){
            for(int index = 0; index < r.getAttributes().size()-1; index++){
                Attribute a = r.getAttributes().get(index);
                ItemSet i = new ItemSet();
                i.add(a);
                if(!canidates.contains(i)){
                    canidates.add(i);
                }
            }
        }
        return canidates;
    }

    /*
    Gets the frequentItemSet by counting the number of occurences of ItemSet i
     */
    public ArrayList<ItemSet> getFrequentItemSets(ArrayList<ItemSet> canidates){
        ArrayList<ItemSet> frequentItemSets = new ArrayList<ItemSet>();
        int minSup = (int) (MIN_SUP * records.size());
        for(ItemSet currSet : canidates){
            if(calculateSupport(currSet) > minSup){
                frequentItemSets.add(currSet);
            }
        }
        return frequentItemSets;
    }

    public ArrayList<ItemSet> buildCanidates(ArrayList<ItemSet> frequentCanidates){
        ArrayList<ItemSet> tempCanidates = new ArrayList<ItemSet>();

        for(int i = 0; i < frequentCanidates.size(); i++){
            ItemSet set1 = frequentCanidates.get(i);

            for(int j = i +1; j < frequentCanidates.size(); j++){
                ItemSet set2 = frequentCanidates.get(j);

                for(Attribute a : set2.getItemSet()){
                    ItemSet tempSet = new ItemSet(set1);

                    if(tempSet.add(a)){
                        if(!tempCanidates.contains(tempSet)){
                            tempCanidates.add(tempSet);
                        }
                    }
                }
            }
        }
        return tempCanidates;
    }
    public Record predict(Record r){
        for(Rule rule : rules){
            //System.out.println(rule);
            boolean ruleApplies = true;
            for(Attribute a : rule.left.getItemSet()){
                if(!r.getAttributes().contains(a)){
                    ruleApplies = false;
                }
            }
            if(ruleApplies){
                Attribute ok = rule.right.getItemSet().get(rule.right.size()-1);
                r.getAttributes().get(9).setValue(ok.getValue());
                return r;
            }
        }
        r.getAttributes().get(9).setValue(1);
        return r;
    }

    private ArrayList<Rule> getRules(ArrayList<ItemSet> canidates, Attribute testAttribute){
        ArrayList<Rule> ruleSet = new ArrayList<Rule>();
        for(ItemSet i : canidates){
            ItemSet temp = new ItemSet(i);
            temp.add(testAttribute);
            double supportCount = calculateSupport(temp);
            ArrayList<ItemSet> subsets = ItemSet.getSubsets(i);
            for(ItemSet subset : subsets){
                if(subset.size() > 0){
                    double subsetSupport = calculateSupport(subset);
                    double conf = supportCount/subsetSupport;
                    ItemSet result = subset.subtract(temp);
                    double lift = (double)calculateSupport(result) / records.size();
                    if(conf >= MIN_CONF){
                        Rule r = new Rule(subset, result, conf, lift,supportCount/records.size());
                        ruleSet.add(r);
                    }
                }
            }
        }
        return ruleSet;
    }
    /*
    Takes a list of potential itemSets and gets the subsets of each ItemSet and checks to make sure every subset of size
    k-1 is a frequent ItemSet. If so add to canidates.
     */
    private ArrayList<ItemSet> prune(ArrayList<ItemSet> tempCanidates, ArrayList<ItemSet> frequentCanidates){
        ArrayList<ItemSet> canidates = new ArrayList<ItemSet>();
        for(ItemSet i : tempCanidates){
            boolean match = true;
            ArrayList<ItemSet> subsets = ItemSet.getSubsets(i);
            for(ItemSet subset : subsets){
                if(subset.size() == i.size()-1){
                    if(!frequentCanidates.contains(subset)){
                        match = false;
                    }
                }
            }
            if(match){
                canidates.add(i);
            }
        }
        return canidates;
    }

    private int calculateSupport(ItemSet s){
        int count = 0;
        for(Record r: records){
            boolean match = true;
            for(Attribute a : s.getItemSet()){
                if(!r.contains(a)){
                    match = false;
                }
            }
            if(match){
                count++;
            }
        }
        return count;
    }

}
