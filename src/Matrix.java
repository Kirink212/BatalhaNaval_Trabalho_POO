import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.*;

public class Matrix implements  MouseListener, MouseMotionListener{
	
	private double width;
	private double height;
	private double offset_x;
	private double offset_y;
	private double tileSize;
	public int matrix_collided[][] = new int[15][15];
	
	public Matrix(double n1, double n2, double tile)
	{
		this.width = n1;
		this.height = n2;
		this.tileSize = tile;
	}
	
	public void drawMatrix(Graphics g, double offset_x, double offset_y)
	{
		Graphics2D g2d = (Graphics2D)g;
		g2d.setPaint(Color.BLACK);
		Rectangle2D r1;
		this.offset_x = offset_x;
		this.offset_y = offset_y;
		for(int i=0; i< this.width; i++)
		{
			int letter = i+65;
			g2d.drawString(Character.toString((char) letter), Math.round(offset_x-tileSize/2),Math.round(this.tileSize*i+offset_y+tileSize/2));
			for(int j=0; j< this.height; j++)
			{
				int number = j+1;
				if(i==0)
				{
					g2d.drawString(Integer.toString(number), Math.round(this.tileSize*j+offset_x+tileSize/2-5),Math.round(offset_y)-5);
				}
				r1 = new Rectangle2D.Double(this.tileSize*j+offset_x,this.tileSize*i+offset_y,this.tileSize,this.tileSize);
				if(matrix_collided[i][j] == 1)
				{
					g2d.setPaint(Color.RED);
					g2d.fill(r1);
				}
				else
				{
					g2d.setPaint(Color.WHITE);
					g2d.fill(r1);
				}
				g2d.setPaint(Color.BLACK);
				g2d.draw(r1);
			}
		}
		
	}

	public boolean mouseCollided(MouseEvent mouse, double x, double y, double w, double h) 
	{
		return (mouse.getX() < x+w && mouse.getY() < y+h && y < mouse.getY()+1 && x < mouse.getX()+1);
	}
	
	public void mouseDragged(MouseEvent me) {
		
	}

	public void mouseMoved(MouseEvent me) {
		System.out.println("Mouse esta se movendo");
		for(int i=0; i< this.width; i++)
		{
			for(int j=0; j< this.height; j++)
			{
				if(mouseCollided(me,this.tileSize*j+offset_x,this.tileSize*j+offset_y, this.tileSize, this.tileSize))
				{
					matrix_collided[i][j] = 1;
					System.out.println("Mouse esta se movendo em:"+me.getPoint());
				}
				matrix_collided[i][j] = 0;
			}
		}
	}

	public void mouseClicked(MouseEvent me) {
		
	}
	
	public void mouseEntered(MouseEvent me) {
		
	}

	public void mouseExited(MouseEvent me) {
		
	}

	public void mousePressed(MouseEvent me) {
		
	}

	public void mouseReleased(MouseEvent me) {
		
	}
}
