package com.ci6225.spring.boot.web;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Tokenization {


    public  static ArrayList<Pair<String,String>> Tokenize(String content, String docId)
    {
        ArrayList<Pair<String,String>> outPutList = new ArrayList<Pair<String,String>> () ;
        StringTokenizer terms = new StringTokenizer(content);
        int sum = terms.countTokens();
        for(int i = 0;i<sum;i++){
            Pair<String,String> term = new Pair<>(terms.nextToken(),docId);
            outPutList.add(term);
        }
        return outPutList;
    }
    
    public  static ArrayList<String> TokenizeKeys(String content)
    {
        ArrayList<String> outPutList = new ArrayList<String> () ;
        StringTokenizer terms = new StringTokenizer(content);
        int sum = terms.countTokens();
        for(int i = 0;i<sum;i++){
        	
            outPutList.add(terms.nextToken());
        }
        return outPutList;
    }
}
