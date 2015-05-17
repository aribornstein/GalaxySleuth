/**
 * Instantiates and runs an SController, which is the actual server. It would
 * have been marginally simpler to make this the controller. Of course, this
 * class does decide whether or not to create and run the SController, so it
 * does control the controller. What a conniver!
 * 
 * @author Thomas Kelliher kelliher@goucher.edu
 * @version 2010.1018
 */
public class GSServer {
	
	public int[] input = new int[5];

    public static void main(String argv[]) {
	SController sController = new SController();
	sController.runSController();
    }
}
