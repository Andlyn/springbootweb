package com.ci6225.spring.boot.web;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Linguistic {
    public  static ArrayList<Pair<String,String>> Linguistics(ArrayList<Pair<String,String>> list)
    {
        PorterStemmer porterStemmer = new PorterStemmer();
        ArrayList<Pair<String,String>> outPutList = new ArrayList<Pair<String,String>> () ;
        for (Pair<String,String> pair:list
             ) {
            Pair<String,String> newPair = new Pair<String,String>(porterStemmer.stemWord(pair.getKey()),pair.getValue());
            outPutList.add(newPair);
        }
        return outPutList;
    }
    
    public  static ArrayList<String> LinguisticsKeys(ArrayList<String> list)
    {
        PorterStemmer porterStemmer = new PorterStemmer();
        ArrayList<String> outPutList = new ArrayList<String> () ;
        for (String pair:list
             ) {
              outPutList.add(porterStemmer.stemWord(pair));
        }
        return outPutList;
    }
}
