//Dylan Lee 9/5/21
import pkg.*;

public class starter {
			
	public static void main(String args[]) {
		Ellipse leftEye = new Ellipse(100,100,50,50);
		Ellipse rightEye = new Ellipse(200,100,50,50);
		Line mouth = new Line(100,200,250,200);

		leftEye.fill();
		rightEye.fill();
		mouth.draw();
	}
}