/*
 * Adventron - Stan Schwertly
 * I made a game I called "adventron" when I was just learning C/C++
 * from a For Dummies book. I'm porting it to Java using what I 
 * know now and what I'm learning. It's still in the early stages from
 * being ported, so there's some work left to be done.
 */
import java.applet.*;
import java.awt.*;

/*
 * Todo list
 * 
 * - investigate slick2d for graphics
 * - flip bits for movement  rather than +=movement (less clunky)
 * - java enums for all the public static variables? global class?
 * - bullets kill monsters
 * - monsters shoot and move normal and can kill
 * - replace wall key for walls that are impossible to touch
 * - profiling shows that collision detection is the worst part
 */
public class Adventron extends Applet implements Runnable
{	
	private Image dbImage;
	private Graphics dbg;

	private Player player = new Player();
	private Monster m1 = new Monster();
	private Monster m2 = new Monster();
	private Map map = new Map();
	
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
			player.move(map);
			//update the bullets
			for (int i=0; i<player.getBullets().size(); i++)
			{
				int bulletQuad = player.getBullets().get(i).getQuadrant();
				player.getBullets().get(i).changePosition(map.getWalls(bulletQuad));
				if (player.getBullets().get(i).getQuadrant()==Map.OUT_OF_BOUNDS)
				{
					player.getBullets().remove(i);
					i--;
					continue;
				}
			}
			
			//update the (single for now) monster
			m1.changePosition(map);
			m2.changePosition(map);
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
		
		//player color. static?
		g.setColor (Color.white);
		
		g.drawRect(player.getPosition().x, 
				   player.getPosition().y, 
				   Player.PLAYER_WIDTH, 
				   Player.PLAYER_HEIGHT);

		g.drawRect(m1.getPosition().x,
                   m1.getPosition().y, 
                   Monster.MONSTER_WIDTH, 
                   Monster.MONSTER_HEIGHT);
		g.drawRect(m2.getPosition().x,
                m2.getPosition().y, 
                Monster.MONSTER_WIDTH, 
                Monster.MONSTER_HEIGHT);
		
		//bullet color. static?
		g.setColor(Color.red);
		for (int i=0; i<player.getBullets().size(); i++)
		{
			// bullet size should be static?
			g.drawRect(player.getBullets().get(i).getPosition().x, 
					   player.getBullets().get(i).getPosition().y, 
					   1, 1);
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
			player.setDirection(Player.PLAYER_LEFT);
			player.setFacing(Player.PLAYER_LEFT);
		} 
		else if (key == Event.RIGHT)
		{
			player.setDirection(Player.PLAYER_RIGHT);
			player.setFacing(Player.PLAYER_RIGHT);
		}		
		else if (key == Event.UP)
		{
			player.setDirection(Player.PLAYER_UP);
			player.setFacing(Player.PLAYER_UP);
		}
		else if (key == Event.DOWN)
		{
			player.setDirection(Player.PLAYER_DOWN);
			player.setFacing(Player.PLAYER_DOWN);
		}
		// user presses space bar
		if (key == 32)
		{
			player.getBullets().add(new Bullet(
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
			player.setDirection(Player.PLAYER_STILL);
		} 
		else if (key == Event.RIGHT)
		{
			player.setDirection(Player.PLAYER_STILL);
		}		
		else if (key == Event.UP)
		{
			player.setDirection(Player.PLAYER_STILL);
		}
		else if (key == Event.DOWN)
		{
			player.setDirection(Player.PLAYER_STILL);
		}

		return true;
	}
}
