//package com.philips.casestudy.chatbot;

import java.util.Scanner;



public class ChatBot {
	public static void main(String[] args) {
		
		 Scanner sc1 = new Scanner(System.in);
		 char c;
		 String a;
		boolean flow=true;
		while(flow) {
		 User user1 = new User();
	    user1.getDetails();
	    user1.suggest();
	    user1.getAdditionalInfo();
	    System.out.println("\n\nDo you want to continue? [Y/n]");
	    a= InputHandler.provide();
	    c=a.charAt(0);
	    if(c!='y') {
	    	flow=false;
	    	System.out.println("Ending...");
	    }
		}
		sc1.close();
	    System.exit(0);
	}

}
