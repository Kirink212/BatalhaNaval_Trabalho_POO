import java.awt.*;
import java.awt.geom.*;

public class Matrix{
	
	private double width;
	private double height;
	private double tileSize;
	private double rx_offset;
	private double ry_offset;
	private double offset_kx[] = new double[6];
	private double offset_ky[] = new double[6];
	private Square q[][] = new Square[15][15];
	private Utilities u = Utilities.getUtilities();
	
	public Matrix(double n1, double n2, double tile)
	{
		this.width = n1;
		this.height = n2;
		this.tileSize = tile;
	}
	
	public void drawMatrix(Graphics g, double mouse_x, double mouse_y, double offset_x,
									double offset_y,Square[][] weapons,int index, boolean[] registerWeapon,int count[])
	{
		int i,j, k;
		Graphics2D g2d = (Graphics2D)g;
		g2d.setPaint(Color.BLACK);
		Rectangle2D r1;
		Rectangle2D r2[] = new Rectangle2D[6];
		
		if(u.MatrixIsEmpty(q, 15, 15))
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
					double segura,auxWidth,auxHeight;
				
					auxWidth = weapons[index][1].getWidth()*3;
					auxHeight = weapons[index][1].getHeight()*2;
					
					
					switch(count[index])
					{
						case 0:
							rx_offset = 0;
							ry_offset = 0;
							for(int t=0; t<6; t++)
							{
								offset_kx[t] = 0;
								offset_ky[t] = 0;
							}
							break;
						case 1:
							rx_offset = -weapons[index][1].getHeight();
							ry_offset = 0;
							segura = auxWidth;
							auxWidth = auxHeight;
							auxHeight = segura;
							for(int t=0; t<6; t++)
							{
								double w = weapons[index][t].getWidth();
								double h = weapons[index][t].getHeight();
								if(t < 3)
								{
									offset_kx[t] = -w*(t+1);
									offset_ky[t] = t*h;
								}
								else
								{
									offset_kx[t] = -w*(t-1);
									offset_ky[t] = (-(t-3) + 1)*h;
								}
							}
							break;
						case 2:
							rx_offset = -weapons[index][1].getWidth();
							ry_offset =-weapons[index][1].getHeight();
							for(int t=0; t<6; t++)
							{
								double w = weapons[index][t].getWidth();
								double h = weapons[index][t].getHeight();
								if(t < 3)
								{
									offset_kx[t] = -w*(2*t-1);
									offset_ky[t] = -h;
								}
								else
								{
									offset_kx[t] = -w*(2*(t-3)-1);
									offset_ky[t] = -3*h;
								}
							}
							break;
						case 3:
							rx_offset = 0;
							ry_offset = -weapons[index][1].getWidth();
							segura = auxWidth;
							auxWidth = auxHeight;
							auxHeight = segura;
							for(int t=0; t<6; t++)
							{
								double w = weapons[index][t].getWidth();
								double h = weapons[index][t].getHeight();
								if(t < 3)
								{
									offset_kx[t] = -w*t;
									offset_ky[t] = -h*(t+1);
								}
								else
								{
									offset_kx[t] = (-(t-3) +1)*w;;
									offset_ky[t] = -h*(t-1);
								}
							}
							break;
						default :
							rx_offset = 0;
							ry_offset = 0;
							for(int t=0; t<6; t++)
							{
								offset_kx[t] = 0;
								offset_ky[t] = 0;
							}
						
					}
					
					for(k=0;k<6;k++)
					{
						if(u.weaponCollided(mouse_x+rx_offset, mouse_y+ry_offset,auxWidth
										   , auxHeight, q[i][j].getX(), q[i][j].getY(), this.tileSize, this.tileSize))
						{
							if(u.weaponCollided(weapons[index][k].getX()+offset_kx[k], weapons[index][k].getY()+offset_ky[k], weapons[index][k].getWidth()
									   , weapons[index][k].getWidth(), q[i][j].getX(), q[i][j].getY(), this.tileSize, this.tileSize))
							{
								g2d.setPaint(weapons[index][k].getColor());
								r2[k] = new Rectangle2D.Double( q[i][j].getX(), q[i][j].getY(), q[i][j].getWidth(), q[i][j].getHeight());
								g2d.fill(r2[k]);
							
								if(registerWeapon[index+1])
								{
									q[i][j].setColor(weapons[index][k].getColor());
									q[i][j].setTipo(weapons[index][0].getTipo());
								}
								else
								{
									q[i][j].setColor(Color.WHITE);
									q[i][j].setTipo(-1);
								}
							}
						}
					}
					
				}
				else if(index!=-1)
				{
					double segura,auxWidth,auxHeight;
					
					auxWidth = weapons[index][0].getWidth();
					auxHeight = weapons[index][0].getHeight();
					
					
					switch(count[index])
					{
						case 0:
							rx_offset = 0;
							ry_offset = 0;
							break;
						case 1:
							rx_offset = -weapons[index][0].getHeight();
							ry_offset = 0;
							segura = auxWidth;
							auxWidth = auxHeight;
							auxHeight = segura;
							break;
						case 2:
							rx_offset = -weapons[index][0].getWidth();
							ry_offset =-weapons[index][0].getHeight();
							break;
						case 3:
							rx_offset = 0;
							ry_offset = -weapons[index][0].getWidth();
							segura = auxWidth;
							auxWidth = auxHeight;
							auxHeight = segura;
							break;
							
						default :
							rx_offset = 0;
							ry_offset = 0;
							
						
					}
					if(u.weaponCollided(mouse_x+rx_offset, mouse_y+ry_offset, auxWidth,
										   auxHeight, q[i][j].getX(), q[i][j].getY(), q[i][j].getWidth(), q[i][j].getHeight()))
					{
						g2d.setPaint(weapons[index][0].getColor());
						g2d.fill(r1);
						if(registerWeapon[index+1])
						{
							q[i][j].setColor(weapons[index][0].getColor());
							q[i][j].setTipo(weapons[index][0].getTipo());
						}
						else
						{
							q[i][j].setColor(Color.WHITE);
							q[i][j].setTipo(-1);
						}
					}
				}
				
				g2d.setPaint(Color.BLACK);
			}
		}
		
	}
}