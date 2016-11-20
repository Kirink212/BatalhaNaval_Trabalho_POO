import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class MouseController implements MouseListener, MouseMotionListener {
	
	private static MouseController m = null;
	private boolean dragging = false;
	private double mouseX;
	private double mouseY;
	private double currentX;
	private double currentY;
	private JPanel panel;
	
	public MouseController(JPanel p)
	{
		this.panel = p;
	}
	
	public static MouseController getMouseController(JPanel p){
		if(m == null)
		{
			m = new MouseController(p);
		}
	   
		return m;
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
		 this.dragging = true;
	}
	public void mouseReleased(MouseEvent me)
	{
		 Point point = me.getPoint();
		 System.out.println("mouseReleased at " + point);
		 this.mouseX = point.x;
		 this.mouseY = point.y;
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
	
	public double getCurrentX()
	{
		return this.mouseX;
	}
	
	public double getCurrentY()
	{
		return this.mouseY;
	}
}
