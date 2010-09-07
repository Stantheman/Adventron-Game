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
		 try
		 {
		    // Open the file that is the first 
		    // command line parameter
		    FileInputStream fstream = new FileInputStream(filename);
		    // Get the object of DataInputStream
		    DataInputStream in = new DataInputStream(fstream);
		    BufferedReader br = new BufferedReader(new InputStreamReader(in));
		    
		    String strLine;
		    strLine=br.readLine();
		   
		    int a =0;
		    
		    //Read File Line By Line
		    while ((strLine = br.readLine()) != null)   
		    {
		      // Print the content on the console
		      rows[a] = strLine;
		      //System.out.println (rows[a]);
		      
		      a++;
		   
		    }
		    //Close the input stream
		    in.close();
		 }
		 catch (Exception e)
		 {//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		      System.exit(0);
		 }
		 
		//Level is read. process it.
		int spaceBelowScreen = 20;
		
		for (int i=0; i <rows.length; i++)
		{
			for (int j=0; j<rows[i].length(); j++)
			{
				if (rows[i].charAt(j)=='#')
				{
					walls.add(new Rectangle((j*10), spaceBelowScreen, 10, 10));
				}
			}
			spaceBelowScreen+=10;
		}
		
		//create the background image
		g = image.createGraphics();
		
		//maybe make this part of the code above?
		// MAKE THIS PART OF THE LOOP ABOVE, create g ahead of time and do it there.
	
		g.setColor(Color.yellow);
		spaceBelowScreen = 20;
		for (int i=0; i<walls.size(); i++)
		{
			g.drawRect(walls.get(i).x, walls.get(i).y, walls.get(i).width, walls.get(i).height);
		}
	}

}