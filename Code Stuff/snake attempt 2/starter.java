import pkg.*;
import java.util.ArrayList;
public class starter implements InputKeyControl {
	
	public static int moveX = 2, moveY = 0, speed = 2;
	public static snake sna;
	public static String inp = "d";
	public static boolean[] direction = new boolean[4]; // 0=w, 1=a, 2=s, 3=d
	public static point turn = new point(60, 60);
	
	public static void main(String args[]){
      KeyController kC = new KeyController(Canvas.getInstance(),new starter());
		
		

		Text t = new Text(800, 300, "Use windowed mode to play");
		t.grow(150, 30);
		t.draw();

		ArrayList<Line> rows = new ArrayList<Line>();
		ArrayList<Line> columns = new ArrayList<Line>();
		int index = 0;
		for(int i = 30; i < 600; i+=30){
			rows.add(new Line(30, 0+i, 570, 0+i));
			rows.get(index).draw();
			index++;
		}
		index = 0;
		for(int i = 30; i < 600; i+=30){
			columns.add(new Line(0+i, 30, 0+i, 570));
			columns.get(index).draw();
			index++;
		}
		
		for(int i = 0; i < direction.length; i++)
			direction[i] = true;
		
		sna = new snake(60, 60, 30, 30);
		sna.addSpeed(2, 0);
		while(sna.getX()<571 && sna.getX()>29 && sna.getY()<571 && sna.getY()>39){
			Canvas.pause(10);
			sna.move(moveX, moveY, turn);
			sna.grow(direction[0], direction[1], direction[2], direction[3]);
		}
    }
    
	//make snake turn at the "rounded" point rather than translating to the point
	public void keyPress(String s){
		//moveX = inp.equals("w") ? 0 : inp.equals("a") ? -1*speed : inp.equals("s") ? 0 : inp.equals("d") ? speed : moveX;
		//moveY = inp.equals("w") ? -1*speed : inp.equals("a") ? 0 : inp.equals("s") ? speed : inp.equals("d") ? 0 : moveY;
		if(!s.equals(inp)){
			turn = new point(sna.body.get(0).getX(), sna.body.get(0).getY());
			sna.addSpeed(sna.xSpeed.get(sna.xSpeed.size()-1), sna.ySpeed.get(sna.ySpeed.size()-1));
		}
		inp = s;
		int alignMoveY = (((int)sna.body.get(0).getX())%30)-30;
		int alignMoveX = (((int)sna.body.get(0).getY())%30)-30;
		if(s.equals("w") && direction[0] == true){
			moveX = 0;
			moveY = -1*speed;
			for(int i = 0; i < sna.body.size(); i++){
				sna.moveChosen(i, -alignMoveY, 0);
			}
			for(int i = 0; i < direction.length; i++)
				direction[i] = (i!=2) ? true : false;
		}
		else if(s.equals("a") && direction[1] == true){
			moveX = -1*speed;
			moveY = 0;
			for(int i = 0; i < sna.body.size(); i++)
				sna.moveChosen(i, 0, -alignMoveX);
			for(int i = 0; i < direction.length; i++)
				direction[i] = (i!=3) ? true : false;
		}
		else if(s.equals("s") && direction[2] == true){
			moveX = 0;
			moveY = speed;
			for(int i = 0; i < sna.body.size(); i++)
				sna.moveChosen(i, -alignMoveY, 0);
			for(int i = 0; i < direction.length; i++)
				direction[i] = (i!=0) ? true : false;
		}
		else if(s.equals("d") && direction[3] == true){
			moveX = speed;
			moveY = 0;
			for(int i = 0; i < sna.body.size(); i++)
				sna.moveChosen(i, 0, -alignMoveX);
			for(int i = 0; i < direction.length; i++)
				direction[i] = (i!=1) ? true : false;
		}
		sna.xSpeed.add(moveX);
		sna.ySpeed.add(moveY);
	}
	
}