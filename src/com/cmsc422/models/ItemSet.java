package com.cmsc422.models;

import java.util.ArrayList;

/**
 * Created by walterwoodall on 3/6/15.
 */
public class ItemSet {
    private ArrayList<Attribute> itemSet;

    public ItemSet(){
        itemSet = new ArrayList<Attribute>();
    }

    public ItemSet(ItemSet i){
        itemSet = new ArrayList<Attribute>();
        for(Attribute a : i.getItemSet()){
            this.itemSet.add(a);
        }
    }
    public boolean add(Attribute item){
        if(item == null) return false;

        if(!itemSet.contains(item)){
            itemSet.add(item);
            return true;
        }
        return false;
    }

    public int size(){
        return itemSet.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemSet itemSet1 = (ItemSet) o;

        if(itemSet1.size() == itemSet.size()){
            for(Attribute a : itemSet1.itemSet){
                if(!itemSet.contains(a)){
                    return false;
                }
            }
        }else{
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return itemSet.hashCode();
    }

    @Override
    public String toString() {
        return "{" +
                itemSet +
                '}';
    }

    public ArrayList<Attribute> getItemSet(){
        return itemSet;
    }

    public boolean subset(ItemSet i){
        for(Attribute a : itemSet){
            if(!i.getItemSet().contains(a)){
                return false;
            }
        }
        return true;
    }
    public void addAll(ItemSet set){
        itemSet.addAll(set.getItemSet());
    }

    public Attribute remove(int index){
        return itemSet.remove(index);
    }
    public static ArrayList<ItemSet> getSubsets(ItemSet set) {

        ArrayList<ItemSet> subsetCollection = new ArrayList<ItemSet>();

        if (set.size() == 0) {
            subsetCollection.add(new ItemSet());
        } else {
            ItemSet reducedSet = new ItemSet();

            reducedSet.addAll(set);

            Attribute first = reducedSet.remove(0);
            ArrayList<ItemSet> subsets = getSubsets(reducedSet);
            subsetCollection.addAll(subsets);

            subsets = getSubsets(reducedSet);

            for (ItemSet subset : subsets) {
                subset.getItemSet().add(0, first);
            }

            subsetCollection.addAll(subsets);
        }

        return subsetCollection;
    }

    public ItemSet subtract(ItemSet i2){
        for(int index = 0; index < i2.size(); index++){
            if(itemSet.contains(i2.getItemSet().get(index))){
                i2.remove(index);
                index--;
            }
        }

        return i2;
    }
}
