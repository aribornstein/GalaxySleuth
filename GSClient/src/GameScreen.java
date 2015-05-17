

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;


/**
 * Class to build Game Screen
 *
 */
public class GameScreen extends JPanel implements ActionListener {
	private static final long serialVersionUID =  1L;
	public CController controller;
	public String username;
	public String character;
	public JPanel westPanel;
	public JPanel southPanel;
	public NotebookWidget notePanel;
	public CardDisplay cardPanel;
	public BoardWidget boardPanel;
	public ChatWidget chatPanel;

	public URL theURL;

	public JButton makeAccusationButton;
	public JButton makeSuggestionButton;
	public JButton endTurnButton;
	boolean activated = false;
	
	
	


	

	public GameScreen(CController controller, URL aURL) {


		//initialize as JFrame with appropriate title, size, layout
		super();
		
		
		this.controller = controller;
		//assign the URL sent in from GSClient to the theURL which will be passed into the board widget
		//so that we can getCodeBase for the board images
		this.theURL = aURL;
		
		//this.setName(this.username + "'s Galaxy Sleuth Game");
		//Dimension dim = new Dimension(3000,900);
		//this.setPreferredSize(dim);
		this.setLayout(new BorderLayout());
		
		
		
		
		//create CardDisplay Panel
		/*Card w_ppc = new Card("weapon","Particle Propulsion Canon","");
		Card w_nc = new Card("weapon","Noisy Cricket","");
		Card w_dis = new Card("weapon","Disruptor","");
		Card[] testing = { w_ppc, w_nc, w_dis};*/
		
		
				
		
		//create Chat Panel
		chatPanel = new ChatWidget(controller);
		chatPanel.setPreferredSize(new Dimension(350,300));
		chatPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//create NotebookPanel
		notePanel = new NotebookWidget();
		notePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//Create boardpanel
		System.out.println("About to create BoardPanel");
		this.controller.findMe().token.print();
		boardPanel = new BoardWidget(theURL, this.controller.findMe().token, controller.myGame);
		//boardPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		Dimension boardDimension = new Dimension(625,651);
		//boardPanel.setPreferredSize(boardDimension);
		
		//create a flexible panel in which to place the boardWidget
		/*JPanel stretchyBoard = new JPanel();
		
	//	stretchyBoard.setPreferredSize(new Dimension(750,650));
		stretchyBoard.setLayout(new BoxLayout(stretchyBoard, BoxLayout.X_AXIS));
		stretchyBoard.add(Box.createGlue());*/
		
		//add board, give it a border
		
		//stretchyBoard.add(boardPanel);
		//boardPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//create panel holding game control buttons and add action listeners
		JPanel controlButtonPanel = new JPanel();
		controlButtonPanel.setLayout(new BoxLayout(controlButtonPanel, BoxLayout.PAGE_AXIS));
		makeSuggestionButton = new JButton("Make Suggestion");
		//makeSuggestionButton.setSize(cbSize);
		makeAccusationButton = new JButton("Make Accusation");
		//makeAccusationButton.setSize(cbSize);
		endTurnButton = new JButton("End Turn");
		//endTurnButton.setSize(cbSize);
		makeSuggestionButton.addActionListener(this);
		makeAccusationButton.addActionListener(this);
		endTurnButton.addActionListener(this);
		//controlButtonPanel.add(Box.createGlue());
		controlButtonPanel.add(makeSuggestionButton);
		//controlButtonPanel.add(Box.createGlue());
		controlButtonPanel.add(makeAccusationButton);
		//controlButtonPanel.add(Box.createGlue());
		controlButtonPanel.add(endTurnButton);
		//controlButtonPanel.add(Box.createGlue());
	//	controlButtonPanel.setPreferredSize(new Dimension(150, 100));
			
		
		//add widgets to GameScreen
		
		//initiate left side panel to hold notebook and cards, give it a border and size
		westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(350, 625));
		
