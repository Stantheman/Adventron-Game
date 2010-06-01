import java.awt.Point;
import java.util.ArrayList;

public class Monster
{
	private Point position;
	private Point direction;
	private ArrayList<Bullet> bullets;
	
	public Monster()
	{
		position = new Point();
		direction = new Point();
		bullets = new ArrayList<Bullet>();
		
		position.setLocation((Math.random()*100),(Math.random()*100));
		direction.setLocation(0,1);
		
	}
	
	public Point getPosition()
	{
		return position;
	}
	
	public void changePosition(int xRight, int yDown)
	{
		position.x+=xRight;
		position.y+=yDown;
	}
	
	public ArrayList<Bullet> getBullets()
	{
		return bullets;
	}
	
	public Point getDirection()
	{
		return direction;
	}
	
	public void setDirection(Point d)
	{
		direction=d;
	}
}