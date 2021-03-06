import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;

import javax.swing.*;

public class DrawPlayerFrame {

	private int LARG_DEFAULT = 300;
	private int ALT_DEFAULT = 200;
	private final int qtdJogadores = 2;
	private int i;		
	private JTextField j[] = new JTextField[qtdJogadores];
	private Player p[] = new Player[qtdJogadores];
	private MenuPanel MenuJogadores;
	private JButton Inicia;
	private Dimension size;
	private Insets borda;
	private MainController mainC = MainController.getMainController();
	private Toolkit tk = Toolkit.getDefaultToolkit();
	private Dimension screenSize = tk.getScreenSize();
	private int sl = screenSize.width;
	private int sa = screenSize.height;
	private int x = sl / 2 - LARG_DEFAULT / 2;
	private int y = sa / 2 - ALT_DEFAULT / 2;
	
	
	public void Draw_Players(MenuJanela ref,int CloseWindow)
	{
	
		ref.setBounds(x, y, LARG_DEFAULT, ALT_DEFAULT);
		ref.setDefaultCloseOperation(CloseWindow);
	
		MenuJogadores = new MenuPanel();
		ref.getContentPane().add(MenuJogadores);
		MenuJogadores.setLayout(null);

		Inicia = new JButton("Come�ar");
		MenuJogadores.add(Inicia);
		size = Inicia.getPreferredSize();
		borda = MenuJogadores.getInsets();
		Inicia.setBounds(borda.left + 92, borda.top+110  , size.width + 15, size.height+ 10);
		
		for(i=0;i<qtdJogadores;i++)
		{
			j[i] = new JTextField(15);
			MenuJogadores.add(j[i]);
		
			size = j[i].getPreferredSize();
			j[i].setBounds(borda.left+90,borda.top+35 +(i*40),size.width,size.height);
			mainC.addPlayer();
		}
		
		Inicia.addActionListener(new SelectionJanela((Janela)ref, j, -1));
		
		
		ref.setTitle("Jogadores");
		ref.setVisible(true);
}
}
