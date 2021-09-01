import pkg.*;
import java.util.ArrayList;
public class snake{
	
	private int x, y, wid, hei;
	private Rectangle apple, leaf;
	public ArrayList<Rectangle> body;
	private ArrayList<Integer> speedX, speedY;
	
	public snake(int startX, int startY, int width, int height){
		x = startX;
		y = startY;
		wid = width;
		hei = height;
		body = new ArrayList<Rectangle>();
		body.add(new Rectangle(x, y, width, height));
		color(0);
		apple = new Rectangle(30*(int)(Math.random()*20), 30*(int)(Math.random()*20), wid, hei);
		apple.setColor(Color.RED);
		apple.fill();
		
		leaf = new Rectangle(apple.getX()+10, apple.getY(), 10, 10);
		leaf.setColor(Color.GREEN);
		leaf.fill();
		
		speedX = new ArrayList<Integer>();
		speedY = new ArrayList<Integer>();
	}
	
	public void addSpeed(int xSpeed, int ySpeed){
		speedX.add(xSpeed);
		speedY.add(ySpeed);
	}
	
	public void grow(boolean up, boolean down, boolean left, boolean right){
		if(body.get(0).contains(apple)){
			System.out.println("hit");
			if(down == false && up == true){
				System.out.println("up");
				body.add(new Rectangle(body.get(body.size()-1).getX(), body.get(body.size()-1).getY() + hei,  wid, hei));
			}
			else if(left == true && right == false){
				System.out.println("left");
				body.add(new Rectangle(body.get(body.size()-1).getX() + wid, body.get(body.size()-1).getY(),  wid, hei));
			}
			else if(down == true && up == false){
				System.out.println("down");
				body.add(new Rectangle(body.get(body.size()-1).getX(), body.get(body.size()-1).getY() - hei,  wid, hei));
			}
			else if(right == true && left == false){
				System.out.println("right");
				body.add(new Rectangle(body.get(body.size()-1).getX() - wid, body.get(body.size()-1).getY(),  wid, hei));
			}
			color(body.size()-1);
			
			int appleMoveX = (int)(30*Math.random()*8), appleMoveY = (int)(30*Math.random()*8);
			if(apple.getX() > 400)
				appleMoveX = (int)(30*Math.random()*-15);
			else if(apple.getX() < 100)
				appleMoveX = (int)(30*Math.random()*15);
			else if(apple.getY() > 400)
				appleMoveY = (int)(30*Math.random()*-15);
			else if(apple.getY() < 100)
				appleMoveY = (int)(30*Math.random()*15);
			else{
				int upOrDown = (int)(Math.random()*1);
				if(upOrDown == 1){
					appleMoveX *= -1;
					appleMoveY *= -1;
				}
			}
			apple.translate(appleMoveX, appleMoveY);
			leaf.translate(appleMoveX, appleMoveY);
			
		}
	}
	
	public void move(int moveX, int moveY){
		for(int i = 0; i < body.size(); i++){
			//body.get(i).translate(moveX, moveY);
			
			if(i == 0)
				body.get(0).translate(moveX, moveY);
			x += moveX;
			y += moveY;
			//else
				//body.get(i).translate(speedX.get(speedX.size()-i), speedY.get(speedY.size()-i));
			
		}
	}
	
	//change position of next box to current box
	public void setBody(Rectangle one, Rectangle two){
		one = new Rectangle(two.getX(), two.getY(), wid, hei);
	}
	
	public void color(int i) {
		Color snake_body = new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
		body.get(i).setColor(snake_body);
		body.get(i).fill();
	}
	
	public void moveChosen(int index, int fredisweird, int y){
		body.get(index).translate(fredisweird, y);
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getWidth(){
		return wid;
	}
	
	public int getHeight(){
		return hei;
	}
	
}