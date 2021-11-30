//Dylan Lee 9/10/21

import java.util.ArrayList;
import pkg.*;
public class ball{
	
	public ArrayList<Ellipse> balls = new ArrayList<Ellipse>(); // change everything below to arrays
	public ArrayList<Double> ballSpeedX = new ArrayList<Double>();
	public ArrayList<Double> ballSpeedY = new ArrayList<Double>();
	public ArrayList<Boolean> left = new ArrayList<Boolean>();
	public ArrayList<Boolean> right = new ArrayList<Boolean>();
	public int[] active;
	public double speed = 1;

	public ball(int amount){
		active = new int[amount];
		for(int i = 0; i < amount; i++){
			balls.add(new Ellipse(280,280 + (i*30),30,30));
			balls.get(i).setColor(Color.BLUE);
			balls.get(i).fill();
			left.add(false);
			right.add(false);
			active[i] = 1; //1 indicates ball still in game, 0 is out of boundaries
		}
		for(int i = 0; i < amount; i++){
			int rand1 = (int)(Math.random()*2), rand2 = (int)(Math.random()*2);
			if(rand1 == 1)
				ballSpeedX.add(speed);
			else
				ballSpeedX.add(-speed);
			if(rand2 == 1)
				ballSpeedY.add(speed);
			else
				ballSpeedY.add(-speed);
		}
	}

	public int scoreLeft(int leftScore){
		int score = leftScore;
		for(int i = 0; i < balls.size(); i++){
			if(balls.get(i).getX() > 600 && active[i] == 1){
				score++;
				left.set(i, true); // true when ball passes left side
				active[i] = 0;
			}
		}
		return score;
	}

	public int scoreRight(int rightScore){
		int score = rightScore;
		for(int i = 0; i < balls.size(); i++){
			if(balls.get(i).getX() < -30 && active[i] == 1){
				score++;
				right.set(i, true); // true when ball passes left side
				active[i] = 0;
			}
		}
		return score;
	}

	public void move(){
		for(int i = 0; i < balls.size(); i++){
			if(active[i] == 1)
				balls.get(i).translate(ballSpeedX.get(i), ballSpeedY.get(i));
		}
	}

	public boolean hit(Rectangle[] players, Rectangle[] borders){
		boolean hit = false;
		for(int i = 0; i < balls.size(); i++){
			if(players[0].contains(balls.get(i))){ //change speed depending on the direction of the surface
				ballSpeedX.set(i, -ballSpeedX.get(i)*1.1);
				hit = true;
			}
			else if(players[1].contains(balls.get(i))){
				ballSpeedX.set(i, -ballSpeedX.get(i)*1.1);
				hit = true;
			}
			else if(borders[0].contains(balls.get(i))){
				ballSpeedY.set(i, -ballSpeedY.get(i));
				hit = true;
			}
			else if(borders[1].contains(balls.get(i))){
				ballSpeedY.set(i, -ballSpeedY.get(i));
				hit = true;
			}
		}
		return hit;
	}
}