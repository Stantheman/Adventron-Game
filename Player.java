import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Player
{	
	private static final int SPEED = 5;
	
	//directions
	public static final Point UP = new Point(0,-SPEED);
	public static final Point DOWN =  new Point(0,SPEED);
	public static final Point LEFT = new Point(-SPEED,0);
	public static final Point RIGHT =  new Point(SPEED,0);
	public static final Point STILL = new Point(0,0);
	
	public static final Color COLOR = Color.white;
	
	public static final int HEIGHT =8;
	public static final int WIDTH=5;
	
	private Point position;
	private ArrayList<Bullet> bullets; 
	private Point direction;
	private Point facing;
	private int quadrant;
	
	public Player()
	{	
		position = new Point();
		bullets = new ArrayList<Bullet>();
		direction = STILL;
		
		// Facing determines the bullet's direction. Needs a default position for level loading
		facing = Player.UP; 
		position.setLocation(100, 100);
		quadrant = Map.TOP_LEFT;
	}
	
	public Point getPosition()
	{
		return position;
	}
	
	public void changePosition(Point direction)
	{
		position.x+=direction.x;
		position.y+=direction.y;
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
	
	/**
	 * @return the facing
	 */
	public Point getFacing() {
		return facing;
	}

	/**
	 * @param facing the facing to set
	 */
	public void setFacing(Point facing) {
		this.facing = facing;
	}

	/**
	 * @return the quadrant
	 */
	public int getQuadrant() {
		return quadrant;
	}

	/**
	 * @param quadrant the quadrant to set
	 */
	public void setQuadrant(int quadrant) {
		this.quadrant = quadrant;
	}

	public void move(ArrayList <Rectangle>walls)
	{
		Rectangle temp = new Rectangle(position.x+direction.x,
				position.y+direction.y,
				WIDTH,
				HEIGHT);
		for (int i=0; i<walls.size(); i++)
		{
			if (temp.intersects(walls.get(i)))
				return;
		}
		changePosition(direction);
		determineQuadrant();
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