import java.applet.*;
import java.awt.*;
import java.io.*;

import javax.imageio.ImageIO;
//GIT!
public class Adventron extends Applet implements Runnable
{	
	private Image dbImage;
	private Graphics dbg;
	private Image pImage;
	
	private Player player = new Player();
	private Monster m1 = new Monster();
	private Map map = new Map();
	
	public void init() 
	{ 
		setBackground (Color.black);
	}
	
	public void start ()
	{
		// define a new thread 
		Thread th = new Thread (this);
		// start this thread
		th.start ();
		map.readLevel("C:\\Users\\stan\\Documents\\Visual Studio Projects\\Adventron\\Level 0.dat");
	}
	public void stop() { }
	
	public void destroy() { }
	
	public void run () 
	{
		// lower ThreadPriority 
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
	
		// run a long while (true) this means in our case "always"
		while (true)
		{
			//update the bullets
			for (int i=0; i<player.getBullets().size(); i++)
			{
				player.getBullets().get(i).changeCoordinate(
						player.getBullets().get(i).getDirection().x, player.getBullets().get(i).getDirection().y);
			}
			
			
			//update the monster. Random for now
			m1.changePosition((int)Math.round(Math.random()), (int)Math.round(Math.random()));
			// repaint the applet
			repaint();
		
			try
			{
			// Stop thread for 20 milliseconds
			Thread.sleep (20);
			}
			catch (InterruptedException ex)
			{
			// do nothing
			}
		
			// set ThreadPriority to maximum value
			Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		}
	}
	
	public void paint (Graphics g) 
	{ 
		// set color
		map.mapLevel(g);
	/*	try{
		pImage = ImageIO.read(new File("C:\\Users\\stan\\Desktop\\player.png"));
		}catch (IOException e){}*/
	
		g.drawImage(map.image, 0, 0, null);
		g.setColor (Color.white);
		//g.drawImage(pImage, player.getPosition().x, player.getPosition().y, null);
		//g.drawI
	    g.drawString("$", player.getPosition().x, player.getPosition().y);

		g.drawString("!!", m1.getPosition().x, m1.getPosition().y);
		for (int i=0; i<player.getBullets().size(); i++)
		{
			g.setColor(Color.red);
			g.drawRect(player.getBullets().get(i).getCoordinate().x, player.getBullets().get(i).getCoordinate().y, 1, 1);
		}
		g.setColor(Color.yellow);
	}
	
	/** Update - Method, implements double buffering */
	public void update (Graphics g)
	{
		// initialize buffer
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
		// Change direction 	
		// DON'T FORGET (although not necessary here)!! 
		return true;
	}
	
	public boolean keyDown (Event e, int key) 
	{
		// user presses left cursor key 
		if (key == Event.LEFT)
		{
			player.changePosition(Player.PLAYER_LEFT);
			player.setDirection(Player.BULLET_LEFT);
		}
		// user presses right cursor key 
		else if (key == Event.RIGHT)
		{
			player.changePosition(Player.PLAYER_RIGHT);
			player.setDirection(Player.BULLET_RIGHT);
		}		
		else if (key == Event.UP)
		{
			player.changePosition(Player.PLAYER_UP);
			player.setDirection(Player.BULLET_UP);
		}
		else if (key == Event.DOWN)
		{
			player.changePosition(Player.PLAYER_DOWN);
			player.setDirection(Player.BULLET_DOWN);
		}
		// user presses space bar (value = 32!)
		if (key == 32)
		{
			player.getBullets().add(new Bullet(player.getPosition().x,player.getPosition().y,player.getDirection()));
		}
		else
		{
		/* Additionally the method prints out the ASCII - value if an other key is pressed. This is not necessary but a possibility for you to test which value a key has.*/ 
		//System.out.println ("Character: " + (char)key + " Integer Value: " + key);
			
		}
	
		// DON'T FORGET (although it has no meaning here) 
		return true;
	}
	
	public void readFile(String filename)
	{
	   try{
		    // Open the file that is the first 
		    // command line parameter
		    FileInputStream fstream = new FileInputStream(filename);
		    // Get the object of DataInputStream
		    DataInputStream in = new DataInputStream(fstream);
		    BufferedReader br = new BufferedReader(new InputStreamReader(in));
		    
		    String strLine;
		    //Read File Line By Line
		    while ((strLine = br.readLine()) != null)   {
		      // Print the content on the console
		      System.out.println (strLine);
		    }
		    //Close the input stream
		    in.close();
		    }catch (Exception e){//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		    }
	}

}