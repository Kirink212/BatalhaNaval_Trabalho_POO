import java.awt.Graphics;
import javax.swing.JPanel;

public class SelectionJanela extends JPanel{
	
	public SelectionJanela() {
		
	}
	
	public void paintComponent(Graphics g)
	{	
		Matrix m = new Matrix(15, 15, 32);
		m.drawMatrix(g, 50.0, 100.0);
	}
}
