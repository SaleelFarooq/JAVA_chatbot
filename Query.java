//package com.philips.casestudy.chatbot;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import java.util.Set;

public class Query {
	private static String spO2,cardiacOutput,masimoRainbow;
	private static void setCombinationFlag(int choice) {
		if(choice==1) {
			spO2="2";
			cardiacOutput="0";
			masimoRainbow="0";
		}
		else if(choice==2)
		{spO2="2";
		cardiacOutput="2";
		masimoRainbow="0";}
		else if(choice==3) {
			spO2="2";
			cardiacOutput="0";
			masimoRainbow="2";	
		}
		else if(choice==4)
		{spO2="2";
		cardiacOutput="2";
		masimoRainbow="2";}
		else {
			spO2="0";
			cardiacOutput="0";
			masimoRainbow="0";
		}
	}
	
	private static List<JSONObject> NarrowDown(List<JSONObject> list1,String property,String valueOfProperty){
		List<JSONObject> result = new ArrayList<JSONObject>();
		int lengthOfList=list1.size();
		for(int i=0;i<lengthOfList;i++)
					{if(list1.get(i).get(property).equals(valueOfProperty))
							{result.add(list1.get(i));}
					}
		return result;
	}
	
	
	public static List<JSONObject> sortByAcuity(List<JSONObject> list1,String acuity){
		return NarrowDown(list1,"type", acuity);
	}
	
	
	public static List<JSONObject> sortByCombination(List<JSONObject> list1) {
		Logger.Log("Select the set of additional parameters recquired\n\t 1. Philips spO2\n\t 2. Philips spO2 , Cardiac output");
		Logger.Log("\t 3. Masimo rainbow , Philips SpO2\n\t 4. Philips spO2 , Cardiac output , Masimo rainbow");
		int parameterCombination=0;
		try {parameterCombination=ConsoleScanner.TakeInteger();
		if(parameterCombination<1 || parameterCombination>4)
			{return sortByCombination(list1); }
		}
		catch(Exception e1) {
			Logger.Log("It was not a valid choice");
			return  sortByCombination(list1);
		}
		
		setCombinationFlag(parameterCombination);
	List<JSONObject> result = new ArrayList<JSONObject>();
	if(spO2=="0" && cardiacOutput=="0" && masimoRainbow=="0")
			{Logger.Log("Enter a relevant option : ");
			}
	else {result=NarrowDown(list1,"philips_spo2",spO2);
		 result=NarrowDown(result,"masimo_rainbow",masimoRainbow);
		 result=NarrowDown(result,"cardiac_output",cardiacOutput);
	}
	return result;
	}
	
	public static List<JSONObject> sortByScreenType(List<JSONObject> list1){
		List<JSONObject> result1 = NarrowDown(list1,"touch","1");
		List<JSONObject> result1a =NarrowDown(list1,"touch","2"); 
		result1.addAll(result1a);
		List<JSONObject> result2 = NarrowDown(list1,"touch","0");
		int screentype=0;	
		if(result1.size()>0 && ((result2.size())>0))
				{System.out.println("\nWhat type of screen do you prefer ?\n\t1.Touch \n\t2.Non Touch");
				try{screentype=ConsoleScanner.TakeInteger();
				//screentype=Integer.parseInt(InputHandler.provide());
				if(screentype<1 || screentype>2) {
					return sortByScreenType(list1);
				}
				}catch(Exception e4) {
					Logger.Log("\nPlease provide valid inputs..");
					return sortByScreenType(list1);
				}
				if(screentype==1)
						{return result1;}
				else if(screentype==2)
						{return result2;}
				}
		else if(result1.size()>0)
				{Logger.Log("\nAll the models have touch screen");}
		else if((result2.size())>0)
					{Logger.Log("\nAll the models have Non touch screen");}
		else
				{
				return list1;
				}
		return list1;
	}
	
	public static List<JSONObject> sortByScreenSize(List<JSONObject> list1){
		if(list1.size()==1) {
			Logger.Log("\nThe screen size will be " + list1.get(0).get("screensize"));
			return list1;
		}
		else
				{Set<String> setOfScreenSizes = new HashSet<String>();
				List<String> listOfTheSame = new ArrayList<String>();
				for(int i=0;i<list1.size();i++)
						{setOfScreenSizes.add((String) list1.get(i).get("screensize"));
						}
				Logger.Log("\nYou can have " + setOfScreenSizes.size()+" screensizes out of these "+list1.size()+" models");
				 @SuppressWarnings("rawtypes")
				Iterator iter = setOfScreenSizes.iterator(); 
				Logger.Log("Which one will you prefer?");
				int i=1;
				String element;
				while(iter.hasNext())
						{element=(String) iter.next();
						Logger.Log("\t"+(i)+". " + element );
						listOfTheSame.add(element);
						i++;
						}
				int scrsize = 0;
				try{scrsize =ConsoleScanner.TakeInteger();
					//scrsize=Integer.parseInt(InputHandler.provide());
				if(scrsize<=0 || scrsize>=setOfScreenSizes.size()) {
					return sortByScreenSize(list1);
				}
				}catch(Exception e5) {
					return sortByScreenSize(list1);
				}
				
				String scrsizeOpted=listOfTheSame.get(scrsize-1);
				List<JSONObject> result1 = NarrowDown(list1,"screensize",scrsizeOpted);
				
				return result1;
				}
	}
}
	


