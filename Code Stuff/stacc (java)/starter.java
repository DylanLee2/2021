import pkg.*;
import java.util.ArrayList;

public class starter implements InputKeyControl {

	public static ArrayList<Rectangle> recs = new ArrayList<Rectangle>();
	public static int counter, xSpeed;
	public static int streak; // make streak system(if streak > 7 then increase width)
	public static String inp="";
	public static boolean playing=true, alive;
	public static Text score;

	public static void main(String args[]){
		KeyController kC = new KeyController(Canvas.getInstance(), new starter());

		//These only need to be initialized once for efficiency
		Rectangle border1 = new Rectangle(0,-10,10,610);
		border1.draw();
		Rectangle border2 = new Rectangle(590,-10,10,610);
		border2.draw();

		score = new Text(300,20,"Score: " + counter);
		score.grow(70,40);
		score.draw();
		
		while(playing){
			// initialize game
			counter = 0;
			xSpeed = -5;

			recs.add(new Rectangle(150,567,150,30));
			recs.get(0).setColor(Color.RED);
			recs.get(0).draw();
			
			alive = true;
			while(alive){
				Canvas.pause(20);
				moveLeftAndRight();

				if(recs.get(counter).getY() <= 120){ // keeps "camera" in the middle
					for(int i = 0; i < recs.size(); i++)
						recs.get(i).translate(0, recs.get(i).getHeight());
				}
				alive = (recs.get(counter).getWidth() >= 1);
			}

			//restart game
			boolean restart = false;
			while(!inp.equals("abcd")){ // wait for user input
				System.out.print(""); // keeps while loop running
				if(inp.equals("r")){
					restart = true;
					break;
				}
				else if(inp.equals("q")){
					playing = false;
					break;
				}
			}

			if(restart){
				for(int i = 0; i < recs.size(); i++){
					recs.get(i).undraw();
					recs.remove(i);
					i--;
				}
				counter = 0;
				score.setText("Score: " + counter);
			}
			
		}
		
	}

	public void keyPress(String s){
		inp = s;
		System.out.println(inp);
		if(s.equals("w") && alive){
			recs.get(counter).setColor(Color.BLACK);
			counter++;
			Rectangle prevRec = recs.get(counter-1);
			recs.add(new Rectangle(prevRec.getX(),prevRec.getY()-prevRec.getHeight(),prevRec.getWidth(),prevRec.getHeight()));
			recs.get(recs.size()-1).setColor(Color.RED);
			recs.get(recs.size()-1).draw();
			score.setText("Score: " + counter);
		}
		else if(s.equals("d"))
			alive = false;
	}

	public static void moveLeftAndRight(){
		//if(recs.get(counter).getX() <= 10 || recs.get(counter).getX()+recs.get(counter).getWidth() >= 590){
			//xSpeed*=-1;
		xSpeed *= (recs.get(counter).getX() <= 10 || recs.get(counter).getX()+recs.get(counter).getWidth() >= 590) ? -1 : 1;
		recs.get(counter).translate(xSpeed,0);
	}

}