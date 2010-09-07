import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
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
    
    public static final int TOP_LEFT = 0;
    public static final int TOP_RIGHT =1;
    public static final int BOTTOM_LEFT = 2;
    public static final int BOTTOM_RIGHT = 3;
    public static final int OUT_OF_BOUNDS = -1;
    
	public String[] rows;
	public BufferedImage image;
	private boolean mapHasBeenMade;
	char[][] mapData; //the old dos box was this size?
	private ArrayList<Rectangle> walls[];
	
	public Map()
	{
		rows = new String[25];
		image = new BufferedImage(MAP_WIDTH,MAP_HEIGHT, BufferedImage.TYPE_INT_RGB);
		mapHasBeenMade=false;
		mapData = new char[80][80];
		
		walls = new ArrayList[4];
		for (int i=0; i<4; i++)
		{
			walls[i] = new ArrayList<Rectangle>();
		}
	}
	
	public ArrayList<Rectangle> getWalls(int whichWall)
	{
		return walls[whichWall];
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
		int lastWall[] = {0,0,0,0};
		int tempQuad = 0; //Where will the wall be placed?
		
		//this is going to be ugly. loop through and determine
		//which quadrant the wall should be in
		for (int i=0; i <rows.length; i++)
		{
			for (int j=0; j<rows[i].length(); j++)
			{
				if (rows[i].charAt(j)=='#')
				{
					tempQuad = determineQuadrant( (j*10), spaceBelowScreen);
					//places the wall in the right spot on the map
					walls[tempQuad].add(new Rectangle((j*10), spaceBelowScreen, 10, 10));
					g.drawRect(
							walls[tempQuad].get(lastWall[tempQuad]).x, 
							walls[tempQuad].get(lastWall[tempQuad]).y, 
							walls[tempQuad].get(lastWall[tempQuad]).width, 
							walls[tempQuad].get(lastWall[tempQuad]).height);
					lastWall[tempQuad]++;
				}
			}
			spaceBelowScreen+=10;
		}
	}
	
	private int determineQuadrant(int x, int y)
	{
		if (x<MAP_WIDTH/2)
		{
			if (y < MAP_HEIGHT/2)
				return TOP_LEFT;
			else
				return BOTTOM_LEFT;
		}
		else if (y < MAP_HEIGHT/2)
			return TOP_RIGHT;
		else return BOTTOM_RIGHT;
	}
}