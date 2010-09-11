import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Bullet
{
	private static final int SPEED = 2;
	public static final Point UP = new Point(0,-SPEED);
	public static final Point DOWN =  new Point(0,SPEED);
	public static final Point LEFT = new Point(-SPEED,0);
	public static final Point RIGHT =  new Point(SPEED,0);
	
	public static final int WIDTH = 1;
	public static final int HEIGHT = 1;
	
	public static final Color COLOR = Color.red;
	
	private Point position;
	private Point direction;
	private int quadrant;
	
	public Bullet()
	{
		position = new Point();
		direction = new Point();
		determineQuadrant();
	}
	
	public Bullet(int x, int y)
	{
		position = new Point(x,y);
		direction = UP;
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
	
	public void changePosition(ArrayList <Rectangle>walls, Player player, Monster monster)
	{
		Rectangle newPosition = new Rectangle(
				position.x + direction.x, 
				position.y + direction.y,
				WIDTH, HEIGHT); 
	
		if (newPosition.intersects(player.getBox()))
			System.out.println("OUCH WTF THE PLAYER IS HIT");
		
		if (newPosition.intersects(monster.getBox()))
			System.out.println("TAKE THAT MONSTER!!");
		
		for (int i=0; i<walls.size(); i++)
		{
			if (newPosition.intersects(walls.get(i)))
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
		if (    (position.x > Map.WIDTH) ||
				(position.x < 0) ||
				(position.y > Map.HEIGHT) ||
				(position.y < 0) )
		{
			quadrant = Map.OUT_OF_BOUNDS;
			return;
		}
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