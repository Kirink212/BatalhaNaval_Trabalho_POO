import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class DrawWeaponsFrame {
	private Square weapons[] = new Square[15];
	private int index = 0;
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
			
			r1 = new Rectangle2D.Double(dx,115.0+dy,tam,tam);
			r2 = new Rectangle2D.Double(tam+dx,115-tam+dy,tam,tam);
			r3 = new Rectangle2D.Double(2*tam+dx,115.0+dy,tam,tam);
			
			g2d.fill(r1);
			g2d.fill(r2);
			g2d.fill(r3);
			if(weapons[index] == null)
			{
				weapons[index] = new Square(dx,115.0+dy, 3*tam, 2*tam, false);
			}
			index++;
			
			if(dx == 30)
            {
            	dx = 125+dx;
            }
            else
            {
            	dx = weapons[index].getX();
            }
        }
        dx=0;
	}
	
	public void drawSubmarinos(Graphics2D g2d,double tam,double dx, double dy)
	{
		int i;
		Rectangle2D r1;
		
		for(i=0;i<4;i++)
        {
			if(weapons[index] == null)
			{
				weapons[index] = new Square(dx,195.0+dy,tam,tam,false);
			}
			
			index++;
			
			r1 = new Rectangle2D.Double(dx ,(dy == 0)? 195.0+dy : dy,tam,tam);
			
			g2d.setPaint(Color.CYAN);
			
            g2d.fill(r1);
            
            if(dx == 30)
            {
            	dx = 125+dx;
            }
            else
            {
            	dx = weapons[index].getX();
            }
        }
        dx=0;
	}
	
	public void drawDestroyers(Graphics2D g2d,double tam,double dx,double dy)
	{
		int i;
		Rectangle2D r1;
		
		for(i=0;i<3;i++)
        {
			r1 = new Rectangle2D.Double(dx,285.0+dy,2*tam,tam);
            
			g2d.setPaint(Color.YELLOW);
			
            g2d.fill(r1);
            if(weapons[index] == null)
			{
            	weapons[index] = new Square(dx,285.0+dy, 2*tam,tam, false);
			}
			index++;
            
			if(dx == 30)
            {
            	dx = 125+dx;
            }
            else
            {
            	dx = weapons[index].getX();
            }
        }
		
        dx=0;
		
	}
	
	public void drawCruzadores(Graphics2D g2d,double tam,double dx,double dy)
	{
		int i;
		Rectangle2D r1;
		
		for(i=0;i<2;i++)
        {
			r1= new Rectangle2D.Double(dx,375.0+dy,4*tam,tam);
            
            g2d.setPaint(Color.ORANGE);
			
            g2d.fill(r1);
            if(weapons[index] == null)
			{
            	weapons[index] = new Square(dx,375.0+dy, 4*tam, tam, false);
			}
			index++;
            
			if(dx == 30)
            {
            	dx = 250+dx;
            }
            else
            {
            	dx = weapons[index].getX();
            }
        }
		
        dx=0;
	}
	
	public void drawCouracado(Graphics2D g2d,double tam,double dx,double dy)
	{
		Rectangle2D r1;
		
		r1 = new Rectangle2D.Double(dx,465.0+dy,5*tam,tam);
	
		g2d.setPaint(Color.RED);
		
		if(weapons[index] == null)
		{
			weapons[index] = new Square(dx,465.0+dy, 5*tam, tam, false);
		}
		
        g2d.fill(r1);
	}
	
	public double getVectorXPosition(int i)
	{
		return weapons[i].getX();
	}
	
	public double getVectorYPosition(int i)
	{
		return weapons[i].getY();
	}
	
	public double getVectorWidthPosition(int i)
	{
		return weapons[i].getWidth();
	}
	
	public double getVectorHeightPosition(int i)
	{
		return weapons[i].getHeight();
	}
	
	public boolean getVectorCollided(int i)
	{
		return weapons[i].getCollided();
	}
}
