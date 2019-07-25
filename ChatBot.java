
import java.util.Scanner;



public class ChatBot {
	public static void main(String[] args) {
		
		 Scanner sc1 = new Scanner(System.in);
		 char c;
		boolean flow=true;
		while(flow) {
		 User user1 = new User();
	    user1.getDetails();
	    user1.suggest();
	    user1.getAdditionalInfo();
	    System.out.println("Do you want to continue? [Y/n]");
	    c= sc1.next().toLowerCase().charAt(0);
	    if(c!='y') {
	    	flow=false;
	    }
		}
		sc1.close();
	    System.exit(0);
	}

}
