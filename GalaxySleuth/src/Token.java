import java.io.Serializable;


public class Token implements Serializable {
	/*
	 * TODO IN THIS CLASSSSSS
	 *  
	 * HOOHKAY
	 * 
	 * When this token is initialized all it gets it a character name, but that is all that it needs.
	 * All other token information should directly correspond to which caracter name has been passed to the 
	 * constructor.
	 * 
	 * That means the constructor should assign each token a unique color, starting position and image filepath
	 * depending on which character it is. As far as I know we haven't decided on any specific colors yet so 
	 * feel free to choose any. Same goes for starting locations, just make sure that they match up with actual board
	 * starting locations. 
	 */
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2627825708593302788L;

	public String character;
	public int xCoord;
	public int yCoord;
	public String imageFilePath;
	public String color;
	


	//Julian made this constructor take only a character because thats how it has to be.
	//TRY NOT TO USE THE OTHER CONSTRUCTOR IN REAL CODE. The other should be only for testing purposes.
	
	//Tim-Edited 11/11/10-If else statements identify a character based on its name and returns a starting position and color for the Token object.
	//Right now, there isn't anything to set a token's color; I think we'll need another class to provide colors to pieces. Perhaps something
	// in the board widget? 
	public Token(String character) {
		super();
		this.character = character;
		
		if(character.equals("Valen Atreides")){

			
			this.xCoord = 0;
			this.yCoord = 7;
			this.color = "red";
			this.imageFilePath = "samFilePath";
		}		
		if(character.equals("Kara Tiberius")){

			this.xCoord = 0;
			this.yCoord = 16;
			this.color = "white";
			this.imageFilePath = "tinaFilePath";
		}		
		if(character.equals("B'Elanna Wiggin")){

			this.xCoord = 7;
			this.yCoord = 23;
			this.color = "green";
			this.imageFilePath = "monaFilePath";
		}
		if(character.equals("Jayne Sheridon")){

			this.xCoord = 15;
			this.yCoord = 24;
			this.color = "yellow";
			this.imageFilePath = "georgeFilePath";
		}
		if(character.equals("Aeryn Ackbar")){

			
			this.xCoord = 23;
			this.yCoord = 7;
			this.color = "pink";
			this.imageFilePath = "uhuraFilePath";
		}
		if(character.equals("Rodney Picard")){

			
			this.xCoord = 16;
			this.yCoord = 0;
			this.color = "orange";
			this.imageFilePath = "steveFilePath";
		}
	}

	public void setPosition(Cell targetCell)
	{
		//change a tokens position after a user makes a move.
		this.xCoord = targetCell.getXcoord(); //Owen should have added these two methods to Cell
		this.yCoord = targetCell.getYcoord();
	}
	
	public void print(){
		System.out.println("\tCharacter: "+character+"\n");
		System.out.println("\tStarting location: ("+xCoord+", "+yCoord+")");
		System.out.println("\tImage: "+imageFilePath);
	}
	
	
	/*public static void main (String[] args) {
		Token testCase1 = new Token("Sam Space-voyager");
		System.out.println(testCase1.character + ", " + testCase1.xCoord + ", " + testCase1.yCoord);
		Token testCase2 = new Token("Tina Time-traveler");
		System.out.println(testCase2.character + ", " + testCase2.xCoord + ", " + testCase2.yCoord);
		Token testCase3 = new Token("Mona Moon-walker");
		System.out.println(testCase3.character + ", " + testCase3.xCoord + ", " + testCase3.yCoord);
		Token testCase4 = new Token("George Galaxy-wanderer");
		System.out.println(testCase4.character + ", " + testCase4.xCoord + ", " + testCase4.yCoord);
		Token testCase5 = new Token("Uhura Universalist");
		System.out.println(testCase5.character + ", " + testCase5.xCoord + ", " + testCase5.yCoord);
		Token testCase6 = new Token("Steve Star-gazer");
		System.out.println(testCase6.character + ", " + testCase6.xCoord + ", " + testCase6.yCoord);
			
	}*/
	

	
}
