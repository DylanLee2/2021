// Dylan Lee 10/30/21
public class starter {
	
	public static String evaluateGame(String game){
		int aScore = 0;
		int bScore = 0;
		for(int i = 0; i < game.length(); i++){
			if(game.substring(i,i+1).equals("A")){
				if(game.substring(i+1,i+2).equals("1")){
					aScore++;
				}
				else if(game.substring(i+1,i+2).equals("2")){
					aScore += 2;
				}
			}
			else if(game.substring(i,i+1).equals("B")){
				if(game.substring(i+1,i+2).equals("1")){
					bScore++;
				}
				else if(game.substring(i+1,i+2).equals("2")){
					bScore += 2;
				}
			}
			i++; // skips numbers and only uses letters
		}

		if(aScore > bScore)
			return "A";
		else if(aScore < bScore)
			return "B";
		return "Tied. Continiue playing and win by 2 to win."; // "win by 2" rule mentioned in problem
	}

	public static void main(String args[]) {
		String sampleInput = "A2B2A1B2A2B1A2B2A1B2A1A1B1A1A2";
		System.out.println(evaluateGame(sampleInput));
	}
//10 minutes to make
}