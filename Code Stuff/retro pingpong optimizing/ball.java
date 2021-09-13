//Dylan Lee 9/10/21
import pkg.*;

public class ball{

	public Ellipse[] balls;
	public double[] ballSpeedX, ballSpeedY;
	public boolean[] left, right;
	public int[] active;
	public double ballSpeed = 1;
	
	public ball(int amount){
		balls = new Ellipse[amount];
		ballSpeedX = new double[amount];
		ballSpeedY = new double[amount];
		left = new boolean[amount];
		right = new boolean[amount];
		active = new int[amount];

		for(int i = 0; i < amount; i++){
			balls[i] = new Ellipse(280,280 + (i*30),30,30);
			balls[i].setColor(Color.BLUE);
			balls[i].fill();
			active[i] = 1; // 1 indicates ball still in game, 0 is out of boundaries

			// generates random speeds for each ball initially
			int rand1 = (int)(Math.random()*2), rand2 = (int)(Math.random()*2); 
			ballSpeedX[i] = (rand1 == 1) ? ballSpeed : -ballSpeed;
			ballSpeedY[i] = (rand2 == 1) ? ballSpeed : -ballSpeed;
		}

	}

	public int[] score(int[] numScores){
		int[] score = new int[numScores.length];
		for(int i = 0; i < numScores.length; i++)
			score[i] = numScores[i];
		for(int i = 0; i < balls.length; i++){
			if(balls[i].getX() > 600 && active[i] == 1){
				score[0]++;
				left[i] = true;
				active[i] = 0;
			}
			else if(balls[i].getX() < -30 && active[i] == 1){
				score[1]++;
				right[i] = true;
				active[i] = 0;
			}
		}
		return score;
	}

	public void move(){
		for(int i = 0; i < balls.length; i++){
			if(active[i] == 1)
				balls[i].translate(ballSpeedX[i], ballSpeedY[i]);
		}
	}

	public boolean hit(Rectangle[] players, Rectangle[] borders){
		boolean hit = false;
		for(int i = 0; i < balls.length; i++){
			//changes speed depending on the direction of the surface
			if((players[0].contains(balls[i]) || players[1].contains(balls[i])) && (balls[i].getX() > 30 || balls[i].getX() < 570)){ 
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