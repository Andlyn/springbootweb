package com.ci6225.spring.boot.web;

import java.util.*;

import javafx.util.Pair;

public class Optimizer {
	
	public static ArrayList <String> getFrequency(TreeMap<String, TreeSet<String>> list) 
	{
		ArrayList <Pair<String,Integer>> sortlist = new ArrayList <Pair<String,Integer>>();
		Iterator iter = list.entrySet().iterator();
		 
		while(iter.hasNext()) {
		 
		    Map.Entry entry = (Map.Entry)iter.next();
		 
		    // get key
		 
		    String key = (String)entry.getKey();
		 
		     // get value
		 
		    TreeSet set = (TreeSet)entry.getValue();
		    sortlist.add(new Pair(key,set.size()));
		 
		}
		sortlist.sort(new Comparator<Pair<String,Integer>>(){
		    @Override
			public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) 
		    {
		    	return o2.getValue().compareTo(o1.getValue());

			}
	});
		ArrayList <String> freList = new ArrayList <String>();
		for(int i =0;i<sortlist.size();i++) {
			freList.add(sortlist.get(i).getKey());
		}
		return freList;

	}
}
