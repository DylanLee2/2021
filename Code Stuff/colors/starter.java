//Dylan Lee 9/29/21
import pkg.*;

public class starter implements InputControl, InputKeyControl {

	public static Ellipse e;
	public static Rectangle[] r;
	public static void main(String args[]) {
		KeyController kC = new KeyController(Canvas.getInstance(),new starter());
		MouseController mC = new MouseController(Canvas.getInstance(),new starter());

		int rects = 30; // 600/rects is number of rectangles
		int limit = 600/rects;
		int counter = 0;

		r = new Rectangle[600/rects];

		for(int i = rects; i < 600; i+=rects){
			if(counter < limit){
				r[counter] = new Rectangle(0,i,600,i);
				r[counter].setColor(new Color(r[i].getX(), r[i].getY(), r[i].getX()));
				r[counter].fill();
			}
			counter++;
		}
		
	}

	public void onMouseClick(double x, double y) {
		/* color clicker (doesnt work)
		System.out.println(x + " " + y + " " + x/y);
		int ex = (int)x;
		int why = (int)y;
		e = new Ellipse(x,y,30,30);
		e.setColor(new Color(ex,why,ex+why));
		e.fill();
		*/
	}

	public void keyPress(String s) {

	}
}