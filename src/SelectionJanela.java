
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectionJanela extends Janela implements ActionListener{
	private Janela j;
	
	public SelectionJanela(Janela j)
	{
		this.j = j;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		SelectionPanel p = new SelectionPanel();
		this.j.setVisible(false);
		this.setVisible(true);
		this.getContentPane().add(p);
	}

}
