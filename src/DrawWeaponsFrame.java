import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class DrawWeaponsFrame {
	private int index = 0;
	
	public DrawWeaponsFrame()
	{
		
	}
	
	public void drawHidroPlanes(Graphics2D g2d, double tam,Square weapons[][])
	{
		int i, j;
		Rectangle2D r[] = new Rectangle2D[6];
		
		for(i=0;i<5;i++)
		{
			if(i==0)
			{
				//g2d.rotate(Math.toRadians(45));
			}
			for(j=0;j<6;j++)
			{
				g2d.setPaint(weapons[this.index][j].getColor());
				if(weapons[this.index][j].getWidth() != 0 && weapons[this.index][j].getHeight() != 0)
				{
					r[j] = new Rectangle2D.Double(weapons[this.index][j].getX(),weapons[this.index][j].getY(),tam,tam);
					g2d.fill(r[j]);
				}
			}
			if(i==0)
			{
				//g2d.rotate(-Math.toRadians(45));
			}
			this.index++;
        }
	}
	
	public void drawSubmarinos(Graphics2D g2d,double tam,Square weapons[][])
	{
		int i;
		Rectangle2D r1;
		
		for(i=0;i<4;i++)
        {	
			if(weapons[this.index][0].getWidth() != 0 && weapons[this.index][0].getHeight() != 0)
			{
				r1 = new Rectangle2D.Double(weapons[this.index][0].getX() , weapons[this.index][0].getY(),tam,tam);
				g2d.setPaint(weapons[this.index][0].getColor());
	            g2d.fill(r1);
			}
            
            this.index++;
        }
	}
	
	public void drawDestroyers(Graphics2D g2d,double tam,Square weapons[][])
	{
		int i;
		Rectangle2D r1;
		
		for(i=0;i<3;i++)
        {
			if(weapons[this.index][0].getWidth() != 0 && weapons[this.index][0].getHeight() != 0)
			{
				r1 = new Rectangle2D.Double(weapons[this.index][0].getX(),weapons[this.index][0].getY(),2*tam,tam);
				g2d.setPaint(weapons[this.index][0].getColor());
	            g2d.fill(r1);
			}

            this.index++;
        }
		
	}
	
	public void drawCruzadores(Graphics2D g2d,double tam,Square weapons[][])
	{
		int i;
		Rectangle2D r1;
		
		for(i=0;i<2;i++)
        {
			if(weapons[this.index][0].getWidth() != 0 && weapons[this.index][0].getHeight() != 0)
			{
				r1= new Rectangle2D.Double(weapons[this.index][0].getX(),weapons[this.index][0].getY(),4*tam,tam);
				g2d.setPaint(weapons[this.index][0].getColor());
	            g2d.fill(r1);
			}
			
            this.index++;
        }
	}
	
	public void drawCouracado(Graphics2D g2d,double tam,Square weapons[][])
	{
		Rectangle2D r1;
		
		if(weapons[this.index][0].getWidth() != 0 && weapons[this.index][0].getHeight() != 0)
		{
			r1 = new Rectangle2D.Double(weapons[this.index][0].getX(),weapons[this.index][0].getY(),5*tam,tam);
			g2d.setPaint(weapons[this.index][0].getColor());
	        g2d.fill(r1);
		}
        
        this.index=0;
	}
}
