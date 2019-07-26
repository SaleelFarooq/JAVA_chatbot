//package com.philips.casestudy.chatbot;
import java.util.Scanner;
public class ConsoleScanner {
	static Scanner sc1=new Scanner(System.in);
	public static String TakeString() {
		return (sc1.next());
	}
	public static int TakeInteger() {
		return (sc1.nextInt());
	}
}
