import pkg.*;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.util.ConcurrentModificationException;
//dylaniscoolkevinsuxatthisgame cheat code :|))
public class stacc implements InputKeyControl {

	public static ArrayList<Rectangle> recs = new ArrayList<Rectangle>();
	public static ArrayList<Rectangle> sliced = new ArrayList<Rectangle>();
	public static ArrayList<Double> slicedAccel = new ArrayList<Double>();
	public static ArrayList<Text> scores = new ArrayList<Text>();
	public static int counter, speed=6, streak = 1;
	public static String inp="", playerName="";
	public static boolean playing=true, alive, nameMade=false;
	public static Text score, displayName;
	public static EasyReader fileReader = new EasyReader("leaderboard.txt");

	/*
	add difficulty // faster movement, starts smaller, etc.
	improve graphics
	optimize code
	add "aim assist"
	make classes to shorten code (goes with optimizing)
	slice & stack cake??
	*/

	public static void main(String args[]) throws ConcurrentModificationException{
		KeyController kC = new KeyController(Canvas.getInstance(), new stacc());

		// These only need to be initialized once for efficiency
		Rectangle background = new Rectangle(0,0,600,600);
		background.setColor(new Color(255,255,255));
		background.fill();

		Rectangle borders = new Rectangle(10,-10,580,620);
		borders.draw();

		Text restartGame = new Text(300,230,"Press R to restart or Q to quit </3");
		restartGame.setColor(Color.RED);
		restartGame.grow(210,30);

		Text playInWindowed = new Text(900,300,"Play in windowed mode!");
		playInWindowed.grow(180,35);
		playInWindowed.draw();

		displayName = new Text(280,280,"Enter username: ");
		displayName.setColor(Color.BLUE);

		score = new Text(300,30,"Score: ");
		score.grow(70,30);
		score.draw();

		while(playing){
			// initialize game
			counter = 1;

			score.setText("Score: " + (counter-1));

			recs.add(new Rectangle(100,567,350,30)); // starting rectangle
			recs.get(0).draw();
			
			recs.add(new Rectangle(Canvas.rand(580-recs.get(0).getWidth())+10,537,recs.get(0).getWidth(),30));
			recs.get(1).setColor(Color.RED);
			recs.get(1).draw();

			alive = true;
			while(alive){
				Canvas.pause(20);

				// move left & right
				if(recs.get(counter).getX() <= 10 || recs.get(counter).getX()+recs.get(counter).getWidth() >= 590)
					speed*=-1; // change directions
				recs.get(counter).translate(speed,0);

				if(recs.get(counter-1).getY() <= 120){ // keeps "camera" in the middle
					for(int i = 0; i < 30; i++){
						Canvas.pause(1);
						for(int j = 0; j < recs.size(); j++)
							recs.get(j).translate(0, 1);
					}
				}
				for(int i = 0; i < sliced.size(); i++){ // sliced part falling "animation"
					sliced.get(i).translate(0,2+slicedAccel.get(i));
					slicedAccel.set(i,slicedAccel.get(i)+0.5);
					if(sliced.get(i).getY() > 600){
						sliced.get(i).undraw();
						sliced.remove(i);
						slicedAccel.remove(i);
						i--;
					}
				}
				if(recs.get(counter-1).getWidth() <= 1) // dies if rectangle is too smol
					alive = false;
			}

			if(!nameMade){ // doesn't check this part if name is already made
				displayName.draw(); //add to scoreboard
				while(!nameMade){
					System.out.print("");
					if(inp.equals((char)10+""))
						nameMade = true;
				}
				displayName.undraw();
			}
			
			ArrayList<Integer> numScores = new ArrayList<Integer>();
			ArrayList<String> users = new ArrayList<String>();

			recordScore("leaderboard.txt", playerName, counter-1);
			fileReader = new EasyReader("leaderboard.txt");
			while(!fileReader.eof()){
				String line = fileReader.readLine();
				numScores.add(Integer.parseInt(line.substring(line.indexOf(";")+1)));
				users.add(line.substring(0,line.indexOf(";")));
				//System.out.println(line);
			}
			
			//get top 5 scores, and your score, sort from greatest to least
			for(int i = 0; i < numScores.size(); i++){
				for(int j = 0; j < numScores.size(); j++){
					if(numScores.get(i)>numScores.get(j)){
						int temp = numScores.get(i);
						numScores.set(i,numScores.get(j));
						numScores.set(j,temp);
						String tempIndex = users.get(i);
						users.set(i,users.get(j));
						users.set(j,tempIndex);
					}
				}
			}

			for(int i = 0; i < 5; i++){ // displays top 5 scores
				scores.add(new Text(250,300+((scores.size()-1)*30),users.get(i)+" "+numScores.get(i)));
				scores.get(scores.size()-1).draw();
			}

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
			//nameMade = false; // makes name each "round"
			restartGame.undraw();
			for(int i = 0; i < recs.size(); i++){
				recs.get(i).undraw();
				recs.remove(i);
				i--;
			}
			for(int i = 0; i < scores.size(); i++){
				scores.get(i).undraw();
				scores.remove(i);
				i--;
			}
		}
		java.lang.System.exit(0); // exits game if user is done playing
	}

