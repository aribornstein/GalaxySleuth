
public abstract class GameState {
	
	public void nextState(ClientHandler newClient) {
		System.out.println("Error: nextState() called on abstract GameState object");
	}
	
	public void run(){
		System.out.println("Error: run() called on abstract GameState object (probably should have been called on Move");
	}
}
