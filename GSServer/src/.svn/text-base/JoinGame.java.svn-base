
public class JoinGame extends GameState {
	
	public SController controller;
	
	public JoinGame(SController controller){
		this.controller = controller;
	}
	
	public String toString(){
		return("JoinGame");	
	}
	
	public void nextState(ClientHandler newClient){
		System.out.println("Changing state to Distribute");
		Distribute distribute = new Distribute(controller);
		controller.gameState = distribute;
		distribute.nextState(null);
	}
	
}
