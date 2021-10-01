//Dylan Lee 9/30/21
import pkg.*;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//Canvas is 450 x 650

public class starter implements InputControl, InputKeyControl {

	public static game platformer;
	public static boolean playing=true, alive;
	public static String input="", playerName=""; // input for keyPress inputs
	public static double horizSpeed = 2.1;

	public static void main(String args[]) {
		KeyController kC = new KeyController(Canvas.getInstance(),new starter());
		MouseController mC = new MouseController(Canvas.getInstance(),new starter());

		while(playing){
			platformer = new game();
			alive = true;

			while(platformer.getPlayer().getY() < 650){ // if player doesnt fall out of screen
				Canvas.pause(10);
				platformer.fall();
				platformer.score.setText(platformer.numScore+"");
				platformer.movePlayer(platformer.horizontalSpeed, 0);

				if(platformer.timer >= 50){
					platformer.numScore+=10;
					platformer.timer=0;
				}
				if(platformer.numScore == 500){
					platformer.background.setColor(new Color(Canvas.rand(255),Canvas.rand(255),Canvas.rand(255)));
					platformer.numScore++;
				}
				platformer.timer++;

			}

			// make code below into a method (include imports to class file too)

			char backSpaceKey = (char)8;
			String backSpaceKeyString = backSpaceKey+"";
			char enterKey = (char)10;
			String enterKeyString = enterKey+"";
			Text displayName = new Text(200,300,"Enter username: ");
			displayName.grow(20,20);
			displayName.draw();
			while(!playerName.contains(enterKeyString)){
				String lastLetter = input;
				String addToName = "";
				while(lastLetter.equals(input) && playerName.length()<10){
					System.out.print("");
					addToName = input;
				}
				if(input.equals(backSpaceKeyString))
					playerName = playerName.substring(0,playerName.length()-1);
				else
					playerName+=addToName;
				displayName.setText(playerName);
			}
			//System.out.println("loop broken :)"); // for debugging
			playerName = playerName.substring(0,playerName.length()-1); // last char is enter which messes up txt file

			//note: the only thing the try catches do is catch runtime errors,
			//but if the file exists and is okay you don't really need them

			//write to file:
			try{
				FileWriter myFileWriter = new FileWriter("scores.txt",true);//the true prevents it from overwriting information
				myFileWriter.write(playerName + ": " + platformer.numScore + "\n");
				myFileWriter.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}
			//read from file:
			EasyReader fileReader = new EasyReader("scores.txt");
			String data = "";
			while(!fileReader.eof())
				data+=fileReader.readLine();

			

			// show high score if possible when game ends
			Text restartGame = new Text(225,200,"Press R to restart");
			restartGame.grow(150,35);
			restartGame.draw();

			boolean continuing = false;
			input = "4321fdsa"; // ensures string arent equal
			while(!input.equals("asdf1234")){ // reset game or not
				System.out.print(""); // loop doesnt run w/ out this line
				if(input.equals("r")){
					//System.out.println("restart"); // for debugging
					continuing = true;
					break;
				}
			}

			if(continuing){
				platformer.resetGame();
				playerName = "";
			}
			else
				break;

		}
		
	}

	public void onMouseClick(double x, double y) {

	}

	public void keyPress(String s) {
		input = s;
		if((s.equals("w")) && (platformer.getPlayer().getY() > 200) && alive)
			platformer.jump();
		else if(s.equals("a") && alive)
			platformer.horizontalSpeed = -horizSpeed;
		else if(s.equals("d") && alive)
			platformer.horizontalSpeed = horizSpeed;
	}

}