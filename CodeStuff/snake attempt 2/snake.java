import pkg.*;
import java.util.ArrayList;
public class snake{
	
	public int x, y, wid, hei;
	private Rectangle apple, leaf;
	public ArrayList<Rectangle> body;
	public ArrayList<Integer> xSpeed = new ArrayList<Integer>();
	public ArrayList<Integer> ySpeed = new ArrayList<Integer>();
	
	public snake(int startX, int startY, int width, int height){
		x = startX;
		y = startY;
		wid = width;
		hei = height;
		body = new ArrayList<Rectangle>();
		body.add(new Rectangle(x, y, width, height));
		color(0);
		apple = new Rectangle((int)(Math.random()*15)*30, (int)(Math.random()*15)*30, wid, hei);
		apple.setColor(Color.RED);
		apple.fill();
		
		leaf = new Rectangle(apple.getX()+10, apple.getY(), 10, 10);
		leaf.setColor(Color.GREEN);
		leaf.fill();
	}
	
	public void grow(boolean up, boolean left, boolean down, boolean right){
		if(body.get(0).contains(apple)){
			//body.add(new Rectangle(, , wid, hei))
			System.out.println("hit");
			System.out.println("up: " + up);
			System.out.println("left: " + left);
			System.out.println("down: " + down);
			System.out.println("right: " + right);
			if(up == true && down == false){
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
			
			int appleMoveX = (int)(Math.random()*6)*30, appleMoveY = (int)(Math.random()*8)*30;
			if(apple.getX() > 400)
				appleMoveX = (int)(Math.random()*-8)*30;
			else if(apple.getX() < 100)
				appleMoveX = (int)(Math.random()*8)*30;
			else if(apple.getY() > 400)
				appleMoveY = (int)(Math.random()*-8)*30;
			else if(apple.getY() < 100)
				appleMoveY = (int)(Math.random()*-8)*30;
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

	public void addSpeed(int addX, int addY){
		xSpeed.add(addX);
		ySpeed.add(addY);
	}

	public void move(int moveX, int moveY, point p){
		for(int i = 0; i < body.size(); i++){
			//x += moveX;
			//y += moveY;
			if(i==0)
				body.get(0).translate(moveX, moveY);
			else if(body.get(i).getX() == p.x && body.get(i).getY() == p.y)
				body.get(i).translate(xSpeed.get(0), ySpeed.get(0));
			else
				body.get(i).translate(moveX, moveY);
			
		}
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