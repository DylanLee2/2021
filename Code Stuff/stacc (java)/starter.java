import pkg.*;
import java.util.ArrayList;

public class starter implements InputKeyControl {

	public static ArrayList<Rectangle> recs = new ArrayList<Rectangle>();
	public static int counter, xSpeed;
	public static int numStreak; // make streak system (if streak > 7 then increase width)
	public static String inp=""; // for restarting game
	public static boolean playing=true, alive;
	public static Text score;

	public static void main(String args[]){
		KeyController kC = new KeyController(Canvas.getInstance(), new starter());

		// These only need to be initialized once for efficiency
		Rectangle background = new Rectangle(0,0,600,600);
		background.setColor(new Color(255,255,200));
		background.fill();

		Rectangle border1 = new Rectangle(0,-10,10,610);
		border1.draw();
		Rectangle border2 = new Rectangle(590,-10,10,610);
		border2.draw();

		score = new Text(300,30,"Score: " + counter);
		score.grow(70,40);
		score.draw();
		
		Text streak = new Text(500,30,"Streak: ");
		streak.grow(50,20);
		streak.draw();

		Text restartGame = new Text(280,250,"Press R to restart or Q to quit");
		restartGame.setColor(Color.RED);
		restartGame.grow(190,40);

		Text playInWindowed = new Text(900,300,"Play in windowed mode");
		playInWindowed.grow(180,35);
		playInWindowed.draw();

		while(playing){
			// initialize game
			numStreak = 0;
			counter = 0;
			xSpeed = -5; // game starts going left

			score.setText("Score: " + counter);

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
				
				alive = (recs.get(counter).getWidth() >= 1); // if the current rectangle is wide enough
				streak.setText("Streak: " + numStreak);
			}

			//restart game
			restartGame.draw();
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
				System.out.println("restarted");
				restartGame.undraw();
				for(int i = 0; i < recs.size(); i++){
					/*
					recs.get(i).undraw();
					recs.remove(i);
					i--;
					*/

					// or...

					
					recs.get(recs.size()-1).undraw();
					recs.remove(recs.size()-1);
					
				}

			}
			System.out.println("quit");
		}
		
	}

	public void keyPress(String s){
		inp = s;
		System.out.println(inp);
		if(s.equals("w") && alive){
			/*
			if(counter > 0){
				recs.get(counter).undraw();
				recs.set(counter,new Rectangle(recs.get(counter-1).getX(), recs.get(counter-1).getY()-recs.get(counter-1).getHeight(), recs.get(counter-1).getWidth(), recs.get(counter-1).getHeight()));
				recs.get(counter).setColor(Color.BLACK); // new Rectangle
			}
			*/
			recs.get(counter).setColor(Color.BLACK);
			counter++;
			Rectangle prevRec = recs.get(counter-1);
			if(counter>=2)
				recs.add(new Rectangle(recs.get(counter-2).getX(),prevRec.getY()-prevRec.getHeight(),(recs.get(counter-2).getX()+recs.get(counter-2).getWidth())-prevRec.getWidth(),prevRec.getHeight()));
			else
			recs.add(new Rectangle(prevRec.getX(),prevRec.getY()-prevRec.getHeight(),prevRec.getWidth(),prevRec.getHeight()));
			//find equation to reduce width of the new rectangle
			
			recs.get(recs.size()-1).setColor(Color.RED);
			recs.get(recs.size()-1).draw();
			score.setText("Score: " + counter);
		}

	}

	public static void moveLeftAndRight(){
		if(recs.get(counter).getX() <= 10 || recs.get(counter).getX()+recs.get(counter).getWidth() >= 590){
			xSpeed*=-1; // change directions
		}
		recs.get(counter).translate(xSpeed,0);
	}

}