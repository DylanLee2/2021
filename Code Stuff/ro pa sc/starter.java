import java.util.Scanner;
//
public class starter {
			
	public static void main(String args[]) {
		
		boolean on = true;
		while(on){
			System.out.println("\n\n\nFirst to 3. Good luck.");
			Scanner sc = new Scanner(System.in);
			boolean game = true;
			int you = 0, opp = 0;
			while(you < 3 && opp < 3){
				System.out.print("\n\nRock(1), Paper(2), Scissors(3): ");
				int inp = sc.nextInt();
				int opponent = (int)(Math.random()*3) + 1;
				System.out.print("You: "+ evaluate(inp) + "- Opponent: " + evaluate(opponent));
				
				if(inp == 1 && opponent == 3){
					you++;
					System.out.print("           +1");
				}
				else if(inp == 2 && opponent == 1){
					you++;
					System.out.print("           +1");
				}
				else if(inp == 3 && opponent == 2){
					you++;
					System.out.print("           +1");
				}
				else if(inp == opponent || inp > 3 || inp < 1){
					System.out.print("           +0");
				}
				else
					opp++;
				//you += (inp == 1 && opponent == 3) ? 1 : (inp == 2 && opponent == 1) ? 1 : (inp == 3 && opponent == 2) ? 1 : 0; 
				//opp += (opponent == 1 && inp == 3) ? 1 : (opponent == 2 && inp == 1) ? 1 : (opponent == 3 && inp == 2) ? 1 : 0;
				
			}
			
			System.out.println("\n\nScore: you=" + you + ", opponent=" + opp);
			String result = (you > opp) ? "You win!\n" : "You lose... get good\n";
			System.out.println(result);

			System.out.print("Enter c to continue or anything else to quit: ");
			on = sc.next().equals("c") ? true : false;
		}
		
	}
	
	public static String evaluate(int num){
		return (num==1) ? "Rock    " : (num==2) ? "Paper   " : (num==3) ? "Scissors" : "Not Valid"; 
	}

}