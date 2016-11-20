import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class SelectionPanel extends JPanel implements  MouseListener, MouseMotionListener {
    
    private  final double dimensao = 15;
    private final double tamquadrado = 32;
    private double dx=0,dy=0;
    private boolean dragging = false;
	private double mouseX;
	private double mouseY;
	private double currentX;
	private double currentY;
	
    public SelectionPanel() {
        
    }
    
    
    public void paintComponent(Graphics g)
    {    
        MainController main = MainController.getMainController();
        Matrix m = new Matrix(dimensao,dimensao, tamquadrado);
        this.addMouseListener(this);
        this.addMouseMotionListener(m);
        DrawWeaponsFrame d = new DrawWeaponsFrame(); 
        Player p = main.getActualPlayer();
        m.drawMatrix(g, 700.0, 80.0);
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawString(p.getName()+",selecione uma arma na lista", 550, 635);
        d.drawHidroPlanes(g2d, tamquadrado, dx);
        d.drawSubmarinos(g2d, tamquadrado, dx);
        d.drawDestroyers(g2d, tamquadrado, dx);
        d.drawCruzadores(g2d, tamquadrado, dx);
        d.drawCouracado(g2d, tamquadrado, dx);
        repaint();
    }
    
    public void mouseClicked(MouseEvent me) 
	{
		
	}

	public void mouseExited(MouseEvent me) 
	{
		
	}

	public boolean mouseCollided(MouseEvent mouse, double x, double y, double w, double h) 
	{
		return mouse.getX() < x+w && mouse.getY() < y+h && y < mouse.getY()+1 && x < mouse.getX()+1;
	}
	
	public void mouseEntered(MouseEvent me) 
	{
		
	}

	public void mousePressed(MouseEvent me) 
	{
		 Point point = me.getPoint();
		 System.out.println("mousePressed at " + point);
		 this.mouseX = point.x;
		 this.mouseY = point.y;
		 this.dragging = true;
	}
	public void mouseReleased(MouseEvent me)
	{
		 Point point = me.getPoint();
		 System.out.println("mouseReleased at " + point);
		 this.dragging = false;
	}

	public void mouseDragged(MouseEvent me) {
		 Point p = me.getPoint();
		 System.out.println("mouse Dragged to " + p);
		 currentX = p.x;
		 currentY = p.y;
	}

	public void mouseMoved(MouseEvent me) {
		
	}
}