import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.*;
import java.applet.*;
import java.net.*;
import javax.swing.*;


public class CardDisplay extends JPanel {

	public CardDisplay(ArrayList<Card> hand, URL theURL) {
		
		int numOfCards = hand.size();
		JButton[] buttonArray = new JButton[numOfCards];
		for (int i = 0; i < numOfCards; i++) {
			
			Card card = hand.get(i);
			String path = card.imageFilepath;
			ImageIcon cardImage = new ImageIcon(path);
			JButton button = new JButton(cardImage);
			button.setPreferredSize(new Dimension(150,200));
			buttonArray[i] = button;
		}
		
		JButton addedButton = new JButton();
		for (int p = 0; p < numOfCards; p++) {
			for (int q = 0; q <= 24; q++) {
				addedButton = buttonArray[p];
				this.add(addedButton);
			}
		}
		//title of box
//		JLabel handTitle = new JLabel("Voici Votre Chartes!",JLabel.CENTER);
		/*Dimension d = new Dimension(800,100);
		this.setPreferredSize(d);
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.add(Box.createVerticalGlue());
//		this.add(handTitle);
		this.add(Box.createVerticalStrut(10));
		this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		for(int i=0;i<hand.length;i++){
		this.add(Box.createHorizontalGlue());
		this.add(hand[i].physicalCard());
		this.add(Box.createRigidArea(new Dimension(10, 0)));*/
	}


	


	
}