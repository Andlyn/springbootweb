package com.ci6225.spring.boot.web;
import javafx.util.Pair;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;

public class PairList {
    //public static List<Pair<String, String>> pl;

    //sort the pairs
    public static ArrayList<Pair<String, String>>  sortPairList(ArrayList<Pair<String, String>> pl) {
        Collections.sort(pl, new Comparator<Pair<String, String>>() {
            public int compare(Pair<String, String> a, Pair<String, String> b) {
            	String aKey = a.getKey();
                String aValue = a.getValue();
                String bKey = b.getKey();
                String bValue = b.getValue();
                if (aKey == null || aValue == null || bKey == null || bValue == null) {
                    throw new NullPointerException("contact pkuimyy@gmail.com");
                }

                if (aKey.compareTo(bKey) > 0) {
                    return 1;
                } else if (aKey.compareTo(bKey) == 0) {
                    if (aValue.compareTo(bValue) > 0) {
                        return 1;
                    } else if (aValue.compareTo(bValue) == 0) {
                        return 0;
                    } else {
                        return -1;
                    }
                } else {
                    return -1;
                }
            }
        });
        return pl;
    }
    // generate list of the pairs
    /*public void generatePairList() {
        this.pl.add(new SimpleEntry<String, String>("apple", "boxes"));
        this.pl.add(new SimpleEntry<String, String>("boy", "cats"));
        this.pl.add(new SimpleEntry<String, String>("apple", "eggs"));
        this.pl.add(new SimpleEntry<String, String>("boy", "dogs"));
        this.pl.add(new SimpleEntry<String, String>("life", "happy"));
    }*/

    // add pairs when needed
    /*public void addPair(String key, String value) {
        this.pl.add(new Pair<String, String>(key, value));
    }

    // print the list
    /*public void printPairList() {
        for (int i = 0; i < this.pl.size(); i++) {
            Pair<String, String> p = this.pl.get(i);
            System.out.println("(" + p.getKey() + ", " + p.getValue() + ")");
        }
    }

    /*public static void main(String[] args) {
        PairList pl = new PairList();
        pl.generatePairList();

        // the original pair
        pl.printPairList();
        System.out.println();

        // the sorted pair
        pl.sortPairList();
        pl.printPairList();
        System.out.println();
    }*/
}