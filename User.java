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
    		 {
    		 acuity="high";
    		 }
    	 
    	 return acuity;
     }
     
	public void getDetails() {
		boolean repeatName=true;
		while(repeatName) {
			System.out.printf("\n\n\nPlease Enter Your Name\n");
			//System.out.println("Len of arrayofinputs :" + InputHandler.inputList.size());
			//System.out.println(InputHandler.inputList.get(InputHandler.order));
			//System.out.println(InputHandler.inputList.get(1));
			
			//@SuppressWarnings("resource")
			//Scanner userinput =new Scanner(System.in);
			//String name=userinput.nextLine();
			String name=InputHandler.provide();
			repeatName=false;
			for(int i=0;i<name.length();i++)
					{if(Character.isDigit(name.charAt(i)))
						repeatName=true;
					}
			if(repeatName==false) {
				this.name=name;
			}
		}
		
		System.out.printf("HI "+this.name+",Enter your location \n\t");
		
		//@SuppressWarnings("resource")
		//Scanner userinput1 =new Scanner(System.in);
		//String location=userinput1.nextLine();
		String location =InputHandler.provide();
		this.Address=location;
		
		System.out.printf("\nHow many bedded hospital?\n\t");
		try {
		//int beds=userinput1.nextInt();
		int beds=Integer.parseInt(InputHandler.provide());
		this.nofbeds=beds;
		}
		catch (Exception e1) {
			System.out.println("\nI have to consider you are from low acuity..");
		}
		this.acuity=User.decideAcuity(location,this.nofbeds);
}
		
	
	public void suggest() {
		List<JSONObject> r1 = new ArrayList<JSONObject>();
		for(int i=0;i<ProductList.PMSList.length;i++) {
			r1.add(ProductList.PMSList[i]);
		}
		if(this.acuity=="low")
			{
			r1=Query.getCombination(r1,"2");
			if(r1.size()==0)
						return;
			System.out.println("\nwe have "+ r1.size() +" product/s with these specs..");
			r1=Query.getScreenType(r1,"2");
			if(r1.size()==0)
					return;
			this.suggestion=Query.getScreenSize(r1,"2");
			}
		else if(this.acuity=="high")
				{
				r1=Query.getScreenType(r1,"1");
				this.suggestion=Query.getScreenSize(r1,"1");
					}
		
		if(this.suggestion.size()==1) {
			System.out.println("\nThe product you can buy is " + this.suggestion.get(0).get("model").toString().toUpperCase());
		}
		else {if(this.acuity=="high")
					System.out.println("\nThe intelliVue base model suitable for you are ..");
			  else
				  	System.out.println("\nThe products you can go for are\n ");
			for(int i=0;i<this.suggestion.size();i++) {
				System.out.println(this.suggestion.get(i).get("model").toString().toUpperCase());
			}
		}
		}
	public void getAdditionalInfo() {
		//@SuppressWarnings("resource")
		//Scanner userinput1 =new Scanner(System.in);
		 System.out.println("\nDo you need central patient monitoring station 1.yes 2.No");
		// int cpms15=userinput1.nextInt();
		 int cpms15=Integer.parseInt(InputHandler.provide());
		 this.centralPMS=Integer.toString(cpms15);
		 if(this.acuity=="high")
			{System.out.println("\nEnter custom specifications");         
	 		//String spec15=userinput1.next();
			String spec15=InputHandler.provide();
	 		this.additionalParams=spec15;
	 		System.out.println("\nOur Nearest Dealer Saleel will contact you for the more information");
			}
			 	
	}
}
