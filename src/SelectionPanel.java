import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class SelectionPanel extends JPanel{
    
    private  final double dimensao = 15;
    private final double tamquadrado = 32;
    private double dx=0,dy=0;
    
    public SelectionPanel() {
        
    }
    
    
    public void paintComponent(Graphics g)
    {    
        MainController main = MainController.getMainController();
        DrawWeaponsFrame d = new DrawWeaponsFrame(); 
        Player p = main.getActualPlayer();
        Matrix m = new Matrix(dimensao,dimensao, tamquadrado);
        m.drawMatrix(g, 700.0, 80.0);
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawString(p.getName()+",selecione uma arma na lista", 550, 635);
        d.drawHidroPlanes(g2d, tamquadrado, dx);
    }
}