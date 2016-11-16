import java.awt.event.ActionListener;

import javax.swing.*;

public class Janela extends JFrame
{
	private static Janela j = null;
	public final int  LARG_DEFAULT=1280;
	public final int  ALT_DEFAULT=720;
	
	public Janela()
	{
		setSize(LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void draw(JPanel p)
	{
		JButton b1 = new JButton("Iniciar");
		this.getContentPane().add(p);
		b1.setBounds(500, 500, 100, 50);
		p.add(b1);
		b1.addActionListener(MenuJanela.getMenuJanela(j));
		p.setLayout(null);
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