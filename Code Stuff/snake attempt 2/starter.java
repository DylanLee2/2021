import pkg.*;

public class starter implements InputKeyControl {
	
	public static String inp;
	public static int speedX, speedY, moveX, moveY, speed = 2;
	public static snake sna;
	public static boolean game = true;
	
	public static void main(String args[]){
      KeyController kC = new KeyController(Canvas.getInstance(),new starter());
		
		sna = new snake(250, 250, 30, 30);
		sna.color(0);
		while(true){
			Canvas.pause(10);
			sna.move(moveX, moveY);
			sna.grow(inp);
		}
    }
    
	public void keyPress(String s){
		inp = s;
		if(inp.equals("w")){
			moveX = 0;
			moveY = -1*speed;
		}
		else if(s.equals("a")){
			moveX = -1*speed;
			moveY = 0;
		}
		else if(s.equals("s")){
			moveX = 0;
			moveY = speed;
		}
		else if(s.equals("d")){
			moveX = speed;
			moveY = 0;
		}
	}
	

}