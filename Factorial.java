import java.util.Scanner;

public class Factorial {
	
	public static int findFactorial(int num) {
		return (num*2);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter a number");
		Scanner sc1 = new Scanner(System.in);
		int n = sc1.nextInt();
		//System.out.println("dee");
		n=findFactorial(n);
		System.out.println(n);
		}

}
