import pkg.*;
import java.util.ArrayList;
public class snake{
	
	private int x, y, wid, hei;
	private Rectangle apple;
	private ArrayList<Rectangle> body;
	
	public snake(int startX, int startY, int width, int height){
		x = startX;
		y = startY;
		wid = width;
		hei = height;
		body = new ArrayList<Rectangle>();
		body.add(new Rectangle(x, y, width, height));
		apple = new Rectangle((int)(Math.random()*500), (int)(Math.random()*500), wid, hei);
		apple.setColor(Color.RED);
		apple.fill();
	}
	
	public void grow(String direction){
		if(body.get(0).contains(apple)){
			System.out.println("hit");
			if(direction.equals("w"))
				body.add(new Rectangle(body.get(body.size()-1).getX(), body.get(body.size()-1).getY() + hei,  wid, hei));
			else if(direction.equals("a"))
				body.add(new Rectangle(body.get(body.size()-1).getX() + wid, body.get(body.size()-1).getY(),  wid, hei));
			else if(direction.equals("s"))
				body.add(new Rectangle(body.get(body.size()-1).getX(), body.get(body.size()-1).getY() - hei,  wid, hei));
			else if(direction.equals("d"))
				body.add(new Rectangle(body.get(body.size()-1).getX() - wid, body.get(body.size()-1).getY(),  wid, hei));
			color(body.size()-1);
			apple = new Rectangle((int)(Math.random()*500), (int)(Math.random()*500), wid, hei);
		}
	}
	
	public void move(int moveX, int moveY){
		for(int i = 0; i < body.size(); i++){
			if(i == 0)
				body.get(0).translate(moveX, moveY);
			else
				setBody(body.get(i), body.get(i-1));
		}
	}
	
	//change position of next box to current box
	public void setBody(Rectangle one, Rectangle two){
		one = new Rectangle(two.getX(), two.getY(), wid, hei);
		color();
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