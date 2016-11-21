import java.awt.*;
import java.awt.geom.*;

public class Matrix{
	
	private double width;
	private double height;
	private double offset_x;
	private double offset_y;
	private double tileSize;
	public Square q[][] = new Square[15][15];
	
	public Matrix(double n1, double n2, double tile)
	{
		this.width = n1;
		this.height = n2;
		this.tileSize = tile;
	}
	
	public void drawMatrix(Graphics g, double mouse_x, double mouse_y, double offset_x, double offset_y)
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
				g2d.setPaint(Color.WHITE);
				g2d.fill(r1);
				g2d.setPaint(Color.BLACK);
				g2d.draw(r1);
				
				q[i][j] = new Square(this.tileSize*j+offset_x, this.tileSize*i+offset_y, false);
				
				if(this.mouseCollided(mouse_x, mouse_y, q[i][j].getX(), q[i][j].getY(), this.tileSize, this.tileSize))
				{
					g2d.setPaint(Color.RED);
					g2d.fill(r1);
				}
				g2d.setPaint(Color.BLACK);
			}
		}
		
	}

	public boolean mouseCollided( double mouse_x, double mouse_y, double x, double y, double w, double h) 
	{
		return (mouse_x < x+w && mouse_y < y+h && y < mouse_y+1 && x < mouse_x+1);
	}
}
