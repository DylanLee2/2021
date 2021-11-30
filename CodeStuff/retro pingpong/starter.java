//Dylan Lee 9/9/21
import pkg.*;

/*
Work on...
	-hold key to move the player
	-fixing "clicking effect" for blue side (it turns purple when clicking)
	-making timer accurate to milliseconds for single player
	-optimizing code
	-make user experience better
	-prevent ball from "clipping" when hitting the player which registers and continues game
	-make the restarting function really restart rather than drawing over the previous game
*/

public class starter implements InputControl, InputKeyControl {
	
	public static Rectangle[] players = new Rectangle[2], borders = new Rectangle[2];
	public static Rectangle singleplayer, multiplayer;
	public static int[] numScores = new int[2]; // numScores[0] is left side's score, numScores[1] is right side's score
	public static Text[] scores = new Text[2];
	public static int speed = 4, speed1 = 0, speed2 = 0;
	public static boolean game = true, play = true, gamemode1 = false, gamemode2 = false, selectable = true;
	public static Text restartGame, winner, gameMode, textSingleP, textMultiP, timer;
	public static double timeSurvived = 0, convertToSec = 0;
	public static String restart = "", previous = "";

	public static void main(String args[]) {
		KeyController kC = new KeyController(Canvas.getInstance(),new starter());
		MouseController mC = new MouseController(Canvas.getInstance(),new starter());

		gameMode = new Text(250,100,"Retro Ping Pong");
		gameMode.grow(150,35);
		gameMode.draw();

		singleplayer = new Rectangle(50,200,200,200);
		multiplayer = new Rectangle(350,200,200,200);
		singleplayer.setColor(new Color(65,105,225)); // blue
		multiplayer.setColor(new Color(255,50,50)); // red 
		singleplayer.fill();
		multiplayer.fill();

		textSingleP = new Text(120,280,"Single Player");
		textMultiP = new Text(430,280,"Multiplayer");
		textSingleP.grow(50,20);
		textMultiP.grow(50,20);
		textSingleP.draw();
		textMultiP.draw();

		while(!gamemode1 && !gamemode2) // Waits for user input for the gamemode
			System.out.print(""); // <- must have to make while loop run

		Canvas.pause(200); // small pause to see "clicking effect"

		while(play){ // gamemode1 = single player, gamemode2 = multiplayer
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
			for(int i = 0; i < 2; i++){ // shows arrays of the players, borders, and scores,  on screen
				players[i].setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
				players[i].fill();
				borders[i].setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
				borders[i].fill();
				scores[i].grow(40,40);
				scores[i].draw();
				numScores[i] = 0; // scores start at 0
			}

			if(gamemode1){
				timer = new Text(100,50,timeSurvived+"");
				timer.grow(30,30);
				timer.setColor(Color.RED);
				timer.draw();
			}

			Line middle = new Line(300,0,300,600); //middle line to split the screen
			middle.draw();

			int totalBalls = (gamemode1) ? 2 : (gamemode2) ? 6 : 1; // totalBalls is totalBalls-1

			ball b = new ball(totalBalls);
			while(game){
				Canvas.pause(8);

				speed1 = ((speed1 < 0 && players[0].contains(borders[0])) || (speed1 > 0 && players[0].contains(borders[1]))) ? 0 : speed1;				
				players[0].translate(0,speed1);

				if(gamemode1){
					if(b.balls[b.ballTurn].getY() < 410 && b.balls[b.ballTurn].getY() > 90) // within boundary
						players[1].translate(0,b.balls[b.ballTurn].getY()-players[1].getY());
				}
				else if(gamemode2){
					speed2 = ((speed2 < 0 && players[1].contains(borders[0])) || (speed2 > 0 && players[1].contains(borders[1]))) ? 0 : speed2;
					players[1].translate(0,speed2);
				}

				

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
					//timer.setText(timeSurvived+"");
				}
				if(gamemode1)
					timer.setText(timeSurvived+"");

				if(numScores[0]+numScores[1] == totalBalls-1){ // ends the game
					String victory = "You survived for " + timeSurvived + " seconds"; // <- if gamemode1
					if(gamemode2){
						victory = "Tie!";
						if(numScores[0] > numScores[1])
							victory = "Left Wins!";
						else if(numScores[0] < numScores[1])
							victory = "Right Wins!"; // convert to ternary when optimizing
							
					}
					winner = new Text(240,250,victory);
					winner.setColor(Color.BLUE);
					winner.grow(200,35);
					winner.draw();
					previous = restart;
					game = false;
				}
				
				convertToSec++;
				if(convertToSec == 150){
					timeSurvived += convertToSec/150;
					convertToSec = 0;
				}

			}
			
			restartGame(); // if r is pressed, game restarts.

		}

	}

	public void onMouseClick(double x, double y) {

		// player chooses gamemode at the beginning
		if(selectable){
			if((x>singleplayer.getX() && x<singleplayer.getX()+singleplayer.getX()+singleplayer.getWidth()) && (y>singleplayer.getY() && y<singleplayer.getY()+singleplayer.getHeight())){
				System.out.println("Single Player!");
				singleplayer.setColor(new Color(170,200,255)); // "Clicking effect"
				gamemode1 = true;
				selectable = false;
			}
			else if((x>multiplayer.getX() && x<multiplayer.getX()+multiplayer.getX()+multiplayer.getWidth()) && (y>multiplayer.getY() && y<multiplayer.getY()+multiplayer.getHeight())){
				System.out.println("Multiplayer!");
				multiplayer.setColor(new Color(250,150,150)); // "Clicking effect"
				gamemode2 = true;
				selectable = false;
			}
		}

	}

	//w and s for left player.
	public void keyPress(String s) {
		restart = s;
		speed1 = s.equals("w") ? -speed : s.equals("s") ? speed : speed1;
		if(gamemode2)
			speed2 = s.equals("i") ? -speed : s.equals("k") ? speed : speed2;
	}

	public static void restartGame(){
		restart = "asdf;ljkasdf"; // random string to ensure that restart and previous arent equal

		timeSurvived = 0;
		restartGame = new Text(250,330,"Press R to restart game");
		restartGame.grow(150,35);
		restartGame.draw();

		for(int i = 0; i < numScores.length; i++)
			numScores[i] = 0;

		boolean continued = false;
		while(!restart.equals(previous)){
			System.out.print(""); // <- must have or else while loop doesnt run
			//System.out.println("restart: " + restart + " previous: " + previous); // for debugging
			if(restart.equals("r")){
				continued = true;
				System.out.println("continued");
				break;
			}
		}

		play = (!continued) ? false : play; // continue if R is pressed

	}

}