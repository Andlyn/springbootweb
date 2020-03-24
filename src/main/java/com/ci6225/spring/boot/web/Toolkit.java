package com.ci6225.spring.boot.web;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

public class Toolkit {
	
public static List<String> DirctoryListing()
{
	ArrayList<String> pathList = new ArrayList<String>();
	 File path = null;
     try {
         path = new File(ResourceUtils.getURL("classpath:").getPath());
     } catch (FileNotFoundException e) {
     }
	File  file= new File(path.getAbsolutePath(),"static/files");
	File[] tempList = file.listFiles();
	for(int i=0;i< tempList.length;i++) {
		if(tempList[i].isFile()) {
			pathList.add(tempList[i].getPath());
		}
	}
	return pathList;
}

	public static String  FileReading(String path) throws IOException {
		File file = new File(path);

		FileReader reader = new FileReader(file);
		BufferedReader bReader = new BufferedReader(reader);
		StringBuilder sb = new StringBuilder();
		String s = "";
		while ((s =bReader.readLine()) != null) {
			sb.append(s + "\n");
		}
		bReader.close();
		return sb.toString();

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


}
