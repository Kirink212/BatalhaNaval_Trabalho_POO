import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class MainPanel extends JPanel{
	
	public MainPanel()
	{
		
	}
	
	public void paint(Graphics g)
	{	
		Graphics2D g2d = (Graphics2D)g;
        g2d.drawString("Batalha Naval Delícia", 130, 120);
	}
}
