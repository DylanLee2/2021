import pkg.*;
import java.util.ArrayList;
public class snake{
	
	private int x, y, wid, hei;
	public ArrayList<Rectangle> body;
	
	public snake(int startX, int startY, int width, int height){
		body = new ArrayList<Rectangle>();
		x = startX;
		y = startY;
		wid = width;
		hei = height;
		body.add(new Rectangle(x, y, width, height));
	}
	
	public void grow(String direction){
		if(direction.equals("w"))
			body.add(new Rectangle(body.get(body.size()).getX(), body.get(body.size()).getY() + hei,  wid, hei));
		else if(direction.equals("a"))
			body.add(new Rectangle(body.get(body.size()).getX() + wid, body.get(body.size()).getY(),  wid, hei));
		else if(direction.equals("s"))
			body.add(new Rectangle(body.get(body.size()).getX(), body.get(body.size()).getY() - hei,  wid, hei));
		else if(direction.equals("d"))
			body.add(new Rectangle(body.get(body.size()).getX() - wid, body.get(body.size()).getY(),  wid, hei));
	}
	
	public void move(int moveX, int moveY){
		for(int i = 0; i < body.size(); i++)
			body.get(i).translate(moveX, moveY);
	}
	
	public void color(int i) {
		int r = (int)(Math.random()*255), g = (int)(Math.random()*255), b = (int)(Math.random()*255);
		Color snake_body = new Color(r, g, b);
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