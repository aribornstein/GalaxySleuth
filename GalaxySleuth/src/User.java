import java.io.Serializable;
import java.util.ArrayList;


public class User implements Serializable {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6453438697411081444L;
	public String userName;
	public Token token;
	public ArrayList<Card> hand = new ArrayList<Card>();

	public User(){
		
	}
	
	
	public User(String name, Token token) {
		super();
		this.userName = name;
		this.token = token;
	}
	
	public boolean accuse(Card charCard, Card weapCard, Card roomCard)
	{
		return false;
	}
	
	public void suggest(Card charCard, Card weapCard)
	{
		//method called when user makes a suggestion
	}
	
	
	public void moveToken(Cell targetCell)
	{
		//calls the setPosition method for this user's token.
		this.token.setPosition(targetCell);
	}
	
	public void print(){
		System.out.println("User Name (human): "+userName+"\n");
		System.out.println("Hand: ");
		for(int i = 0; i < hand.size(); i++){
			System.out.println("\t" + hand.get(i).toString());
		}
		System.out.println("Token:");
		token.print();
	}
	
	
	
	
	

}
