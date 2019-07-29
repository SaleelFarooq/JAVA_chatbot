//package com.philips.casestudy.chatbot;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class ProductList {
 public static List<JSONObject> PMSList = new ArrayList<JSONObject>() ;

 	static {
 		try {
 			
 			String filename = System.getProperty("user.dir")+"\\Productdetails.txt";
			FileReader fr=new FileReader(filename);    
	         String wholeText="";
	         try {int i;
				while((i=fr.read())!=-1)    
				 			{wholeText+=(char) i;
				 			}
			} catch (IOException e) {
				Logger.Log("Can't read from the file .. ");
				e.printStackTrace();
			}
	         String[] rows = wholeText.split("\n");
	         String[] arrayOfKeys = rows[0].split(" ");
	         for(int m=1;m<rows.length;m++) {
	         String[] values = rows[m].split(" ");
	         JSONObject pms= new JSONObject();
	         for(int j=0;j<arrayOfKeys.length;j++) {
	        	 if(j==arrayOfKeys.length-1)
	        	 		{pms.put(arrayOfKeys[j].substring(0,arrayOfKeys[j].length()-1),values[j].substring(0,values[j].length()-1));}
	        	 pms.put(arrayOfKeys[j],values[j]);
	         }
	         PMSList.add(pms);
	         }
	         try {
				fr.close();
			} catch (IOException e) {
				Logger.Log("Struggle to close the file..");
				e.printStackTrace();
			}    
		} catch (FileNotFoundException e) {
			Logger.Log("Check file path again..");
			e.printStackTrace();
		} 	
 	
 	}
}
