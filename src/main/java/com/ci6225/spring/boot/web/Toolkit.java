package com.ci6225.spring.boot.web;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

}
