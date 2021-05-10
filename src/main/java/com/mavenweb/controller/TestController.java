package com.mavenweb.controller;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/test")
public class TestController {
	  @GetMapping()
      public Map test(@RequestParam Map map) {
		  
    	  return map; 
      }
	  @PostMapping()
	    public void insert(HttpServletRequest req, HttpServletResponse resp, @RequestParam Map map, MultipartFile fname) throws IOException  {
	        PrintWriter out = resp.getWriter();
	        if (fname != null && !fname.isEmpty()) {
	            try {
	                String sPath = req.getServletContext().getRealPath("/");
	                sPath = sPath + "/upimgs/";

	                fname.transferTo(new File(sPath + map.get("fld1") + "_.png"));
	            } catch (Exception e) {
	                System.out.println(e.getMessage());
	            }
	        }
	        out.print("<html><body>");
	        out.print(map);
	        out.print("<hr><img src='" + req.getContextPath() + "/images/" +map.get("fld1") + "_.png" + "'></body></html>");
	    }

}
