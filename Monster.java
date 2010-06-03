import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Monster
{
	
	public static double MONSTER_HEIGHT=4;
	public static double MONSTER_WIDTH=4;
	
	private Point position;
	private Point direction;
	private ArrayList<Bullet> bullets;
	private Rectangle2D.Double box;
	
	public Monster()
	{
		position = new Point();
		direction = new Point();
		bullets = new ArrayList<Bullet>();
		position.setLocation((Math.random()*100),(Math.random()*100));
		direction.setLocation(0,1);
		
		box = new Rectangle2D.Double(position.x, position.y, MONSTER_HEIGHT, MONSTER_WIDTH);
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