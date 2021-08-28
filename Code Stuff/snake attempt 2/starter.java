import pkg.*;

public class starter implements InputKeyControl {
		
	public static int speedX, speedY, moveX, moveY, speed = 5;
	public static snake sna;
	public static boolean game = true;
	
	public static void main(String args[]){
      KeyController kC = new KeyController(Canvas.getInstance(),new starter());
		
		sna = new snake(250, 250, 30, 30);
		sna.color(0);
    }
    
	public void keyPress(String s){
		String inp = s;
		if(inp.equals("w")){
			moveX = 0;
			moveY = -1*speed;
			sna.move(moveX, moveY);
		}
		else if(s.equals("a"))
			sna.move(-1*speed, 0);
		else if(s.equals("s"))
			sna.move(0, speed);
		else if(s.equals("d"))
			sna.move(speed, 0);
		else
			sna.move(moveX, moveY);
	}
	

}