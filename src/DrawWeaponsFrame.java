import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class DrawWeaponsFrame {
	private int index = 0;
	
	public DrawWeaponsFrame()
	{
		
	}
	
	public void drawHidroPlanes(Graphics2D g2d, double tam,Square weapons[][], boolean[] rotate, int count[],boolean[] comeback)
	{
		int i, j;
		Rectangle2D r[] = new Rectangle2D[6];
		
		
		for(i=0;i<5;i++)
		{
			if(rotate[this.index] == true)
			{
				
				g2d.rotate(Math.toRadians(90*count[this.index]), weapons[this.index][0].getX(), weapons[this.index][0].getY());
			}
			
			
			for(j=0;j<6;j++)
			{	
				g2d.setPaint(weapons[this.index][j].getColor());
				
				if(weapons[this.index][j].getWidth() != 0 && weapons[this.index][j].getHeight() != 0)
				{
					r[j] = new Rectangle2D.Double(weapons[this.index][j].getX(),weapons[this.index][j].getY(),
												 weapons[this.index][j].getWidth(),weapons[this.index][j].getHeight());
					g2d.fill(r[j]);
				}
			}
			
			if(rotate[this.index] == true)
			{
				
				g2d.rotate(-Math.toRadians(90*count[this.index]), weapons[this.index][0].getX(), weapons[this.index][0].getY());
			}
			
			this.index++;
        }
	}
	
	public void drawSubmarinos(Graphics2D g2d,double tam,Square weapons[][], boolean[] rotate,int count[],boolean[] comeback)
	{
		int i;
		Rectangle2D r1;
		
		for(i=0;i<4;i++)
        {	
			
			if(rotate[this.index] == true)
			{
				
				g2d.rotate(Math.toRadians(90*count[this.index]), weapons[this.index][0].getX(), weapons[this.index][0].getY());
			}
			
			if(weapons[this.index][0].getWidth() != 0 && weapons[this.index][0].getHeight() != 0)
			{
				r1 = new Rectangle2D.Double(weapons[this.index][0].getX() , 
											weapons[this.index][0].getY(),weapons[this.index][0].getWidth(),weapons[this.index][0].getHeight());
				g2d.setPaint(weapons[this.index][0].getColor());
	            g2d.fill(r1);
			}
			
			if(rotate[this.index] == true)
			{
				g2d.rotate(-Math.toRadians(90*count[this.index]), weapons[this.index][0].getX(), weapons[this.index][0].getY());
			}
			
            this.index++;
        }
		
	}
	
	public void drawDestroyers(Graphics2D g2d,double tam,Square weapons[][], boolean[] rotate,int count[],boolean[] comeback)
	{
		int i;
		Rectangle2D r1;
		
		for(i=0;i<3;i++)
        {
			if(rotate[this.index] == true)
			{
				
				g2d.rotate(Math.toRadians(90*count[this.index]), weapons[this.index][0].getX(), weapons[this.index][0].getY());
			}
			
			if(weapons[this.index][0].getWidth() != 0 && weapons[this.index][0].getHeight() != 0)
			{
				r1 = new Rectangle2D.Double(weapons[this.index][0].getX(),
											weapons[this.index][0].getY(),weapons[this.index][0].getWidth(),weapons[this.index][0].getHeight());
				g2d.setPaint(weapons[this.index][0].getColor());
	            g2d.fill(r1);
			}
			
			if(rotate[this.index] == true)
			{
				g2d.rotate(-Math.toRadians(90*count[this.index]), weapons[this.index][0].getX(), weapons[this.index][0].getY());
			}
			

            this.index++;
        }
		
	}
	
	public void drawCruzadores(Graphics2D g2d,double tam,Square weapons[][], boolean[] rotate,int count[],boolean[] comeback)
	{
		int i;
		Rectangle2D r1;
		
		for(i=0;i<2;i++)
        {
			if(rotate[this.index] == true)
			{
				
				g2d.rotate(Math.toRadians(90*count[this.index]), weapons[this.index][0].getX(), weapons[this.index][0].getY());
			}
			
			if(weapons[this.index][0].getWidth() != 0 && weapons[this.index][0].getHeight() != 0)
			{
				r1= new Rectangle2D.Double(weapons[this.index][0].getX(),weapons[this.index][0].getY()
										  ,weapons[this.index][0].getWidth(),weapons[this.index][0].getHeight());
				g2d.setPaint(weapons[this.index][0].getColor());
	            g2d.fill(r1);
			}
			
			if(rotate[this.index] == true)
			{
				g2d.rotate(-Math.toRadians(90*count[this.index]), weapons[this.index][0].getX(), weapons[this.index][0].getY());
			}
			
			
            this.index++;
        }
	}
	
	public void drawCouracado(Graphics2D g2d,double tam,Square weapons[][], boolean[] rotate,int count[],boolean[] comeback)
	{
		Rectangle2D r1;
		
		if(weapons[this.index][0].getWidth() != 0 && weapons[this.index][0].getHeight() != 0)
		{
			if(rotate[this.index] == true)
			{
				
				g2d.rotate(Math.toRadians(90*count[this.index]), weapons[this.index][0].getX(), weapons[this.index][0].getY());
			}
			
			
			r1 = new Rectangle2D.Double(weapons[this.index][0].getX(),
										weapons[this.index][0].getY(),weapons[this.index][0].getWidth(),weapons[this.index][0].getHeight());
			g2d.setPaint(weapons[this.index][0].getColor());
	        g2d.fill(r1);
	        
	    	if(rotate[this.index] == true)
			{
				g2d.rotate(-Math.toRadians(90*count[this.index]), weapons[this.index][0].getX(), weapons[this.index][0].getY());
			}
			
	        
		}
        
        this.index=0;
	}
}
