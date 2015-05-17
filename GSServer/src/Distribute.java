
public class Distribute extends GameState {

	public SController controller;
	
	public Distribute(SController controller){
		this.controller = controller;
		System.out.println("Distribute state created");			
		
}
	//called by join game. initializes the game and prints out game.
	//per my bad design, all of the nextState methods must take a client handler even though only one of them needed it...
	public void nextState(ClientHandler none){
		int numClients = controller.clients.size();
		controller.masterGame.solution = controller.masterGame.makeSolution();
		controller.masterGame.playerHands = controller.masterGame.dealCards(numClients);
		
		for(int i = 0; i < controller.masterGame.userList.size(); i++){
			User user = controller.masterGame.userList.get(i);
			
			for(int j = 0; j < controller.masterGame.playerHands[i].length;j++){
				user.hand.add(controller.masterGame.playerHands[i][j]);
			}
		}
		
		
		
		controller.masterGame.print();
		System.out.println("TURN ORDER: "+controller.turnOrder.toString());
		
		
		
		
		// send game to all clients
		Envelope gameEnvelope = new Envelope(MsgType.GAME_OBJECT, controller.masterGame);
		for (int i =0; i< controller.clients.size();i++)
		{
			controller.clients.get(i).sendEnvelope(gameEnvelope);
		}
		
		GameState playGame = new PlayGame(controller);
		controller.gameState = playGame;
		controller.gameState.nextState(null);
		
	}
	public String toString(){
		return("Distribute");
	}
}
