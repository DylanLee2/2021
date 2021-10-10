//Dylan Lee 9/30/21

import pkg.*;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class game{

	private Rectangle player, leftEye, rightEye, mouth;
	private ArrayList<Rectangle> platforms = new ArrayList<Rectangle>(); // add and remove rectangles appropriately
	public ArrayList<String> names = new ArrayList<String>();
	public ArrayList<Integer> scores = new ArrayList<Integer>();
	public Text score;
	public double speed = 0, vertSpeed = 2, horizSpeed = 0;
	public int numScore = 0, airTime, rectIndex = -1;

	// Idea: as time increases, platforms fall faster

	public game(){
		//initialize
		player = new Rectangle(200,450,40,50);
		leftEye = new Rectangle(205,455,10,10);
		rightEye = new Rectangle(225,455,10,10);
		mouth = new Rectangle(207,480,25,10);
		score = new Text(220,30,numScore+"");
		for(int i = 0; i < 10; i++)
			platforms.add(new Rectangle(Canvas.rand(420),Canvas.rand(600),80,20));
		//draw
		for(Rectangle r : platforms){
			r.draw();
			r.setColor(new Color(78,250,78)/*new Color(Canvas.rand(255),Canvas.rand(255),Canvas.rand(255))*/);
			r.fill();
		}
		score.grow(40,30);
		score.draw();
		player.setColor(new Color(205, 127, 50));
		player.fill();
		leftEye.fill();
		rightEye.fill();
		mouth.setColor(new Color(180,0,0));
		mouth.fill();
	}

	public void swapPositions(){
		if(player.getX() < -40)
			movePlayer(490, 0);
		else if(player.getX() > 490)
			movePlayer(-530, 0);
	}

	public void recyclePlatforms(){
		for(int i = 0; i < platforms.size(); i++){
			if(platforms.get(i).getY() > 670){
				int moveX = (platforms.get(i).getX()>200) ? -1*Canvas.rand(200) : Canvas.rand(200);
				platforms.get(i).translate(moveX,-platforms.get(i).getY());
			}
		}
	}

	public void fall(){
		boolean canChange = true;
		swapPositions();
		for(int i = 0; i < platforms.size(); i++){
			// fix player stops when under the platform then goes top

			//if(vertSpeed>0 && ((player.getY()+player.getHeight()) >= platforms.get(i).getY()) && (player.contains(platforms.get(i)))){
			if(vertSpeed>0 && player.contains(platforms.get(i))){
				if(rectIndex != i){
					numScore += 10;
					rectIndex = i;
				}
				System.out.println("jump!");
				vertSpeed = -4;
				jump(-4);
				canChange = false;
			}
			//else if((!player.contains(platforms.get(i)) || (player.getY() > platforms.get(i).getY())) || canChange)
				//vertSpeed = 2.5;
			else
				vertSpeed = 2.5;

		}
		movePlayer(0, 2.5);

	}

	public void jump(double velocity){
		//vertSpeed = 3;
		airTime = 0;
		while(airTime<30){
			recyclePlatforms();
			Canvas.pause(5);
			//movePlayer(horizSpeed,-vertSpeed+(airTime*0.01));
			if(velocity<0)
				movePlayer(horizSpeed, velocity+(airTime*0.02));
			else if(velocity>0)
				movePlayer(horizSpeed, velocity-(airTime*0.001));
			if(player.getY()<200){
				for(Rectangle plats : platforms)
					plats.translate(0,5);
			}
			airTime++;
		}
	}

	public void movePlayer(double x, double y){
		player.translate(x,y);
		leftEye.translate(x,y);
		rightEye.translate(x,y);
		mouth.translate(x,y);
	}

	public void resetGame(){
		score.translate(-750,0);
		for(int i = 0; i < platforms.size(); i++)
			platforms.get(i).translate(-750,0);
		for(int i = 0; i < platforms.size(); i++)
			platforms.remove(0);
	}

	public String recordScore(String fileName, String userName, int score){
			//note: the only thing the try catches do is catch runtime errors,
			//but if the file exists and is okay you don't really need them

			//write to file:
			try{
				FileWriter myFileWriter = new FileWriter("scores.txt",true); // the true prevents it from overwriting information
				myFileWriter.write(userName + ";" + numScore + "\n");
				myFileWriter.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}
			//read from file:
			EasyReader fileReader = new EasyReader("scores.txt"); // get arraylist to have scores to sort them
			String data = "";
			while(!fileReader.eof()){
				String line = fileReader.readLine()+"\n";
				data+=line;
				//names.add(line.substring(0,line.indexOf(";")-1));
				//scores.add(Integer.parseInt(line.substring(line.indexOf(";")+1)));
			}
			System.out.println(data);
			return data;
	}

	public Rectangle getPlayer(){
		return player;
	}

	public ArrayList getPlatforms(){
		return platforms;
	}

}