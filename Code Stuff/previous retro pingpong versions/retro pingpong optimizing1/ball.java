//Dylan Lee 9/10/21
import pkg.*;

public class ball{

	public Ellipse[] balls;
	public double[] ballSpeedX, ballSpeedY; 
	public boolean[] left, right; // could optimize each pair of arrays to 1 array with double length
	public int[] active, turn;
	public double ballSpeed = 1;
	
	public ball(int amount){
		balls = new Ellipse[amount];
		ballSpeedX = new double[amount];
		ballSpeedY = new double[amount];
		left = new boolean[amount];
		right = new boolean[amount];
		active = new int[amount];
		turn = new int[amount];

		for(int i = 0; i < amount; i++){
			Color col = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
			balls[i] = new Ellipse(280,280,30,30);
			balls[i].setColor(col);
			//balls[i].fill();
			active[i] = 1; // 1 indicates ball still in game, 0 is out of boundaries
			turn[i] = 0; // 0 indicates not the turn, 1 indicates turn for ball

			// generates random speeds for each ball initially
			int rand1 = (int)(Math.random()*2), rand2 = (int)(Math.random()*2); 
			ballSpeedX[i] = (rand1 == 1) ? ballSpeed : -ballSpeed;
			ballSpeedY[i] = (rand2 == 1) ? ballSpeed : -ballSpeed;
		}
		turn[0] = 1;

	}

	public int[] score(int[] numScores){
		int[] score = new int[numScores.length];
		for(int i = 0; i < numScores.length; i++)
			score[i] = numScores[i];
		for(int i = 1; i < balls.length; i++){
			if(balls[i-1].getX() > 600 && active[i-1] == 1){
				score[0]++;
				left[i-1] = true;
				active[i-1] = 0;
				turn[i] = 1;
			}
			else if(balls[i-1].getX() < -30 && active[i-1] == 1){
				score[1]++;
				right[i-1] = true;
				active[i-1] = 0;
				turn[i] = 1;
			}
		}
		return score;
	}

	public void moveOnTurn(){ // 1 ball at a time
		for(int i = 0; i < turn.length; i++){
			if(turn[i] == 1){
				balls[i].fill();
				balls[i].translate(ballSpeedX[i], ballSpeedY[i]);
			}
		}
	}

	public void move(){ // all balls at once
		for(int i = 0; i < balls.length; i++){
			if(active[i] == 1)
				balls[i].translate(ballSpeedX[i], ballSpeedY[i]);
		}
	}

	public boolean hit(Rectangle[] players, Rectangle[] borders){
		boolean hit = false;
		for(int i = 0; i < balls.length; i++){
			//changes speed depending on the direction of the surface
			//if((players[0].contains(balls[i]) || players[1].contains(balls[i])) && (balls[i].getX() > 30 || balls[i].getX() < 570)){ // works but has seizure
			//if((players[0].contains(balls[i]) && balls[i].getX() == 60) || (players[1].contains(balls[i]) && balls[i].getX() == 540)){
			if((ballSpeedX[i]>0 && players[1].contains(balls[i])) || (ballSpeedX[i]<0 && players[0].contains(balls[i]))){
				ballSpeedX[i] = -ballSpeedX[i]*1.1; // speed multiplyer each time a player hits the ball
				hit = true;
			}
			else if(borders[0].contains(balls[i]) || borders[1].contains(balls[i])){
				ballSpeedY[i] = -ballSpeedY[i];
				hit = true;
			}
		}
		return hit;
	}

	public boolean gameOver() {
		boolean over = true;
		for(int i = 0; i < active.length; i++)
			over = (active[i]==1) ? false : over;
		return over;
	}

}