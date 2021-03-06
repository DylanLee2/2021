//Dylan Lee 9/9/21
import pkg.*;

public class starter implements InputKeyControl {
	
	public static Rectangle[] players = new Rectangle[2], borders = new Rectangle[2];
	public static int[] numScores = new int[2]; // numScores[0] is left side's score, numScores[1] is right side's score
	public static Text[] scores = new Text[2];
	public static int speed = 3, speed1 = 0, speed2 = 0, totalBalls = 2; // <- totalBalls is total-1
	public static boolean game = true, play = true;
	public static Text restartGame, winner;
	public static double timeSurvived = 0, convertPauseToSec = 0;
	public static String restart = "", previous = "";

	public static void main(String args[]) {
		KeyController kC = new KeyController(Canvas.getInstance(),new starter());

		while(play){
			game = true;

			//initilazes each part of the game...
			Rectangle background = new Rectangle(0,90,600,420);
			background.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));

			borders[0] = new Rectangle(0,0,600,90); // borders[0] is top, borders[1] is bottom
			borders[1] = new Rectangle(0,510,600,90);

			players[0] = new Rectangle(0,250,30,100); // player[0] is left, player[1] is right
			players[1] = new Rectangle(570,250,30,100);

			scores[0] = new Text(230,40,numScores[0]+""); // scores[0] is left, scores[1] is right
			scores[1] = new Text(360,40,numScores[1]+"");

			background.fill();
			for(int i = 0; i < 2; i++){ // shows arrays of the borders, scores, and players on screen
				borders[i].setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
				borders[i].fill();
				scores[i].grow(40,40);
				scores[i].draw();
				players[i].setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
				players[i].fill();
				numScores[i] = 0; // scores start at 0
			}

			Text timer = new Text(100,50,timeSurvived+"");
			timer.grow(30,30);
			timer.setColor(Color.RED);
			timer.draw();

			Line middle = new Line(300,0,300,600); //middle line to split the screen
			middle.draw();

			ball b = new ball(totalBalls);
			while(game){
				Canvas.pause(5);

				speed1 = ((speed1 < 0 && players[0].getY() < 90) || (speed1 > 0 && players[0].getY() > 410)) ? 0 : speed1;				
				players[0].translate(0,speed1);

				if(b.balls[b.ballTurn].getY() < 410 && b.balls[b.ballTurn].getY() > 90) // within boundary
					players[1].translate(0,(b.balls[b.ballTurn].getY()-players[1].getY()));

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
					timer.setText(timeSurvived+"");
				}

				if(numScores[0]+numScores[1] == totalBalls-1){ // ends the game
					winner = new Text(240,250,"You survived for " + timeSurvived + " seconds");
					winner.setColor(Color.BLUE);
					winner.grow(200,35);
					winner.draw();
					previous = restart;
					game = false;
				}
				
				convertPauseToSec++;
				if(convertPauseToSec == 200){
					timeSurvived += convertPauseToSec/200;
					convertPauseToSec = 0;
				}

			}
			
			restartGame(); // if r is pressed, game restarts.

		}

	}

	//w and s for left player.
	public void keyPress(String s) {
		restart = s;
		speed1 = s.equals("w") ? -speed : s.equals("s") ? speed : speed1;
	}

	public static void restartGame(){
		restart = "asdf;ljkasdf"; // random string to ensure that restart and previous arent equal

		timeSurvived = 0;
		restartGame = new Text(250,330,"Press R to restart game");
		restartGame.grow(150,35);
		restartGame.draw();

		boolean continued = false;
		
		while(!restart.equals(previous)){
			System.out.print("");
			//System.out.println("restart: " + restart + " previous: " + previous); // for debugging
			if(restart.equals("r")){
				continued = true;
				System.out.println("continued");
				break;
			}
		}

		play = (!continued) ? false : play; // continue if R is pressed

	}

	/* just in case I break the code...
			if(restart.equals(previous)){
				restart = "asdf;ljkasdf"; // random string to ensure Strings arent equal
			}

			timeSurvived = 0;
			restartGame.draw();
			restartGame.translate(600,0);
			boolean continued = false;
			
			while(!restart.equals(previous)){
				System.out.println("restart: " + restart + " previous: " + previous);
				if(restart.equals("r")){
					System.out.print("r");
					for(int i = 0; i < 2; i++){
						players[i].translate(1500,0);
						borders[i].translate(1500,0);
						scores[i].translate(1500,0);
						restartGame.translate(-550,0);
					}
					continued = true;
					winner.translate(1500,0);
					System.out.println("continued");
					break;
				}
			}
			if(!continued)
				play = false;
	*/

}