//
import pkg.*;

public class starter implements InputControl, InputKeyControl {
			
	public static void main(String args[]) {
		KeyController kC = new KeyController(Canvas.getInstance(),new starter());
		MouseController mC = new MouseController(Canvas.getInstance(),new starter());
		
		
	}

	public void onMouseClick(double x, double y) {

	}

	public void keyPress(String s) {

	}
}