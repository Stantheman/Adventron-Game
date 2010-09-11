/*
 * Adventron - Stan Schwertly
 * I made a game I called "adventron" when I was just learning C/C++
 * from a For Dummies book. I'm porting it to Java using what I 
 * know now and what I'm learning. It's still in the early stages from
 * being ported, so there's some work left to be done.
 */
import java.applet.*;
import java.awt.*;
import java.util.ArrayList;

/*
 * Todo list
 * 
 * - investigate slick2d for graphics
 * - java enums for all the public static variables? global class?
 * - bullets kill monsters
 * - monsters shoot and move normal and can kill
 * - replace wall key for walls that are impossible to touch
 * - load levels for new rooms
 */
public class Adventron extends Applet implements Runnable
{	
	private Image dbImage;
	private Graphics dbg;

	private Player player = new Player();
	
	private Monster m1 = new Monster();
	private Monster m2 = new Monster();
	
	private Map map = new Map();
	
	private ArrayList <Bullet> bullets = new ArrayList<Bullet>();
	
	public void init() 
	{ 
		setBackground (Color.black);
	}
	
	public void start ()
	{
		Thread th = new Thread (this);
		th.start ();
		
		map.readLevel("level0.dat", dbg);
	}
	
	public void stop() { }
	
	public void destroy() { }
	
	public void run () 
	{
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
	
		while (true)
		{
			
			// update the player
			player.move(map.getWalls(player.getQuadrant()));
			//update the bullets
			for (int i=0; i<bullets.size(); i++)
			{
				int bulletQuad = bullets.get(i).getQuadrant();
				bullets.get(i).changePosition(map.getWalls(bulletQuad), player, m1);
				if (bullets.get(i).getQuadrant()==Map.OUT_OF_BOUNDS)
				{
					bullets.remove(i);
					i--;
					continue;
				}
			}
			
			//update the (single for now) monster
			m1.changePosition(map.getWalls(m1.getQuadrant()));
			m2.changePosition(map.getWalls(m2.getQuadrant()));
			repaint();
		
			try
			{
				Thread.sleep (20);
			}
			catch (InterruptedException ex)
			{}

			Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		}
	}
	
	public void paint (Graphics g) 
	{ 
		g.drawImage(map.image, 0, 0, null);
		
		g.setColor (Player.COLOR);
		
		g.drawRect(player.getPosition().x, 
				   player.getPosition().y, 
				   Player.WIDTH, 
				   Player.HEIGHT);

		g.drawRect(m1.getPosition().x,
                   m1.getPosition().y, 
                   Monster.WIDTH, 
                   Monster.HEIGHT);
		g.drawRect(m2.getPosition().x,
                m2.getPosition().y, 
                Monster.WIDTH, 
                Monster.HEIGHT);
		
		g.setColor(Bullet.COLOR);
		for (int i=0; i<bullets.size(); i++)
		{
			g.drawRect(bullets.get(i).getPosition().x, 
					   bullets.get(i).getPosition().y, 
					   Bullet.WIDTH, Bullet.HEIGHT);
		}
	}
	
	/** Update - Method, implements double buffering */
	public void update (Graphics g)
	{
		// java reminder: initialize buffer
		if (dbImage == null)
		{
			dbImage = createImage (this.getSize().width, this.getSize().height);
			dbg = dbImage.getGraphics ();
		}
	    
		// clear screen in background
		dbg.setColor (getBackground ());
		dbg.fillRect (0, 0, this.getSize().width, this.getSize().height);
	
		// draw elements in background
		dbg.setColor (getForeground());
		paint (dbg);
	
		// draw image on the screen
		g.drawImage (dbImage, 0, 0, this);
	}
	
	public boolean mouseDown (Event e, int x, int y) 
	{
		return true;
	}
	
	public boolean keyDown (Event e, int key) 
	{
		if (key == Event.LEFT)
		{
			player.setDirection(Player.LEFT);
			player.setFacing(Player.LEFT);
		} 
		else if (key == Event.RIGHT)
		{
			player.setDirection(Player.RIGHT);
			player.setFacing(Player.RIGHT);
		}		
		else if (key == Event.UP)
		{
			player.setDirection(Player.UP);
			player.setFacing(Player.UP);
		}
		else if (key == Event.DOWN)
		{
			player.setDirection(Player.DOWN);
			player.setFacing(Player.DOWN);
		}
		// user presses space bar
		if (key == 32)
		{
			bullets.add(new Bullet(
					player.getPosition().x,
					player.getPosition().y,
					player.getFacing()));
		}

		return true;
	}
	
	public boolean keyUp (Event e, int key) 
	{
		if (key == Event.LEFT)
		{
			player.setDirection(Player.STILL);
		} 
		else if (key == Event.RIGHT)
		{
			player.setDirection(Player.STILL);
		}		
		else if (key == Event.UP)
		{
			player.setDirection(Player.STILL);
		}
		else if (key == Event.DOWN)
		{
			player.setDirection(Player.STILL);
		}

		return true;
	}
}
