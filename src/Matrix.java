import java.awt.*;
import java.awt.geom.*;

public class Matrix{
	
	private double width;
	private double height;
	private double offset_x;
	private double offset_y;
	private double tileSize;
	private Square q[][] = new Square[15][15];
	
	public Matrix(double n1, double n2, double tile)
	{
		this.width = n1;
		this.height = n2;
		this.tileSize = tile;
	}
	
	public void drawMatrix(Graphics g, double mouse_x, double mouse_y, double offset_x, double offset_y,Square[][] weapons,int index, boolean[] registerWeapon)
	{
		int i,j, k;
		Graphics2D g2d = (Graphics2D)g;
		g2d.setPaint(Color.BLACK);
		Rectangle2D r1;
		Rectangle2D r2[] = new Rectangle2D[6];
		this.offset_x = offset_x;
		this.offset_y = offset_y;
		
		if(MatrixIsEmpty(q, 15, 15))
		{
			for(i=0; i< this.width; i++)
			{
				for(j=0; j< this.height; j++)
				{
					q[i][j] = new Square(-1, -1, -1, -1, false, -1, Color.WHITE);
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
					q[i][j] = new Square(this.tileSize*j+offset_x, this.tileSize*i+offset_y, this.tileSize, this.tileSize, false, (registerWeapon[index+1])? weapons[index][0].getTipo():-1, (registerWeapon[index+1])? weapons[index][0].getColor():Color.WHITE);
				}
				
				r1 = new Rectangle2D.Double( q[i][j].getX(), q[i][j].getY(), q[i][j].getWidth(), q[i][j].getHeight());
				
				g2d.setPaint(q[i][j].getColor());
				g2d.fill(r1);
				g2d.setPaint(Color.BLACK);
				g2d.draw(r1);
				
				if(index >=0 && index <= 4)
				{
					if(this.weaponCollided(mouse_x, mouse_y, weapons[index][1].getWidth()*3, weapons[index][1].getHeight()*2, q[i][j].getX(), q[i][j].getY(), this.tileSize, this.tileSize))
					{
						for(k=0;k<6;k++)
						{
							if(k%2 != 0)
							{
								g2d.setPaint(weapons[index][k].getColor());
								r2[k] = new Rectangle2D.Double( q[i][j].getX(), q[i][j].getY(), q[i][j].getWidth(), q[i][j].getHeight());
								g2d.fill(r2[k]);
							}
							else
							{
								g2d.setPaint(weapons[index][k].getColor());
								r2[k] = new Rectangle2D.Double( q[i][j].getX(), q[i][j].getY(), q[i][j].getWidth(), q[i][j].getHeight());
								g2d.fill(r2[k]);
							}
							if(registerWeapon[index+1])
							{
								q[i][j].setColor(weapons[index][k].getColor());
								q[i][j].setTipo(weapons[index][0].getTipo());
							}
						}
					}
				}
				else if(index!=-1)
				{
					if(this.weaponCollided(mouse_x, mouse_y, weapons[index][0].getWidth(), weapons[index][0].getHeight(), q[i][j].getX(), q[i][j].getY(), q[i][j].getWidth(), q[i][j].getHeight()))
					{
						g2d.setPaint(weapons[index][0].getColor());
						g2d.fill(r1);
						if(registerWeapon[index+1])
						{
							q[i][j].setColor(weapons[index][0].getColor());
							q[i][j].setTipo(weapons[index][0].getTipo());
						}
					}
				}
				
				g2d.setPaint(Color.BLACK);
			}
		}
		
	}

	
	public boolean weaponCollided( double mouse_x, double mouse_y, double weapon_w, double weapon_h, double x, double y, double w, double h) 
	{
			return (
						x + (w / 2) >= mouse_x &&
						x + (w / 2) < mouse_x + weapon_w && 
						
						y + (h / 2) >= mouse_y &&
						y + (h / 2) < mouse_y + weapon_h  
						
					);
	}
	
	public boolean mouseCollided( double mouse_x, double mouse_y, double x, double y, double w, double h) 
	{
		return (mouse_x <= x+w && mouse_y <= y+h && y <= mouse_y+1 && x <= mouse_x+1);
	}

	public boolean MatrixIsEmpty(Square s[][], int m, int n)
    {
    	for(int i=0; i<m; i++)
    	{
    		for(int j=0; j<n; j++)
        	{
	    		if(s[i][j] != null)
	    		{
	    			return false;
	    		}
        	}
    	}
    	return true;
    }
}
