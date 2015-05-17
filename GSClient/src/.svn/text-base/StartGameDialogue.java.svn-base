import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.List;	

public class StartGameDialogue extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = -9086945173414747143L;
	//This GUI needs to talk to the controller
	public CController controller;
	//also needs to be able to talk back to the applet
	public GSClient caller;
	/**
	 * Frame which holds dialog
	 */	
	public JPanel createGameDialog;
	/**
	 * Wrappers for display purposes
	 */
	//holds always-displayed items
	public JPanel joinGameWrap;
	//holds Create Game options
	public JPanel optionWrap;
	//inside joinGameWrap; holds username items
	public JPanel usernamePanel;
	//inside joinGameWrap; holds character selection items
	public JPanel charPanel;
	//inside optionWrap; holds items which appear once radio button is selected
	public JPanel optionsPanel;
	/**
	 * Items displayed for both Create and Join
	 */
	//username items
	public JLabel usernameLabel;
	public JTextPane usernamePane;
	//character selection items
	public JLabel characterLabel;
	public JComboBox charList;
	
	/**
	 * Create Game items
	 */
	//question for first player
	public JLabel optionQuestion;
	//radio buttons to select which option to wait for
	public JRadioButton timeRadio;
	public JRadioButton numPlayersRadio;
	public ButtonGroup optionGroup;
	//items which appear once a radio button is selected
	public JTextField optionField;
	//if they wait for a time
	public JLabel timeLabel;
	//if they wait for players
	public JLabel playersLabel;
	/**
	 * Submit button
	 */
	public JButton submitButton;
	/**
	 * Integer representing which radio button is checked
	 */
	
	public void init(CController controller, String s, List<String> characterList, GSClient caller) {
	
		this.controller = controller;
		this.caller = caller;
		/**
	     * "Create Game" or "Join Game" button has been clicked.
	     * This will display a username text input and character selection pane, no matter
	     * what, as well as options for the game setup if s's value was "Create Game".
	     * The "Submit" button will have an action listener which will submit all values
	     * to the server. ((Do we need form validation?))
	     */
		//create JFrame
		createGameDialog = new JPanel(new BorderLayout());
		this.add(createGameDialog);
		//createGameDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		//Panels displayed for both Create and Join
		joinGameWrap = new JPanel (new BorderLayout());
		createGameDialog.add(joinGameWrap, BorderLayout.NORTH);
		usernamePanel = new JPanel(new FlowLayout());
		charPanel = new JPanel(new FlowLayout());
			//create elements
		usernameLabel = new JLabel("Username:");
	    usernamePane = new JTextPane();
	    characterLabel = new JLabel("Character:");
	    	//list of characters (will presumably be replaced with dynamic function to pull chars which remain)
	    String[] charStrings = characterList.toArray(new String[characterList.size()]);
	    
	    charList = new JComboBox(charStrings);
	    charList.setSelectedIndex(0);
	    
	    usernameLabel.setPreferredSize(new Dimension(100, 25));
	    usernamePane.setPreferredSize(new Dimension(175, 25));
	    characterLabel.setPreferredSize(new Dimension(100, 25));
	    
	    //add panels to frame
	    joinGameWrap.add(usernamePanel,BorderLayout.NORTH);
	    joinGameWrap.add(charPanel,BorderLayout.CENTER);
	    //add elements to panels
	    usernamePanel.add(usernameLabel);
	    usernamePanel.add(usernamePane);
	    charPanel.add(characterLabel);
	    charPanel.add(charList);
	    
	    //if player is first, display game options
		if (s.equals("Create Game")) {
			//set window title
			//createGameDialog.setTitle("Create Game");
			
			//create panels and elements
			optionWrap = new JPanel(new BorderLayout());
			optionQuestion = new JLabel("To begin the game, do you want to wait for:", SwingConstants.CENTER);
			timeRadio = new JRadioButton("A certain amount of time?");
			numPlayersRadio = new JRadioButton("A certain number of players?");
			optionGroup = new ButtonGroup();
			optionGroup.add(timeRadio);
			optionGroup.add(numPlayersRadio);
			
			//add option panel wrapper
			createGameDialog.add(optionWrap,BorderLayout.CENTER);
			//add question panel and question
			optionWrap.add(optionQuestion,BorderLayout.NORTH);
			optionWrap.add(timeRadio, BorderLayout.WEST);
			optionWrap.add(numPlayersRadio, BorderLayout.EAST);
			
			//initialize panels for time options and players options
			optionsPanel = new JPanel();
			optionWrap.add(optionsPanel, BorderLayout.SOUTH);
			optionField = new JTextField(4);
			timeLabel = new JLabel("minutes");
			playersLabel = new JLabel("players");
			
			//implement actionlisteners for radio buttons
			timeRadio.addActionListener(this);
			numPlayersRadio.addActionListener(this);
		
		//if player is not first, just change the window title
		} else if(s.equals("Join Game")) {
			//createGameDialog.setTitle("Join Game");
		}
		
		//add submit button
		submitButton = new JButton(s);
		createGameDialog.add(submitButton, BorderLayout.SOUTH);
		submitButton.addActionListener(this);
		
		//Display the window.
        //createGameDialog.pack();
        createGameDialog.setVisible(true);
	}
	
	
	//handler for dialogue events
	public void actionPerformed(ActionEvent event) {
        //if time radio button was clicked
        if(event.getSource() == this.timeRadio) {
        	this.optionsPanel.removeAll();
        	this.optionsPanel.add(this.optionField, BorderLayout.CENTER);
        	this.optionsPanel.add(this.timeLabel, BorderLayout.SOUTH);
        	this.optionsPanel.revalidate();  
        	this.optionsPanel.repaint();
        	//this.createGameDialog.pack();
        }
        //if players radio button was clicked
        else if(event.getSource() == this.numPlayersRadio) {
        	this.optionsPanel.removeAll();
        	this.optionsPanel.add(this.optionField, BorderLayout.CENTER);
        	this.optionsPanel.add(this.playersLabel, BorderLayout.SOUTH);
        	this.optionsPanel.revalidate();  
        	this.optionsPanel.repaint();
        	//this.createGameDialog.pack();
        }
        //if submit button was clicked
        else if (event.getSource() == this.submitButton) {
        	System.out.println("Submit button pressed");
        	//method to submit data to server.
        	
        	//GET ITEMS FROM FORM
        	//USER NAME
        	String submittedUsername = this.usernamePane.getText();
        	//CHARACTER
        	String submittedChar = this.charList.getSelectedItem().toString();
        	//String which will represent which radio button was selected
        	String selectedOption = new String();
        	//Integer which will hold number of players or number of minutes
  
        	if (this.submitButton.getText() == "Create Game") {
        		//Set selectedOption and inputNum
	        	if (this.timeRadio.isSelected()) {
	        		selectedOption = this.optionField.getText() + "minutes";	        		
	        	} else if (this.numPlayersRadio.isSelected()) {
	        		selectedOption = this.optionField.getText() + "players";
	        	}
	        	
	        	//Send server game start condition envelope
	        	Envelope gameCondition = new Envelope(MsgType.START_GAME_CONDITION, selectedOption);        	
	        	controller.sendEnvelope(gameCondition);
	        	
	        	controller.myUserName = submittedUsername;
	        	
	        	//Send server User class envelope;
	        	Token myToken = new Token(submittedChar);
	        	User me = new User(submittedUsername, myToken);        	
	        	Envelope user = new Envelope(MsgType.USER, me );        	
	        	controller.sendEnvelope(user);
	        	
	        	//
	        	
        	} 
        	//If we are at this else it meanst that we are not the first player
        	else{
        		controller.myUserName = submittedUsername;
        		Token myToken = new Token(submittedChar);
	        	User me = new User(submittedUsername, myToken);        	
	        	Envelope user = new Envelope(MsgType.USER, me );        	
	        	controller.sendEnvelope(user);
        		
        	}
        	
        	
        	
        	//SEND ENVELOPES HERE USING ABOVE DATA
        	
        	//Pop up message displaying information
        	/*String message = "Your name is " + submittedUsername + " and your character is " + submittedChar + ".\n";
        	if (inputNum != 0) {
        		message += "You want to wait for " + inputNum + selectedOption + "\n";
        	} else {
        		message += "You were not the first player, so you made no selections.\n";
        	}
        	message += "Eventually, this button will submit a message to the server.";
        	JOptionPane.showMessageDialog(this.createGameDialog, message);
        	this.createGameDialog.dispose();*/
        	
        	caller.setName(controller.myUserName);
        	caller.setWaitScreen();
        	
        }

	}
	
}


