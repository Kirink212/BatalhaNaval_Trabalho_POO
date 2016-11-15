import java.awt.*;
import java.awt.geom.*;

public class Matrix{
	
	private double width;
	private double height;
	private double tileSize;
	
	public Matrix(double n1, double n2, double tile)
	{
		this.width = n1;
		this.height = n2;
		this.tileSize = tile;
	}
	
	public void drawMatrix(Graphics g, Double offset_x, Double offset_y)
	{
		Graphics2D g2d = (Graphics2D)g;
		g2d.setPaint(Color.black);
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
				g2d.draw(new Rectangle2D.Double(this.tileSize*j+offset_x,this.tileSize*i+offset_y,this.tileSize,this.tileSize));
			}
		}
	}
}
