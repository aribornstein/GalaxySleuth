import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


import javax.swing.JButton;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;

import javax.swing.SwingConstants;


public class AccusationDialogue extends JFrame implements ActionListener{

	public CController controller;
	
	public String suggestOrAccuse;
	//also needs to be able to talk back to the applet
	public GSClient caller;
	/**
	 * Panel which holds dialog
	 */	
	public JFrame accusationDialogue;
	public JPanel accusationPanel;
	/**
	 * Wrappers for display purposes
	 */
	//holds items displayed in both suggestion and accusation
	public JPanel suggestionWrap;
	
	//inside suggestionWrap; holds suggestion selection items and result
	public JPanel characterPanel;
	public JPanel weaponPanel;
	public JPanel planetPanel;

	
	//holds options for Accusation only
	public JPanel accusationWrap;
	
	/**
	 * Items to be displayed
	 */
	//instruction items
	JLabel instructionLabel;

	//character selection items
	public JLabel characterLabel;
	public JComboBox charList;
	//weapon selection items
	public JLabel weaponLabel;
	public JComboBox weaponList;
	//planet selection items
	public JLabel planetLabel;
	public JComboBox planetList;
	/**
	 * Items shown only to accuser
	 */
	//warning for accusation
	public JLabel optionQuit;
	public JButton quitButton;
	/**
	 * Submit button
	 */
	public JButton submitButton;
	/**
	 * Integer representing which list members are selected
	 */
	
	public void init(CController controller, String s) {
		this.suggestOrAccuse =s;
		this.controller = controller;
		/**
	     * "Accuse" or "Suggest" button has been clicked.
	     * This will display an instruction label, a character selection pane, a weapon selection pane, and
	     * a planet selection pane, no matter what, as well as a warning of the consequences of
	     * an incorrect accusation if s's value was "Accusation".
	     * The "Accuse/Suggest/Submit" button will have an action listener which will submit all values
	     * to the server. 
	     */
		//create JFrame
		accusationDialogue = new JFrame();
		accusationPanel = new JPanel(new BorderLayout());
		this.add(accusationPanel);
				
		//Panels displayed for both Suggest and Accuse
		suggestionWrap = new JPanel (new BorderLayout());
		accusationPanel.add(suggestionWrap, BorderLayout.NORTH);
		characterPanel = new JPanel(new FlowLayout());
		weaponPanel = new JPanel(new FlowLayout());
		planetPanel = new JPanel(new FlowLayout());
		

		//create elements
		JLabel instructionLabel = new JLabel("Select who, how and where!",JLabel.CENTER);
	   
	    characterLabel = new JLabel("Suspect:");
	    	//list of characters
	    String[] charStrings = {"Kara Tiberius","Valen Atreides","B'Elanna Wiggin","Jayne Sheridon","Aeryn Ackbar","Rodney Picard"};
	   	
	    charList = new JComboBox(charStrings);
	    charList.setSelectedIndex(0);
	    
	    weaponLabel = new JLabel("Murder Weapon:");
	    String[] weaponStrings = {"Disruptor","Crysknife","Daemon Hammer","Noisy Cricket","Bat'leth","Particle Projector Cannon"};
	    weaponList = new JComboBox(weaponStrings);
	    weaponList.setSelectedIndex(0);
	    
	    planetLabel = new JLabel("Crime Scene:");
	    String[] planetStrings = {"Caprica","Earth That Was","Ferenginar","Arrakis","Sheldon Alpha 5","Miranda","Perelandra","Sateda","Endor"};
	    planetList = new JComboBox(planetStrings);
	    planetList.setSelectedIndex(0);
	    
	    characterLabel.setPreferredSize(new Dimension(100, 25));
	    weaponLabel.setPreferredSize(new Dimension(100, 25));
	    planetLabel.setPreferredSize(new Dimension(100, 25));
	    //add panels to SuggestionWrap
	    suggestionWrap.add(instructionLabel,BorderLayout.NORTH);
	    suggestionWrap.add(characterPanel,BorderLayout.WEST);
	    suggestionWrap.add(weaponPanel,BorderLayout.CENTER);
	    suggestionWrap.add(planetPanel,BorderLayout.EAST);
	 
	    //add elements to panels

	    characterPanel.add(characterLabel);
	    characterPanel.add(charList);
	    weaponPanel.add(weaponLabel);
	    weaponPanel.add(weaponList);
	    planetPanel.add(planetLabel);
	    planetPanel.add(planetList);
	    
	    //if player is making accusation
		if (suggestOrAccuse.equals("Accuse")) {
		
					
			//create panels and elements
			accusationWrap = new JPanel(new BorderLayout());
			optionQuit = new JLabel("If you make an incorrect accusation, you will forfeit the game. Do you wish to continue?", SwingConstants.CENTER);
			quitButton = new JButton("Nevermind");

			
			//add option panel wrapper
			accusationPanel.add(accusationWrap,BorderLayout.CENTER);
			//add question panel and question
			accusationWrap.add(optionQuit,BorderLayout.NORTH);
			accusationWrap.add(quitButton, BorderLayout.CENTER);
		
			
			//implement actionlisteners for the quit button
			quitButton.addActionListener(this);
		
		
		//if player is making a suggestion, just change the window title
		} else {}

		
		//add submit button
		submitButton = new JButton(s);
		accusationPanel.add(submitButton, BorderLayout.SOUTH);
		submitButton.addActionListener(this);
		
		//Display the window.
 
        accusationDialogue.add(accusationPanel);
        accusationDialogue.pack();
        accusationDialogue.setAlwaysOnTop(true);
        accusationDialogue.setVisible(true);
	}
	
	
	//handler for dialogue events
	public void actionPerformed(ActionEvent event) {
        //if accuser decides not to accuse
        if(event.getSource() == this.quitButton) {
        	this.accusationDialogue.dispose();
        }
  
        //if submit button was clicked
        else if (event.getSource() == this.submitButton) {
        	System.out.println("Submit button pressed");
        	//method to submit data to server.
        	
        	//create string array of selections
        	
        	String character = "Name: "+ this.charList.getSelectedItem().toString()+", Type: character";
        	String weapon = "Name: "+ this.weaponList.getSelectedItem().toString()+", Type: weapon";
        	String planet = "Name: "+ this.planetList.getSelectedItem().toString()+", Type: planet";
        	// if accuse Send server accusation information envelope
        	if (suggestOrAccuse.equals("Accuse"))
        	{	
        		controller.validateAccusation(character, weapon, planet);
        		this.accusationDialogue.dispose();
        	}
        	
        	else
        	{
        		controller.sendSuggestion(character, weapon, planet);
        		this.accusationDialogue.dispose();
        	} 
        	//Unless we are making a suggestion, in which case - Send server suggestion information
        }
        else{
       
        	}
        	
        	
        	// Display the results of the accusation/suggestion and close the pane
        	
    
        	
        }
	
	
}
