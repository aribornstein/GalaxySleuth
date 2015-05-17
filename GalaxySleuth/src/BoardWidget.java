
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.net.*;

import javax.swing.*;
import java.awt.*;



public class BoardWidget extends JPanel implements ActionListener  {
	URL theURL;
	public Token myToken;
	String[][] boardArray = new String[24][25];
	JButton[][] buttonArray = new JButton[24][25];
	public boolean activated= true;
	public Game myGame;
	public int spin;
	public int myUserNumber;
	private static final long serialVersionUID = 619543447511321792L;
	
	
	
	public BoardWidget(URL aURL, Token myToken, Game myGame) {
	    //initialize as JPanel
		super();
		this.myGame = myGame;
		
		
		for(int i = 0; i < myGame.userList.size(); i ++){
			if(myToken.color.equals(myGame.userList.get(i).token.color)){
				this.myToken = myGame.userList.get(i).token;
				this.myUserNumber = i;
			}
		}
		
		theURL = aURL;
		
		/* The strings in boardArray will give allow us to check 
		 * what type of button we are on as well as valid moves*/
		
		//make the planets
		//earth
		for(int a=0;a<4;a++){
			for(int b=0;b<7;b++){
				boardArray[a][b]="earth";
			}
		}
		//miranda
		for(int q=6;q<11;q++){
			for(int w=0;w<7;w++){
				boardArray[q][w]="miranda";
			}
		}
		boardArray[6][0]="regular";
		boardArray[6][6]="regular";
		boardArray[10][0]="regular";
		boardArray[10][6]="regular";
		
		//endor
		for(int a=12;a<16;a++){
			for(int b=0;b<6;b++){
				boardArray[a][b]="endor";
			}
		}
		
		//perelandra
		for(int a=19;a<24;a++){
			for(int b=0;b<6;b++){
				boardArray[a][b]="perelandra";
			}
		}
		boardArray[19][0]="regular";
		boardArray[19][5]="regular";
		
		//sateda
		for(int a=0;a<7;a++){
			for(int b=9;b<15;b++){
				boardArray[a][b]="sateda";
			}
		}
		
		//spinner
		for(int a=10;a<16;a++){
			for(int b=9;b<14;b++){
				boardArray[a][b]="spinner";
			}
		}
		
		//alpha 5
		for(int a=18;a<24;a++){
			for(int b=8;b<16;b++){
				boardArray[a][b]="alpha 5";
			}
		}
		boardArray[23][8]="regular";
		boardArray[23][9]="regular";
		boardArray[23][14]="regular";
		boardArray[23][15]="regular";
		
		//ferenginar
		for(int a=0;a<6;a++){
			for(int b=18;b<24;b++){
				boardArray[a][b]="ferenginar";
			}
		}
		boardArray[0][17]="ferenginar";
		
		//arrakis
		for(int a=9;a<16;a++){
			for(int b=16;b<24;b++){
				boardArray[a][b]="arrakis";
			}
		}
		boardArray[15][16]="regular";
		boardArray[15][17]="regular";
		boardArray[15][18]="regular";
		
		//caprica
		for(int a=18;a<24;a++){
			for(int b=19;b<24;b++){
				boardArray[a][b]="caprica";
			}
		}
		boardArray[18][23]="regular";
		boardArray[18][18]="caprica";
		
		
		//make the doors
		boardArray[3][6]="door";
		boardArray[8][6]="door";
		boardArray[10][3]="door";
		boardArray[12][1]="door";
		boardArray[14][5]="door";
		boardArray[19][4]="door";
		boardArray[4][9]="door";
		boardArray[6][11]="door";
		boardArray[6][12]="door";
		boardArray[20][8]="door";
		boardArray[18][9]="door";
		boardArray[18][14]="door";
		boardArray[20][15]="door";
		boardArray[5][18]="door";
		boardArray[9][17]="door";
		boardArray[12][16]="door";
		boardArray[18][19]="door";
		
		//make everything else a regular cell
		for(int a=0;a<24;a++){
			for(int b=0;b<25;b++){
				if(boardArray[a][b]==null){
					boardArray[a][b]="regular";
				}
			}
		}
		
		 
		
		
			//JButton[][] buttonArray = new JButton[24][25];
			String buttonName = new String();

			//create frame (this will eventually be the board widget panel, instead)
			//JFrame f = new JFrame();
			//box layout will flow vertically
			this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));


			for(int x=0; x < 24;x++){
				String row = "row"+x;
				for(int y=0; y < 25;y++){

			//making the button.
					String column = "column"+y;
					buttonName = column+row;

					ImageIcon buttonImage = new ImageIcon("G://ButtonImages" + "/"+buttonName+".gif");
					JButton button = new JButton(buttonImage);
					button.setPreferredSize(new Dimension(26,26));
					button.setBorder(null);
					
					buttonArray[x][y] = button;
					//add the actionListener
					button.addActionListener(this);
				}
			}
				
				JButton addedButton = new JButton();
				for (int p = 0; p < 24; p++) {
					JPanel thePanel = new JPanel(new FlowLayout(1006,0,0));
					for (int q = 0; q < 25; q++) {
						addedButton = buttonArray[p][q];
						thePanel.add(addedButton);
					}
					add(thePanel);
				}
				//f.pack();
				//f.setVisible(true);
				
			}
	
	public boolean isMoveValid(int newX, int newY){
		boolean valid = false;
		int originalX = myGame.userList.get(myUserNumber).token.xCoord;
		int originalY = myGame.userList.get(myUserNumber).token.yCoord;
		int dX = newX - originalX;
		int dY = newY - originalY;
		
		System.out.printf("IsMoveValid: (%d,%d) ->(%d,%d)",originalX,originalY,newX,newY);
		
		
		//System.out.println("Dx is " + dX + " and Dy is " + dY);
		
		if(boardArray[originalX][originalY]==boardArray[newX][newY]){
			valid=true;
		}
		if(boardArray[newX][newY]=="door"){
			valid=true;
		}
		if(boardArray[originalX][originalY]=="door"){
			valid=true;
		}
		if(Math.abs(dX)>1||Math.abs(dY)>1){
			
			valid=false;
		}
		//diagonals
		if(Math.abs(dX)==Math.abs(dY)) {
			valid=false;
		}
		if(dX==0&&dY==0){
			valid=false;
		}
		System.out.println("Valid? "+valid);
		return valid;
	}
	
	public String getMoveDirection(int newX, int newY){
		
		//Make this return N, S, E, W 
		int dX; //newX - X
		int dY; //newY - Y
		//System.out.printf("the values are:%d, %d, %d, %d",newX,newY,myGame.userList.get(myUserNumber).token.xCoord,myGame.userList.get(myUserNumber).token.yCoord);
		
		dX = newX - myGame.userList.get(myUserNumber).token.xCoord;
		dY = newY - myGame.userList.get(myUserNumber).token.yCoord;
		
		if(dY==0 && dX == 1){
			return "S";
		} else if(dY==0 && dX == -1){
			return "N";
		} else if (dY==1 && dX == 0){
			return "E";
		} else if (dY==-1 && dX == 0){
			return"W";
		} else{
			return "XXX";
		}
		
	}
	
	public void drawTokens(Token myToken, int newX, int newY){
		int x = myGame.userList.get(myUserNumber).token.xCoord;
		int y = myGame.userList.get(myUserNumber).token.yCoord;
		//Set the starting locations image back to the original image
		String buttonName = "column"+y+"row"+x;
		
		ImageIcon original = new ImageIcon("G://ButtonImages/"+buttonName+".gif");
		//JButton newButton = new JButton(original);
		buttonArray[x][y].setIcon(original);
		//buttonArray[x][y]=newButton;
		
		//Set the ending location to the token image
		String finalButtonName = myToken.color;
		ImageIcon newLocation = new ImageIcon("G://ButtonImages/"+finalButtonName+".gif");

		buttonArray[newX][newY].setIcon(newLocation);
		
		this.revalidate();
	}
	
	public void drawTokenToSquare(User theUser) {
		int x = theUser.token.xCoord;
		int y = theUser.token.yCoord;
		
		//Set new token image location
		String buttonName = theUser.token.color;
		ImageIcon tokenImage = new ImageIcon("G://ButtonImages" + "/"+buttonName+".gif");
		buttonArray[x][y].setIcon(tokenImage);
	}
	
	public void removeToken(int x, int y){
		
		String buttonName = "column"+y+"row"+x;
    	buttonArray[x][y].setIcon(new ImageIcon("G:\\ButtonImages" + "\\"+buttonName+".gif"));
		
	}
	
	public void drawUpdatedToken(User updatedUser){
		//System.out.println("DrawUpdatedToken called");
		Token updatedToken = updatedUser.token;	
		
		
		User originalUser = null;
		
		
		//Find the token which is being updated
		for(int i = 0; i < myGame.userList.size(); i++){
			if(updatedUser.userName.equals(myGame.userList.get(i).userName)){
				originalUser = myGame.userList.get(i);
				
			}
		}		
		int xNew = updatedToken.xCoord;
		int yNew = updatedToken.yCoord;
		
		int xOld = originalUser.token.xCoord;
		int yOld = originalUser.token.yCoord;
		
	
		System.out.printf("In draw Updated token %s Token is moved from (%d, %d) to (%d, %d)",updatedUser.token.color,xOld,yOld,xNew,yNew);
		
		
		//Set original image back to what it was
		String buttonName = "column"+yOld+"row"+xOld;
		ImageIcon original = new ImageIcon("G://ButtonImages" + "/"+buttonName+".gif");
		buttonArray[xOld][yOld].setIcon(original);
		
		//Set new token image location
		String finalButtonName = updatedToken.color;
		ImageIcon newLocation = new ImageIcon("G://ButtonImages" + "/"+finalButtonName+".gif");
		buttonArray[xNew][yNew].setIcon(newLocation);
		
		this.revalidate();
		
		
	}

		

	public void actionPerformed(ActionEvent event) {
		
		
        //if make accusation button was clicked
		if(activated){
		for(int i=0;i < 24;i++){			
			for(int j=0;j < 25;j++){
	        if(event.getSource().equals(buttonArray[i][j])) //Filenames for each button are /columnJrowI.gif
	        {	        	
	        	System.out.println("actionPerformed: Button of type "+boardArray[i][j]+" pressed at: "+i+" , "+j);	        	
	        	
	        	if(isMoveValid(i, j)){

	        		//Old and new planet typse
	        		String oldType = boardArray[myGame.userList.get(myUserNumber).token.xCoord][myGame.userList.get(myUserNumber).token.yCoord];
	        		String newType = boardArray[i][j];
	        		
	        		
	        		
	        		
	        		//Planet to Planet
	        		if(oldType == newType && newType != "regular" && newType != "door"){
	        			drawTokens(myToken,i,j);
	        			myGame.userList.get(myUserNumber).token.xCoord = i;
		        		myGame.userList.get(myUserNumber).token.yCoord = j;	      
		        	//Regular to regular	
	        		}else if (oldType == "regular" && newType == "regular" && spin > 0){
	        			drawTokens(myToken,i,j);
		        		myGame.userList.get(myUserNumber).token.xCoord = i;
		        		myGame.userList.get(myUserNumber).token.yCoord = j;
		        		spin--;
		        		updateSpin(spin);
		        	// Regular to door
	        		} else if (oldType == "regular" && newType == "door" && spin > 0){
	        			spin = 0;
	        			updateSpin(spin);
	        			drawTokens(myToken,i,j);
		        		myGame.userList.get(myUserNumber).token.xCoord = i;
		        		myGame.userList.get(myUserNumber).token.yCoord = j;
		        	// Door to planet	
	        		} else if (oldType == "door" && newType != "regular"){
	        			drawTokens(myToken,i,j);
		        		myGame.userList.get(myUserNumber).token.xCoord = i;
		        		myGame.userList.get(myUserNumber).token.yCoord = j;
		        	// Planet to door
	        		} else if ( oldType != "regular" && newType == "door"){
	        			drawTokens(myToken,i,j);
		        		myGame.userList.get(myUserNumber).token.xCoord = i;
		        		myGame.userList.get(myUserNumber).token.yCoord = j;
		        	//Door to regular
	        		} else if( oldType == "door" && newType == "regular" && spin >0){
	        			drawTokens(myToken,i,j);
		        		myGame.userList.get(myUserNumber).token.xCoord = i;
		        		myGame.userList.get(myUserNumber).token.yCoord = j;
		        		spin--;
		        		updateSpin(spin);
	        		}	        		
	        		
	        		
	        	}
				
	        	}
			}
			
		}
		//myGame.userList.get(myUserNumber).token = myToken;
		
	}
	
	
		
}
	
	public void updateSpin(int spin) {
		//col11row13
		ImageIcon spinNum = new ImageIcon("G://ButtonImages" + "/spin"+spin+".gif");
		//JButton newButton = new JButton(original);
		buttonArray[13][11].setIcon(spinNum);
		this.revalidate();
	}
	
	/*
	public static void main(String[] args){
		URL U;
		JFrame f = new JFrame("Test");
		try {
			U = new URL("http://google.com");
			//Token aToken = new Token("Valen Atreides");
			//Token aToken = new Token("Kara Tiberius");
			//Token aToken = new Token("B'Elanna Wiggin");
			//Token aToken = new Token("Jayne Sheridon");
			//Token aToken = new Token("Aeryn Ackbar");
			Token aToken = new Token("Rodney Picard");
			System.out.println("The token's starting position is:"+aToken.xCoord+","+aToken.yCoord);
			Game aGame = new Game();
			BoardWidget b = new BoardWidget(U, aToken, aGame);
			b.revalidate();
			
			
			
			
			b.myToken.print();
			
			b.spin = 6;
			b.updateSpin(b.spin);
			//aGame.print();
			
			
			
			f.add(b);
			f.pack();
			f.show();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		
	}*/
}


