
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectionPanel extends Janela implements ActionListener{
	private Janela j;
	
	public SelectionPanel(Janela j)
	{
		this.j = j;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		SelectionJanela p = new SelectionJanela();
		System.out.println("Rola no seu cu");
		this.j.setVisible(false);
		this.setVisible(true);
		this.getContentPane().add(p);
	}

}
