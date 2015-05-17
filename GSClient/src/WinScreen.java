import javax.swing.*;
import java.awt.event.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.IOException;
public class WinScreen extends JFrame implements ActionListener{
	JLabel announcement;
	JPanel celebration;
	JButton yay;
	Boolean isFinished = false;

 public WinScreen(String winner){
	super();
	Dimension d = new Dimension(300,100);
	this.setPreferredSize(d);
	this.setLocation(500,350);

	announcement = new JLabel("Game Over! "+winner+" has won the game!", JLabel.CENTER);
	yay = new JButton("Hurray!");
	yay.addActionListener(this);
	this.add(announcement, BorderLayout.NORTH);
	this.add(yay, BorderLayout.CENTER);
	this.pack();	
}
 public void actionPerformed(ActionEvent event) 
 {
	 if(event.getSource() == this.yay) {
		 isFinished=true;
    }
  }
 

}