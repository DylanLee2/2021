import pkg.*;
public class starter implements InputKeyControl {
	
	public static int moveX, moveY, speed = 2;
	public static snake sna;
	public static boolean[] direction = new boolean[4]; // 0=w, 1=a, 2=s, 3=d
	
	public static void main(String args[]){
      KeyController kC = new KeyController(Canvas.getInstance(),new starter());
		
		for(int i = 0; i < direction.length; i++){
			direction[i] = true;
		}
		sna = new snake(250, 250, 30, 30);
		while(true){
			Canvas.pause(10);
			sna.move(moveX, moveY);
			sna.grow(direction[0], direction[1], direction[2], direction[3]);
		}
    }
    
	public void keyPress(String s){
		//moveX = inp.equals("w") ? 0 : inp.equals("a") ? -1*speed : inp.equals("s") ? 0 : inp.equals("d") ? speed : moveX;
		//moveY = inp.equals("w") ? -1*speed : inp.equals("a") ? 0 : inp.equals("s") ? speed : inp.equals("d") ? 0 : moveY;
		
		if(s.equals("w") && direction[0] == true){
			moveX = 0;
			moveY = -1*speed;
			direction[2] = false;
			for(int i = 0; i < direction.length; i++)
				direction[i] = i!=2 ? true : false;
		}
		else if(s.equals("a") && direction[1] == true){
			moveX = -1*speed;
			moveY = 0;
			direction[3] = false;
			for(int i = 0; i < direction.length; i++)
				direction[i] = i!=3 ? true : false;
		}
		else if(s.equals("s") && direction[2] == true){
			moveX = 0;
			moveY = speed;
			direction[0] = false;
			for(int i = 0; i < direction.length; i++)
				direction[i] = i!=0 ? true : false;
		}
		else if(s.equals("d") && direction[3] == true){
			moveX = speed;
			moveY = 0;
			direction[1] = false;
			for(int i = 0; i < direction.length; i++)
				direction[i] = i!=1 ? true : false;
		}
		sna.addSpeed(moveX, moveY);
		
	}
	
}