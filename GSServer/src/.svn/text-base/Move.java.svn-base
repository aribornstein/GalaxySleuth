import java.util.ListIterator;


public class Move extends PlayGame {

	public SController controller;
	
	public int spin;
	
	public ClientHandler client;
	
	public String userName;
	
	public Move(SController controller, int spin, String userName) {
		//super constructor contains: this.controller = controller;
		super(controller);
		this.controller = controller;
		this.spin = spin;
		this.userName = userName;
	}
	
	public void run(){
		
		//Search through the SController's list of clients to find which 
		//client gets the next turn.
		client = findClient(userName);
		
		Envelope e = new Envelope(MsgType.SPIN, spin);
		client.sendEnvelope(e);
		System.out.println("Sending spin "+spin+" to "+client.userName);		
		//Now the server will wait to receive an end turn message
		
		}
	
	public void nextState(ClientHandler none){
		
		controller.turnCounter++;
		//Check if we need to reset the counter to 0 if we need wrap around back to the first player
		if(controller.turnCounter >= controller.turnOrder.size()){
			controller.turnCounter = 0;
		}
		if(removed(controller.turnOrder.get(controller.turnCounter).userName)){
			controller.turnCounter++;
		}
		//Find the next user in the turn orders userName and then make it their turn
		String nextUserName = controller.turnOrder.get(controller.turnCounter).userName;		
		GameState move = new Move(controller, controller.spin(), nextUserName );
		controller.gameState = move;
		controller.gameState.run();
	}
	
	public ClientHandler findClient(String userName){
		
		ClientHandler result = null;
				
		for(int i = 0; i < controller.clients.size(); i++){
			if(userName == controller.clients.get(i).userName){
				result = controller.clients.get(i);
			}
		}
		
		//System.out.println("It is "+result.userName+"'s turn");		
		return result;
	}
	
	public String toString(){
		return "Move";
	}
	//checks if player is removed from game
	public boolean removed(String userName){
		User iteratedUser = null;
		ListIterator<User> iterator = controller.removedFromGame.listIterator();
		
		while (iterator.hasNext()){
			iteratedUser = iterator.next();
			if(iteratedUser.userName.equals(userName)){
				System.out.println("Skipping "+userName+"'s turn because they have been removed.");
				return true;
			}
			
		}		
		return false;
	}
	

}
