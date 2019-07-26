//package com.philips.casestudy.chatbot;


public class ChatBot {
	public static void main(String[] args) {
		
		 char c;
		 String a;
		boolean flow=true;
		while(flow) {
		 User user = new User();
	    user.getDetails();
	    user.getPreferences();
	    user.suggest();
	    user.conclude();
	    System.out.println("\n\nDo you want to continue? [Y/n]\n\n");
	    a=ConsoleScanner.TakeString();
	    //a= InputHandler.provide();
	    c=a.charAt(0);
	    if(c!='y') {
	    	flow=false;
	    	System.out.println("Ending...");
	    }
		}
	    System.exit(0);
	}

}
