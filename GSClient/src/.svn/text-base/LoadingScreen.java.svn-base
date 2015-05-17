import javax.swing.*;

import java.awt.*;
/**
 * Class with method initializing loading screen
 * @author Jessica Bonnie
 *
 */
public class LoadingScreen extends JPanel {
	private static final long serialVersionUID = 1L;
	public String username;
	
	//screen needs to be able to communicate with the applet calling it
	public GSClient caller;
	
	public void init(GSClient caller)
	{
		//be able to refer back to caller to call the method which will hide this and show game screen
		
		this.caller = caller;
		JLabel loading = new JLabel("Your game will begin shortly.", JLabel.CENTER);
		loading.setFont(new Font("Serif", Font.BOLD, 36));
		add(Box.createVerticalStrut(500));
		add(loading,BoxLayout.Y_AXIS);
		setVisible(true);
		
		/**Some kind of listener will go here which will then call the line below to call the GSClient method which 
		 * will remove the Loading Screen and display the game screen.
		 */
		//caller.setGameScreen();
		
		
	}
		

		
}




