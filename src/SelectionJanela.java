
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class SelectionJanela extends Janela implements ActionListener{
	private Janela j;
	private JTextField[] t = new JTextField[2];
	
	public SelectionJanela(Janela j, JTextField[] t)
	{
		this.j = j;
		this.t = t;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		SelectionPanel p = new SelectionPanel();
		MainController mainC = MainController.getMainController();
		mainC.createPlayers(this.t);
		this.j.setVisible(false);
		this.setVisible(true);
		this.setResizable(false);
		this.setSize(1280, 720);
		this.getContentPane().add(p);
	}

}
