import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class DrawWeaponsFrame {
	private int index = 0;
	
	public DrawWeaponsFrame()
	{
		
	}
	
	public void drawHidroPlanes(Graphics2D g2d, double tam, LoadLogicController l)
	{
		int i;
		Rectangle2D r[] = new Rectangle2D[6];
		
		this.index = 0;
		
		for(i=0;i<5;i++)
		{
			l.hidroPlanesLogic(g2d, r, this.index);
			
			this.index++;
        }
	}
	
	public void drawSubmarinos(Graphics2D g2d,double tam, LoadLogicController l)
	{
		int i;
		Rectangle2D r1= new Rectangle2D.Double(0,0,0,0);
		
		for(i=0;i<4;i++)
        {	
			l.submarinosLogic(g2d, r1, this.index);
			
            this.index++;
        }
		
	}
	
	public void drawDestroyers(Graphics2D g2d,double tam, LoadLogicController l)
	{
		int i;
		Rectangle2D r1= new Rectangle2D.Double(0,0,0,0);
		
		for(i=0;i<3;i++)
        {
			l.destroyersLogic(g2d, r1, this.index);

            this.index++;
        }
		
	}
	
	public void drawCruzadores(Graphics2D g2d,double tam, LoadLogicController l)
	{
		int i;
		Rectangle2D r1= new Rectangle2D.Double(0,0,0,0);
		
		for(i=0;i<2;i++)
        {
			l.cruzadoresLogic(g2d, r1, this.index);
            this.index++;
        }
	}
	
	public void drawCouracado(Graphics2D g2d,double tam, LoadLogicController l)
	{
		Rectangle2D r1 = new Rectangle2D.Double(0,0,0,0);
		
		l.couracadoLogic(g2d, r1, this.index);
        
        this.index=0;
	}
}
