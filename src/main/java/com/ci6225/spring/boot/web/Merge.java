package com.ci6225.spring.boot.web;

import java.util.*;

public class Merge {
	//生成倒排索引
	public static TreeMap<String, TreeSet<String>> merge(TreeMap<String, TreeSet<String>> map1, TreeMap<String, TreeSet<String>> map2) {
		//TreeMap(map1)内部的
		TreeSet<String> innerSet;
		//传进来的map2的迭代器
		Iterator<Map.Entry<String, TreeSet<String>>> it = map2.entrySet().iterator();
		for(;it.hasNext();) {
			Map.Entry<String, TreeSet<String>> temp = it.next();
			if(map1.containsKey(temp.getKey())) { //如果倒排索引里已经有这个词了
				map1.get(temp.getKey()).addAll(temp.getValue());
			} 
			else { //倒排索引中还没添加这个词
				innerSet = new TreeSet<String>();
				innerSet.addAll(temp.getValue());
				map1.put((String)temp.getKey(), innerSet);
			}
		}
		//System.out.println(map1);
		return map1;
	}
}
