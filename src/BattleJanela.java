import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class BattleJanela extends Janela implements ActionListener {
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
	private MainController mainC = MainController.getMainController();
	
	public BattleJanela(Janela j)
	{
		this.j = j;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		this.setBounds(x, y, LARG_DEFAULT, ALT_DEFAULT);
		//SelectionPanel p = new SelectionPanel(this, this.t);
		this.j.setVisible(false);
		this.setResizable(false);
		this.setSize(LARG_DEFAULT, ALT_DEFAULT);
		this.setTitle("Jantar a luz de velas");
		//this.getContentPane().add(p);
		
		//p.setLayout(null);
		
		this.setVisible(true);
	}
}
