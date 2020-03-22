package com.ci6225.spring.boot.web.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ci6225.spring.boot.web.Mainclass;
import com.ci6225.spring.boot.web.Toolkit;

@Controller
public class HelloController {
	SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
	@RequestMapping("/index")
	@ResponseBody
	public String hello() throws IOException
	{
		
	    Date d1 = new Date();
		Mainclass.GetIndex();
		Date d2 = new Date();
		//long d3 =d2.getTime() - d1.getTime();
		return"Started creating: "+sdf.format(d1)+" Finished: " +sdf.format(d2);
		
	}
	

@SuppressWarnings("deprecation")
@RequestMapping("main")

public String test2(HttpServletRequest request, HashMap<String, Object> map) throws IOException 
{  
	String[] key= request.getParameterValues("searchInput");
	if(key==null||key.length == 0)
        return "main"; 
	else 
	{
		//Mainclass.GetIndex();
		Date d1 = new Date();
		ArrayList<String> results = Mainclass.hi(key[0]);
		Date d2 = new Date();
		for(int i=1;i<=results.size();i++)
		{
			File file = new File(results.get(i-1));
			String content = Toolkit.FileReading(results.get(i-1));
			map.put("title"+i, file.getName());
			map.put("content"+i, content);
			map.put("add"+i, results.get(i-1));	
			
		}
		map.put("start", sdf.format(d1));	
		map.put("end", sdf.format(d2));	
		map.put("total", results.size());	
		return "details"; 
	}
}    

}

