import pkg.*;

public class starter implements InputControl {
			
	public static void main(String args[]) {
		MouseController mC = new MouseController(Canvas.getInstance(),new starter());
		
	}

	public void onMouseClick(double x, double y) {
		int width = 90, height = 90;
		Ellipse c = new Ellipse(x-(width*0.6), y-75, width, height);
		int r = (int)(Math.random()*255), g = (int)(Math.random()*255), b = (int)(Math.random()*255);
		Color col = new Color(r, g, b);
		c.setColor(col);
		c.fill();
	}

}