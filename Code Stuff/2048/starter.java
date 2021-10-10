//Dylan Lee 10/9/21
import pkg.*;

public class starter implements InputKeyControl {
	
	public static int[][] boxes = new int[4][4]; // 0 indicates no num, only same numbers can add.
	public static Text[][] displayBoxes = new Text[4][4];
	public static int amountOfNums = 0, score = 0;

	public static void main(String args[]) {
		KeyController kC = new KeyController(Canvas.getInstance(),new starter());

		for(int r = 0; r < displayBoxes.length; r++){ // initializes text
			for(int c = 0; c < displayBoxes[0].length; c++){
				displayBoxes[r][c] = new Text(170*r,170*c,"");
				displayBoxes[r][c].grow(30,40);
				displayBoxes[r][c].draw();
			}
		}

		newGame();
		printBoxes();
		
	}

	public static void newGame(){
		amountOfNums = 0;
		for(int rows = 0; rows < boxes.length; rows++){
			for(int columns = 0; columns < boxes[0].length; columns++){
				if(amountOfNums < 2){
					boxes[(int)(Math.random()*4)][(int)(Math.random()*4)] = 2; // beginning nums start on random position
					amountOfNums++;
				}
				else if(boxes[rows][columns] != 2)
					boxes[rows][columns] = 0;
				displayBoxes[rows][columns].setText(boxes[rows][columns]+"");
			}
		}
	}
	
	public static void printBoxes(){
		for(int rows = 0; rows < boxes.length; rows++){
			for(int columns = 0; columns < boxes[0].length; columns++)
				System.out.print(boxes[rows][columns] + " ");
			System.out.println();
		}
		System.out.println();
	}

	public static void addNums(){
		int numsAdded = 0;
		for(int rows = 0; rows < boxes.length; rows++){
			for(int columns = 0; columns < boxes[0].length; columns++){
				int randRow = (int)(Math.random()*4), randCol = (int)(Math.random()*4);
				if((numsAdded == 0) && (boxes[randRow][randCol] == 0)){
					boxes[randRow][randCol] = 2; // new num on random position
					numsAdded++;
					break;
				}
			}
			if(numsAdded > 0) // makes loop more efficient
				break;
		}
	}

	// w(up), a(left), s(down), d(right)
	public void keyPress(String s) {

		int upOrDown = 0, leftOrRight = 0;
		boolean limit = false;
		for(int rows = 0; rows < boxes.length; rows++){
			//r = rows;
			if(s.equals("w")){
				upOrDown = -1;
				limit = (rows > 0);
			}
			else if(s.equals("s")){
				upOrDown = 1;
				limit = (rows < boxes.length-1);
			}
			for(int columns = 0; columns < boxes[0].length; columns++){
				//c = columns;
				if(s.equals("a")){
					leftOrRight = -1;
					limit = (columns > 0);
				}
				else if(s.equals("d")){
					leftOrRight = 1;
					limit = (columns < boxes[0].length-1);
				}
				if(limit && (boxes[rows+upOrDown][columns+leftOrRight] == boxes[rows][columns])){ // if 
					boxes[rows+upOrDown][columns+leftOrRight] *= 2;
					boxes[rows][columns] = 0;
				}
				else if(limit && (boxes[rows+upOrDown][columns+leftOrRight] == 0)){
					boxes[rows+upOrDown][columns+leftOrRight] = boxes[rows][columns];
					boxes[rows][columns] = 0;
				}
				displayBoxes[rows][columns].setText(boxes[rows][columns]+"");
			}
		}
		addNums();
		printBoxes();
		
		// Or write each function individually

	}
}