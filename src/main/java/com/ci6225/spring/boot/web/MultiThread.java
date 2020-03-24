package com.ci6225.spring.boot.web;

import java.io.IOException;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class MultiThread extends Thread{
	public int Begin;
	public int End;
	public List<String> Files;
	public TreeMap<String, TreeSet<String>> treemap;
    public static int count=3;
	public MultiThread(int begin,int end,List<String> files )
	{  
		this.Begin = begin;
		this.End = end;
		this.Files = files;
	}
		    @Override
		    public void run() {
		        while(true){
		        	try {
		        		treemap = Mainclass.GetPartIndex(Begin,End,Files);
		        		count--;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }

}
}
