import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class SelectionPanel extends JPanel implements  MouseListener, MouseMotionListener {
    
    private  final double dimensao = 15;
    private final double tamquadrado = 32;
    private double dx_couracado=30.0,dy_couracado=465.0;
    private double dx=30,dy=0;
    private boolean pressed = false;
	private double mouseX;
	private double mouseY;
	private boolean couracado = false;
	private double actual_weapon_w = 5*this.tamquadrado;
	private double actual_weapon_h = this.tamquadrado;
	
    public SelectionPanel() {
        
    }
    
    
    public void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
        MainController main = MainController.getMainController();
        Matrix m = new Matrix(dimensao,dimensao, tamquadrado);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        DrawWeaponsFrame d = new DrawWeaponsFrame(); 
        Player p = main.getActualPlayer();
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawString(p.getName()+",selecione uma arma na lista", 550, 635);
        d.drawHidroPlanes(g2d, tamquadrado, dx, dy);
        d.drawSubmarinos(g2d, tamquadrado, dx, dy);
        d.drawDestroyers(g2d, tamquadrado, dx, dy);
        d.drawCruzadores(g2d, tamquadrado, dx, dy);
        d.drawCouracado(g2d, tamquadrado, dx_couracado, dy_couracado);
        m.drawMatrix(g, this.mouseX, this.mouseY, 700.0, 80.0, this.actual_weapon_w, this.actual_weapon_h);
    }
    
    public void mouseClicked(MouseEvent me) 
	{
		
	}

	public void mouseExited(MouseEvent me) 
	{
		
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
		 if(mouseCollided( this.mouseX, this.mouseY, this.dx_couracado, this.dy_couracado, tamquadrado, tamquadrado))
		 {
			 this.couracado = true;
		 }
		 //this.pressed = true;
	}
	public void mouseReleased(MouseEvent me)
	{
		 
	}

	public void mouseDragged(MouseEvent me) {
		 
	}

	public void mouseMoved(MouseEvent me) {
		 Point point = me.getPoint();
		 System.out.println("mousePressed at " + point);
		 this.mouseX = point.x;
		 this.mouseY = point.y;
		 if(this.couracado)
		 {
			 this.dx_couracado = this.mouseX;
			 this.dy_couracado = this.mouseY;
			 this.repaint();
		 }
		 
		 if(this.pressed)
		 {
			 this.dx = this.mouseX;
			 this.dy = this.mouseY;
			 this.repaint();
		 }
	}
	
	public boolean mouseCollided( double mouse_x, double mouse_y, double x, double y, double w, double h) 
	{
		return (mouse_x < x+w && mouse_y < y+h && y < mouse_y+1 && x < mouse_x+1);
	}
}