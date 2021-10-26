import pkg.*;
import java.util.ArrayList;

public class stacc implements InputKeyControl {

	public static ArrayList<Rectangle> recs = new ArrayList<Rectangle>();
	public static int counter, xSpeed, speed=5;
	public static int numStreak; // make streak system (if streak > 7 then increase width)
	public static String inp=""; // for restarting game
	public static boolean playing=true, alive;
	public static Text score;


	/*
	add difficulty
	improve graphics
	*/

	public static void main(String args[]){
		KeyController kC = new KeyController(Canvas.getInstance(), new stacc());

		// These only need to be initialized once for efficiency
		Rectangle background = new Rectangle(0,0,600,600);
		background.setColor(new Color(255,255,200));
		background.fill();

		Rectangle border1 = new Rectangle(0,-10,10,610);
		border1.draw();
		Rectangle border2 = new Rectangle(590,-10,10,610);
		border2.draw();

		score = new Text(300,30,"Score: ");
		score.grow(70,40);
		score.draw();
		
		Text streak = new Text(500,30,"Streak: ");
		streak.grow(50,20);
		streak.draw();

		Text restartGame = new Text(250,250,"Press R to restart or Q to quit");
		restartGame.setColor(Color.RED);
		restartGame.grow(190,40);

		Text playInWindowed = new Text(900,300,"Play in windowed mode");
		playInWindowed.grow(180,35);
		playInWindowed.draw();

		while(playing){
			// initialize game
			numStreak = 0;
			counter = 1;
			xSpeed = speed;

			score.setText("Score: " + (counter-1));

			recs.add(new Rectangle(100,567,350,30)); // starting rectangle
			recs.get(0).draw();
			
			recs.add(new Rectangle(Canvas.rand(590-recs.get(0).getWidth()+10),537,recs.get(0).getWidth(),30));
			recs.get(1).setColor(Color.RED);
			recs.get(1).draw();

			alive = true;
			while(alive){
				Canvas.pause(20);
				moveLeftAndRight();

				if(recs.get(counter).getY() <= 120){ // keeps "camera" in the middle
					for(int i = 0; i < recs.size(); i++)
						recs.get(i).translate(0, recs.get(i).getHeight());
				}
				if(recs.get(counter).getWidth() <= 1)
					alive = false;
				//streak.setText("Streak: " + numStreak);
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
				restartGame.undraw();
				for(int i = 0; i < recs.size(); i++){
					recs.get(i).undraw();
					recs.remove(i);
					i--;
				}
			}

		}
		java.lang.System.exit(0); // exits game if user is done playing
		
	}

	public void keyPress(String s){
		inp = s;
		if(s.equals("w") && alive){
			recs.get(counter).setColor(Color.BLACK);
			if(((recs.get(counter).getX()+recs.get(counter).getWidth()) < recs.get(counter-1).getX()) || (recs.get(counter).getX() > (recs.get(counter-1).getX()+recs.get(counter-1).getWidth()))){
				alive = false;
			}
			if(alive){
				// current rec is to the left of prev rectangle
				if(recs.get(counter).getX() < recs.get(counter-1).getX()){
					//recs.get(counter).setWidth(recs.get(counter-1).getX()-recs.get(counter).getX());
					recs.get(counter).setWidth(recs.get(counter).getX()+recs.get(counter).getWidth()-recs.get(counter-1).getX());
					recs.get(counter).setX(recs.get(counter-1).getX());
					
				}
				// current rec is to the right of prev rectangle
				else if(recs.get(counter).getX() > recs.get(counter-1).getX()){
					recs.get(counter).setWidth(recs.get(counter).getWidth()-((recs.get(counter).getX()+recs.get(counter).getWidth())-(recs.get(counter-1).getX()+recs.get(counter-1).getWidth())));
				}
				//else{ // rectangles connect
					//numStreak++;
				//}
				counter++;
				recs.add(new Rectangle(recs.get(counter-1).getX(),recs.get(counter-1).getY()-recs.get(counter-1).getHeight(),recs.get(counter-1).getWidth(),recs.get(counter-1).getHeight()));
				//find equation to reduce width of the new rectangle
				recs.get(counter).setColor(Color.RED);
				recs.get(counter).draw();
				score.setText("Score: " + (counter-1));
			}
		}

	}

	public static void moveLeftAndRight(){
		if(recs.get(counter).getX() <= 10 || recs.get(counter).getX()+recs.get(counter).getWidth() >= 590){
			xSpeed*=-1; // change directions
		}
		recs.get(counter).translate(xSpeed,0);
	}

}