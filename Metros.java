//package com.philips.casestudy.chatbot;

import java.util.HashSet;
import java.util.Set;

public class Metros {
	public static Set<String> list = new HashSet<String>();
	static {
		list.add("delhi");
		list.add("bengaluru");
		list.add("bangalore");
		list.add("mumbai");
		list.add("kolkatha");
		list.add("pune");
		list.add("hyderabad");
	}
	
	public static boolean isMetro(String location) {
		return (list.contains(location.toLowerCase()));
	}
	
	
}
