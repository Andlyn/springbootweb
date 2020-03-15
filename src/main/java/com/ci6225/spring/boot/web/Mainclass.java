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
	public static void GetIndex() throws IOException  {
		
		List<String> files = Toolkit.DirctoryListing();
		//int size = files.size();
		int size = 50;
		TreeMap<String, TreeSet<String>> list1 = GetPartIndex(0,size/2,files);
		TreeMap<String, TreeSet<String>> list2 = GetPartIndex(size/2,size,files);
		TreeMap<String, TreeSet<String>> list3 = Merge.merge(list1, list2);		
		//list3.
		File path  = new File(ResourceUtils.getURL("classpath:").getPath());
		convertToXml(list3,path + "/static/index.xml");

	}
	public static TreeMap<String, TreeSet<String>> GetPartIndex(int begin,int end,List<String> files) throws IOException
	{
		ArrayList<Pair<String,String>> out= new ArrayList<Pair<String,String>>();		
		for(int i =begin;i<end;i++)
		{
			String docID = files.get(i);
			String content = Toolkit.FileReading(docID);
			ArrayList<Pair<String,String>> outPutList =Tokenization.Tokenize(content,docID);
			ArrayList<Pair<String,String>> outPutList2 = Linguistic.Linguistics(outPutList);
			out.addAll(outPutList2);
		}
		ArrayList<Pair<String,String>> outPutList3 = PairList.sortPairList(out);
		TreeMap<String, TreeSet<String>> outMap = Transformation.trans(outPutList3);
		return outMap;
	}

	public static ArrayList<String> hi(String key) throws IOException
    {
		ArrayList<String> keys= Tokenization.TokenizeKeys(key);
		keys = Linguistic.LinguisticsKeys(keys);
		File path  = new File(ResourceUtils.getURL("classpath:").getPath());
		TreeMap<String, TreeSet<String>> map = (TreeMap<String, TreeSet<String>>) convertXmlFileToObject(TreeMap.class, path + "/static/index.xml");
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
	public static void convertToXml(TreeMap<String, TreeSet<String>> obj, String path) throws IOException {  
        try {  
        	FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
             oos.writeObject(obj);
             oos.flush();
             fos.flush();
             oos.close();
             }
        catch (FileNotFoundException e) {e.printStackTrace();}
            
	}
        
        @SuppressWarnings("unchecked")  
        /** 
         * 将file类型的xml转换成对象 
         */  
        public static TreeMap<String, TreeSet<String>> convertXmlFileToObject(Class clazz, String xmlPath) throws IOException {  
        	TreeMap<String, TreeSet<String>> anotherList = new TreeMap<String, TreeSet<String>>();
        	try {  
                FileInputStream fis = new FileInputStream(xmlPath);
                ObjectInputStream ois = new ObjectInputStream(fis);
                anotherList = (TreeMap<String, TreeSet<String>>) ois.readObject();
                ois.close(); 
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
            return anotherList;  
        }  

	/*public static void Main(String[] args) throws IOException 
	{
		GetIndex();
	}*/

}