	public void keyPress(String s) throws ConcurrentModificationException{
		inp = s;
		if(alive && s.equals("w")){
			recs.get(counter).setColor(Color.BLACK);
			// rectangle is out of bounds
			if(((recs.get(counter).getX()+recs.get(counter).getWidth()) < recs.get(counter-1).getX()) || (recs.get(counter).getX() > (recs.get(counter-1).getX()+recs.get(counter-1).getWidth())))
				alive = false;
			if(alive){
				// current rec is to the left of prev rectangle
				if(recs.get(counter).getX() < recs.get(counter-1).getX()){
					sliced.add(new Rectangle(recs.get(counter).getX(),recs.get(counter).getY(),recs.get(counter-1).getX()-recs.get(counter).getX(),recs.get(counter).getHeight()));
					sliced.get(sliced.size()-1).draw();
					recs.get(counter).setWidth(recs.get(counter).getX()+recs.get(counter).getWidth()-recs.get(counter-1).getX());
					recs.get(counter).setX(recs.get(counter-1).getX());
					streak = 1;
				}
				// current rec is to the right of prev rectangle
				else if(recs.get(counter).getX() > recs.get(counter-1).getX()){
					sliced.add(new Rectangle(recs.get(counter-1).getX()+recs.get(counter-1).getWidth(),recs.get(counter).getY(),recs.get(counter).getX()+recs.get(counter).getWidth()-recs.get(counter-1).getX()-recs.get(counter-1).getWidth(),recs.get(counter).getHeight()));
					sliced.get(sliced.size()-1).draw();
					recs.get(counter).setWidth(recs.get(counter).getWidth()-((recs.get(counter).getX()+recs.get(counter).getWidth())-(recs.get(counter-1).getX()+recs.get(counter-1).getWidth())));
					streak = 1;
				}
				// current rec is exactly on prev rectangle
				else if(recs.get(counter).getX()==recs.get(counter-1).getX() && recs.get(counter).getX()+recs.get(counter).getWidth()==recs.get(counter-1).getX()+recs.get(counter-1).getWidth()){
					recs.get(counter).setColor(Color.GREEN);
					streak++;
				}
				if(streak>3){
					recs.get(counter).setX(recs.get(counter).getX()-5);
					recs.get(counter).setWidth(recs.get(counter).getWidth()+10); // net gain is 10(5 left, 5 right)
				}
				slicedAccel.add(0.0);
				counter++;
				recs.add(new Rectangle(recs.get(counter-1).getX(),recs.get(counter-1).getY()-recs.get(counter-1).getHeight(),recs.get(counter-1).getWidth(),recs.get(counter-1).getHeight()));
				recs.get(counter).setColor(Color.RED);
				recs.get(counter).draw();
				score.setText("Score: " + (counter-1));
			}
		}

		if(!alive && !s.equals((char)10+"")){ // (char)8 = back space, (char)10 = enter key
			if(s.equals((char)8+"") && playerName.length()>0)
				playerName = playerName.substring(0,playerName.length()-1);
			else if(playerName.length() < 10)
				playerName+=s;
			displayName.setText("Enter username: " + playerName);
		}

	}

	public static void recordScore(String fileName, String userName, int score){
		//note: the only thing the try catches do is catch runtime errors,
		//but if the file exists and is okay you don't really need them

		//write to file:
		try{
			String data = "";
			boolean nameExists = false;
			while(!fileReader.eof()){
				String line = fileReader.readLine();
				if(playerName.equals(line.substring(0,line.indexOf(";")))){
					nameExists = true;
					if(Integer.parseInt(line.substring(line.indexOf(";")+1))<counter-1) // current score is greater than existing score, change it
						data+=line.substring(0,line.indexOf(";")+1)+(counter-1)+"\n";
					else
						data+=line+"\n";
				}
				else
					data+=line+"\n";
			}
			if(nameExists){
				FileWriter myFileWriter = new FileWriter("leaderboard.txt");
				myFileWriter.write(data);
				myFileWriter.close();
			}
			else if(!nameExists){
				FileWriter myFileWriter = new FileWriter("leaderboard.txt",true); // the true prevents it from overwriting information
				myFileWriter.write(userName + ";" + (counter-1) + "\n");
				myFileWriter.close();
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

}