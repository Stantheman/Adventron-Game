import java.awt.Point;

public class Bullet
{
	private Point coordinate;
	private Point direction;
	
	public Bullet()
	{
		coordinate = new Point();
		direction = Player.BULLET_UP;
	}
	
	public Bullet(int x, int y)
	{
		coordinate = new Point(x,y);
		direction = Player.BULLET_UP;
	}
	
	public Bullet(int x, int y, Point d)
	{
		coordinate = new Point(x,y);
		direction = d;
	}
	
	public Point getCoordinate()
	{
		return coordinate;
	}
	
	public void changeCoordinate(int xRight, int yDown)
	{
		coordinate.x+=xRight;
		coordinate.y+=yDown;
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