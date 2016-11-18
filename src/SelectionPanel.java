import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class SelectionPanel extends JPanel{
    
    private  final double dimensao = 15;
    private final double tamquadrado = 32;
    private int i;
    private double dx=0,dy=0;
    
    public SelectionPanel() {
        
    }
    
    public void paintComponent(Graphics g)
    {    
        MainController main = MainController.getMainController();
        Player p = main.getActualPlayer();
        Matrix m = new Matrix(dimensao,dimensao, tamquadrado);
        m.drawMatrix(g, 700.0, 80.0);
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawString(p.getName()+",selecione uma arma na lista", 550, 635);
        
        for(i=0;i<3;i++)
        {
            g2d.draw(new Rectangle2D.Double(65.0+dx,90.0,tamquadrado,tamquadrado));
            g2d.draw(new Rectangle2D.Double(97.0+dx,58.0,tamquadrado,tamquadrado));
            g2d.draw(new Rectangle2D.Double(129.0+dx,90.0,tamquadrado,tamquadrado));
            dx = 125*i +dx;
            System.out.println(Double.toString(dx));
        }
    }
}