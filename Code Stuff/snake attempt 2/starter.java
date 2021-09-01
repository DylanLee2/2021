import pkg.*;
import java.util.ArrayList;
public class starter implements InputKeyControl {
	
	public static int moveX, moveY, speed = 2;
	public static snake sna;
	public static boolean[] direction = new boolean[4]; // 0=w, 1=a, 2=s, 3=d
	
	public static void main(String args[]){
      KeyController kC = new KeyController(Canvas.getInstance(),new starter());
		
		ArrayList<Line> rows = new ArrayList<Line>();
		ArrayList<Line> columns = new ArrayList<Line>();
		int index = 0;
		for(int i = 0; i < 600; i+=30){
			rows.add(new Line(0, 0+i, 600, 0+i));
			rows.get(index).draw();
			index++;
		}
		index = 0;
		for(int i = 0; i < 600; i+=30){
			columns.add(new Line(0+i, 0, 0+i, 600));
			columns.get(index).draw();
			index++;
		}
		
		for(int i = 0; i < direction.length; i++)
			direction[i] = true;
		
		sna = new snake(0, 0, 30, 30);
		while(true){
			Canvas.pause(10);
			sna.move(moveX, moveY);
			sna.grow(direction[0], direction[1], direction[2], direction[3]);
		}
    }
    
	public void keyPress(String s){
		//moveX = inp.equals("w") ? 0 : inp.equals("a") ? -1*speed : inp.equals("s") ? 0 : inp.equals("d") ? speed : moveX;
		//moveY = inp.equals("w") ? -1*speed : inp.equals("a") ? 0 : inp.equals("s") ? speed : inp.equals("d") ? 0 : moveY;
		
		int alignMoveY = (((int)sna.body.get(0).getX())%30)-30;
		int alignMoveX = (((int)sna.body.get(0).getY())%30)-30;
		if(s.equals("w") && direction[0] == true){
			moveX = 0;
			moveY = -1*speed;
			sna.moveChosen(0, -alignMoveY, 0);
			//sna.moveChosen(0, 0, -1*((int)sna.body.get(0).getX()%(int)sna.body.get(0).getHeight()));
			for(int i = 0; i < direction.length; i++)
				direction[i] = (i!=2 && i!=0)? true : false;
		}
		else if(s.equals("a") && direction[1] == true){
			moveX = -1*speed;
			moveY = 0;
			sna.moveChosen(0, 0, -alignMoveX);
			for(int i = 0; i < direction.length; i++)
				direction[i] = (i!=3 && i!=1) ? true : false;
		}
		else if(s.equals("s") && direction[2] == true){
			moveX = 0;
			moveY = speed;
			sna.moveChosen(0, -alignMoveY, 0);
			for(int i = 0; i < direction.length; i++)
				direction[i] = (i!=0 && i!=2) ? true : false;
		}
		else if(s.equals("d") && direction[3] == true){
			moveX = speed;
			moveY = 0;
			sna.moveChosen(0, 0, -alignMoveX);
			for(int i = 0; i < direction.length; i++)
				direction[i] = (i!=1 && i!=3) ? true : false;
		}
		sna.addSpeed(moveX, moveY);
		
	}
	
}