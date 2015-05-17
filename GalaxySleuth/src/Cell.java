import java.awt.Image;
import java.awt.Toolkit;
import java.io.Serializable;

public class Cell implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2132127290203876749L;
	int xCoord;
	int yCoord;
	String type;
	

	
	public Cell(int xcoord, int ycoord, String type) {
		super();
		xCoord = xcoord;
		yCoord = ycoord;
		this.type = type;
	}
	public int getXcoord(){
		int x = this.xCoord;
		return x;
	}
	public int getYcoord(){
		int y = this.yCoord;
		return y;
	}

	public String getType() {
		return type;
	}
	
	public Image createCellDsiplay(Cell cell){
		Image img = null;
		if (cell.type == "Regular"){
			img = Toolkit.getDefaultToolkit().getImage("http:/www.apple.com");
		
		return img;
		}
		if (cell.type == "Planet"){
			img = Toolkit.getDefaultToolkit().getImage("http:/www.apple.com");
		}
		return img;
	}

}
