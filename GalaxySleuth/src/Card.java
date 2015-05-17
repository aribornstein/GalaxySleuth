import java.io.Serializable;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;


public class Card  extends JPanel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6967938867951784469L;
	String type;
	String name;
	String imageFilepath;
	
	
	public Card(String type, String name, String imageFilepath) {
		super();
		this.type = type;
		this.name = name;
		this.imageFilepath = imageFilepath;
	}
	
	public String toString(){
		return "Name: "+name+", Type: "+type;
	}
	
	public BufferedImage cardImage(){
		BufferedImage image=null;
		try{
		image = ImageIO.read(new File(this.imageFilepath));
		System.out.println("the width of the image of "+this.name+" is "+ image.getWidth());
		System.out.println("the height of the image of "+this.name+" is "+ image.getHeight());
	} catch (IOException e) {
        System.err.println("Error reading file: " + imageFilepath);
	}
	
	
	return image;
	}
	public Graphics2D cardGraphics(){
		Graphics2D graphic = this.cardImage().createGraphics();
		return graphic;
	}
		
	public JPanel physicalCard(){

		JPanel card = new JPanel();
//		Dimension size = new Dimension();
//		BufferedImage image = this.cardImage();
//		Graphics graphic = this.cardGraphics();
//		size.setSize(image.getWidth(), image.getHeight());
		
		if (this.type =="character"){
			card.setBackground(Color.green);
		}
		if (this.type=="weapon"){
			card.setBackground(Color.red);
		}
		else {card.setBackground(Color.blue);}
		
//		int x = (image.getWidth() - size.width)/2;
//        int y = (image.getHeight() - size.height)/2;
//        graphic.drawImage(image, x, y, card);		
		
		//Dimension cardSize = new Dimension(30,50);
		
	//	card.setSize(cardSize);
	//	Canvas canvas = new Canvas();
	//	canvas.setSize(28,49);
	//	card.add(canvas);
	//	canvas.paint(this.cardGraphics());

	

		//add cardTitle
		
		card.setBorder(BorderFactory.createLineBorder(Color.black));
		//add cardImage
		//build card and make visible
		card.revalidate();
		card.setVisible(true);
		//return card
		return card;
	}

	

}
