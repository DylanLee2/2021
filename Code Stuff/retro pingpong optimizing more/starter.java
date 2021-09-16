//Dylan Lee 9/9/21
import java.util.Scanner;
import pkg.*;

public class starter implements InputKeyControl {
	//first to 7 wins
	public static Rectangle[] players = new Rectangle[2];
	public static int[] numScores = new int[2]; // numScores[0] is left side's score, numScores[1] is right side's score
	public static int speed = 3, speed1 = 0, speed2 = 0, totalBalls = 7; // <- totalBalls is total-1
	public static boolean game = true, play = true;
	public static Text restartGame, winner;

	public static void main(String args[]) {
		KeyController kC = new KeyController(Canvas.getInstance(),new starter());

		while(play){
			game = true;

			for(int i = 0; i < numScores.length; i++) // scores start at 0
				numScores[i] = 0;

			Rectangle[] borders = new Rectangle[2]; // borders[0] is top, borders[1] is bottom
			borders[0] = new Rectangle(0,0,600,90);
			borders[1] = new Rectangle(0,510,600,90);

			players[0] = new Rectangle(0,250,30,100); // player[0] is left, player[1] is right
			players[1] = new Rectangle(570,250,30,100);

			Text[] scores = new Text[2]; // scores[0] is left, scores[1] is right
			scores[0] = new Text(230,50,numScores[0]+"");
			scores[1] = new Text(360,50,numScores[1]+"");

			restartGame = new Text(-400,330,"Enter R in the console to restart game");
			restartGame.grow(150,50);
			restartGame.draw();

			for(int i = 0; i < 2; i++){ // shows arrays of the borders, scores, and players on screen
				borders[i].setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
				borders[i].fill();
				scores[i].grow(40,40);
				scores[i].draw();
				players[i].setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
				players[i].fill();
			}

			Line middle = new Line(300,0,300,600); //middle line to split the screen
			middle.draw();

			ball b = new ball(totalBalls);
			while(game){
				Canvas.pause(5);

				speed1 = ((speed1 < 0 && players[0].getY() < 90) || (speed1 > 0 && players[0].getY() > 410)) ? 0 : speed1;
				speed2 = ((speed2 < 0 && players[1].getY() < 90) || (speed2 > 0 && players[1].getY() > 410)) ? 0 : speed2;

				players[0].translate(0,speed1);
				players[1].translate(0,speed2);
				b.moveOnTurn();
				b.hit(players, borders);
				numScores = b.score(numScores);

				for(int i = 0; i < b.balls.length; i++){ // updates the score
					if(b.leftOrRight[i]){
						scores[0].setText(numScores[0]+"");
						b.leftOrRight[i] = false;
					}
					if(b.leftOrRight[i+b.balls.length]){
						scores[1].setText(numScores[1]+"");
						b.leftOrRight[i] = false;
					}
				}

				if(numScores[0]+numScores[1] == totalBalls-1){
					String victory = "Tie";
					if(numScores[0] > numScores[1])
						victory = "Left Wins!";
					else if(numScores[0] < numScores[1])
						victory = "Right Wins!";
					winner = new Text(300,250,victory);
					winner.grow(80,40);
					winner.draw();
					game = false;
				}
			}

			restartGame.translate(600,0);
			Scanner sc = new Scanner(System.in);
			if(!sc.next().equals("r"))
				play = false;
			else{
				for(int i = 0; i < 2; i++){
					players[i].translate(1000,0);
					borders[i].translate(1000,0);
					scores[i].translate(1000,0);
					restartGame.translate(-550,0);
				}
				winner.translate(1000,0);
			}
		}
	}

	//w and s for left player. i and k for right player
	public void keyPress(String s) {
		speed1 = s.equals("w") ? -speed : s.equals("s") ? speed : speed1;
		speed2 = s.equals("i") ? -speed : s.equals("k") ? speed : speed2;
	}

}