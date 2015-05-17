import java.io.Serializable;
import java.util.Random;
import java.util.ArrayList;
/*
 * NWSE is our convention don't fuck it up
*/
public class Game implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3524466854591746609L;
	
	public ArrayList<User> userList = new ArrayList<User>();
	/**
	 * List of ALL cards in the game.
	 */
	Card[] cardList;
	

	/**
	 * Solution cards: list of three cards in the order [character, weapon, planet]
	 */
	
	//This is the list of variables that will be used repeatedly throughout the game.
	String[] characters;
	Card[] solution;
	Card[][] playerHands;
	Token[] tokenlist;

	//initilize for testing purpouses
	Board myBoard = new Board();




	public Game()
	{
		cardList =makeCardList();

		
	}

	/**
	 * This method first decides whether or not the accusation passed to it from a user
	 * is correct or not and then tells the controller the results. The controller proceeds
	 * with further actions depending on the case.
	 */
	
	//Hardcoded list of all characters. Now redundant.
/*	public String[] makeCharacterList() {
		String[] result = new String[6];
		result[0]="Tina Time-traveler";
		result[0]="Sam Space-voyager";
		result[0]="Mona Moon-walker";
		result[0]="George Galaxy-wanderer";
		result[0]="Uhura Universalist";
		result[0]="Steve Star-gazer";
		return result;
	}
	*/
	//Hardcoded list of all the cards. The first six are weapons, the next six characters, and the last nine planets. Note the 
	// name of each type. At the time of this writing, image files are not filled in. 
	public Card[] makeCardList(){
			
		
			Card[] result = new Card[21];
					
			result[0] =

			new Card ("character", "Kara Tiberius", "G://ButtonImages/karatiberius.gif");

			result[1] =
		new Card ("character", "Valen Atreides", "G://ButtonImages/valenatreides.gif");


			result[2] =

			new Card ("character", "B'Elanna Wiggin", "G://ButtonImages/belannawiggin.gif");


			result[3] =

			new Card ("character", "Jayne Sheridon", "G://ButtonImages/jaynesheridon.gif");


			result[4] =
			new Card ("character", "Aeryn Ackbar", "G://ButtonImages/aerynackbar.gif");

			result[5] =

			new Card ("character", "Rodney Picard", "G://ButtonImages/rodneypicard.gif");

			result[6] =
			new Card ("weapon", "Disruptor", "G://ButtonImages/disruptor.gif");

			result[7] =
			new Card ("weapon", "Crysknife", "G://ButtonImages/crysknife.gif");

			result[8] =
			new Card ("weapon", "Daemon Hammer", "G://ButtonImages/daemonhammer.gif");

			result[9] =
			new Card ("weapon", "Bat'leth", "G://ButtonImages/batleth.gif");

			result[10] =
			new Card ("weapon", "Particle Projector Cannon", "G://ButtonImages/ppc.gif");

			result[11] =
			new Card ("weapon", "Noisy Cricket", "G://ButtonImages/noisycricket.gif");

			result[12] =
			new Card ("planet", "Caprica", "G://ButtonImages/caprica.gif");

			result[13] =
			new Card ("planet", "Earth That Was", "G://ButtonImages/earththatwas.gif");

			result[14] =
			new Card ("planet", "Ferenginar", "G://ButtonImages/ferenginar.gif");

			result[15] =
			new Card ("planet", "Arrakis", "G://ButtonImages/arrakis.gif");

			result[16] =
			new Card ("planet", "Sheldon Alpha 5", "G://ButtonImages/sheldonalpha5.gif");

			result[17] =
			new Card ("planet", "Miranda", "G://ButtonImages/miranda.gif");

			result[18] =
			new Card ("planet", "Perelandra", "G://ButtonImages/perelandra.gif");

			result[19] =
			new Card ("planet", "Sateda", "G://ButtonImages/sateda.gif");

			result[20] =
			new Card ("planet", "Endor", "G://ButtonImages/endor.gif");
			
			return result;
		
	}
	
	public Card[] makeSolution(){
		
		Card[] result = new Card[3];		
		Random r = new Random();
		//Picks a number between 0-5 to select a random character
		int i = r.nextInt(6);
		//picks a number between 6-11 to select a random weapon
		int j = r.nextInt(6)+6;
		//picks a number between 12-20 to select a random planet
		int k = r.nextInt(9)+12;
		
		result[0] = this.cardList[i];
		result[1] = this.cardList[j];
		result[2] = this.cardList[k];

		System.out.println("Solution: ("+ result[0].toString()+"), ("+result[1].toString()+"), ("+result[2].toString()+")");
		
		return result;	
		
	}
	
	//The block below shuffles the cards and deals them out to the players. It takes the cardList and the number of participating players as args.
	//It returns a 2D array displaying how the cards will be distributed. Assuming each player is represented by a number in this array,
	// we can use the returned allPlayersHands array to deal with any parts of this game that involve checking/using the players' cards.
	public Card[][] dealCards (int countOfPlayers) {
		Random rgen = new Random();
		int numberOfPlayers = countOfPlayers;
		int playerDivisions = new Double(18/numberOfPlayers+0.5).intValue();
		Card[] shuffledDeck = new Card[21];
		shuffledDeck = cardList;
		 //--- Shuffle by exchanging each element randomly. I found this code on the web, and I'm not sure how it works, but it's supposed
		//to swap the places of cards in the shuffledDeck, which has its values initialized to those of the cardList. 
	for (int i=0; i<cardList.length; i++) {
		int randomPosition = rgen.nextInt(shuffledDeck.length);
		Card temp = shuffledDeck[i];
		shuffledDeck[i] = shuffledDeck[randomPosition];
		shuffledDeck[randomPosition] = temp;
	}
	Random rgen2 = new Random();
	for (int i=0; i<cardList.length; i++) {
		int randomPosition = rgen2.nextInt(shuffledDeck.length);
		Card temp = shuffledDeck[i];
		shuffledDeck[i] = shuffledDeck[randomPosition];
		shuffledDeck[randomPosition] = temp;
	}
	Random rgen3 = new Random();
	for (int i=0; i<cardList.length; i++) {
		int randomPosition = rgen3.nextInt(shuffledDeck.length);
		Card temp = shuffledDeck[i];
		shuffledDeck[i] = shuffledDeck[randomPosition];
		shuffledDeck[randomPosition] = temp;
	}
		//This is the bit where the cards are actually distributed. The 1D array shuffledDeck gets fed into the 2D array allPlayersHands.
		//Before being passed to players, the names of the cards are checked against the solution array to make sure that no player gets
		// dealt a card that's part of the solution.
		Card[][] allPlayersHands = new Card [numberOfPlayers][playerDivisions];
		int countEmOff = 0;
		for (int i=0; i<allPlayersHands.length; i++){
			for (int j=0; j<allPlayersHands[i].length; j++){
				if (shuffledDeck[countEmOff].name.equals (solution[0].name) || 
					shuffledDeck[countEmOff].name.equals (solution[1].name) || 
					shuffledDeck[countEmOff].name.equals (solution[2].name)){
					countEmOff++;
					j--;
				}
				else{
				allPlayersHands[i][j] = shuffledDeck[countEmOff];
				countEmOff++;
				}
			}
		}
		
		
		return allPlayersHands;
	}
	
	
	
	
	

	
	public void sendToken(Token token)
	{
		// used to send updated information about token position
		
	}
	
	public boolean isMoveValid (Token token, String direction )
	{
		
		
		
        String[] planets = {"la","pu","pg","en","nl","ve","eh","pn","sp"};
		boolean valid = false;
		if (direction=="N") //n
		{
			
			if (myBoard.board[token.xCoord][token.yCoord].type== "##")//u are in a regular cell u can only to a door or a regular cell
			{ 
				if ((myBoard.getAboveOf(token).type =="##") ||(myBoard.getAboveOf(token).type.equals("=="))) valid= true;// if the N cell is a door or a regular cell u can move
			}
			else //token is on a planet 
			{ 
				if (myBoard.getAboveOf(token).type  == "==")valid = true; //if the top cell is a door moves valid 
				for (int i=0; i< planets.length;i++)
				{
					if (myBoard.getAboveOf(token).type.equals(planets[i]))valid = true; 
				}
			}
		}
		if (direction=="S")
		{
			
			if (myBoard.board[token.xCoord][token.yCoord].type== "##")//u are in a regular cell u can only move to a door or a regular cell
			{ 
				if ((myBoard.getBelowOf(token).type =="##") ||(myBoard.getBelowOf(token).type.equals("=="))) valid= true;// if the S cell is a door or a regular cell u can move
			}
			else //token is on a planet 
			{ 
				if (myBoard.getBelowOf(token).type  == "==")valid = true; //if the top cell is a door moves valid 
				for (int i=0; i< planets.length;i++)
				{
					if (myBoard.getBelowOf(token).type.equals(planets[i]))valid = true;
				}			
			}
		}
		if (direction=="E")
		{
			
			if (myBoard.board[token.xCoord][token.yCoord].type== "##")//u are in a regular cell u can only to a door or a regular cell
			{ 
				if ((myBoard.getLeftOf(token).type =="##") ||(myBoard.getLeftOf(token).type.equals("=="))) valid= true;// if the N cell is a door or a regular cell u can move
			}
			else //token is on a planet 
			{ 
				if (myBoard.getLeftOf(token).type == "==")valid = true; //if the top cell is a door moves valid 
				for (int i=0; i< planets.length;i++)
				{
					if (myBoard.getLeftOf(token).type.equals(planets[i]))valid = true;
				}			
			}
		}
		if (direction=="W")
		{
		
			if (myBoard.board[token.xCoord][token.yCoord].type== "##")//u are in a regular cell u can only to a door or a regular cell
			{ 
				if ((myBoard.getRightOf(token).type =="##") ||(myBoard.getRightOf(token).type.equals("=="))) valid= true;// if the N cell is a door or a regular cell u can move
			}
			else //token is on a planet 
			{ 
				if (myBoard.getRightOf(token).type  == "==")valid = true; //if the top cell is a door moves valid 
				for (int i=0; i< planets.length;i++)
				{
					if (myBoard.getLeftOf(token).type.equals(planets[i]))valid = true;
				}
			}
		}
		if (direction=="XXX"){//THIS OCCURS WHEN: the player clicked on a square not adjacent to their current square
			valid = false;
		}
		
		
		return valid;
	}

	

	
	public Token move(Token token, String direction )

	{
		//if the move is to the north
		if (direction == "N"){
			if (this.isMoveValid(token, "N")){
				token.yCoord =  token.yCoord-1;
			}
		}
		//if the move is to the south
		if (direction == "S"){
			if (this.isMoveValid(token, "S")){
				token.yCoord = token.yCoord+1;
			}
		}
		//if the move is to the east
		if (direction == "E"){
			if (this.isMoveValid(token, "E")){
				token.xCoord = token.xCoord-1;
			}
		}
		// if the move is to the west
		if (direction == "W"){
			if (this.isMoveValid(token, "W")){
				token.xCoord = token.xCoord+1;
			}
		}
		return token;
	}
	
	public Card[] getSolution()
	{
		return this.solution;
	}
	public void addUser(User newUser){
		userList.add(newUser);
	}
	
	//A way to print out all game info
	public void print(){

				
		//print solution
		System.out.println("Solution : ");
		for(int i = 0; i < solution.length; i++){
			System.out.println(solution[i].toString());
		}
		
		//print out all users w/ token, hands etc..
		System.out.println("User List: "+userList.toString());		
		for(int n = 0; n < userList.size(); n++){
			userList.get(n).print();
		}
			
		}



	/*	Card[] mycards = myGame.cardList;
			for (int i=0; i<mycards.length; i++){
			System.out.print(mycards[i].name+mycards[i].type+", ");
			}
			System.out.println("");
			System.out.println("\n");
		Card[][] hands = myGame.dealCards(5);
			for (int i=0; i<hands.length; i++){
				for (int j=0; j<hands[i].length; j++){
				 System.out.print(hands[i][j].name+hands[i][j].type+", \t");
				}
			}
			System.out.println("\n");
			hands = myGame.dealCards(6);
			for (int i=0; i<hands.length; i++){
				for (int j=0; j<hands[i].length; j++){
				 System.out.print(hands[i][j].name+hands[i][j].type+", \t");
				}
			  }
			System.out.println("\n");
			hands = myGame.dealCards(4);
			for (int i=0; i<hands.length; i++){
				for (int j=0; j<hands[i].length; j++){
				 System.out.print(hands[i][j].name+hands[i][j].type+", \t");
				}
			}
		}
*/
	
