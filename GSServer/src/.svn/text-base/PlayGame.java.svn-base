
public class PlayGame extends GameState {
	
	public SController controller;
	
	public PlayGame(SController controller){
		this.controller = controller;
	}

	public void nextState(ClientHandler none){

		//finish sending PLAY_GAME envelope to all clients
		Envelope e = new Envelope(MsgType.PLAY_GAME, true);
		for(int i=0; i < controller.clients.size(); i++){
			controller.clients.get(i).sendEnvelope(e);			
		}
		
		//get userName of next user
		String userName = controller.turnOrder.get(controller.turnCounter).userName;
		
		GameState move = new Move(controller, controller.spin(), userName );
		controller.gameState = move;
		controller.gameState.run();
		
	}
}
