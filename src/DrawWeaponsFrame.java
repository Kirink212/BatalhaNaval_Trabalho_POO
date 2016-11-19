import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class DrawWeaponsFrame {
	
	public DrawWeaponsFrame()
	{
		
	}
	
	public void drawHidroPlanes(Graphics2D g2d, double tam, double dx)
	{
		int i;
		for(i=0;i<3;i++)
        {
            g2d.draw(new Rectangle2D.Double(65.0+dx,90.0,tam,tam)); 
            g2d.draw(new Rectangle2D.Double(97.0+dx,58.0,tam,tam));
            g2d.draw(new Rectangle2D.Double(129.0+dx,90.0,tam,tam));
            
            dx = 125+dx;
            System.out.println(Double.toString(dx));
        }
        dx=0;
	}
}
