import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.net.*;

/**
 * The "Galaxy Sleuth Client." In reality, a simple, primitive chat client
 * applet.
 * 
 * Very little logic is provided here, other than the in-lined ActionListeners
 * needed by the two buttons. For the most part, the applet's code is simply the
 * "pretty face" of the client's UI. The real work is done by the controller.
 * The UI elements are all public so that the controller can manipulate them
 * directly.
 * 
 * @author Thomas Kelliher kelliher@goucher.edu
 * @version 2010.1018
 * 
 * #######TODO#######
 * What we need to add here is all of our various widgets: BoardWidget, NotebookWidget,
 * CardDisplayWidget. Also we need to change the functionality of the existing login/logout
 * so that it extracts more than just an arbitrary username from users when they login. 
 * 
 */
public class GSClient extends JApplet {

    private static final long serialVersionUID = -9086945173414747143L;
    /**
     * The applet's real control logic. Maintains the applet's state and its
     * connection to the server.
     */
    private CController controller;
    /**
     * The base URL from whence the class file was retrieved. "Magically," the
     * URL is correct not only when the applet is served from a live web server,
     * but also when the applet is run by Eclipse in an Applet Viewer.
     */
    private URL codeBase;
    /**
     * The server name from whence the class file was retrieved.
     */
    private String serverName;
    /**
     * The username used to identify messages from this client. Queried for
     * during the login process.
     */
    public String userName;
    /**
     * Used to determine if this instance of the Applet should be allowed to Create Game 
     * or Join Game.
     */
    public Boolean firstPlayer;
    
    
    /**
     * Main visual panels
     */
    private JPanel openingScreen;
    private StartGameDialogue startGameDialogue;
    private LoadingScreen loadingScreen;
    public GameScreen gameScreen;
    
    /**
     * UI visual indication of login/logoff status. The logInOut button
     * implicitly shows the same information, so this is redundant. Oh well...
     */
    public JLabel status;
    /**
     * Used to initiate the login/logout process with the server. Logging in
     * will result in a dialog box which sets up game preferences, depending on whether
     * player is first or not.
     */
    public JButton logInOut;
    
    /**
     * The button used to send chat messages. It would be nice if this button
     * were disabled when the client is logged out. Perhaps in the next
     * version...
     */
    public JButton send;
    /**
     * The user constructs their chat message in this JTextArea.
     */
    public JTextArea myMesg;
    /**
     * Received chat messages are displayed here. The newest chat message is
     * displayed at the top. Older messages follow. receivedMesgs is embedded in
     * scrollPane to provide scrolling text messages.
     */
    public JTextArea receivedMesgs;
    public JScrollPane scrollPane;

    @Override
    public void init() {

    System.out.println("Applet Initialized");
    this.firstPlayer = false;
    codeBase = getCodeBase();
    serverName = codeBase.getHost();
    controller = new CController(this, serverName);
    System.out.println("Controller Initialized");

    
    //Set size and layout of applet window
    resize(250,100);
    setLayout(new BorderLayout());
    
    //create opening screen panel
    openingScreen = new JPanel(new BorderLayout());   
    openingScreen.setPreferredSize(new Dimension(250, 100));
    add(openingScreen, BorderLayout.CENTER);


	/*status = new JLabel("Let's Play Galaxy Sleuth!",JLabel.CENTER);
	add(status,BorderLayout.NORTH);*/
	
	/**
	 * Note that the ActionListener for this button is the anonymous inner
	 * class directly following "new ActionListener()."
	 */

    status = new JLabel("Let's Play Galaxy Sleuth!",SwingConstants.CENTER);
    openingScreen.add(status,BorderLayout.NORTH);
    
    /**
     * Note that the ActionListener for this button is the anonymous inner
     * class directly following "new ActionListener()."
     */


    
    System.out.println("firstPlayer: " + this.firstPlayer);
    
    
    logInOut = new JButton();
    logInOut.setPreferredSize(new Dimension(50, 20));
        
    if(this.firstPlayer){
        logInOut.setText("Create Game");
        
    } else{
        logInOut.setText("Join Game");
    
            
    }
    
    openingScreen.add(logInOut, BorderLayout.CENTER);
    openingScreen.setBorder(BorderFactory.createLineBorder(Color.black));
    
    
    
    logInOut.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        String s = logInOut.getText();
        setStartGameDialogue(s);
        }
    });
    
    
    
        
//    /**
//     * Again, we're using an anonymous inner class to implement the
//     * ActionListener. We'll blindly send a sendMesg message to the
//     * controller, expecting it to figure out whether we're currently
//     * capable of sending a message or not.
//     */
//    send = new JButton("Send");
//    send.addActionListener(new ActionListener() {
//        public void actionPerformed(ActionEvent e) {
//        try {
//            controller.sendMesg(myMesg.getText());
//        } catch (IOException ev) {
//            System.out.println(ev.getMessage()
//                + ": In send button handler.");
//        }
//        }
//    });
//    add(send);
//
//    /**
//     * The default text in myMesg and receivedMesgs is just there to help me
//     * remember which JTextArea is which. Oh, the travails of getting old...
//     */
//    myMesg = new JTextArea("Input here.", 5, 20);
//    myMesg.setEditable(true);
//    myMesg.setLineWrap(true);
//    myMesg.setWrapStyleWord(true);
//    add(myMesg);
//
//    receivedMesgs = new JTextArea("Output here.", 10, 20);
//    receivedMesgs.setEditable(false);
//    receivedMesgs.setLineWrap(true);
//    receivedMesgs.setWrapStyleWord(true);
//    scrollPane = new JScrollPane(receivedMesgs);
//    add(scrollPane);
    }
    
    //method to remove opening screen elements and offer Start Game Dialogue
    public void setStartGameDialogue(String s) {
        startGameDialogue = new StartGameDialogue();
        startGameDialogue.init(controller,s, controller.remainingCharacters, this);
        openingScreen.setVisible(false);
        resize(500, 200);
        this.add(startGameDialogue);
    }
    
    public void setWaitScreen() {
    	loadingScreen = new LoadingScreen();
    	loadingScreen.init(this);
    	startGameDialogue.setVisible(false);
    	resize(1200,825);
    	this.add(loadingScreen);    
    }
    
    public void setGameScreen() {

    	URL theURL = getCodeBase();
    	gameScreen = new GameScreen(controller, theURL);

    	System.out.println("game screen initialized");

    	loadingScreen.setVisible(false);
    	this.add(gameScreen);
    	
    }
    
    public void dealCardsToScreen() {
    	//calls equivalent method in GameScreen
    	gameScreen.dealCardsToScreen();
    	
    }
    
    public void updateChat(String msg) {
    	//sends string to Game Screen which wil pass it to Chat Widget
    	gameScreen.updateChat(msg);
    }
    
    public void GUIActivated(boolean bool){
		gameScreen.activated = bool;
		gameScreen.boardPanel.activated = bool;
    	//If this is set to false GameScreen buttons will be deactivated and vice versa

    }
    
    public void updateSpin(int spin){
    	gameScreen.boardPanel.spin = spin;
    	//Call the make GUI refresh the number on the spinner
    	gameScreen.updateSpin(spin);
    }
    
    public void updateBoard(User updatedUser){
    	gameScreen.boardPanel.drawUpdatedToken(updatedUser);
    }
    
    public void drawInitialTokens() {
    	gameScreen.drawInitialTokens();
    }
    
    public void removeToken(Token token){
    	int x = token.xCoord;
    	int y = token.yCoord;
    	
    	gameScreen.boardPanel.removeToken(x,y);
    	


    	
    }
    
    

}