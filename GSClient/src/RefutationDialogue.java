import java.applet.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import javax.swing.JFrame;


public class RefutationDialogue extends JFrame implements ActionListener{
	public CController controller;
	
	/**
	 * Panel which holds dialog
	 */	
	JFrame refutationDialogue;
	JPanel refutationWrap;
	
	JLabel refutationQuestion;
	
	JPanel radioPanel;
	ButtonGroup refutationRadioGroup;
	JRadioButton characterRadio;
	JRadioButton weaponRadio;
	JRadioButton planetRadio;
	
	JPanel submitPanel;
	JButton refuteButton;
	JButton denialButton;
	
	String refutation;
	boolean isFinished= false;

	
	
	
	public void init(String[] suggestion) {
	
	//Create JFrame
		refutationDialogue = new JFrame();
	//Create JPanel and add to JFrame
		refutationWrap = new JPanel(new BorderLayout());
		this.add(refutationWrap);
	
	//Create Question
		refutationQuestion = new JLabel("Can you refute the suggestion? If so, select your evidence.");
	//Create Radio Button Panel
		radioPanel = new JPanel(new BorderLayout());
	//Create Radio Buttons and add them to the panel
		characterRadio = new JRadioButton(suggestion[0]);
		characterRadio.setActionCommand(suggestion[0]);

		radioPanel.add(characterRadio, BorderLayout.NORTH);
		weaponRadio = new JRadioButton(suggestion[1]);
		weaponRadio.setActionCommand(suggestion[1]);
		radioPanel.add(weaponRadio, BorderLayout.CENTER);
		planetRadio = new JRadioButton(suggestion[2]);
		planetRadio.setActionCommand(suggestion[2]);
		radioPanel.add(planetRadio, BorderLayout.SOUTH);
	//Make buttons part of a button group
		refutationRadioGroup = new ButtonGroup();
		refutationRadioGroup.add(characterRadio);
		refutationRadioGroup.add(weaponRadio);
		refutationRadioGroup.add(planetRadio);
	//Create submission button panel
		submitPanel = new JPanel(new BorderLayout());
	//Create refutation buttons
		refuteButton = new JButton("Refute Suggestion");
		denialButton = new JButton("I Cannot Refute");
	//Add action listeners to buttons
		refuteButton.addActionListener(this);
		denialButton.addActionListener(this);
	//Add buttons to panel
		submitPanel.add(denialButton,BorderLayout.WEST);
		submitPanel.add(refuteButton,BorderLayout.EAST);
	//Put refutationWrap together
		refutationWrap.add(refutationQuestion,BorderLayout.NORTH);
		refutationWrap.add(radioPanel, BorderLayout.CENTER);
		refutationWrap.add(submitPanel,BorderLayout.SOUTH);
	//Add refutationWrap to refutationDialogue
	    refutationDialogue.add(refutationWrap);
	        refutationDialogue.pack();
	        refutationDialogue.setAlwaysOnTop(true);
	        refutationDialogue.setVisible(true);
		
	}
		
	


	public void actionPerformed(ActionEvent event) {

		  //if user cannot refute
        if(event.getSource() == this.denialButton) {
        	System.out.println("User cannot refute.");
    //----> tell the controller that the refutation is null
        	isFinished = true;
        	this.refutationDialogue.dispose();
        	
        }
  
        //if user refutes
        else if (event.getSource() == this.refuteButton) {
        	System.out.println("User refuted suggestion.");
    //----> tell the controller what evidence was used to refute
        	refutation=refutationRadioGroup.getSelection().getActionCommand();
        	isFinished = true;
        	this.refutationDialogue.dispose();
        	
        }
      
        	//method to submit data to server.
        	
	}

}
