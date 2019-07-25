//package com.philips.casestudy.chatbot;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputHandler {
	public static List<String> inputList=new ArrayList<String>();
	public static int order=0;
	public static String provide() {
		if(InputHandler.order<inputList.size())
			{String result=InputHandler.inputList.get(InputHandler.order).substring(0,InputHandler.inputList.get(InputHandler.order).length()-1);
			InputHandler.order++;
			return result;
			}
		else
			return "stop";
	}
	static {
		 String inputString="";
		try {
 			String filename = "C:\\Users\\320065411\\eclipse-workspace\\MyTraining\\src\\com\\philips\\casestudy\\chatbot\\Inputvalues.txt";
 			
			FileReader fr=new FileReader(filename);
			int i;   
	         try {
				while((i=fr.read())!=-1)    
				 			{inputString+=(char) i;
				 			}
			} catch (IOException e) {
				System.out.println("Problem with reading input .. ");
				e.printStackTrace();
			}
	} catch (FileNotFoundException e2) {
		
		e2.printStackTrace();
	}finally {
		//System.out.println(inputString);
		if(!(inputString.equals(""))) {
			String[] arrayofinputs=inputString.split("\n");
			//System.out.println("Len of arrayofinputs : " + arrayofinputs.length);
			for(int i=0;i<arrayofinputs.length;i++) {
				inputList.add(arrayofinputs[i]);
			}
		}
	}
}
}
