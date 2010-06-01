import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Map
{
	public String[] rows;
	public BufferedImage image;
	private boolean mapHasBeenMade;
	
	public Map()
	{
		rows = new String[25];
		image = new BufferedImage(800,400, BufferedImage.TYPE_INT_RGB);
		mapHasBeenMade=false;
	}
	
	public void readLevel(String filename)
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
		    //debug
		    System.out.println(strLine);
		    //
		    int a =0;
		    
		    //Read File Line By Line
		    while ((strLine = br.readLine()) != null)   
		    {
		      // Print the content on the console
		      rows[a] = strLine.replace('0', (char)32 );
		      System.out.println (rows[a]);
		      
		      a++;
		   
		    }
		    //Close the input stream
		    in.close();
		 }
		 catch (Exception e)
		 {//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		 }
	}
	
	public boolean mapLevel(Graphics g)
	{
		if (mapHasBeenMade) return false;
		g = image.createGraphics();
		
		int temp = 20;
		for (int i=0; i <rows.length; i++)
		{
			for (int j=0; j<rows[i].length(); j++)
			{
				if (rows[i].charAt(j)==' ')
				{
					g.clearRect((j*10), temp,5,5);
				}
				else g.drawRect((j*10), temp,5,5);
			}
			temp+=10;
		}
		mapHasBeenMade=true;
		return true;
		
	}
}