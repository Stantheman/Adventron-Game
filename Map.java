import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Map
{
	public static final int MAP_WIDTH = 800;
    public static final int MAP_HEIGHT = 400;
    
    public static final int TOP_LEFT = 1;
    public static final int TOP_RIGHT =2;
    public static final int BOTTOM_LEFT = 3;
    public static final int BOTTOM_RIGHT = 4;
    
	public String[] rows;
	public BufferedImage image;
	private boolean mapHasBeenMade;
	char[][] mapData; //the old dos box was this size?
	private ArrayList<Rectangle> walls;
	
	public Map()
	{
		rows = new String[25];
		image = new BufferedImage(MAP_WIDTH,MAP_HEIGHT, BufferedImage.TYPE_INT_RGB);
		mapHasBeenMade=false;
		mapData = new char[80][80];
		walls = new ArrayList<Rectangle>();
	}
	
	public ArrayList<Rectangle> getWalls()
	{
		return walls;
	}
	
	public void readLevel(String filename, Graphics g)
	{
		// Read in the level
		try
		{
			FileInputStream fstream = new FileInputStream(filename);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			int index = 0;
			
			while ((strLine = br.readLine()) != null)   
			{
				rows[index] = strLine;
				index++;
			}
			
			in.close();
		}
		
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
			System.exit(0);
		}
		// Level is read. process it.
		
		// java reminder for me. This creates the background image
		g = image.createGraphics();
		g.setColor(Color.yellow);  // wall color. static variable?
		
		// Spacer for walls. Increases as lines of wall continue
		int spaceBelowScreen = 20;
		int lastWall = 0;
		
		for (int i=0; i <rows.length; i++)
		{
			for (int j=0; j<rows[i].length(); j++)
			{
				if (rows[i].charAt(j)=='#')
				{
					//places the wall in the right spot on the map
					walls.add(new Rectangle((j*10), spaceBelowScreen, 10, 10));
					g.drawRect(
							walls.get(lastWall).x, 
							walls.get(lastWall).y, 
							walls.get(lastWall).width, 
							walls.get(lastWall).height);
					lastWall++;
				}
			}
			spaceBelowScreen+=10;
		}
	}
}