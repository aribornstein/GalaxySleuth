
public class InitialState extends GameState {
		
	public SController controller;
		
	public InitialState(SController controller){
		this.controller = controller;
	}
		
	public void nextState(ClientHandler newClient){
		//Tell new client that they are the firstClient
		Boolean True = new Boolean(true);
		Envelope e = new Envelope(MsgType.FIRST_PLAYER,True);
		newClient.sendEnvelope(e);
		System.out.println("Sent FIRST_PLAYER == True envelope");
		
		GameState createGame = new CreateGame(controller);
		this.controller.gameState = createGame;
	}
	
	public String toString(){
		String result = new String("InitialState");
		return result;
	}
}


