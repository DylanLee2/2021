import pkg.*;
import java.util.ArrayList;
public class snake{
	
	private int x, y, wid, hei;
	private ArrayList<Rectangle> apple;
	private ArrayList<Rectangle> body;
	private boolean[] direction = new boolean[4];
	private ArrayList<Integer> speedX, speedY;
	
	public snake(int startX, int startY, int width, int height){
		x = startX;
		y = startY;
		wid = width;
		hei = height;
		body = new ArrayList<Rectangle>();
		body.add(new Rectangle(x, y, width, height));
		color(0);
		apple = new ArrayList<Rectangle>();
		apple.add(new Rectangle((int)(Math.random()*500), (int)(Math.random()*500), wid, hei));
		apple.get(0).setColor(Color.RED);
		apple.get(0).draw();
		speedX = new ArrayList<Integer>();
		speedY = new ArrayList<Integer>();
	}
	
	public void addSpeed(int xSpeed, int ySpeed){
		speedX.add(xSpeed);
		speedY.add(ySpeed);
	}
	
	public void grow(boolean up, boolean down, boolean left, boolean right){
		if(body.get(0).contains(apple.get(apple.size()-1))){
			apple.get(apple.size()-1).translate(600, 0);
			apple.set(apple.size()-1, null);
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
			
			apple.add(new Rectangle((int)(Math.random()*500), (int)(Math.random()*500), wid, hei));
			apple.get(apple.size()-1).setColor(Color.RED);
			apple.get(apple.size()-1).draw();
		}
	}
	
	public void move(int moveX, int moveY){
		for(int i = 0; i < body.size(); i++){
			//body.get(i).translate(moveX, moveY);
			
			if(i == 0)
				body.get(0).translate(moveX, moveY);
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
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
}