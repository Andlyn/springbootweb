package com.ci6225.spring.boot.web;

import java.util.*;
import javafx.util.Pair;

public class Transformation {
	//生成倒排索引
	public static TreeMap<String, TreeSet<String>> trans(List<Pair<String, String>> list) {
		//TreeMap内部的
		TreeSet<String> innerSet;
		//倒排索引
		TreeMap<String, TreeSet<String>> index = new TreeMap<String, TreeSet<String>>();
		//传进来的list的迭代器
		Iterator<Pair<String, String>> it = list.iterator();
		for(;it.hasNext();) {
			Pair<String, String> temp = it.next();
			if(index.containsKey(temp.getKey())) { //如果倒排索引里已经有这个词了
				index.get(temp.getKey()).add((String)temp.getValue());
			} 
			else { //倒排索引中还没添加这个词
				innerSet = new TreeSet<String>();
				innerSet.add((String)temp.getValue());
				index.put((String)temp.getKey(), innerSet);
			}
		}
		System.out.println(index);
		return index;
	}
	
}
	/*public static void main(String args[]) {
		//假设如下为传进来的第一组数据
		List<Pair<String, String>> list1 = new ArrayList<Pair<String, String>>();
		list1.add(new Pair<String, String>("aa","1"));
		list1.add(new Pair<String, String>("aa","2"));
		list1.add(new Pair<String, String>("az","1"));
		list1.add(new Pair<String, String>("ba","1"));
		list1.add(new Pair<String, String>("ba","2"));
		list1.add(new Pair<String, String>("ba","3"));
		list1.add(new Pair<String, String>("bz","2"));
		list1.add(new Pair<String, String>("ca","1"));
		list1.add(new Pair<String, String>("ca","3"));
		list1.add(new Pair<String, String>("cz","3"));
		
		//假设如下为传进来的第二组数据
		List<Pair<String, String>> list2 = new ArrayList<Pair<String, String>>();
		list2.add(new Pair<String, String>("aa","3"));
		list2.add(new Pair<String, String>("aa","4"));
		list2.add(new Pair<String, String>("az","1"));
		list2.add(new Pair<String, String>("ba","1"));
		list2.add(new Pair<String, String>("ba","4"));
		list2.add(new Pair<String, String>("ba","4"));
		list2.add(new Pair<String, String>("bz","3"));
		list2.add(new Pair<String, String>("ca","2"));
		list2.add(new Pair<String, String>("ca","4"));
		list2.add(new Pair<String, String>("cz","4"));
		
		Merge.merge(trans(list1), trans(list2));	
	}
}*/
