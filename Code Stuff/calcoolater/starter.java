import java.util.Scanner;
//Dylan Lee 8/17/21
public class starter {
			
	public static void main(String args[]) {
		while(true){
		
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter first number: ");
			int one = sc.nextInt();
			System.out.print("Enter operator: ");
			String op = sc.next();
			System.out.print("Enter second number: ");
			int two = sc.nextInt();
			
			if(op.equals("+"))
				pr(one, op, two, one + two);
			else if(op.equals("-"))
				pr(one, op, two, one - two);
			else if(op.equals("*"))
				pr(one, op, two, one * two);
			else if(op.equals("/"))
				pr(one, op, two, one / two);
		
		}
	}
	
	public static void pr(int one, String op, int two, int result){
		System.out.println(one + " " + op + " " + two + " = " + result +"\n");
	}
	
}