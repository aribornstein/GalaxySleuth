
public class StateTimer extends Thread {

	long minutes;
	GameState gameState;
	SController controller;
	
	public StateTimer(long minutes, SController controller){
		this.minutes = minutes;
		this.gameState = gameState;
		this.controller = controller;
	}
	
	@Override
	public void run() {
		try{

			Thread.sleep(minutes*60*1000);

			}catch(InterruptedException e){
				System.out.println(e);
			}

	System.out.println("Current State: "+ controller.gameState);		
	System.out.println("Timer finished. Moving from JoinGame to Distribute");
	controller.gameState.nextState(null);
	}
}
