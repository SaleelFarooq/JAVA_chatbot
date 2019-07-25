package com.philips.casestudy.chatbot;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class ProductList {
 public static JSONObject[] PMSList = new JSONObject[11] ;

 	static {
 		try {
 			
 			String str0 = System.getProperty("user.dir");
 			str0=str0 + "\\Productdetails.txt";
 			
 			//String str1 = "C:\\Users\\320065411\\eclipse-workspace\\MyTraining\\src\\com\\philips\\casestudy\\chatbot\\ProductDetails.txt";
 		
			FileReader fr=new FileReader(str0);
			int i;    
	         String wholeText="";
	         try {
				while((i=fr.read())!=-1)    
				 			{wholeText+=(char) i;
				 			}
			} catch (IOException e) {
				System.out.println("Can't read from the file .. ");
				e.printStackTrace();
			}
	         String[] rows = wholeText.split("\n");
	         String[] ArrayOfKeys = rows[0].split(" ");
	         for(int m=1;m<rows.length;m++) {
	         String[] values = rows[m].split(" ");
	         JSONObject pms034= new JSONObject();
	         for(int j=0;j<ArrayOfKeys.length;j++) {
	        	 if(j==ArrayOfKeys.length-1)
	        	 		{pms034.put(ArrayOfKeys[j].substring(0,ArrayOfKeys[j].length()-1),values[j].substring(0,values[j].length()-1));}
	        	 pms034.put(ArrayOfKeys[j],values[j]);
	         }
	         pms034.remove("12lead_ecg\r");
	         PMSList[m-1]=pms034;
	         }
	         try {
				fr.close();
			} catch (IOException e) {
				System.out.println("Struggle to close the file..");
				e.printStackTrace();
			}    
		} catch (FileNotFoundException e) {
			System.out.println("Check file path again..");
			e.printStackTrace();
		} 	
 	
 	}
}
