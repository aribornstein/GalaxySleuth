import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;


public class ChatWidget extends JPanel {
	private static final long serialVersionUID = 1L;
	public CController controller;
	public JButton send;
 	public JTextArea myMesg;
    /**
     * Received chat messages are displayed here. The newest chat message is
     * displayed at the top. Older messages follow. receivedMesgs is embedded in
     * scrollPane to provide scrolling text messages.
     */
    public JTextArea receivedMesgs;
    public JScrollPane scrollPane;
    public JScrollPane scrollPane2;

	

	public ChatWidget(CController controller){
		//controller which will receive sent messages and print received ones
		this.controller = controller;
		
		//title of box
/*<<<<<<< .mine
		JLabel chatTitle = new JLabel("User Chat",JLabel.CENTER);
		Dimension d = new Dimension(500,275);
		this.setLayout(new FlowLayout());
=======*/
		JLabel chatTitle = new JLabel("Talk Smack!",JLabel.CENTER);
		Dimension d = new Dimension(200,300);
		this.setLayout(new BorderLayout());
//>>>>>>> .r132
		this.setPreferredSize(d);
		
		//Figure out which color this person is so we can display it in the chat box:
		String myColor = controller.findMe().token.color;
		
		//create output text box
		receivedMesgs = new JTextArea("Your token color is:"+myColor, 1, 20);
		receivedMesgs.setEditable(false);
		receivedMesgs.setLineWrap(true);
		receivedMesgs.setWrapStyleWord(true);
		scrollPane = new JScrollPane(receivedMesgs);
/*<<<<<<< .mine
		Dimension dim = new Dimension (500, 200);
		scrollPane.setPreferredSize(dim);		
=======*/
		scrollPane.setPreferredSize(new Dimension(300,150));
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
//>>>>>>> .r132
		
		//create input text box
		myMesg = new JTextArea("Enter Message Here");
		myMesg.setEditable(true);
		myMesg.setLineWrap(true);
		myMesg.setWrapStyleWord(true);
		scrollPane2 = new JScrollPane(myMesg);
		scrollPane2.setPreferredSize(new Dimension(300,100));
		scrollPane2.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//create send button
		send = new JButton("Send");
		send.setPreferredSize(new Dimension(100, 25));
		send.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	sendMsg();
			}
		});	
		
		//add components to chat panel
		//add output box
		this.add(scrollPane, BorderLayout.NORTH);
		this.add(scrollPane2, BorderLayout.CENTER);
		this.add(send, BorderLayout.SOUTH);
		
	
		//pack and finalize the ChatWidget
		this.revalidate();
		this.setVisible(true);
	}

	public void sendMsg() {
		try {
		    controller.sendMesg(myMesg.getText());
		    myMesg.setText("");
		} catch (IOException ev) {
		    System.out.println(ev.getMessage()
			    + ": In send button handler.");
		}

	}
	
	public void updateChat(String msg) {
		//message passed from CController through GSClient and Game Screen
		receivedMesgs.insert(msg, 0);
		
	}
		    
	
	/*public static void main(String[] args) {
		ChatWidget chatWindow = new ChatWidget();
	    chatWindow.setVisible(true);
	}*/

}
