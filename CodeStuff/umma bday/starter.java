import pkg.*;

public class starter {
			
	public static void main(String args[]) {
		
		String letter = " Dear umma, \n\n Thanks for constantly making sacrifices for our family so that our future would look brighter. Also, thank you for always making good food for our family when you were super busy. Always stay happy and healthy (Remember this forever). sarang hae umma! \n\n from sarang hanun adul";
		System.out.println(letter);
		
		Rectangle[] r = new Rectangle[100];
		for(int i = 0; i < r.length; i++){
			r[i] = new Rectangle(Canvas.rand(600), Canvas.rand(600), 20, 30);
			Color col = new Color(Canvas.rand(255), Canvas.rand(255), Canvas.rand(255));
			r[i].setColor(col);
			r[i].fill();
		}
		
		Text t = new Text(280, 250, "Happy Birthday umma!");
		t.setColor(Color.RED);
		t.grow(180, 70);
		t.draw();
		
		while(true){
			Canvas.pause(10);
			for(int i = 0; i < r.length; i++){
				r[i].translate(0, 2);
				if(r[i].getY() > 600)
					r[i].translate(0, -630);
			}
		}
	}

}