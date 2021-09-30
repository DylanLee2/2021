//Dylan Lee 9/10/21
import pkg.*;

public class ball{

	public Ellipse[] balls;
	public double[] ballSpeeds; // 0 through balls.length-1 is X speed. balls.length through balls.length*2 is Y speed
	public boolean[] leftOrRight; // same logic applies to left and right ^
	public int[] active, turn;
	public double ballSpeed = 1.5;
	public int ballTurn = 0;
	
	public ball(int amount){
		balls = new Ellipse[amount];
		ballSpeeds = new double[amount*2];
		leftOrRight = new boolean[amount*2];
		active = new int[amount];
		turn = new int[amount];

		for(int i = 0; i < amount*2; i++){
			if(i<amount){
				balls[i] = new Ellipse(280,280,30,30);
				balls[i].setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
				active[i] = 1; // 1 indicates ball still in game, 0 is out of boundaries
				turn[i] = 0; // 0 indicates not the turn, 1 indicates turn for ball
			}
			// generates random speeds for each ball initially
			int rand = (int)(Math.random()*2);
			ballSpeeds[i] = (rand == 1) ? ballSpeed : -ballSpeed;
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
				leftOrRight[i-1] = true;
				active[i-1] = 0;
				turn[i] = 1;
				ballTurn++;
			}
			else if(balls[i-1].getX() < -30 && active[i-1] == 1){
				score[1]++;
				leftOrRight[i+balls.length-1] = true;
				active[i-1] = 0;
				turn[i] = 1;
				ballTurn++;
			}
		}
		return score;
	}

	public void moveOnTurn(){ // 1 ball at a time
		for(int i = 0; i < turn.length; i++){
			if(turn[i] == 1){
				balls[i].fill();
				balls[i].translate(ballSpeeds[i], ballSpeeds[i+balls.length]);
			}
		}
	}

	public void move(){ // all balls at once
		for(int i = 0; i < balls.length; i++){
			if(active[i] == 1)
				balls[i].translate(ballSpeeds[i], ballSpeeds[i+balls.length]);
		}
	}

	public void hit(Rectangle[] players, Rectangle[] borders){
		for(int i = 0; i < balls.length; i++){
			//changes speed depending on the direction of the surface
			if((ballSpeeds[i]>0 && players[1].contains(balls[i])/* && balls[i].getX() < 571*/) || (ballSpeeds[i]<0 && players[0].contains(balls[i])/* && balls[i].getX() > 28*/))
				ballSpeeds[i] *= -1.1; // speed multiplyer each time a player hits the ball
			else if(borders[0].contains(balls[i]) || borders[1].contains(balls[i]))
				ballSpeeds[i+balls.length] *= -1;
		}
	}

	public boolean gameOver() {
		boolean over = true;
		for(int i = 0; i < active.length; i++)
			over = (active[i]==1) ? false : over;
		return over;
	}

}