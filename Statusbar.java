/**
 * 
 */

/**
 * @author stanschwertly
 *
 */
public class Statusbar 
{
	private Player player;
	private String status;
	private boolean debug;
	
	public Statusbar()
	{
		player = new Player();
		status = new String();
		debug = false;
		return;
	}
	
	public Statusbar(Player p)
	{
		player = p;
		status = "";
		debug = false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return status; 
	}

	/**
	 * @return the debug
	 */
	public boolean isDebug() {
		return debug;
	}

	/**
	 * @param debug the debug to set
	 */
	public void switchDebug() {
		debug = !debug;
	}
	
	public void updateStatus()
	{
		status = "Score: " + player.getScore() + "\t\tHealth: " + player.getHealth();
		if (debug)
			status+="\t\tQuadrant: " + player.getQuadrant() + "\t\t xPos: " + player.getPosition().x + 
			     "\t\t yPos: " + player.getPosition().y ;
	}
	
}
