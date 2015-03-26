package com.cmsc422;

import com.cmsc422.models.Attribute;
import com.cmsc422.models.Record;
import com.cmsc422.statistics.Entropy;

import java.util.ArrayList;

/**
 * Created by walterwoodall on 2/23/15.
 */
public class Tree {

    public Node buildTree(ArrayList<Record> records, Node root){
        int bestAttribute = -1;
        double bestGain = 0;

        root.setEntropy(Entropy.calculateEntropy(root.getData()));

        if(root.getEntropy() == 0){
            return root;
        }

        for(int i = 0; i < 9; i++){
            if(!Main.isAttributeUsed(i)){
                double entropy = 0;
                ArrayList<Double> entropies = new ArrayList<Double>();
                ArrayList<Integer> setSizes = new ArrayList<Integer>();

                for(int j = 1; j < 11; j++){
                    ArrayList<Record> subset = subset(root, i, j);
                    setSizes.add(subset.size());
                    entropy = Entropy.calculateEntropy(subset);
                    entropies.add(entropy);
                }

                double gain = Entropy.calculateGain(root.getEntropy(), entropies, setSizes, root.getData().size());

                if(gain > bestGain){
                    bestAttribute = i;
                    bestGain = gain;
                }
            }
        }

        if(bestAttribute != -1){
            //System.out.println("Best Attribute is --->" + Main.attrMap.get(bestAttribute));
            root.setTestAttribute(new Attribute(Main.attrMap.get(bestAttribute), 0));
            root.children = new Node[10];
            root.setUsed(true);
            Main.usedAttributes.add(bestAttribute);

            for(int i = 0; i < 10; i++){
                root.children[i] = new Node();
                root.children[i].setParent(root);
                root.children[i].setData(subset(root, bestAttribute, i+1));
                root.children[i].getTestAttribute().setValue(i+1);
            }

            for(int j = 0; j < 10; j++){
                buildTree(root.children[j].getData(), root.children[j]);
            }
            root.setData(null);
        }
        return root;
    }

    public ArrayList<Record> subset(Node root, int attr, int value){
        ArrayList<Record> subset = new ArrayList<Record>();

        for(int i = 0; i < root.getData().size(); i++){
            Record record = root.getData().get(i);

            if(record.getAttributes().get(attr).getValue() == value){
                subset.add(record);
            }
        }
        return subset;
    }

    public int traverseTree(Record r, Node root){
        Node curr = root;
        while(curr.children != null){
            int value = r.getAttributes().get(Main.attrMap.indexOf(root.getTestAttribute().getName())).getValue();

            if(curr.children[value-1] != null){
                curr = curr.children[value-1];
            }
        }
        r.getAttributes().get(9).setValue(curr.getPrediction());
        return curr.getPrediction();
    }
}
