//package com.philips.casestudy.chatbot;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
//import java.util.Scanner;
import java.util.Set;

public class Query {
	
	public static List<JSONObject> getCombination(List<JSONObject> list1,String acuity) {
		//@SuppressWarnings("resource")
		//Scanner userinput2 = new Scanner(System.in);
		System.out.println("Select the set of additional parameters recquired");
		System.out.println("\t 1. Philips spO2 ");
		System.out.println("\t 2. Philips spO2 , Cardiac output ");
		System.out.println("\t 3. Masimo rainbow , Philips SpO2");
		System.out.println("\t 4. Philips spO2 , Cardiac output , Masimo rainbow");
		int parameterCombination=0;
		
		try {//parameterCombination=userinput2.nextInt();
		parameterCombination=Integer.parseInt(InputHandler.provide());
		if(parameterCombination<1 || parameterCombination>4)
			{return getCombination(list1, acuity); }
		}
		catch(Exception e1) {
			System.out.println("It was not a valid choice");
			return  getCombination(list1, acuity);
		}
		
		
		String spO2,cardiacOutput,masimoRainbow;
		if(parameterCombination==1) {
			spO2="2";
			cardiacOutput="0";
			masimoRainbow="0";
		}
		else if(parameterCombination==2)
		{spO2="2";
		cardiacOutput="2";
		masimoRainbow="0";}
		else if(parameterCombination==3) {
			spO2="2";
			cardiacOutput="0";
			masimoRainbow="2";	
		}
		else if(parameterCombination==4)
		{spO2="2";
		cardiacOutput="2";
		masimoRainbow="2";}
		else {
			spO2="0";
			cardiacOutput="0";
			masimoRainbow="0";
		}
	List<JSONObject> result1 = new ArrayList<JSONObject>();
	if(spO2=="0" && cardiacOutput=="0" && masimoRainbow=="0")
			{System.out.println("Enter a relevant option : ");
			}
	else {
		boolean b1,b2,b3,b4;
		for(int i=0;i<11;i++) {
			b1=(list1.get(i).get("type").equals(acuity));
			b2=(list1.get(i).get("philips_spo2").equals(spO2)) ;
			b3 = (list1.get(i).get("masimo_rainbow").equals(masimoRainbow));
			b4=(list1.get(i).get("cardiac_output").equals(cardiacOutput));
			if(b1 && b2 && b3 && b4)
					{
				result1.add(list1.get(i));}
		}
	}
	return result1;
	}
	
	public static List<JSONObject> getScreenType(List<JSONObject> list1,String acuity){
		int countTouch=0;
		//@SuppressWarnings("resource")
		//Scanner userinput2 = new Scanner(System.in);
		List<JSONObject> result1 = new ArrayList<JSONObject>();
		List<JSONObject> result2 = new ArrayList<JSONObject>();
		boolean b1,b2;
		for(int i=0;i<list1.size();i++)
			if(list1.get(i).get("type").equals(acuity))
			{b1=(list1.get(i).get("touch").equals("1"));
			b2=(list1.get(i).get("touch").equals("2"));
			if((b1 || b2)) 
					{countTouch++;
					result1.add(list1.get(i));
					}
				else
					{result2.add(list1.get(i));}
				}
		int screentype=0;	
		if(countTouch>0 && ((list1.size()-countTouch)>0))
				{System.out.println("What type of screen do you prefer ?\n\t1.Touch \t2.Non Touch");
				try{//screentype=userinput2.nextInt();
				screentype=Integer.parseInt(InputHandler.provide());
				if(screentype<1 || screentype>2) {
					return getScreenType(list1, acuity);
				}
				}catch(Exception e4) {
					System.out.println("Please provide valid inputs..");
					return getScreenType(list1, acuity);
				}
				if(screentype==1)
						{return result1;}
				else if(screentype==2)
						{return result2;}
				}
		else if(countTouch>0)
				{System.out.println("All the models have touch screen");}
		else if((list1.size()-countTouch)>0)
					{System.out.println("All the models have Non touch screen");}
		else
				{
				return list1;
				}
		return list1;
	}
	
	public static List<JSONObject> getScreenSize(List<JSONObject> list1,String acuity){
		//@SuppressWarnings("resource")
		//Scanner userinput2 = new Scanner(System.in);
		List<JSONObject> result1 = new ArrayList<JSONObject>();
		if(list1.size()==1) {
			System.out.println("The screen size will be " + list1.get(0).get("screensize"));
			return list1;
		}
		else
				{Set<String> setOfScreenSizes = new HashSet<String>();
				List<String> listOfTheSame = new ArrayList<String>();
				for(int i=0;i<list1.size();i++)
						{setOfScreenSizes.add((String) list1.get(i).get("screensize"));
						}
				System.out.println("You can have " + setOfScreenSizes.size()+" screensizes out of these "+list1.size()+" models");
				 @SuppressWarnings("rawtypes")
				Iterator iter = setOfScreenSizes.iterator(); 
				System.out.println("Which one will you prefer?");
				int i=1;
				String element;
				while(iter.hasNext())
						{element=(String) iter.next();
						System.out.println((i)+". " + element );
						listOfTheSame.add(element);
						i++;
						}
				int scrsize = 0;
				try{//scrsize = userinput2.nextInt();
					scrsize=Integer.parseInt(InputHandler.provide());
				if(scrsize<=0 || scrsize>=setOfScreenSizes.size()) {
					return getScreenSize(list1, acuity);
				}
				}catch(Exception e5) {
					return getScreenSize(list1, acuity);
				}
				
				String scrsizeOpted=listOfTheSame.get(scrsize-1);
				for(int j=0;j<list1.size();j++) {
					if(scrsizeOpted.equals(list1.get(j).get("screensize"))) {
					result1.add(list1.get(j));	
					}	
					}
				return result1;
				}
	}
}
	