		//add chat panel and button panel
		westPanel.add(chatPanel, BorderLayout.NORTH);
		westPanel.add(controlButtonPanel, BorderLayout.SOUTH);
		System.out.println("West panel created");
		//add bottom panel and give it a border
		//southPanel = new JPanel();
		//southPanel.add(cardPanel);
//>>>>>>> .r132
		
		//southPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		add(westPanel, BorderLayout.WEST);
		add(boardPanel, BorderLayout.EAST);
		add(notePanel, BorderLayout.CENTER);
		//Card panel is added in separate method
		
		
		//create panel containing boardPanel, button panel and westPanel
		/*JPanel allButCards = new JPanel();
		allButCards.setLayout(new BoxLayout(allButCards,BoxLayout.X_AXIS));
		allButCards.add(Box.createGlue());
		allButCards.add(westPanel);
		allButCards.add(Box.createHorizontalStrut(15));
		allButCards.add(Box.createGlue());
		//allButCards.add(stretchyBoard);
		allButCards.add(Box.createHorizontalStrut(15));
		allButCards.add(Box.createGlue());
		allButCards.add(notePanel);
		//notePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		//add top and bottom panels to the GameScreen
		this.add(allButCards);
		this.add(Box.createVerticalStrut(20));
		this.add(southPanel);*/
		//add this to chat container as well
//		southContainer.add(controlButtonPanel, BorderLayout.EAST);
//		this.add(southContainer, BorderLayout.SOUTH);
//		
		//pack window and set visible
		//this.pack();
	
		this.setVisible(true);
		
	}
	
	public void updateChat(String msg) {
		//sends message to Chat Widget to update
		chatPanel.updateChat(msg);
		
	}
	
	//ACTION LISTENERS FOR GAME CONTROL BUTTONS
	public void actionPerformed(ActionEvent event) {
        //if make accusation button was clicked
		
		if(activated){
	        if(event.getSource() == this.makeAccusationButton) {
	        	AccusationDialogue accusationDialogue = new AccusationDialogue();
	        	accusationDialogue.init(this.controller, "Accuse");
	        	
	        	System.out.println("Clicked Make Accusation");
	        }
	        //if make suggestion button was clicked
	        else if(event.getSource() == this.makeSuggestionButton) {
	        	
	        	int x = controller.findMe().token.xCoord;
	        	int y = controller.findMe().token.yCoord;
	        	
	        	if(boardPanel.boardArray[x][y] != "regular" && boardPanel.boardArray[x][y] != "door"){
	        	AccusationDialogue suggestionDialogue = new AccusationDialogue();
	        	suggestionDialogue.init(this.controller, "Suggest");
	        	System.out.println("Clicked Make Suggestion");
	        	}
	        }
	        //if end turn button was clicked
	        else if (event.getSource() == this.endTurnButton) {
	        	System.out.println("Clicked End Turn");
	        	activated = false;
	        	boardPanel.activated = false;
	        	controller.myGame = boardPanel.myGame;
	        	
	        	Envelope e = new Envelope(MsgType.END_TURN,controller.findMe());
	        	controller.sendEnvelope(e);
	        	
	        	//S
	        	
	        }
	}
	}
	
	public void dealCardsToScreen() {
		System.out.println("DealCardsToScreen got called!");
		User thisUser = controller.findMe();
		
		ArrayList<Card> thisHand = thisUser.hand;
		cardPanel = new CardDisplay(thisHand, theURL);
		cardPanel.setPreferredSize(new Dimension(800,200));
		
		add(cardPanel, BorderLayout.SOUTH);
		revalidate();
	}
	
	public void updateSpin(int spin) {
		boardPanel.updateSpin(spin);
	}
	
	public void drawInitialTokens() {
		for (int i = 0; i < controller.myGame.userList.size(); i++) {
			this.boardPanel.drawTokenToSquare(controller.myGame.userList.get(i));
			
		}
	}


}