/*	public static void main(String[] args){
		Token t = new Token("red","Tina",8,5,"asdf");
		Game myGame = new Game();
		System.out.println("Original pos "+ t.xCoord +"," + t.yCoord);
		t =myGame.move(t,"N"); 
		System.out.println("move N " +t.xCoord +","+ t.yCoord );
		t =myGame.move(t,"E"); 
		System.out.println("move E " +t.xCoord +","+ t.yCoord );
		t =myGame.move(t,"W"); 
		System.out.println("move W " +t.xCoord +","+ t.yCoord );
		t =myGame.move(t,"S"); 
		System.out.println("move S " +t.xCoord +","+ t.yCoord );
		t =myGame.move(t,"N"); 
		System.out.println("move N " +t.xCoord +","+ t.yCoord );
		t =myGame.move(t,"N"); 
		System.out.println("move N " +t.xCoord +","+ t.yCoord );
		t =myGame.move(t,"W"); 
		System.out.println("move W " +t.xCoord +","+ t.yCoord );
		t =myGame.move(t,"W"); 
		System.out.println("move W " +t.xCoord +","+ t.yCoord );
		t =myGame.move(t,"S"); 
		System.out.println("move S " +t.xCoord +","+ t.yCoord );
	}
}*/
	

	public static void main(String[] args){
	Game mygame = new Game();
	Card[] mycards = mygame.cardList;
		for (int i=0; i<mycards.length; i++){
		System.out.print(mycards[i].name+mycards[i].type+", ");
		}
		System.out.println("");
		System.out.println("\n");
	Card[][] hands = mygame.dealCards(5);
		for (int i=0; i<hands.length; i++){
			for (int j=0; j<hands[i].length; j++){
			 System.out.print(hands[i][j].name+hands[i][j].type+", \t");
			}
		}
		System.out.println("\n");
		hands = mygame.dealCards(6);
		for (int i=0; i<hands.length; i++){
			for (int j=0; j<hands[i].length; j++){
			 System.out.print(hands[i][j].name+hands[i][j].type+", \t");
			}
		  }
		System.out.println("\n");
		hands = mygame.dealCards(4);
		for (int i=0; i<hands.length; i++){
			for (int j=0; j<hands[i].length; j++){
			 System.out.print(hands[i][j].name+hands[i][j].type+", \t");
			}
		}
 }

}

	
	
