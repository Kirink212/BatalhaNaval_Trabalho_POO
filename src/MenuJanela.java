import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;

import javax.swing.*;

public class MenuJanela extends Janela implements ActionListener{

	private static MenuJanela frame = null;
	private Janela j;
	
	private MenuJanela(Janela j) 
	{
		this.j = j;
	}

	public static MenuJanela getMenuJanela(Janela j)
	{
		if(frame == null)
		{
			frame = new MenuJanela(j);
		}
		
		return frame;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		DrawPlayerFrame v = new DrawPlayerFrame();
		this.j.setVisible(false);
		this.setVisible(true);
		v.Draw_Players(this,EXIT_ON_CLOSE);
	}
	
}
 