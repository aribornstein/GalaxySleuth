import java.io.Serializable;


public class Board implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1138643955698818314L;
	/*
	Top coordinate 0,0
	Board size  [25,24]
	Board objects 
	•	Linuta  [(0-6),(0-3)]
	•	Psu [(1-5),6] [(1-5),10][(0-6),(7-9)]
	•	Ping[(0-5),(12-15)]
	•	Evilon [(1-4),19] [(0-5),(20-23)]
	•	Neudel [(8-15),(17-22)] [(10-13),(23)]
	•	Verlute [(19-23),(19-23)]  [(18-22),18]
	•	Cathitar [(19-23),15] [(16-23),(9-14)]
	•	Earth [(17-23), (1-5)] [(16-23),0]
	•	Phaedrun [(9-14),(0-6)]
	•	Spinner [(9-13),(8-15)]

	Doors 
	•	Linuta [6,3]
	•	Psu  [6,8] [3,10]
	•	Ping[1,12] [5,15]
	•	Evilon [4,19]
	•	Neudel [8,19][9,17][15,19]
	•	Verlute[19,18] 
	•	Cathitar[16,12][17,9] 
	•	Earth [17,5]
	•	Phaedrun [11,6][12,6][9,4]

	*/
	Cell [][] board = new Cell[25][24];
	
	public Board()
	{
	 //create linuta 
	 for( int x =0; x<=6;x++)
	 {
	   for (int y =0; y<=3;y++)
	   {
	    if ((x==6)&&(y==3))board[x][y]= new Cell(x,y,"==");
	    else board[x][y]= new Cell(x,y,"la");
	   }
	 }
	 //create psu
	 for( int x =1; x<=5;x++)
	 {
	    board[x][6]= new Cell(x,6,"pu");
	    if (x==3)board[x][10]= new Cell(x,10,"==");// door
	    else board[x][10]= new Cell(x,10,"pu");// not door
	 }
	 for( int x =0; x<=6;x++)
	 {
	   for (int y =7; y<=9;y++)
	   {
	      if ((x==6)&&(y==8))board[x][y]= new Cell(x,y,"==");//door
	      else board[x][y]= new Cell(x,y,"pu");//planet
	   }
	 }
	 //create ping   
	 for( int x =0; x<=5;x++)
	 {
	  for (int y =12; y<=15;y++)
	  {
	    if (((x==1)&&(y==12))||((x==5)&&(y==15)))board[x][y]= new Cell(x,y,"==");// door
	    else board[x][y]= new Cell(x,y,"pg");//not door
	  }
	 }
	 //create evilon
	 for (int x=1; x<=4;x++)
	 {
	   if (x==4)board[x][19]= new Cell(x,19,"==");// door
	   else board[x][19]= new Cell(x,19,"en");//not door   
	 }
	 for( int x=0; x<=5;x++)
	 {
	   for (int y =20; y<=23;y++)
	   {
	     board[x][y]= new Cell(x,y,"en");
	   }
	 }
	 //create neudel
	 for( int x=8; x<=15;x++)
	 {
	  for (int y=18; y<=22;y++)
	  {
	     if (((x==8)&&(y==20))||((x==9)&&(y==18))||((x==15)&&(y==20))||((x==14&&y==18)))board[x][y]= new Cell(x,y,"==");//door
	     else board[x][y]= new Cell(x,y,"nl");//not door
	  }
	 }
	 for( int x=10; x<=13;x++)
	 {
	  board[x][23]= new Cell(x,23,"nl"); 
	 }
	 //create verlute
	 for(int x=19;x<=23;x++)
	 {
	  for (int y=19;y<=23;y++)
	  {
	    board[x][y]= new Cell(x,y,"ve");
	  }
	 }
	 for(int x=18;x<=22;x++)
	 {
	  if (x==19)board[x][18]= new Cell(x,18,"==");//door
	  else board[x][18]= new Cell(x,18,"ve");//not door
	 }
	 //create cathitar
	 //Really?? Cathitar? Like the thing you use to pee in in a hospital?? Wtf
	 for(int x=19;x<=23;x++)
	 { 
	  board[x][15]= new Cell(x,5,"cr");
	 }
	 for(int x=16;x<=23;x++)
	 {
	  for (int y=9;y<=14;y++)
	  {
	   if (((x==16)&&(y==12))||((x==17)&&(y==9)))board[x][y]= new Cell(x,y,"==");//door
	   else board[x][y]= new Cell(x,y,"cr");//not door
	  }
	 }
	 //create earth 
	 for(int x=18;x<=23;x++)
	 {
	  for (int y=1;y<=5;y++)
	  {
	   if ((x==18)&&(y==5))board[x][y]= new Cell(x,y,"==");//door
	   else board[x][y]= new Cell(x,y,"eh");//not door
	  }
	 }
	 for(int x=17;x<=23;x++)
	 {
	  board[x][0]= new Cell(x,0,"eh");
	 }
	 //create phaedrun 
	 for(int x=9;x<=14;x++)
	 {
	  for (int y=0;y<=6;y++)
	  {
	   if ((((x==11)||(x==12))&&(y==6))||((x==9)&&(y==4)))board[x][y]= new Cell(x,y,"==");//door
	   else board[x][y]= new Cell(x,y,"pn");//not door
	  }
	 }
	 //create spinner
	 for(int x=9;x<=13;x++)
	 {
	   for (int y=10;y<=15;y++)
	   {
	     board[x][y]= new Cell(x,y,"sp");
	   }
     }
	 //create regular cells
	 for( int x =0; x<25;x++)
	 {
	  for (int y =0; y<24;y++)
	  {        
	     if (board[x][y]==null)board[x][y]= new Cell(x,y,"##");
	  }
	 }
    }

	public Cell getLeftOf(Token current)
	{
		return board [current.xCoord+1][current.yCoord];
	}
	public Cell getRightOf(Token current)
	{
		return board [current.xCoord-1][current.yCoord];
	}
	public Cell getAboveOf(Token current)
	{
		return board [current.xCoord][current.yCoord-1];
	}
	public Cell getBelowOf(Token current)
	{
		return board [current.xCoord][current.yCoord+1];	
	}
	
	public static void main(String[] args) 
	{
		Board myBoard = new Board();
		for (int y =0; y<24;y++)
		{
	     for( int x =0; x<25;x++)
		 {
	    	 System.out.print( myBoard.board[x][y].getType()+ ",");
		 }
		 System.out.println("\n");
		}
	}
}
