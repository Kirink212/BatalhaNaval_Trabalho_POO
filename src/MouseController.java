import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseController implements MouseListener{
	
	private static MouseController m = null;
	
	public MouseController()
	{
		
	}
	
	public static MouseController getMouseController(){
		if(m == null)
		{
			m = new MouseController();
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

	}
	public void mouseReleased(MouseEvent me)
	{
		
	}
}
