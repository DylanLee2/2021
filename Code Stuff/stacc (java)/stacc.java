import pkg.*;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class stacc implements InputKeyControl {

	public static ArrayList<Rectangle> recs = new ArrayList<Rectangle>();
	public static ArrayList<Text> scores = new ArrayList<Text>();
	public static int counter, xSpeed, speed=6;
	public static int numStreak; // make streak system (if streak > 7 then increase width)
	public static String inp="", playerName="";
	public static boolean playing=true, alive, nameMade=false;
	public static Text score, streak, displayName;
	public static EasyReader fileReader = new EasyReader("leaderboard.txt");

	/*
	add difficulty
	improve graphics
	fix streak system
	optimize code
	*/

	public static void main(String args[]){
		KeyController kC = new KeyController(Canvas.getInstance(), new stacc());

		// These only need to be initialized once for efficiency
		Rectangle background = new Rectangle(0,0,600,600);
		background.setColor(new Color(255,255,200));
		background.fill();

		Rectangle border1 = new Rectangle(0,-10,10,610);
		border1.draw();
		Rectangle border2 = new Rectangle(590,-10,10,610);
		border2.draw();

		displayName = new Text(280,280,"Enter username: ");
		displayName.setColor(Color.BLUE);

		score = new Text(300,30,"Score: ");
		score.grow(70,40);
		score.draw();
		
		streak = new Text(500,30,"Streak: ");
		streak.grow(50,20);
		streak.draw();

		Text restartGame = new Text(220,250,"Press R to restart or Q to quit");
		restartGame.setColor(Color.RED);
		restartGame.grow(190,40);

		Text playInWindowed = new Text(900,300,"Play in windowed mode");
		playInWindowed.grow(180,35);
		playInWindowed.draw();

		while(playing){
			// initialize game
			numStreak = 0;
			counter = 1;
			xSpeed = speed;

			score.setText("Score: " + (counter-1));

			recs.add(new Rectangle(100,567,350,30)); // starting rectangle
			recs.get(0).draw();
			
			recs.add(new Rectangle(Canvas.rand(600-recs.get(0).getWidth())+10,537,recs.get(0).getWidth(),30));
			recs.get(1).setColor(Color.RED);
			recs.get(1).draw();

			alive = true;
			while(alive){
				Canvas.pause(20);
				moveLeftAndRight();
				if(recs.get(counter).getY() <= 120){ // keeps "camera" in the middle
					for(int i = 0; i < recs.size(); i++)
						recs.get(i).translate(0, recs.get(i).getHeight());
				}
				if(recs.get(counter).getWidth() <= 2)
					alive = false;
			}

			//add to scoreboard
			displayName.draw();
			while(!nameMade)
				System.out.print("");
			displayName.undraw();
			//playerName = playerName.substring(0,playerName.length()-1); // last char is enter key which messes up txt file
			
			int separateScores = 0; // counter to separate distances
			String data = recordScore("leaderboard.txt", playerName, counter-1);
			/*
			while(data.length()>2){
				System.out.println(data.substring(0,data.indexOf("\n")));
				scores.add(new Text(250,270+(separateScores*10),data.substring(0,data.indexOf("\n"))));
				scores.get(separateScores).grow(100,40);
				scores.get(separateScores).draw();
				data = data.substring(data.indexOf("\n"));
				separateScores++;
			}
			System.out.println("loop broken :)");
			*/
			//restart game
			restartGame.draw();
			while(!inp.equals("abcd")){ // wait for user input
				System.out.print(""); // keeps while loop running
				if(inp.equals("r"))
					break;
				else if(inp.equals("q")){
					playing = false;
					break;
				}
			}
			playerName="";
			restartGame.undraw();
			for(int i = 0; i < recs.size(); i++){
				recs.get(i).undraw();
				recs.remove(i);
				i--;
			}
		}
		java.lang.System.exit(0); // exits game if user is done playing
	}

	public void keyPress(String s){
		inp = s;
		if(s.equals("w") && alive){
			recs.get(counter).setColor(Color.BLACK);
			if(((recs.get(counter).getX()+recs.get(counter).getWidth()) < recs.get(counter-1).getX()) || (recs.get(counter).getX() > (recs.get(counter-1).getX()+recs.get(counter-1).getWidth()))){
				alive = false;
			}
			if(alive){
				// current rec is to the left of prev rectangle
				if(recs.get(counter).getX() < recs.get(counter-1).getX()){
					recs.get(counter).setWidth(recs.get(counter).getX()+recs.get(counter).getWidth()-recs.get(counter-1).getX());
					recs.get(counter).setX(recs.get(counter-1).getX());
					
				}
				// current rec is to the right of prev rectangle
				else if(recs.get(counter).getX() > recs.get(counter-1).getX()){
					recs.get(counter).setWidth(recs.get(counter).getWidth()-((recs.get(counter).getX()+recs.get(counter).getWidth())-(recs.get(counter-1).getX()+recs.get(counter-1).getWidth())));
				}
				// rectangles connect
				else if(recs.get(counter).getX()==recs.get(counter-1).getX() && recs.get(counter).getX()+recs.get(counter).getWidth()==recs.get(counter-1).getX()+recs.get(counter-1).getWidth()){ 
					numStreak++;
					streak.setText("Streak: " + numStreak);
				}
				counter++;
				recs.add(new Rectangle(recs.get(counter-1).getX(),recs.get(counter-1).getY()-recs.get(counter-1).getHeight(),recs.get(counter-1).getWidth(),recs.get(counter-1).getHeight()));
				recs.get(counter).setColor(Color.RED);
				recs.get(counter).draw();
				score.setText("Score: " + (counter-1));
			}
		}

		//(char)8 = back space, (char)10 = enter key
		if(!alive && !s.equals((char)10+"")){
			if(s.equals((char)8+"") && playerName.length()>0)
				playerName = playerName.substring(0,playerName.length()-1);
			else if(playerName.length() < 10)
				playerName+=s;
			displayName.setText("Enter username: " + playerName);
		}
		if(s.equals((char)10+""))
			nameMade = true;

	}

	public static void moveLeftAndRight(){
		if(recs.get(counter).getX() <= 10 || recs.get(counter).getX()+recs.get(counter).getWidth() >= 590){
			xSpeed*=-1; // change directions
		}
		recs.get(counter).translate(xSpeed,0);
	}

	public static String recordScore(String fileName, String userName, int score){
		//note: the only thing the try catches do is catch runtime errors,
		//but if the file exists and is okay you don't really need them

		//write to file:
		try{
			FileWriter myFileWriter = new FileWriter("leaderboard.txt",true); // the true prevents it from overwriting information
			myFileWriter.write(userName + ";" + (counter-1) + "\n");
			myFileWriter.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		//read from file:
		String data = "";
		while(!fileReader.eof()){
			String line = fileReader.readLine()+"\n";
			data+=line;
		}
		System.out.println(data);
		return data;
	}

}