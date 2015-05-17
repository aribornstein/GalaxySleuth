import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Notebook widget to be displayed for player use only.
 * Submits nothing to the server
 * Extends JPanel and should be added in similar fashion
 */


public class NotebookWidget extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 619543447511321792L;
	JPanel characterPanel;
	JPanel weaponPanel;
	JPanel planetPanel;
	
  public static void main(String[] args) {
    new NotebookWidget();
  }

  public NotebookWidget() {
    //initialize as JPanel
	super();
    
    //set up layout
    this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    planetPanel = new JPanel();
    weaponPanel = new JPanel();
    characterPanel = new JPanel();
    this.add(characterPanel);
    this.add(Box.createVerticalGlue());
    this.add(weaponPanel);
    this.add(Box.createVerticalGlue());
    this.add(planetPanel);
   
    characterPanel.setLayout(new BoxLayout(characterPanel, BoxLayout.PAGE_AXIS));
    weaponPanel.setLayout(new BoxLayout(weaponPanel, BoxLayout.PAGE_AXIS));
    planetPanel.setLayout(new BoxLayout(planetPanel, BoxLayout.PAGE_AXIS));
    
    
    //characters on left panel
    characterPanel.add(new JLabel("Characters"));
    characterPanel.add(new JCheckBox("Kara Tiberius"));
    characterPanel.add(new JCheckBox("Valen Atreides"));
    characterPanel.add(new JCheckBox("B'Elanna Wiggin"));
    characterPanel.add(new JCheckBox("Jayne Sheridon"));
    characterPanel.add(new JCheckBox("Aeryn Ackbar"));
    characterPanel.add(new JCheckBox("Rodney Picard"));
    
    //weapons on left panel
    weaponPanel.add(new JLabel("Weapons"));
    weaponPanel.add(new JCheckBox("Disruptor"));
    weaponPanel.add(new JCheckBox("Crysknife"));
    weaponPanel.add(new JCheckBox("Daemon Hammer"));
    weaponPanel.add(new JCheckBox("Noisy Cricket"));
    weaponPanel.add(new JCheckBox("Bat'leth"));
    weaponPanel.add(new JCheckBox("Particle Projector Cannon"));
    
    //planets on right panel
    planetPanel.add(new JLabel("Planets"));
    planetPanel.add(new JCheckBox("Caprica"));
    planetPanel.add(new JCheckBox("Earth That Was"));
    planetPanel.add(new JCheckBox("Feranginar"));
    planetPanel.add(new JCheckBox("Arrakis"));
    planetPanel.add(new JCheckBox("Sheldon Alpha 5"));
    planetPanel.add(new JCheckBox("Miranda"));
    planetPanel.add(new JCheckBox("Perelandra"));
    planetPanel.add(new JCheckBox("Sateda"));
    planetPanel.add(new JCheckBox("Endor"));
  }
}