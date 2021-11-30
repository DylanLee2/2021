//Dylan Lee 10/23/21 physics :p
import pkg.*;

public class starter implements InputControl, InputKeyControl {

	public static double xVel=0;
	public static double yVel=0;
	public static double resistance=0.98;
	public static Rectangle r;

	public static void main(String args[]) {
		KeyController kC = new KeyController(Canvas.getInstance(),new starter());
		MouseController mC = new MouseController(Canvas.getInstance(),new starter());

		r = new Rectangle(100,100,100,100);
		r.setColor(Color.RED);
		r.draw();
		while(true){
			Canvas.pause(20);
			if(yVel <= 0)
				yVel+=resistance;
			//r.translate(xVel, yVel);
		}

	}

	public void onMouseClick(double x, double y) {}

	public void keyPress(String s) {
		if(s.equals("w")){
			yVel = -10;
			r.translate(0,yVel);
		}
		else if(s.equals("a")){
			xVel = -10;
			r.translate(xVel,0);
		}
		else if(s.equals("s")){
			yVel = 10;
			r.translate(0,yVel);
		}
		else if(s.equals("d")){
			xVel = 10;
			r.translate(xVel,0);
		}
	}

}