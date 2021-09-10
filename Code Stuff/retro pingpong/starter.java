//Dylan Lee 9/9/21
import pkg.*;

public class starter implements InputKeyControl {
	//first to 7 wins
	public static Rectangle[] players = new Rectangle[2];
	public static int leftScore = 0, rightScore = 0, speed = 2, speed1 = 0, speed2 = 0;

	public static void main(String args[]) {
		KeyController kC = new KeyController(Canvas.getInstance(),new starter());
		
		Rectangle[] borders = new Rectangle[2]; // borders[0] is top, borders[1] is bottom
		borders[0] = new Rectangle(0,0,600,90);
		borders[1] = new Rectangle(0,510,600,90);

		Text[] scores = new Text[2]; // scores[0] is left, scores[1] is right
		scores[0] = new Text(230,50,leftScore+"");
		scores[1] = new Text(360,50,rightScore+"");

		players[0] = new Rectangle(0,250,30,100); //player[0] is left, player[1] is right
		players[1] = new Rectangle(570,250,30,100);

		for(int i = 0; i < 2; i++){ //initialize arrays of the borders, scores, and players
			borders[i].setColor(Color.YELLOW);
			borders[i].fill();
			scores[i].grow(40,40);
			scores[i].draw();
			players[i].setColor(Color.RED);
			players[i].fill();
		}

		Line middle = new Line(300,0,300,600); //middle line to split the screen
		middle.draw();

		ball b = new ball(3);
		while(true){
			Canvas.pause(5);
			if(speed1 < 0 && players[0].getY() < 90)
					speed1 = 0;
			else if(speed1 > 0 && players[0].getY() > 410)
					speed1 = 0;
			if(speed2 < 0 && players[1].getY() < 90)
					speed2 = 0;
			else if(speed2 > 0 && players[1].getY() > 410)
					speed2 = 0;
			players[0].translate(0,speed1);
			players[1].translate(0,speed2);
			b.move();
		}
		
	}

	//w and s for left player. i and k for right player
	public void keyPress(String s) {
		speed1 = s.equals("w") ? -speed : s.equals("s") ? speed : speed1;
		speed2 = s.equals("i") ? -speed : s.equals("k") ? speed : speed2;
	}
}