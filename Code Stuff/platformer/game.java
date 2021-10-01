//Dylan Lee 9/30/21

import pkg.*;
import java.util.ArrayList;

public class game{

	private Rectangle player;
	public Rectangle background, leftEye, rightEye, mouth;
	private ArrayList<Rectangle> platforms; // add and remove rectangles appropriately
	public Text score;
	public double speed = 0, fallSpeed = 2, horizontalSpeed = 0, jumpSpeed = 0;
	public int numScore = 0, timer = 0, airTime = 0;

	// Idea: as time increases, platforms fall faster

	public game(){
		//initialize
		background = new Rectangle(0,0,450,650);
		player = new Rectangle(200,450,40,60);
		leftEye = new Rectangle(205,455,10,10);
		rightEye = new Rectangle(225,455,10,10);
		mouth = new Rectangle(207,480,25,10);
		score = new Text(220,30,numScore+"");
		platforms = new ArrayList<Rectangle>(); // always have 8 platforms on screen
		for(int i = 0; i < 8; i++){
			platforms.add(new Rectangle(Canvas.rand(420),Canvas.rand(630),80,20));
		}
		//draw
		background.setColor(new Color(Canvas.rand(255),Canvas.rand(255),Canvas.rand(255)));
		background.fill();
		for(Rectangle r : platforms){
			r.setColor(new Color(Canvas.rand(255),Canvas.rand(255),Canvas.rand(255)));
			r.fill();
		}
		score.grow(25,40);
		score.setColor(new Color(255,255,255));
		score.draw();
		player.setColor(new Color(Canvas.rand(255),Canvas.rand(255),Canvas.rand(255)));
		player.fill();
		leftEye.fill();
		rightEye.fill();
		mouth.setColor(new Color(200,0,0));
		mouth.fill();
	}

	public void swapPositions(){
		if(player.getX() < -40){
			movePlayer(490, 0);
		}
		else if(player.getX() > 490){
			movePlayer(-530, 0);
		}
	}

	public void recyclePlatforms(){
		for(Rectangle r : platforms){
			if(r.getY() > 670){
				if(r.getX() > 200)
					r.translate(-1*Canvas.rand(200), 0);
				else if(r.getX() < 200)
					r.translate(Canvas.rand(200), 0);
				r.setColor(new Color(Canvas.rand(255),Canvas.rand(255),Canvas.rand(255)));
				r.translate(0,-690 - Canvas.rand(50));
			}
		}
	}

	public void fall(){
		boolean canChange = true;
		recyclePlatforms();
		swapPositions();
		for(int i = 0; i < platforms.size(); i++){

			// fix player stops when under the platform then goes top

			//if(player.contains(platforms.get(i))) // for debugging
			//	System.out.println(player.getY()+player.getHeight() + " " + platforms.get(i).getY());
			if((player.contains(platforms.get(i))) && fallSpeed>0/*((player.getY()+player.getHeight()) >= platforms.get(i).getY())*/){
				jump();
				fallSpeed = 0.0;
				canChange = false;
			}
			else if((!player.contains(platforms.get(i)) && (player.getY() < platforms.get(i).getY())) && canChange)
				fallSpeed = 2.0;

			platforms.get(i).translate(0,1);
		}
		movePlayer(0,fallSpeed);

	}

	public void jump(){
		/*
		if(player.getY() < 100)
			jumpSpeed = 0;
		else if(player.getY() > 100)
			jumpSpeed = -80;
		movePlayer(0,jumpSpeed);
		*/
		
		//jump for a specific amount of time. try making a smooth animation
		jumpSpeed = -80;
		for(int i = 0; i < 3; i++){
			Canvas.pause(10);
			movePlayer(0,jumpSpeed);
			jumpSpeed/=2;
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

	public Rectangle getPlayer(){
		return player;
	}

	public ArrayList getPlatforms(){
		return platforms;
	}

}