import java.awt.Point;
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
	
	private Point position;
	private ArrayList<Bullet> bullets; 
	private Point direction;
	
	public Player()
	{	
		position = new Point();
		bullets = new ArrayList<Bullet>();
		direction = Player.BULLET_UP;
		
		position.setLocation(100, 100);
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
}