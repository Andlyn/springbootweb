package com.ci6225.spring.boot.web;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.naming.directory.DirContext;

public class Mainclass
{
	public static TreeMap<String, TreeSet<String>> GetIndex() throws IOException  {
		ArrayList<Pair<String,String>> out= new ArrayList<Pair<String,String>>();
		for(int i =0;i<10;i++) 
		{
			
			//String contextPath = request.GetContextPath();
			String docID = Toolkit.DirctoryListing().get(i);
			String content = Toolkit.FileReading(docID);
			ArrayList<Pair<String,String>> outPutList =Tokenization.Tokenize(content,docID);
			ArrayList<Pair<String,String>> outPutList2 = Linguistic.Linguistics(outPutList);
			out.addAll(outPutList2);
		}
		ArrayList<Pair<String,String>> outPutList3 = PairList.sortPairList(out);
		TreeMap<String, TreeSet<String>> outMap = Transformation.trans(outPutList3);
		//TreeMap<String, ArrayList<String>> outMap2 = 
		TreeMap<String, ArrayList<String>> outMap1 = new TreeMap<String, ArrayList<String>>();
		//Iterator<String, TreeSet<String>> it = outMap.
		return outMap;		


	}
	public static ArrayList<String> hi(String key) throws IOException
    {
		ArrayList<String> keys= Tokenization.TokenizeKeys(key);
		TreeMap<String, TreeSet<String>> map = GetIndex();
		ArrayList<String> results = new ArrayList<String>();
	    for(int i=0;i<keys.size();i++) 
	    {
	    	String keyi = keys.get(i);
	    	if(map.containsKey(keyi)) 
	    	{
	    		TreeSet<String> urls = map.get(keyi);
	    		if(results.isEmpty()) 
	    		{
	    			results.addAll(urls);
	    		}
	    		else 
	    		{
	    			results.retainAll(urls);
	    		}
	    	}
	    }
	    return results;
    }
	
	/*public static void Main(String[] args) throws IOException 
	{
		System.out.println(hi("com"));
	}*/

}
