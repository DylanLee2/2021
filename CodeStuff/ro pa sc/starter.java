import java.util.Scanner;
//
public class starter {
			
	public static void main(String args[]) {
		
		int you, opp;
		Scanner sc = new Scanner(System.in);
		boolean playing = true;
		while(playing){
			System.out.println("\n\n\nFirst to 3. Good luck.");
			you = 0;
			opp = 0;
			while(you < 3 && opp < 3){
				System.out.print("\n\nRock(1), Paper(2), Scissors(3): ");
				int inp = sc.nextInt();
				int opponent = (int)(Math.random()*3) + 1;
				System.out.print("You: "+ evaluate(inp) + "   Opponent: " + evaluate(opponent));
		
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
				else if(inp == opponent || inp > 3 || inp < 1)
					System.out.print("           +0");
				else
					opp++;
				
			}
			System.out.println("\n\nScore: you=" + you + "; opponent=" + opp);
			//String result = (you > opp) ? "You Win, Try again\n" : "You lose\n";
			System.out.println((you > opp) ? "You Win\n" : "You lose\n");

			System.out.print("Enter c to continue or anything else to quit: ");
			playing = sc.next().equals("c") ? true : false;
		}
		
	}
	
	public static String evaluate(int num){
		return (num==1) ? "Rock    " : (num==2) ? "Paper   " : (num==3) ? "Scissors" : "Not Valid"; 
	}

}