import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class DrawWeaponsFrame {
	
	public DrawWeaponsFrame()
	{
		
	}
	
	public void drawHidroPlanes(Graphics2D g2d, double tam, double dx, double dy)
	{
		int i;
		Rectangle2D r1,r2,r3;
		
		for(i=0;i<5;i++)
        {
			g2d.setPaint(Color.GREEN);
			
			r1 = new Rectangle2D.Double(dx,dy,tam,tam);
			r2 = new Rectangle2D.Double(tam+dx,-tam+dy,tam,tam);
			r3 = new Rectangle2D.Double(2*tam+dx, dy,tam,tam);
			
			g2d.fill(r1);
			g2d.fill(r2);
			g2d.fill(r3);
			
        }
        dx=0;
	}
	
	public void drawSubmarinos(Graphics2D g2d,double tam,double dx, double dy)
	{
		int i;
		Rectangle2D r1;
		
		for(i=0;i<4;i++)
        {	
			r1 = new Rectangle2D.Double(dx , dy,tam,tam);
			
			g2d.setPaint(Color.CYAN);
			
            g2d.fill(r1);
            
        }
        dx=0;
	}
	
	public void drawDestroyers(Graphics2D g2d,double tam,double dx,double dy)
	{
		int i;
		Rectangle2D r1;
		
		for(i=0;i<3;i++)
        {
			r1 = new Rectangle2D.Double(dx,dy,2*tam,tam);
            
			g2d.setPaint(Color.YELLOW);
			
            g2d.fill(r1);

        }
		
        dx=0;
		
	}
	
	public void drawCruzadores(Graphics2D g2d,double tam,double dx,double dy)
	{
		int i;
		Rectangle2D r1;
		
		for(i=0;i<2;i++)
        {
			r1= new Rectangle2D.Double(dx,dy,4*tam,tam);
            
            g2d.setPaint(Color.ORANGE);
			
            g2d.fill(r1);
			
        }
		
        dx=0;
	}
	
	public void drawCouracado(Graphics2D g2d,double tam,double dx,double dy)
	{
		Rectangle2D r1;
		
		r1 = new Rectangle2D.Double(dx,dy,5*tam,tam);
	
		g2d.setPaint(Color.RED);
		
        g2d.fill(r1);
        
	}
}
