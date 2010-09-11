import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Monster
{	
	public static int HEIGHT=4;
	public static int WIDTH=4;
	
	private Point position;
	private Point direction;
	private ArrayList<Bullet> bullets;
	private int quadrant;
	
	public Monster()
	{
		position = new Point();
		direction = new Point();
		bullets = new ArrayList<Bullet>();
		position.setLocation(1,1);
		direction.setLocation(1,1);
		determineQuadrant();
	}
	
	public Point getPosition()
	{
		return position;
	}
	
	public void changePosition(Map map)
	{
		// keep him on the screen
		if ( ((position.x+direction.x)>Map.WIDTH) || ((position.x+direction.x)<0))
			direction.x*=-1;
		else if ( ((position.y+direction.y)>Map.HEIGHT) || ( (position.y+direction.y)<0))
			direction.y*=-1;
		
		Rectangle temp = new Rectangle(
				position.x + direction.x, 
				position.y + direction.y,
				HEIGHT,WIDTH); 
		
		for (int i=0; i<map.getWalls(quadrant).size(); i++)
		{
			if (temp.intersects(map.getWalls(quadrant).get(i)))
			{
				direction.setLocation(direction.x*-1, direction.y*-1);
				break;
			}
		}
		
		position.x+=direction.x;
		position.y+=direction.y;
		determineQuadrant();
		if (Math.random()<.5) direction.y*=-1;		
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
	
	private void determineQuadrant()
	{
		// Optimize this later by precalculating width/2
		if (position.x<Map.WIDTH/2)
		{
			if (position.y < Map.HEIGHT/2)
				quadrant = Map.TOP_LEFT;
			else
				quadrant = Map.BOTTOM_LEFT;
		}
		else if (position.y < Map.HEIGHT/2)
			quadrant = Map.TOP_RIGHT;
		else quadrant = Map.BOTTOM_RIGHT;
	}
}