package com.ci6225.spring.boot.web;
import javafx.util.Pair;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.naming.directory.DirContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.core.serializer.Serializer;
import org.springframework.util.ResourceUtils;

public class Mainclass
{
	public static File path;
	public static TreeMap<String, TreeSet<String>> map;
	public static ArrayList<String> sortList;
	public static void GetIndex() throws IOException, InterruptedException  {
		//Get all the file path
		List<String> files = Toolkit.DirctoryListing();
		int size = files.size();
		
		//int size = 500;
		/*TreeMap<String, TreeSet<String>> list1 = GetPartIndex(0,size/2,files);
		TreeMap<String, TreeSet<String>> list2 = GetPartIndex(size/2,size,files);*/
		
		/*TreeMap<String, TreeSet<String>> list1 = GetPartIndex(0,size/4,files);
		TreeMap<String, TreeSet<String>> list2 = GetPartIndex(size/4,2*size/4,files);
		TreeMap<String, TreeSet<String>> list21 = GetPartIndex(2*size/4,3*size/4,files);
		TreeMap<String, TreeSet<String>> list22 = GetPartIndex(3*size/4,size,files);
		MultiThread threadRuning1 = new MultiThread(0,size/3,files);
        threadRuning1.start();
        MultiThread threadRuning2 = new MultiThread(size/3,2*size/3,files);
        threadRuning2.start();
        MultiThread threadRuning3 = new MultiThread(2*size/3,size,files);
        threadRuning3.start();
        */
		
		//divided the files into 3 parts
		TreeMap<String, TreeSet<String>> list1 = GetPartIndex(0,size/3,files);
		TreeMap<String, TreeSet<String>> list2 = GetPartIndex(size/3,2*size/3,files);
		TreeMap<String, TreeSet<String>> list21 = GetPartIndex(2*size/3,size,files);
		TreeMap<String, TreeSet<String>> list3 = Merge.merge(list1, list2);	
		//Merge the index
		list3 = Merge.merge(list3, list21);	
		File path  = new File(ResourceUtils.getURL("classpath:").getPath());
		Toolkit.convertToXml(list3,path + "/static/index.xml");
			sortList=Optimizer.getFrequency(list3);
		
	}
	
	//To build index
	public static TreeMap<String, TreeSet<String>> GetPartIndex(int begin,int end,List<String> files) throws IOException
	{
		ArrayList<Pair<String,String>> out= new ArrayList<Pair<String,String>>();
		//handle each files individually and add the tokens into one list
		for(int i =begin;i<end;i++)
		{
			String docID = files.get(i);
			String content = Toolkit.FileReading(docID);
			ArrayList<Pair<String,String>> outPutList =Tokenization.Tokenize(content,docID);
			ArrayList<Pair<String,String>> outPutList2 = Linguistic.Linguistics(outPutList);
			out.addAll(outPutList2);
		}
		//sort the index
		ArrayList<Pair<String,String>> outPutList3 = PairList.sortPairList(out);
		//Transformation the list3
		TreeMap<String, TreeSet<String>> outMap = Transformation.trans(outPutList3);
		return outMap;
	}
   
	//to handle the calling from client
	public static ArrayList<String> hi(String key) throws IOException
    {
		//tokenize and stem the key words like the files
		ArrayList<String> keys= Tokenization.TokenizeKeys(key);
		keys = Linguistic.LinguisticsKeys(keys);
		//get the index which is stored in files
if(path==null)
{
	path  = new File(ResourceUtils.getURL("classpath:").getPath());
	map = (TreeMap<String, TreeSet<String>>) Toolkit.convertXmlFileToObject(TreeMap.class, path + "/static/index.xml");
}
		

		ArrayList<String> results = new ArrayList<String>();
		keys.sort(new Comparator<String>(){
		    @Override
			public int compare(String o1, String o2) 
		    {
		    	int index1 = sortList.indexOf(o1);
		    	int index2 = sortList.indexOf(o2);
		    	return index1>=index2?0:1;

			}
	});
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

}
