
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class SelectionJanela extends Janela implements ActionListener{
	private Janela j;
	private JTextField[] t = new JTextField[2];
	private int ALT_DEFAULT = 720;
	private int LARG_DEFAULT = 1280;
	private Toolkit tk = Toolkit.getDefaultToolkit();
	private Dimension screenSize = tk.getScreenSize();
	private int sl = screenSize.width;
	private int sa = screenSize.height;
	private int x = sl / 2 - LARG_DEFAULT / 2;
	private int y = sa / 2 - ALT_DEFAULT / 2;
	
	public SelectionJanela(Janela j, JTextField[] t)
	{
		this.j = j;
		this.t = t;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int sl = screenSize.width;
		int sa = screenSize.height;
		int x = sl / 2 - LARG_DEFAULT / 2;
		int y = sa / 2 - ALT_DEFAULT / 2;
		
		this.setBounds(x, y, LARG_DEFAULT, ALT_DEFAULT);
		
		SelectionPanel p = new SelectionPanel();
		MainController mainC = MainController.getMainController();
		mainC.createPlayers(this.t);
		this.j.setVisible(false);
		this.setResizable(false);
		this.setSize(LARG_DEFAULT, ALT_DEFAULT);
		this.setTitle("Batalha Naval");
		//this.pack();
		this.getContentPane().add(p);
		this.setVisible(true);
	}

}
