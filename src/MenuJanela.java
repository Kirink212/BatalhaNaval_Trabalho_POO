import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;

import javax.swing.*;

public class MenuJanela extends Janela implements ActionListener{

	private Janela j;
	
	public  MenuJanela(Janela j) 
	{
		this.j = j;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		DrawPlayerFrame v = new DrawPlayerFrame();
		this.j.setVisible(false);
		v.Draw_Players(this,EXIT_ON_CLOSE);
	}
	
}
 