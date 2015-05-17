
public class CreateGame extends GameState {
	
	public SController controller;
	
	public CreateGame(SController controller){
		this.controller = controller;
	}
	
	public void nextState(ClientHandler newClient){
		//This message is being sent to the SECOND PLAYER
		Boolean False = new Boolean(false);
		Envelope e = new Envelope(MsgType.FIRST_PLAYER,False);
		newClient.sendEnvelope(e);
		System.out.println("Sent FIRST_PLAYER == False envelope");
		
		
		JoinGame joinGame = new JoinGame(controller);
		this.controller.gameState = joinGame;
		
	}
	
	public String toString(){
		String result = new String("CreateGame");
		return result;
	}
}
