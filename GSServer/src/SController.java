import java.io.*;
import java.net.*;
import java.util.*;
/**
 * The "Galaxy Sleuth server," disguised as a simple chat system server. It will
 * listen for commands, encapsulated as objects, on Constants.SERVER_PORT.
 * Clients being served are tracked in clients.
 * 
 * @author Thomas Kelliher kelliher@goucher.edu
 * @version 2010.1018
 */
public class SController {

    /**
     * Our listen socket.
     */
    private ServerSocket serverSocket;
    /**
     * The list of connected, logged in clients. Once the server is up and
     * accepting client requests, be careful to ensure that all access to
     * clients is only via synchronized methods.
     */
    public List<ClientHandler> clients;
    
    /**
     * Constructs an SController, but doesn't start it running.
     */
    
    public GameState gameState;
    
    public Game masterGame = new Game();
    
    public ArrayList<String> remainingCharacters = new ArrayList<String>();
    
    public int playersToWaitFor;
    
    public ArrayList<User> turnOrder = new ArrayList<User>();
    
    public ArrayList<User> removedFromGame = new ArrayList<User>();
    
    public int turnCounter = 0;
     
    public int killCounter =0;
    public int suggestClientIndex=0;
    
    public SController() {

    	
	try {
	    /**
	     * Attempt to bind to the server socket.
	     */
	    serverSocket = new ServerSocket(Constants.SERVER_PORT);
	} catch (IOException e) {
	    System.out.println(e.getMessage()
		    + ": No server socket.  Quitting.");
	    System.exit(-1);
	}

	/**
	 * Collections.synchronizedList() simply provides a layer of
	 * unsynchronized access detection; it DOES NOT synchronize access. We
	 * still have to do that ourselves.
	 */
	clients = Collections.synchronizedList(new ArrayList<ClientHandler>());
	this.gameState = new InitialState(this);
	this.masterGame = new Game();
	
	remainingCharacters.add("Kara Tiberius");
	remainingCharacters.add("Valen Atreides");
	remainingCharacters.add("B'Elanna Wiggin");
	remainingCharacters.add("Jayne Sheridon");
	remainingCharacters.add("Aeryn Ackbar");
	remainingCharacters.add("Rodney Picard");
	
    }
    /**
     * The SController's server loop. When a new client initially connects,
     * create a ClientHandler for the client, start it, and add it to the
     * clients list.
     */
    public void runSController() {
	Socket newClientConnection;
	ClientHandler newClient;

	System.out.println("Running SController()");

	while (true) {
	    try {
	    	
		newClientConnection = serverSocket.accept();
		System.out.println("Accepted connection");
		User newUser = new User();
		newClient = new ClientHandler(this, newClientConnection, newUser);
		
    	System.out.println("controller.gameState.toString(): " + this.gameState.toString());
    	addClient(newClient);
		newClient.start();
	    } catch (IOException e) {
		System.out.println(e.getMessage() + ": In runScontroller()");
	    }
	}
    }
    /*
     * checks if number of logged in clients equals number of players to wait for
     */
    public boolean checkStartGame(){
    	if(clients.size() == playersToWaitFor){
    		if(this.gameState.toString().equals("JoinGame")){
    			return true;
    		}
    	}
    	return false;
    }
	
	public ClientHandler findClient(String userName){
		ClientHandler result = null;
		for(int i = 0; i < clients.size(); i++){
			if(userName == clients.get(i).userName){
				result = clients.get(i);
			}
		}
		//System.out.println("It is "+result.userName+"'s turn");		
		return result;
	}

    //broadcastAll
    public void broadcastAll(String message)
    {
    	Envelope e = new Envelope(MsgType.CHAT_MSG,message+"\n");
		for (int i =0; i< clients.size();i++)
		{
			clients.get(i).sendEnvelope(e);
		}
    }

    
    public int spin(){
    	Random rgen = new Random();
    	int i = rgen.nextInt(600);
    	int spin = 0;
    		if (i < 100){    			
    			spin = 1;
    		} else if ( i < 200 && i > 99){
    			spin = 2;
    		} else if ( i < 300 && i > 199){
    			spin = 3; 
    		} else if ( i < 400 && i > 299){
    			spin = 4; 
    		} else if ( i < 500 && i > 399){
    			spin = 5;
    		} else if ( i < 600 && i > 499){
    			spin = 6;
    		} else {System.out.println("Error in SController.spin()");}
    		
    		return spin;
    }


