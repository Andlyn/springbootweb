package com.ci6225.spring.boot.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello(@RequestParam String name) throws IOException
	{
		//Mainclass.hi("1");
		return Mainclass.hi("2009").get(0);
	}
	

@RequestMapping("main")

public String test2(HttpServletRequest request, HashMap<String, Object> map) throws IOException 
{  
	String[] key= request.getParameterValues("searchInput");
	if(key==null||key.length == 0)
        return "main"; 
	else 
	{
		ArrayList<String> results = Mainclass.hi(key[0]);
		for(int i=1;i<=results.size();i++)
		{
			File file = new File(results.get(i-1));
			String content = Toolkit.FileReading(results.get(i-1));
			map.put("title"+i, file.getName());
			map.put("content"+i, content);
			map.put("add"+i, results.get(i-1));	
			
		}
		return "details"; 
	}
}    

}

