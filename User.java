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
		boolean repeatName=true;
		while(repeatName) {
			System.out.printf("\nPlease Enter Your Name\n");
			
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
			else
				{System.out.println("Please enter a string for 'NAME'");}
		}
		
		System.out.printf("HI "+this.name+",Enter your location \n\t");
		//@SuppressWarnings("resource")
		//Scanner userinput1 =new Scanner(System.in);
		//String location=userinput1.nextLine();
		String location =InputHandler.provide();
		this.Address=location;
		
		boolean repeatBed=true;
		while(repeatBed) {
			System.out.printf("\nHow many bedded hospital?\n");
			//@SuppressWarnings("resource")
			//Scanner userinput =new Scanner(System.in);
			//String name=userinput.nextLine();
			String beds=InputHandler.provide();
			repeatBed=false;
			for(int i=0;i<beds.length();i++)
					{if(!(Character.isDigit(beds.charAt(i))))
						repeatBed=true;
					}
			if(repeatBed==false) {
				this.nofbeds=Integer.parseInt(beds);
			}
			else
				{System.out.println("Please enter an integer for 'No.of Beds'");}
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
			System.out.println("\nYou can find this product and order from our website ");
		}
		else {if(this.acuity=="high")
					{System.out.println("\nThe intelliVue base model suitable for you are ..");
					for(int i=0;i<this.suggestion.size();i++) {
			  			System.out.println(this.suggestion.get(i).get("model").toString().toUpperCase());
			  	}
					}
			  else
				  	{System.out.println("\nThe products you can go for are\n ");
				  	for(int i=0;i<this.suggestion.size();i++) {
				  			System.out.println(this.suggestion.get(i).get("model").toString().toUpperCase());
				  	}
				  	System.out.println("\n\tYou can find this product and order from our website ");
				  	}
			 }
		}
	
	
	public void getAdditionalInfo() {
		//@SuppressWarnings("resource")
		//Scanner userinput1 =new Scanner(System.in);
		 System.out.println("\nDo you need central patient monitoring station \n\t1.yes \n\t2.No");
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
