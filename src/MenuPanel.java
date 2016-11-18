import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
	
	public static final int TXT_X=20;
	public static final int TXT_Y=50;
	
	public MenuPanel()
	{
	
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawString("Jogador 1: ",TXT_X,TXT_Y);
		g.drawString("Jogador 2: ",TXT_X,TXT_Y+40);
	}	
}
