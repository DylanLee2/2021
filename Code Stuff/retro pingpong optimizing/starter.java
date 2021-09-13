//Dylan Lee 9/9/21
import pkg.*;

public class starter implements InputKeyControl {
	//first to 7 wins
	public static Rectangle[] players = new Rectangle[2];
	public static int[] numScores = new int[2]; // numScores[0] is left side's score, numScores[1] is right side's score
	public static int speed = 3, speed1 = 0, speed2 = 0;
	public static boolean game = true;
	public static String continueGame = "";
	public static ball b;
	public static Text restartGame;

	public static void main(String args[]) {
		KeyController kC = new KeyController(Canvas.getInstance(),new starter());
		
		for(int i = 0; i < numScores.length; i++)
			numScores[i] = 0;

		Rectangle[] borders = new Rectangle[2]; // borders[0] is top, borders[1] is bottom
		borders[0] = new Rectangle(0,0,600,90);
		borders[1] = new Rectangle(0,510,600,90);

		players[0] = new Rectangle(0,250,30,100); // player[0] is left, player[1] is right
		players[1] = new Rectangle(570,250,30,100);

		Text[] scores = new Text[2]; // scores[0] is left, scores[1] is right
		scores[0] = new Text(230,50,numScores[0]+"");
		scores[1] = new Text(360,50,numScores[1]+"");

		restartGame = new Text(-200,250,"Press R to restart game");
		restartGame.grow(80,40);
		restartGame.draw();

		for(int i = 0; i < 2; i++){ // shows arrays of the borders, scores, and players on screen
			borders[i].setColor(Color.YELLOW);
			borders[i].fill();
			scores[i].grow(40,40);
			scores[i].draw();
			players[i].setColor(Color.RED);
			players[i].fill();
		}

		Line middle = new Line(300,0,300,600); //middle line to split the screen
		middle.draw();

		b = new ball(4);
		while(game){
			Canvas.pause(5);
			if((speed1 < 0 && players[0].getY() < 90) || (speed1 > 0 && players[0].getY() > 410))
					speed1 = 0;
			if((speed2 < 0 && players[1].getY() < 90) || (speed2 > 0 && players[1].getY() > 410))
					speed2 = 0;
			players[0].translate(0,speed1);
			players[1].translate(0,speed2);
			b.move();
			b.hit(players, borders);
			numScores = b.score(numScores);

			for(int i = 0; i < b.balls.length; i++){ // updates the score
				if(b.left[i]){
					scores[0].setText(numScores[0]+"");
					b.left[i] = false;
				}
				if(b.right[i]){
					scores[1].setText(numScores[1]+"");
					b.right[i] = false;
				}
			}

		}
		
	}

	//w and s for left player. i and k for right player
	public void keyPress(String s) {
		continueGame = s;
		speed1 = s.equals("w") ? -speed : s.equals("s") ? speed : speed1;
		speed2 = s.equals("i") ? -speed : s.equals("k") ? speed : speed2;
		/*
		if(b.gameOver()){
			restartGame.translate(450,0);
			if(continueGame.equals("r")){
				//restart game
				System.out.println("Restarting game");
			}
			else
				game = false;
		}
		*/
	}

}