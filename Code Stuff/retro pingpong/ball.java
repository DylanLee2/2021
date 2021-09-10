//Dylan Lee 9/10/21

import java.util.ArrayList;
import pkg.*;
public class ball{
	
	public ArrayList<Ellipse> balls = new ArrayList<Ellipse>();
	public ArrayList<Integer> ballSpeedX = new ArrayList<Integer>();
	public ArrayList<Integer> ballSpeedY = new ArrayList<Integer>();
	public int speed = 4;

	public ball(int amount){
		for(int i = 0; i < amount; i++){
			balls.add(new Ellipse(280,280 + (i*30),30,30));
			balls.get(i).setColor(Color.BLUE);
			balls.get(i).fill();
		}
		for(int i = 0; i < amount; i++){
			int rand1 = (int)(Math.random()*1), rand2 = (int)(Math.random()*1);
			if(rand1 == 0)
				ballSpeedX.add(speed);
			else
				ballSpeedX.add(-speed);
			if(rand2 == 0)
				ballSpeedY.add(speed);
			else
				ballSpeedY.add(-speed);
		}
	}

	public void move(){
		for(int i = 0; i < balls.size(); i++)
			balls.get(i).translate(ballSpeedX.get(i), ballSpeedY.get(i));
	}
/*
	public boolean hit(Rectangle r){
		boolean hit = false;
		for(int i = 0; i < balls.size(); i++){
			if(r.contains(balls.get(i))){ //change speed depending on the direction of the surface
				ballSpeedX.set(i, )
			}
		}
	}
*/
}