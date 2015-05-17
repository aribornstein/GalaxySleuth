import java.net.*;
import java.io.*;

/**
 * Maintain client state, listen for Envelopes from client, and send Envelopes
 * to client.
 * 
 * @author Thomas Kelliher kelliher@goucher.edu
 * @version 2010.1018
 */
public class ClientHandler extends Thread {

    /**
     * Simple client state --- either the client is logged in or isn't logged
     * in.
     */
    private boolean loggedIn = false;
    /**
     * State of the listener thread.
     */
    private boolean running = true;
    /**
     * Username associated with the client.
     */
    public String userName;
    /**
     * Reference to the server controller.
     */
    private SController controller;
    /**
     * The socket associated with this client, along with the Object streams.
     */
    public User user;
    
    private Socket socket;
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;

    /**
     * 
     * @param controller
     *                The server controller.
     * @param socket
     *                The client socket.
     * @throws IOException
     *                 Thrown if we can't get Object input and output streams on
     *                 this socket.
     */
    public ClientHandler(SController controller, Socket socket, User user)
	    throws IOException {
	this.controller = controller;
	this.socket = socket;
	//this.user = user;
	this.setUserName(user.userName);

	try {
	    oos = new ObjectOutputStream(socket.getOutputStream());
	    ois = new ObjectInputStream(socket.getInputStream());
	} catch (IOException e) {
	    System.out.println(e.getMessage() + ": In ClientHandler()");
	}
    }

    /**
     * The thread which will listen for Envelopes from the client, passing them
     * along to the server controller for action.
     */
    public void run() {
    	
	while (running)
	    try {
		Envelope en = (Envelope) ois.readObject();
		controller.receiveEnvelope(this, en);
	    } catch (IOException e) {
		/**
		 * The client probably died a horrible death. Simulate a logout.
		 * We won't be able to send successfully a LOGOUT message, but
		 * we can deal with that.
		 */
		System.out.println(e.getMessage() + ": In ClientHandler.run()");
		controller.receiveEnvelope(this, new Envelope(MsgType.LOGOUT,
			null));
	    } catch (ClassNotFoundException e) {
		System.out.println(e.getMessage() + ": In ClientHandler.run()");
	    }
    }

    public void sendEnvelope(Envelope en) {
	try {
	    oos.writeObject(en);
	    oos.flush();
	} catch (IOException e) {
	    System.out.println(e.getMessage()
		    + ": In ClientHandler.sendEnvelope()");
	}
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public boolean getLoggedIn() {
	return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
	this.loggedIn = loggedIn;
    }

    /**
     * Tell the thread it's time to call it a day, then pull the rug out from
     * under it by closing its object streams and socket. That'll teach it!
     */
    public void halt() {
	running = false;
	try {
	    ois.close();
	    oos.close();
	    socket.close();
	} catch (IOException e) {
	    System.out.println(e.getMessage() + ": In ClientHandler.halt()");
	}
    }
}
