import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Bullet
{
	private Point position;
	private Point direction;
	private int quadrant;
	
	public Bullet()
	{
		position = new Point();
		direction = Player.BULLET_UP; //bullet up is default?
		determineQuadrant();
	}
	
	public Bullet(int x, int y)
	{
		position = new Point(x,y);
		direction = Player.BULLET_UP;
		determineQuadrant();
	}
	
	public Bullet(int x, int y, Point d)
	{
		position = new Point(x,y);
		direction = d;
		determineQuadrant();
	}
	
	public Point getPosition()
	{
		return position;
	}
	
	/*
	 * This is the slowest function in the game after profiling.
	 * Need to come up with some better stuff here.
	 */
	public void changePosition(ArrayList <Rectangle>walls)
	{
		// check where it's going
		
		Rectangle temp = new Rectangle(
				position.x + direction.x, 
				position.y + direction.y,
				1,1); 
		for (int i=0; i<walls.size(); i++)
		{
			if (temp.intersects(walls.get(i)))
			{
				quadrant = Map.OUT_OF_BOUNDS;
				return;
			}
		}
		
		position.x+=direction.x;
		position.y+=direction.y;
		determineQuadrant();
	}
	
	public Point getDirection()
	{
		return direction;
	}
	
	public void setDirection(Point d)
	{
		direction=d;
	}
	
	public int getQuadrant()
	{
		return quadrant;
	}
	
	private void determineQuadrant()
	{
		//Is the bullet on the map?
		if (    (position.x > Map.MAP_WIDTH) ||
				(position.x < 0) ||
				(position.y > Map.MAP_HEIGHT) ||
				(position.y < 0) )
		{
			quadrant = Map.OUT_OF_BOUNDS;
			return;
		}
		// Optimize this later by precalculating width/2
		if (position.x<Map.MAP_WIDTH/2)
		{
			if (position.y < Map.MAP_HEIGHT/2)
				quadrant = Map.TOP_LEFT;
			else
				quadrant = Map.BOTTOM_LEFT;
		}
		else if (position.y < Map.MAP_HEIGHT/2)
			quadrant = Map.TOP_RIGHT;
		else quadrant = Map.BOTTOM_RIGHT;
	}
}