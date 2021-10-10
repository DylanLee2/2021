//Dylan Lee 9/30/21
import pkg.*;

//Canvas is 450 x 650
//make reset for efficient by reusing variables

public class starter implements InputControl, InputKeyControl {

	public static game platformer;
	public static boolean playing=true, alive, nameMade=false;
	public static String input="", playerName=""; // input for keyPress inputs
	public static double xSpeed = 2.1;
	public static int jumps = 5;
	public static Text displayName, numJumps;

	public static void main(String args[]) {
		KeyController kC = new KeyController(Canvas.getInstance(),new starter());
		MouseController mC = new MouseController(Canvas.getInstance(),new starter());

		Text restartGame = new Text(-310,200,"Press R to restart or Q to quit");
		restartGame.setColor(Color.RED);
		restartGame.grow(180,35);
		//restartGame.draw();

		numJumps = new Text(380,20,"Jumps: " + jumps+"");
		numJumps.draw();

		displayName = new Text(-300,300,"Enter username: ");
		//displayName.draw();

		while(playing){

			platformer = new game();
			alive = true;
			
			while(alive){ // if player doesnt fall out of screen
				Canvas.pause(10);
				platformer.fall();
				platformer.score.setText(platformer.numScore+"");
				platformer.movePlayer(platformer.horizSpeed, 0);
				if(platformer.getPlayer().getY() > 650)
					alive = false;
			}
			displayName.draw();
			displayName.translate(500,0);
			displayName.setText("Enter username: ");
			while(!nameMade)
				System.out.print("");
			displayName.translate(-500,0);
			//playerName = playerName.substring(0,playerName.length()-1); // last char is enter key which messes up txt file
			
			String data = platformer.recordScore("score.txt", playerName, platformer.numScore);
			Text leaderboard = new Text(225,300,data);
			leaderboard.grow(80,30);
			leaderboard.draw();

			restartGame.draw();
			restartGame.translate(520,0);

			boolean continuing = false;
			input = "4321fdsa"; // ensures string arent equal
			while(!input.equals("asdf1234")){ // reset game or not
				System.out.print(""); // loop doesnt run w/ out this line
				if(input.equals("r")){
					continuing = true;
					break;
				}
				else if(input.equals("q")){
					continuing = false;
					restartGame.translate(-520,0);
					break;
				}
			}

			if(continuing){
				leaderboard.translate(500,0);
				restartGame.translate(-500,0);
				platformer.resetGame();
				playerName = "";
				jumps = 5;
			}
			else
				playing = false;
		}
		
	}

	public void keyPress(String s) {
		input = s;
		if((s.equals("w")) && alive && jumps > 0){ // for testing mechanics (maybe make it a part of game?)
			platformer.movePlayer(0, -150);
			jumps--;
			numJumps.setText("Jumps: " + jumps);
		}
		platformer.horizSpeed = (s.equals("a") && alive) ? -xSpeed : (s.equals("d") && alive) ? xSpeed : platformer.horizSpeed;
		
		//(char)8 = back space, (char)10 = enter key
		if(!alive && !s.equals((char)10+"")){
			if(s.equals((char)8+"") && playerName.length()>0)
				playerName = playerName.substring(0,playerName.length()-1);
			else if(playerName.length() < 10)
				playerName+=s;
			displayName.setText(playerName);
		}
		if(s.equals((char)10+""))
			nameMade = true;
	}

	public void onMouseClick(double x, double y) {}

}