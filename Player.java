import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Player
{
	private static final int BULLET_SPEED = 2;
	private static final int PLAYER_SPEED = 5;
	
	//enums?
	public static final Point BULLET_UP = new Point(0,-BULLET_SPEED);
	public static final Point BULLET_DOWN =  new Point(0,BULLET_SPEED);
	public static final Point BULLET_LEFT = new Point(-BULLET_SPEED,0);
	public static final Point BULLET_RIGHT =  new Point(BULLET_SPEED,0);
	
	public static final Point PLAYER_UP = new Point(0,-PLAYER_SPEED);
	public static final Point PLAYER_DOWN =  new Point(0,PLAYER_SPEED);
	public static final Point PLAYER_LEFT = new Point(-PLAYER_SPEED,0);
	public static final Point PLAYER_RIGHT =  new Point(PLAYER_SPEED,0);
	
	public static final int PLAYER_HEIGHT =8;
	public static final int PLAYER_WIDTH=5;
	
	private Point position;
	private ArrayList<Bullet> bullets; 
	private Point direction;
	private Rectangle box;
	
	public Player()
	{	
		position = new Point();
		bullets = new ArrayList<Bullet>();
		direction = Player.BULLET_UP;
		position.setLocation(100, 100);
		
		box = new Rectangle(position.x, position.y, PLAYER_WIDTH, PLAYER_HEIGHT);
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
	
	public void moveLeft(Map map)
	{
		Rectangle temp = new Rectangle(position.x-PLAYER_SPEED,position.y,PLAYER_WIDTH,PLAYER_HEIGHT);
		for (int i=0; i<map.getWalls().size(); i++)
		{
			if (temp.intersects(map.getWalls().get(i)))
				return;
		}
		changePosition(new Point(-5,0));
		setDirection(Player.BULLET_LEFT);
	}
	
	public void moveRight(Map map)
	{
		Rectangle temp = new Rectangle(position.x+PLAYER_SPEED,position.y,PLAYER_WIDTH,PLAYER_HEIGHT);
		for (int i=0; i<map.getWalls().size(); i++)
		{
			if (temp.intersects(map.getWalls().get(i)))
				return;
		}
		changePosition(new Point(5,0));
		setDirection(Player.BULLET_RIGHT);
	}
	
	public void moveUp(Map map)
	{
		Rectangle temp = new Rectangle(position.x, position.y-PLAYER_SPEED,PLAYER_WIDTH,PLAYER_HEIGHT);
		for (int i=0; i<map.getWalls().size(); i++)
		{
			if (temp.intersects(map.getWalls().get(i)))
				return;
		}
		changePosition(new Point(0,-5));
		setDirection(Player.BULLET_UP);
	}
	
	public void moveDown(Map map)
	{
		Rectangle temp = new Rectangle(position.x,position.y+PLAYER_SPEED,PLAYER_WIDTH,PLAYER_HEIGHT);
		for (int i=0; i<map.getWalls().size(); i++)
		{
			if (temp.intersects(map.getWalls().get(i)))
				return;
		}
		changePosition(new Point(0,5));
		setDirection(Player.BULLET_DOWN);
	}
	
}