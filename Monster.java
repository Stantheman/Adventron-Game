import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Monster
{	
	public static int HEIGHT=4;
	public static int WIDTH=4;
	
	private Point position;
	private Point direction;;
	private int quadrant;
	private Rectangle box;
	private boolean hit;
	
	public Monster()
	{
		position = new Point();
		direction = new Point();
		position.setLocation(1,1);
		direction.setLocation(1,1);
		box = new Rectangle(position.x, position.y, WIDTH, HEIGHT);
		determineQuadrant();
		hit = false;
	}
	
	public Point getPosition()
	{
		return position;
	}
	
	public void changePosition(ArrayList <Rectangle>walls, ArrayList <Bullet> bullets)
	{
		if (Math.random()<.5) direction.y*=-1;
		
		// keep him on the screen
		if ( ((position.x+direction.x)>Map.WIDTH) || ((position.x+direction.x)<0))
			direction.x*=-1;
		else if ( ((position.y+direction.y)>Map.HEIGHT) || ( (position.y+direction.y)<0))
			direction.y*=-1;
		
		Rectangle temp = new Rectangle(
				position.x + direction.x, 
				position.y + direction.y,
				HEIGHT,WIDTH); 
		
		for (int i=0; i<walls.size(); i++)
		{
			if (temp.intersects(walls.get(i)))
			{
				direction.setLocation(direction.x*-1, direction.y*-1);
				break;
			}
		}
		
		position.x+=direction.x;
		position.y+=direction.y;
		box.setRect(position.x, position.y, WIDTH, HEIGHT);
		determineQuadrant();		
		if (Math.random()<.1)
			bullets.add(new Bullet(position.x, position.y+HEIGHT+1, Player.DOWN));
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

	/**
	 * @return the box
	 */
	public Rectangle getBox() {
		return box;
	}

	/**
	 * @param box the box to set
	 */
	public void setBox(Rectangle box) {
		this.box = box;
	}

	/**
	 * @return the hit
	 */
	public boolean isHit() {
		return hit;
	}

	/**
	 * @param hit the hit to set
	 */
	public void setHit(boolean hit) {
		this.hit = hit;
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