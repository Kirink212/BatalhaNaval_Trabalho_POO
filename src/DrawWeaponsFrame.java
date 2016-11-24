import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class DrawWeaponsFrame {
	private int index = 0;
	
	public DrawWeaponsFrame()
	{
		
	}
	
	public void drawHidroPlanes(Graphics2D g2d, double tam,Square weapons[])
	{
		int i;
		Rectangle2D r1,r2,r3;
		
		for(i=0;i<5;i++)
        {
			g2d.setPaint(Color.GREEN);
			
			r1 = new Rectangle2D.Double(weapons[this.index].getX(),weapons[this.index].getY(),tam,tam);
			r2 = new Rectangle2D.Double(tam+weapons[this.index].getX(),-tam+weapons[this.index].getY(),tam,tam);
			r3 = new Rectangle2D.Double(2*tam+weapons[this.index].getX(), weapons[this.index].getY(), tam,tam);
			
			g2d.fill(r1);
			g2d.fill(r2);
			g2d.fill(r3);
			this.index++;
        }
	}
	
	public void drawSubmarinos(Graphics2D g2d,double tam,Square weapons[])
	{
		int i;
		Rectangle2D r1;
		
		for(i=0;i<4;i++)
        {	
			r1 = new Rectangle2D.Double(weapons[this.index].getX() , weapons[this.index].getY(),tam,tam);
			
			g2d.setPaint(Color.CYAN);
			
            g2d.fill(r1);
            
            this.index++;
        }
	}
	
	public void drawDestroyers(Graphics2D g2d,double tam,Square weapons[])
	{
		int i;
		Rectangle2D r1;
		
		for(i=0;i<3;i++)
        {
			r1 = new Rectangle2D.Double(weapons[this.index].getX(),weapons[this.index].getY(),2*tam,tam);
            
			g2d.setPaint(Color.YELLOW);
			
            g2d.fill(r1);

            this.index++;
        }
		
	}
	
	public void drawCruzadores(Graphics2D g2d,double tam,Square weapons[])
	{
		int i;
		Rectangle2D r1;
		
		for(i=0;i<2;i++)
        {
			r1= new Rectangle2D.Double(weapons[this.index].getX(),weapons[this.index].getY(),4*tam,tam);
            
            g2d.setPaint(Color.ORANGE);
			
            g2d.fill(r1);
			
            this.index++;
        }
	}
	
	public void drawCouracado(Graphics2D g2d,double tam,Square weapons[])
	{
		Rectangle2D r1;
		
		r1 = new Rectangle2D.Double(weapons[this.index].getX(),weapons[this.index].getY(),5*tam,tam);
	
		g2d.setPaint(Color.RED);
		
        g2d.fill(r1);
        
        //this.index++;
	}
}
