import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Janela extends JFrame
{
	private static Janela j = null;
	private int  LARG_DEFAULT=400;
	private int  ALT_DEFAULT=300;
	private Toolkit tk = Toolkit.getDefaultToolkit();
	private Dimension screenSize = tk.getScreenSize();
	private int sl = screenSize.width;
	private int sa = screenSize.height;
	private int x = sl / 2 - LARG_DEFAULT / 2;
	private int y = sa / 2 - ALT_DEFAULT / 2;
	
	public Janela()
	{
		setSize(LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void draw(JPanel p)
	{
		j.setBounds(x, y, LARG_DEFAULT, ALT_DEFAULT);
		j.setTitle("Batalha Naval =)");
		JButton b1 = new JButton("Iniciar");
		this.getContentPane().add(p);
		b1.setBounds(140,180, 100, 50);
		p.add(b1);
		b1.addActionListener(MenuJanela.getMenuJanela(j));
		p.setLayout(null);
		
		j.setVisible(true);
		
	}
	
	public static Janela getJanela()
	{
		if(j == null)
		{
			j = new Janela();
		}
	   
		return j;
	}
	
}