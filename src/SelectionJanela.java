
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.peer.ComponentPeer;

import javax.swing.JButton;
import javax.swing.JTextField;

public class SelectionJanela extends Janela implements ActionListener{
	
	private Janela j;
	private int playerNum;
	private JTextField[] t = new JTextField[2];
	private int ALT_DEFAULT = 720;
	private int LARG_DEFAULT = 1280;
	private Toolkit tk = Toolkit.getDefaultToolkit();
	private Dimension screenSize = tk.getScreenSize();
	private int sl = screenSize.width;
	private int sa = screenSize.height;
	private int x = sl / 2 - LARG_DEFAULT / 2;
	private int y = sa / 2 - ALT_DEFAULT / 2;
	private MainController mainC = MainController.getMainController();
	
	public SelectionJanela(Janela j, JTextField[] t, int playerNum)
	{
		this.j = j;
		this.t = t;
		this.playerNum = playerNum;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		this.setBounds(x, y, LARG_DEFAULT, ALT_DEFAULT);
		SelectionPanel p = new SelectionPanel(this, this.t);
		if(playerNum == -1)
		{
			System.out.println("MEU DEUS, FUNCIONA PFV");
			mainC.createPlayers(this.t);
		}
		else
		{
			System.out.println("NUNCA TE PEDI NADA");
			mainC.changeActualPlayer();
		}
		this.j.setVisible(false);
		this.setResizable(false);
		this.setSize(LARG_DEFAULT, ALT_DEFAULT);
		this.setTitle("Posicionamento das Armas");
		this.getContentPane().add(p);
		
		p.setLayout(null);
		
		this.setVisible(true);
	}

}
