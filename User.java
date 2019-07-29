//package com.philips.casestudy.chatbot;

import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;


import org.json.simple.JSONObject;

public class User {
	 String name;
     String Address;
     int nofbeds;
     String acuity;
     List<JSONObject> suggestion;
     String centralPMS;
     String additionalParams;
     User(){
     }
     
     
     public static String decideAcuity(String location,int beds) {
    	 String acuity="low";
    	 if(Metros.isMetro(location) && (beds>200))
    		 {acuity="high";}
    	 return acuity;
     }
     
     
	public void getDetails() {
		Logger.Log("\nPlease enter your name\n");
		this.name = ConsoleScanner.TakeString();
		//String name=InputHandler.provide();
		Logger.Log("HI "+this.name+",Enter your location \n\t");
		//String location =InputHandler.provide();
		this.Address=ConsoleScanner.TakeString();
		boolean repeatBed=true;
		while(repeatBed) {
			Logger.Log("\nHow many bedded hospital?\n");
			String beds=ConsoleScanner.TakeString();
			repeatBed=false;
			for(int i=0;i<beds.length();i++)
					{if(!(Character.isDigit(beds.charAt(i))))
						repeatBed=true;
					}
			if(repeatBed==false) {
				this.nofbeds=Integer.parseInt(beds);
			}
			else
				{Logger.Log("You entered something that doesn't look like a number , enter once again..");}
		}
		this.acuity=User.decideAcuity(this.Address,this.nofbeds);
}
		
	
	public void getPreferences() {
		List<JSONObject> r1 = new ArrayList<JSONObject>();
		r1=ProductList.PMSList;
		String arg;
		if(this.acuity=="high") {arg="1";}
		else {arg="2";}
		r1=Query.sortByAcuity(r1,arg);
		if(this.acuity=="low")
			{
			r1=Query.sortByCombination(r1);
			if(r1.size()==0)
						return;
			Logger.Log("\nWe have "+ r1.size() +" product/s with these specs..");
			r1=Query.sortByScreenType(r1);
			if(r1.size()==0)
					return;
			this.suggestion=Query.sortByScreenSize(r1);
			}
		else if(this.acuity=="high")
			{
			r1=Query.sortByScreenType(r1);
			this.suggestion=Query.sortByScreenSize(r1);
			}
		Logger.Log("\nDo you need central patient monitoring station? \n\t1.YES \t2.NO\n");
		
		 int cpms=ConsoleScanner.TakeInteger();
		 //int cpms15=Integer.parseInt(InputHandler.provide());
		 this.centralPMS=Integer.toString(cpms);
	}
	
	
	public void suggest() {
		if(this.suggestion.size()==1) {
			Logger.Log("\nThe product you can buy is " + this.suggestion.get(0).get("model").toString().toUpperCase());
			
		}
		else {if(this.acuity=="high")
					{Logger.Log("\nThe intelliVue base model suitable for you are ..");
					}
			  else
				  	{Logger.Log("\nThe products you can go for are\n ");
				  	}
		for(int i=0;i<this.suggestion.size();i++) {
  			Logger.Log(this.suggestion.get(i).get("model").toString().toUpperCase());
  	}
			 }
		Logger.Log("\nYou can find this product and order from our website ");
		}
	
	

	public void conclude() {
		 if(this.acuity=="high")
				{System.out.println("\nEnter custom specifications");         
				String spec=ConsoleScanner.TakeString();
				//String spec15=InputHandler.provide();
				this.additionalParams=spec;
				Logger.Log("\nOur Nearest Dealer Saleel will contact you for the more information");
				}
		 Logger.Log("\nHope you have got what you were asking..");
		}
	
}