    /**
     * Open an Envelope from a client, parse the contents, and take action.
     * 
     * @param client
     *                The client from which the Envelope came.
     * @param en
     *                The Envelope received from the client.
     */
    public void receiveEnvelope(ClientHandler client, Envelope en) {
	switch (en.getMsgType()) {
	/**
	 * Change client's state to logged in, store the client's username, and
	 * respond with an empty LOGIN message so that the client changes state
	 * to logged in.
	 */
	case LOGIN:
	    if (client.getLoggedIn() == false) {
		client.setLoggedIn(true);
		//client.setUserName((String) en.getMsg());
		//System.out.println((String) en.getMsg() + " logged in.");
			//This should tell the client if they are the first client
			if(gameState.toString().equals("InitialState")){
				//first player will enter this if statement
				gameState.nextState(client);
				System.out.println("I just changed the SControllers state from Initial to create game");
				
			} else if(this.gameState.toString().equals("CreateGame")){
					//second player will enter this if statement
					gameState.nextState(client);	
				
			} else if(this.gameState.toString().equals("JoinGame")){
				//third and later players will enter this loop.
				Boolean False = new Boolean(false);
				Envelope e = new Envelope(MsgType.FIRST_PLAYER,False);
				client.sendEnvelope(e);
				System.out.println("Sent FIRST_PLAYER == False envelope");
				
				//if(this.playersToWaitFor == clients.size()){
					//gameState.nextState(null);
				//}
				
			}
				
	    } else
		System.out.println("Client already logged in.");
	    //commented out because I added the C
	    //client.sendEnvelope(new Envelope(MsgType.LOGIN, null));
	    break;

	/**
	 * Change the client's state to logged out, halt the ClientHandler's
	 * thread, and remove the client from the clients list.
	 */
	case LOGOUT:
	    if (client.getLoggedIn() == true) {
		client.sendEnvelope(new Envelope(MsgType.LOGOUT, null));
		client.setLoggedIn(false);
		client.halt();
		System.out.println(client.getUserName() + " logged out.");
		removeClient(client);
	    } else
		System.out.println("Client already logged out.");
	    break;

	/**
	 * Prepend the username to the chat message and broadcast it to all
	 * logged in clients.
	 */
	case CHAT_MSG:
	    if (client.getLoggedIn() == true) {
		Envelope e = new Envelope(MsgType.CHAT_MSG, client
			.getUserName()
			+ ": " + (String) en.getMsg() + "\n");
		broadcast(e);
	    } else
		System.out.println(client.getUserName()
			+ " can't send message.");
	    break;
	    
	//Used primarily to send system messages to everyone    
	case CHAT_MSG_ALL:
		String message = (String) (en.getMsg() + "\n");
		broadcastAll(message);
		
		
	break;
	
	case BOARD_UPDATE:
		
		System.out.println("BOARD_UPDATE message received");
		//Firstly, send everyone a new board
			//TO BE IMPLEmented
				
		break;
	
	case ACCUSATION:
	   Boolean isAccusationValid = (Boolean)en.getMsg();
	   if (isAccusationValid == false) {
		   removedFromGame.add(turnOrder.get(turnCounter));
		   //Tell everyone that this player was removed
		   broadcastAll(client.userName+ " Was removed from the game after making a false accusation");
		   //send an envelope to envoke a remove state
		   Envelope remove = new Envelope(MsgType.REMOVE, null);
		   findClient(turnOrder.get(turnCounter).userName).sendEnvelope(remove);
		  
	   }
	   else
	   {
		   Envelope gameWin = new Envelope (MsgType.WIN,turnOrder.get(turnCounter).userName);
		   //tell every client that the current user just won
		   for (int i=0; i<clients.size();i++) clients.get(i).sendEnvelope(gameWin);
		   
	   }
	   break;
	case SUGGESTION:
		
		System.out.println("Receiving Suggestion message");
		
		suggestClientIndex = turnCounter;
		// get list
		String[] givenSuggestion = (String[])en.getMsg();
		SuggestionHolder mySuggestionHolder = new SuggestionHolder(turnOrder.get(suggestClientIndex).hand,givenSuggestion);
		Envelope sugestionToRefute = new Envelope(MsgType.REFUTATION,mySuggestionHolder) ;
		// increment suggest client index 
		if(turnOrder.size() == turnCounter)suggestClientIndex=0;
		else suggestClientIndex = 0;
		
		System.out.println("Sending Refutation message to: "+turnOrder.get(suggestClientIndex).userName); 
		// ask the client to refute
		findClient(turnOrder.get(suggestClientIndex).userName).sendEnvelope(sugestionToRefute);
		
		//System.out.prinf("Refutation message sent to %s: "+mySuggestionHolder.myHand.toString());		
		break;
		
	case SUGGESTION_HELPER:
		refutationHolder refutation =(refutationHolder) en.getMsg();
		// if refutal is found or no one else can refute
		if ((refutation.refutalCard != null)||(refutation.refutalCard!="")||(clients.get(suggestClientIndex).userName.equals( turnOrder.get(turnCounter).userName)))
		{
			//if card exsists send refutal back the client that asked for the move
			if ((refutation.refutalCard != null)||(refutation.refutalCard!=""))
			{
				Envelope refutedCard = new Envelope(MsgType.CHAT_MSG,"The "+refutation.refutalCard+ " was used to refute your suggestion.\n");
				findClient(turnOrder.get(turnCounter).userName).sendEnvelope( refutedCard);
			}
			//else notify user that no one could refute his suggestion
			else
			{
			 Envelope refutedCard  = new Envelope(MsgType.CHAT_MSG,"Nobody Could Refute Your Suggestion\n" );
			 findClient(turnOrder.get(turnCounter).userName).sendEnvelope(refutedCard);
			}
		}
		else
		{
			//if client index is greater than turnCounter
			if (suggestClientIndex>turnCounter)
			{
				//if suggestClientIndex is greater than the size of turnorder set suggestClientIndex to 0
				if (suggestClientIndex > turnOrder.size()) suggestClientIndex =0;
				else 
				{
				 //increment suggestClientIndex counter send 
					suggestClientIndex++;
				 // send the suggestion to the next player
					sugestionToRefute =  new Envelope(MsgType.REFUTATION, refutation.mySuggestion);
					findClient(turnOrder.get(suggestClientIndex).userName).sendEnvelope(sugestionToRefute);
				}
			}
            if (suggestClientIndex<turnCounter)
            {
				//increment suggestClientIndex counter send 
            	if((suggestClientIndex + 1) == turnCounter ){
            		Envelope refutedCard  = new Envelope(MsgType.CHAT_MSG,"Nobody Could Refute Your Suggestion\n" );
            		findClient(turnOrder.get(turnCounter).userName).sendEnvelope(refutedCard);
            	}
            	
            	suggestClientIndex++;
				//send the suggestion to the next player
            	sugestionToRefute =  new Envelope(MsgType.REFUTATION, refutation.mySuggestion);
				findClient(turnOrder.get(suggestClientIndex).userName).sendEnvelope(sugestionToRefute);
            }
		}
		break;
	case STATE_QUERY:
		
	case START_GAME_CONDITION:
		//Turn incoming message into char array
		String msg = (String) en.getMsg();
		
		
		System.out.println("Received START_GAME_CONDITION message");
		//check if the condition is for time or number of players
		if(msg.charAt(1)=='m'){
			//call JoinGame method which will sleep for specified num of minutes
			System.out.println("GameState being passed to Timer is " + gameState.toString());
			long n = (long) Integer.parseInt(msg.charAt(0)+"");
			StateTimer s = new StateTimer(n, this);
			s.start();
			System.out.println("START_GAME_CONDITION is wait"+n+"minutes");
			
		}if(msg.charAt(1)=='p'){
			Integer n = Integer.parseInt(msg.charAt(0)+"");
			this.playersToWaitFor = n;
			System.out.println("START_GAME_CONDITION is wait for "+n+" players");
		}
		break;
		
	case USER:
		User user = (User) en.getMsg();
		System.out.println("Received USER message:\nAdding User: "+user.userName+", character: " +user.token.character+" to masterGame");
		//client.user = user;
		client.userName = user.userName;
		masterGame.addUser(user);
		turnOrder.add(user);
		System.out.println("Remaining Characters before removal: "+remainingCharacters);
		remainingCharacters.remove(user.token.character);
		System.out.println("Remaining Characters after removal: "+remainingCharacters);
		
		//checks if correct number of users has logged in. 
		//Supposed to move from Join Game to Distribute
		if(checkStartGame()){
			System.out.println("Starting Game...");
			System.out.println("Current gameState: "+ gameState.toString());
			gameState.nextState(null);
		}
		
		break;
		
	case GET_CHARACTERS:
		Envelope f = new Envelope(MsgType.CHARACTER_LIST, remainingCharacters);
		client.sendEnvelope(f);
		
		break;
		
	case END_TURN:
		
		System.out.println("End turn message received from client: "+client.userName);
		broadcastAll(client.userName+"'s turn is ending...");
		
		User updatedUser = (User) en.getMsg();
		
		Envelope boardUpdate = new Envelope(MsgType.BOARD_UPDATE, updatedUser);
		
		
				
		System.out.println("UpdatedUser received by SController in the BOARD_UPDATE message: "); updatedUser.token.print();
		
		
		for(int i = 0; i < clients.size(); i++){
			clients.get(i).sendEnvelope(boardUpdate);
		}
		
		//Start next turn	
		
		
		if(gameState.toString().equals("Move")){
			gameState.nextState(null);
		}	else{
			System.out.println("ERROR: End game button pressed when not in move state");
		}
	
		break;
		
	case PLAY_AGAIN:
		if( clients.size() ==0)
		{
			System.out.println("sever should be killed");
		}
		
	}
    }

    private synchronized void addClient(ClientHandler client) {
	clients.add(client);
    }

    private synchronized void removeClient(ClientHandler client) {
	clients.remove(client);
    }

    /**
     * Iterate through all clients, sending an Envelope to all logged in
     * clients.
     * 
     * @param en
     *                The Envelope to broadcast to all logged in clients.
     */
    private synchronized void broadcast(Envelope en) {
	ClientHandler client;
	ListIterator<ClientHandler> iterator = clients.listIterator();

	while (iterator.hasNext()) {
	    client = iterator.next();

	    if (client.getLoggedIn() == true)
		client.sendEnvelope(en);
	}

    }
}
