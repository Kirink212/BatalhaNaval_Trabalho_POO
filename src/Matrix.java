import java.awt.*;
import java.awt.geom.*;

import javax.swing.JPanel;

public class Matrix{
	
	private double width;
	private double height;
	private double tileSize;
	private Square q[][] = new Square[15][15];
	private Utilities u = Utilities.getUtilities();
	
	public Matrix(double n1, double n2, double tile)
	{
		this.width = n1;
		this.height = n2;
		this.tileSize = tile;
	}
	
	public void drawMatrix(Graphics g, JPanel h, double mouse_x, double mouse_y, double offset_x,
									double offset_y, LoadLogicController l, Player p)
	{
		int i,j;
		Graphics2D g2d = (Graphics2D)g;
		g2d.setPaint(Color.BLACK);
		Rectangle2D r1;
		
		if(u.MatrixIsEmpty(q, 15, 15))
		{
			for(i=0; i< this.width; i++)
			{
				for(j=0; j< this.height; j++)
				{
					q[i][j] = new Square(-1, -1, -1, -1, -1, -1, false, -1, Color.WHITE);
				}
			}
		}
		
		for(i=0; i< this.width; i++)
		{
			int letter = i+65;
			g2d.drawString(Character.toString((char) letter), Math.round(offset_x-tileSize/2),Math.round(this.tileSize*i+offset_y+tileSize/2));
			for(j=0; j< this.height; j++)
			{
				int number = j+1;
				if(i==0)
				{
					g2d.drawString(Integer.toString(number), Math.round(this.tileSize*j+offset_x+tileSize/2-5),Math.round(offset_y)-5);
				}
				
				if(q[i][j].isEmpty())
				{
					q[i][j] = new Square(this.tileSize*j+offset_x, this.tileSize*i+offset_y, this.tileSize, this.tileSize, -1, -1, false, (l.getRegisterWeapons(l.getActualWeapon()+1))? l.getWeapons(l.getActualWeapon(), 0).getTipo():-1, (l.getRegisterWeapons(l.getActualWeapon()+1))? l.getWeapons(l.getActualWeapon(), 0).getColor():Color.WHITE);
				}
				
				r1 = new Rectangle2D.Double( q[i][j].getX(), q[i][j].getY(), q[i][j].getWidth(), q[i][j].getHeight());
				
				g2d.setPaint(q[i][j].getColor());
				g2d.fill(r1);
				g2d.setPaint(Color.BLACK);
				g2d.draw(r1);
				
				l.loadMatrixLogic(g2d, h, u, q, i, j, mouse_x, mouse_y, r1, this.tileSize);
				
				g2d.setPaint(Color.BLACK);
			}
		}
		
		p.setMatrix(q, this.width, this.height);
	}
	
	public void drawMatrix(Graphics g, double mouse_x, double mouse_y, double offset_x,
			double offset_y,Square a[][])
	{
		{
			int i,j;
			Graphics2D g2d = (Graphics2D)g;
			g2d.setPaint(Color.BLACK);
			Rectangle2D r1;
			
			for(i=0; i< this.width; i++)
			{
				int letter = i+65;
				g2d.drawString(Character.toString((char) letter), Math.round(offset_x-tileSize/2),Math.round(this.tileSize*i+offset_y+tileSize/2));
				for(j=0; j< this.height; j++)
				{
					int number = j+1;
					if(i==0)
					{
						g2d.drawString(Integer.toString(number), Math.round(this.tileSize*j+offset_x+tileSize/2-5),Math.round(offset_y)-5);
					}
					
					r1 = new Rectangle2D.Double( this.tileSize*j+offset_x, this.tileSize*i+offset_y, this.tileSize, this.tileSize);
					
					g2d.setPaint(a[i][j].getColor());
					g2d.fill(r1);
					g2d.setPaint(Color.BLACK);
					g2d.draw(r1);
					
					g2d.setPaint(Color.BLACK);
				}
			}
	}
  }
	
	public void drawMatrixBattle(Graphics g, double mouse_x, double mouse_y, double offset_x,
			double offset_y,Square[][]s)
	{
		{
			int i,j;
			Graphics2D g2d = (Graphics2D)g;
			g2d.setPaint(Color.BLACK);
			Rectangle2D r1;
			
			for(i=0; i< this.width; i++)
			{
				int letter = i+65;
				g2d.drawString(Character.toString((char) letter), Math.round(offset_x-tileSize/2),Math.round(this.tileSize*i+offset_y+tileSize/2));
				for(j=0; j< this.height; j++)
				{
					int number = j+1;
					if(i==0)
					{
						g2d.drawString(Integer.toString(number), Math.round(this.tileSize*j+offset_x+tileSize/2-5),Math.round(offset_y)-5);
					}
					
					r1 = new Rectangle2D.Double( this.tileSize*j+offset_x, this.tileSize*i+offset_y, this.tileSize, this.tileSize);
					
					if(s==null)
					{
						g2d.setPaint(Color.CYAN);
						g2d.fill(r1);
					}
					else
					{
						if (s[i][j].getCollided() == false)
						{
							g2d.setPaint(Color.CYAN);
							g2d.fill(r1);
						}
						else
						{
							if(s[i][j].getColor() != Color.WHITE)
							{
								g2d.setPaint(s[i][j].getColor());
							}
							else
							{
								g2d.setPaint(Color.GREEN);
							}
							g2d.fill(r1);
						}
					}
					
					g2d.setPaint(Color.BLACK);
					g2d.draw(r1);
					
					g2d.setPaint(Color.BLACK);
				}
			}
	}
  }
	
	
}