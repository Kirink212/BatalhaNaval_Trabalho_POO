import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class LoadLogicController {
	
	private Color transparente = new Color(0,0,0,0);
	private double previousX[] = new double[15];
	private double previousY[] = new double[15];
	private int countRotations[] =new int[15];
	private Square weapons[][] = new Square[15][6];
	private Square weapons_repos[][] = new Square[15][6];
	private boolean rotate[] = new boolean[15];
	private int qtdTipo[] = new int[5];
	private Color colorTipo[] = new Color[5];
	private boolean registerWeapons[] = new boolean [16];
	private boolean comeback[] = new boolean[15];
	private int num_click[] = new int[15];
	private double offset_kx[] = new double[6];
	private double offset_ky[] = new double[6];
	private double rx_offset;
	private double ry_offset;
	private static int actualWeapon = -1;
	private int index = 0;
	
	public LoadLogicController()
	{
		
	}
	
	public void load_qdTipo(int[] qtdTipo)
	{
	    for(int i=0; i<5; i++)
	    {
	    	qtdTipo[i] = (5-i);
	    }
	}
	public void load_colorTipo(Color[] colorTipo)
	{
	    colorTipo[0] = Color.GREEN;
	    colorTipo[1] = Color.CYAN;
	    colorTipo[2] = Color.YELLOW;
	    colorTipo[3] = Color.ORANGE;
	    colorTipo[4] = Color.RED;
	}
    
	public void load_weapons(int[] qtdTipo, Color[] colorTipo, Utilities u, double tamquadrado)
	{
		double increment; 
		double aux_x, aux_y;
		int aux_index = 0;
		
		if(u.ArrayisEmpty(weapons, 15))
		{
			resetIndex();
	        for(int i=0; i<5; i++)
	    	{
	    		while(aux_index < qtdTipo[i])
	    		{
	    			if(i == 0)
	    			{
	    				for(int j=0; j<6; j++)
	    				{
	    					if(j < 3)
	    					{
	    						weapons[index][j] = (j%2 != 0)? new Square(30+j*tamquadrado, 115, tamquadrado, tamquadrado, -1, -1, false, i, colorTipo[i]) 
	    								: new Square(30+j*tamquadrado, 115, tamquadrado, tamquadrado, -1, -1, false, -2, transparente);
	    					}
	    					else
	    					{
	    						weapons[index][j] = (j%2 != 0)? new Square(30+(j-3)*tamquadrado, 115+tamquadrado, tamquadrado, tamquadrado, -1, -1, false, i, colorTipo[i])
	    								: new Square(30+(j-3)*tamquadrado, 115+tamquadrado, tamquadrado, tamquadrado, -1, -1, false, -2, transparente);
	    					}
	    				}
	    			}
	    			else
	    			{
	    				if(i > 3 )
	    				{
	    					weapons[index][0] = new Square(30, 115, (i+1)*tamquadrado, tamquadrado, -1, -1, false, i, colorTipo[i]);
	    				}
	    				else
	    				{
	    					weapons[index][0] = new Square(30, 115, i*tamquadrado, tamquadrado, -1, -1, false, i, colorTipo[i]);
	    				}
	    				
	    			}
	    			increment = (i == 3 && aux_index == 1)? 125 : 0;
	    			if(i==0)
	    			{
	    				for(int j=0; j<6; j++)
	    				{
			    			aux_x = weapons[index][j].getX()+125*(aux_index) + increment;
			    			aux_y = weapons[index][j].getY()+80*i;
			    			weapons[index][j].setX(aux_x);
			    			weapons[index][j].setY(aux_y);
	    				}
	    				this.previousX[index] = weapons[index][0].getX();
	    				this.previousY[index] = weapons[index][0].getY();
	    			}
	    			else
	    			{
	    				aux_x = weapons[index][0].getX()+125*(aux_index) + increment;
		    			aux_y = weapons[index][0].getY()+80*i;
		    			weapons[index][0].setX(aux_x);
		    			weapons[index][0].setY(aux_y);
		    			this.previousX[index] = aux_x;
		    			this.previousY[index] = aux_y;
	    			}
	    			index++;
	    			aux_index++;
	    		}
	    		aux_index = 0;
	    	}
		}
	}
	
	public void mousePressedLogic(JPanel p, MouseEvent me, Player play, double mouseX, double mouseY, double tamquadrado, Utilities u)
	{
		int index, j, count_intocaveis = 0;
		index = u.findWeapon(weapons, 15, mouseY, mouseX);
		if(index != -1)
		{
			 if(SwingUtilities.isLeftMouseButton(me) && registerWeapons[index+1] == false)
			 {
				 if(index >=0 && index <= 4)
				 {
					 for(j=0; j<6; j++)
					 {
						if(j < 3)
						{
							weapons[index][j].setX(mouseX+j*tamquadrado);
							weapons[index][j].setY(mouseY);
						}
						else
						{
							weapons[index][j].setX(mouseX+(j-3)*tamquadrado);
							weapons[index][j].setY(mouseY+tamquadrado);
						}
						weapons[index][j].setCollided(true);
						actualWeapon = index;
					 }
				 }
				 else
				 {
					weapons[index][0].setX(mouseX);
					weapons[index][0].setY(mouseY);
					weapons[index][0].setCollided(true);
					actualWeapon = index;
				 }
				 
				 for(int i=0; i<15; i++)
				 {
					 for(int k=0; k<15; k++)
					 {
						 if(u.weaponCollided(mouseX, mouseY, weapons[index][0].getWidth(), weapons[index][0].getHeight(), play.getMatrix()[i][k].getX(), play.getMatrix()[i][k].getY(), play.getMatrix()[i][k].getWidth(), play.getMatrix()[i][k].getHeight()))
						 {
							 if(u.mouseCollided( mouseX, mouseY, 700.0, 80.0, 15*tamquadrado, 15*tamquadrado))
					 		 {
								if(play.getMatrix()[i][k].getTipo() == -3)
								{
									count_intocaveis++;
								}
								if(count_intocaveis == 0)
								{
									System.out.println(play.getMatrix()[i][k].getTipo());
									registerWeapons[index+1] = true;
									p.removeAll();
									p.repaint();
								}
					 		 }
						 }
					 }
				 }
			 }
					  
			 if(SwingUtilities.isRightMouseButton(me) && registerWeapons[index+1] == true)
			 {
		 		 if(u.mouseCollided( mouseX, mouseY, 700.0, 80.0, 15*tamquadrado, 15*tamquadrado))
		 		 {
					registerWeapons[index+1] = false;
					num_click[index] = 0;
					if(index >=0 && index <= 4)
					{
						 for(j=0; j<6; j++)
						 {
							if(j < 3)
							{
								weapons[index][j].setX(mouseX+j*tamquadrado);
								weapons[index][j].setY(mouseY);
							}
							else
							{
								weapons[index][j].setX(mouseX+(j-3)*tamquadrado);
								weapons[index][j].setY(mouseY+tamquadrado);
							}
							weapons[index][j].setCollided(true);
							actualWeapon = index;
						 }
					 }
					 else if(index != -1)
					 {
						weapons[index][0].setX(mouseX);
						weapons[index][0].setY(mouseY);
						weapons[index][0].setCollided(true);
						actualWeapon = index;
					 }
		 		 }
			 }
		}
	}
	
	public void mouseMovedLogic(JPanel p, MouseEvent me, Player play, double mouseX, double mouseY, double tamquadrado)
	{
		int j;
		if(actualWeapon >=0 && actualWeapon <= 4)
		{
			 for(j=0; j<6; j++)
			 {
				 if(weapons[actualWeapon][j].getCollided())
				 {
					 if(j < 3)
					 {
						weapons[actualWeapon][j].setX(mouseX+j*tamquadrado);
						weapons[actualWeapon][j].setY(mouseY);
					 }
					 else
					 {
						weapons[actualWeapon][j].setX(mouseX+(j-3)*tamquadrado);
						weapons[actualWeapon][j].setY(mouseY+tamquadrado);
					 }
				 }
				 if(comeback[actualWeapon] == true)
				 {
					 if(j < 3)
					 {
						 weapons[actualWeapon][j].setX(previousX[actualWeapon]+j*tamquadrado);
						 weapons[actualWeapon][j].setY(previousY[actualWeapon]);
						 weapons[actualWeapon][j].setCollided(false);
					 }
					 else
					 {
						 weapons[actualWeapon][j].setX(previousX[actualWeapon]+(j-3)*tamquadrado);
						 weapons[actualWeapon][j].setY(previousY[actualWeapon]+tamquadrado);
						 weapons[actualWeapon][j].setCollided(false);
					 }
				 }
				 
			 }
			 if(comeback[actualWeapon] == true)
			 {
				 rotate[actualWeapon] = false;
				 p.removeAll(); 
				 p.repaint();
				 comeback[actualWeapon] = false;
				 actualWeapon =-1;
			 }
			 
		 }
		 else if(actualWeapon != -1)
		 {
			 if(weapons[actualWeapon][0].getCollided())
			 {
				 weapons[actualWeapon][0].setX(mouseX);
				 weapons[actualWeapon][0].setY(mouseY);
			 }
			 
			 if(comeback[actualWeapon] == true)
			 {
				 weapons[actualWeapon][0].setX(previousX[actualWeapon]);
				 weapons[actualWeapon][0].setY(previousY[actualWeapon]);
				 weapons[actualWeapon][0].setCollided(false);
				 rotate[actualWeapon] = false;
				 p.removeAll(); 
				 p.repaint();
				 comeback[actualWeapon] = false;
				 actualWeapon =-1;
			 }
			 
		 }
		 
		 if(this.registerWeapons[actualWeapon+1])
		 {
			 if(actualWeapon >=0 && actualWeapon <= 4)
			 {
				 for(j=0; j<6; j++)
				 {
					 weapons[actualWeapon][j].setX(play.getMatrix()[weapons[actualWeapon][j].getLine()][weapons[actualWeapon][j].getColumn()].getX());
					 weapons[actualWeapon][j].setY(play.getMatrix()[weapons[actualWeapon][j].getLine()][weapons[actualWeapon][j].getColumn()].getY());
				 }
			 }
			 else if(actualWeapon != -1)
			 {
				 weapons[actualWeapon][0].setX(play.getMatrix()[weapons[actualWeapon][0].getLine()][weapons[actualWeapon][0].getColumn()-weapons[actualWeapon][0].getTipo()].getX());
				 weapons[actualWeapon][0].setY(play.getMatrix()[weapons[actualWeapon][0].getLine()][weapons[actualWeapon][0].getColumn()-weapons[actualWeapon][0].getTipo()].getY());
			 }
			 weapons[actualWeapon][0].setCollided(false);
			 actualWeapon = -1;
		 }
		 
		 p.removeAll(); 
		 p.repaint();
	}
	
	public void loadMatrixLogic(Graphics2D g2d, JPanel p, Utilities u, Square[][] q, int i, int j, double mouse_x, double mouse_y, Rectangle2D r1, double tileSize)
	{
		int k;
		Rectangle2D r2[] = new Rectangle2D[6];
		int count = 0;
		if(actualWeapon >=0 && actualWeapon <= 4)
		{
			double segura,auxWidth,auxHeight;
		
			auxWidth = weapons[actualWeapon][1].getWidth()*3;
			auxHeight = weapons[actualWeapon][1].getHeight()*2;
			
			
			switch(countRotations[actualWeapon])
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
					rx_offset = -weapons[actualWeapon][1].getHeight();
					ry_offset = 0;
					segura = auxWidth;
					auxWidth = auxHeight;
					auxHeight = segura;
					for(int t=0; t<6; t++)
					{
						double w = weapons[actualWeapon][t].getWidth();
						double h = weapons[actualWeapon][t].getHeight();
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
					rx_offset = -weapons[actualWeapon][1].getWidth();
					ry_offset =-weapons[actualWeapon][1].getHeight();
					for(int t=0; t<6; t++)
					{
						double w = weapons[actualWeapon][t].getWidth();
						double h = weapons[actualWeapon][t].getHeight();
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
					ry_offset = -weapons[actualWeapon][1].getWidth();
					segura = auxWidth;
					auxWidth = auxHeight;
					auxHeight = segura;
					for(int t=0; t<6; t++)
					{
						double w = weapons[actualWeapon][t].getWidth();
						double h = weapons[actualWeapon][t].getHeight();
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
								   , auxHeight, q[i][j].getX(), q[i][j].getY(), tileSize, tileSize))
				{
					if(u.weaponCollided(weapons[actualWeapon][k].getX()+offset_kx[k], weapons[actualWeapon][k].getY()+offset_ky[k], weapons[actualWeapon][k].getWidth()
							   , weapons[actualWeapon][k].getWidth(), q[i][j].getX(), q[i][j].getY(), tileSize, tileSize))
					{
						g2d.setPaint(weapons[actualWeapon][k].getColor());
						r2[k] = new Rectangle2D.Double( q[i][j].getX(), q[i][j].getY(), q[i][j].getWidth(), q[i][j].getHeight());
						g2d.fill(r2[k]);
						
						if(registerWeapons[actualWeapon+1])
						{
							if(i-1 >= 0)
							{
								if(j-1 >= 0)
								{
									if(q[i-1][j-1].getTipo() == -1)
									{
										q[i-1][j-1].setTipo(-3);
									}
								}
								if(q[i-1][j].getTipo() == -1)
								{
									q[i-1][j].setTipo(-3);
								}
								if(j+1 <= 14)
								{
									if(q[i-1][j+1].getTipo() == -1)
									{
										q[i-1][j+1].setTipo(-3);
									}
								}
							}
							if(i+1 <= 14)
							{
								if(j-1 >= 0)
								{
									if(q[i+1][j-1].getTipo() == -1)
									{
										q[i+1][j-1].setTipo(-3);
									}
								}
								if(q[i+1][j].getTipo() >= -2)
								{
									q[i+1][j].setTipo(-3);
								}
								if(j+1 <= 14)
								{
									if(q[i+1][j+1].getTipo() == -1)
									{
										q[i+1][j+1].setTipo(-3);
									}
								}
							}
							if(j-1 >= 0)
							{
								if(q[i][j-1].getTipo() == -1)
								{
									q[i][j-1].setTipo(-3);
								}
							}
							if(j+1 <= 14)
							{
								if(q[i][j+1].getTipo() == -1)
								{
									q[i][j+1].setTipo(-3);
								}
							}
							q[i][j].setColor((k%2 != 0)? weapons[actualWeapon][k].getColor() : Color.WHITE);
							q[i][j].setTipo(weapons[actualWeapon][k].getTipo());
							weapons[actualWeapon][k].setLine(i);
							weapons[actualWeapon][k].setColumn(j);
							p.removeAll();
							p.repaint();
						}
					}
				}
				if(registerWeapons[actualWeapon+1] == false && weapons[actualWeapon][0].getColumn() != -1 && weapons[actualWeapon][0].getLine() != -1)
				{
					q[weapons[actualWeapon][k].getLine()][weapons[actualWeapon][k].getColumn()].setColor(Color.WHITE);
					q[weapons[actualWeapon][k].getLine()][weapons[actualWeapon][k].getColumn()].setTipo(-1);
				}
			}
			
		}
		else if(actualWeapon!=-1)
		{
			double segura,auxWidth,auxHeight;
			
			auxWidth = weapons[actualWeapon][0].getWidth();
			auxHeight = weapons[actualWeapon][0].getHeight();
			
			
			switch(countRotations[actualWeapon])
			{
				case 0:
					rx_offset = 0;
					ry_offset = 0;
					break;
				case 1:
					rx_offset = -weapons[actualWeapon][0].getHeight();
					ry_offset = 0;
					segura = auxWidth;
					auxWidth = auxHeight;
					auxHeight = segura;
					break;
				case 2:
					rx_offset = -weapons[actualWeapon][0].getWidth();
					ry_offset =-weapons[actualWeapon][0].getHeight();
					break;
				case 3:
					rx_offset = 0;
					ry_offset = -weapons[actualWeapon][0].getWidth();
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
				g2d.setPaint(weapons[actualWeapon][0].getColor());
				g2d.fill(r1);
				if(registerWeapons[actualWeapon+1])
				{
					if(i-1 >= 0)
					{
						if(j-1 >= 0)
						{
							if(q[i-1][j-1].getTipo() == -1)
							{
								q[i-1][j-1].setTipo(-3);
							}
						}
						if(q[i-1][j].getTipo() == -1)
						{
							q[i-1][j].setTipo(-3);
						}
						if(j+1 <= 14)
						{
							if(q[i-1][j+1].getTipo() == -1)
							{
								q[i-1][j+1].setTipo(-3);
							}
						}
					}
					if(i+1 <= 14)
					{
						if(j-1 >= 0)
						{
							if(q[i+1][j-1].getTipo() == -1)
							{
								q[i+1][j-1].setTipo(-3);
							}
						}
						if(q[i+1][j].getTipo() >= -2)
						{
							q[i+1][j].setTipo(-3);
						}
						if(j+1 <= 14)
						{
							if(q[i+1][j+1].getTipo() == -1)
							{
								q[i+1][j+1].setTipo(-3);
							}
						}
					}
					if(j-1 >= 0)
					{
						if(q[i][j-1].getTipo() == -1)
						{
							q[i][j-1].setTipo(-3);
						}
					}
					if(j+1 <= 14)
					{
						if(q[i][j+1].getTipo() == -1)
						{
							q[i][j+1].setTipo(-3);
						}
					}
					weapons[actualWeapon][0].setLine(i);
					weapons[actualWeapon][0].setColumn(j);
					q[i][j].setColor(weapons[actualWeapon][0].getColor());
					q[i][j].setTipo(weapons[actualWeapon][0].getTipo());
				}
			}
			if(registerWeapons[actualWeapon+1] == false && weapons[actualWeapon][0].getColumn() != -1 && weapons[actualWeapon][0].getLine() != -1)
			{
				if(weapons[actualWeapon][0].getTipo() < 4)
				{
					while(count < weapons[actualWeapon][0].getTipo())
					{
						if(countRotations[actualWeapon]%2 == 0)
						{
							q[weapons[actualWeapon][0].getLine()][weapons[actualWeapon][0].getColumn() - count].setColor(Color.WHITE);
							q[weapons[actualWeapon][0].getLine()][weapons[actualWeapon][0].getColumn() - count].setTipo(-1);
						}
						else
						{
							q[weapons[actualWeapon][0].getLine() - count][weapons[actualWeapon][0].getColumn()].setColor(Color.WHITE);
							q[weapons[actualWeapon][0].getLine() - count][weapons[actualWeapon][0].getColumn()].setTipo(-1);
						}
						count++;
					}
				}
				else
				{
					while(count < weapons[actualWeapon][0].getTipo()+1)
					{
						if(countRotations[actualWeapon]%2 == 0)
						{
							q[weapons[actualWeapon][0].getLine()][weapons[actualWeapon][0].getColumn() - count].setColor(Color.WHITE);
							q[weapons[actualWeapon][0].getLine()][weapons[actualWeapon][0].getColumn() - count].setTipo(-1);
						}
						else
						{
							q[weapons[actualWeapon][0].getLine() - count][weapons[actualWeapon][0].getColumn()].setColor(Color.WHITE);
							q[weapons[actualWeapon][0].getLine() - count][weapons[actualWeapon][0].getColumn()].setTipo(-1);
						}
						count++;
					}
				}
			}
		}
	}
	
	/*
	 * Espaço para as funções de lógica relacionadas
	 * a cada uma dos 5 tipos de armas/navios existentes
	 */
	
	public void hidroPlanesLogic(Graphics2D g2d, Rectangle2D[] r, int index)
	{
		int j;

		if(rotate[index] == true)
		{	
			g2d.rotate(Math.toRadians(90*countRotations[index]), weapons[index][0].getX(), weapons[index][0].getY());
		}
		
		if(comeback[index] == true)
		{
			g2d.rotate(-Math.toRadians(90*countRotations[index]), weapons[index][0].getX(), weapons[index][0].getY());
		}
		
		for(j=0;j<6;j++)
		{	
			g2d.setPaint(weapons[index][j].getColor());
			
			if(weapons[index][j].getWidth() != 0 && weapons[index][j].getHeight() != 0)
			{
				r[j] = new Rectangle2D.Double(weapons[index][j].getX(),weapons[index][j].getY(),
											 weapons[index][j].getWidth(),weapons[index][j].getHeight());
				g2d.fill(r[j]);
			}
		}
		
		if(rotate[index] == true && comeback[index] == false)
		{
			
			g2d.rotate(-Math.toRadians(90*countRotations[index]), weapons[index][0].getX(), weapons[index][0].getY());
		}
	}
	
	public void submarinosLogic(Graphics2D g2d, Rectangle2D r1, int index)
	{
		if(rotate[index] == true)
		{
			
			g2d.rotate(Math.toRadians(90*countRotations[index]), weapons[index][0].getX(), weapons[index][0].getY());
		}
		
		if(weapons[index][0].getWidth() != 0 && weapons[index][0].getHeight() != 0)
		{
			r1 = new Rectangle2D.Double(weapons[index][0].getX() , 
										weapons[index][0].getY(),weapons[index][0].getWidth(),weapons[index][0].getHeight());
			g2d.setPaint(weapons[index][0].getColor());
            g2d.fill(r1);
		}
		
		if(rotate[index] == true)
		{
			g2d.rotate(-Math.toRadians(90*countRotations[index]), weapons[index][0].getX(), weapons[index][0].getY());
		}
	}
	
	public void destroyersLogic(Graphics2D g2d, Rectangle2D r1, int index)
	{
		if(rotate[index] == true)
		{
			
			g2d.rotate(Math.toRadians(90*countRotations[index]), weapons[index][0].getX(), weapons[index][0].getY());
		}
		
		if(weapons[index][0].getWidth() != 0 && weapons[index][0].getHeight() != 0)
		{
			r1 = new Rectangle2D.Double(weapons[index][0].getX(),
										weapons[index][0].getY(),weapons[index][0].getWidth(),weapons[index][0].getHeight());
			g2d.setPaint(weapons[index][0].getColor());
            g2d.fill(r1);
		}
		
		if(rotate[index] == true)
		{
			g2d.rotate(-Math.toRadians(90*countRotations[index]), weapons[index][0].getX(), weapons[index][0].getY());
		}
	}
	
	public void cruzadoresLogic(Graphics2D g2d, Rectangle2D r1, int index)
	{
		if(rotate[index] == true)
		{
			
			g2d.rotate(Math.toRadians(90*countRotations[index]), weapons[index][0].getX(), weapons[index][0].getY());
		}
		
		if(weapons[index][0].getWidth() != 0 && weapons[index][0].getHeight() != 0)
		{
			r1= new Rectangle2D.Double(weapons[index][0].getX(),weapons[index][0].getY()
									  ,weapons[index][0].getWidth(),weapons[index][0].getHeight());
			g2d.setPaint(weapons[index][0].getColor());
            g2d.fill(r1);
		}
		
		if(rotate[index] == true)
		{
			g2d.rotate(-Math.toRadians(90*countRotations[index]), weapons[index][0].getX(), weapons[index][0].getY());
		}
	}
	
	public void couracadoLogic(Graphics2D g2d, Rectangle2D r1, int index)
	{
		if(weapons[index][0].getWidth() != 0 && weapons[index][0].getHeight() != 0)
		{
			if(rotate[index] == true)
			{
				
				g2d.rotate(Math.toRadians(90*countRotations[index]), weapons[index][0].getX(), weapons[index][0].getY());
			}
			
			
			r1 = new Rectangle2D.Double(weapons[index][0].getX(),
										weapons[index][0].getY(),weapons[index][0].getWidth(),weapons[index][0].getHeight());
			g2d.setPaint(weapons[index][0].getColor());
	        g2d.fill(r1);
	        
	    	if(rotate[index] == true)
			{
				g2d.rotate(-Math.toRadians(90*countRotations[index]), weapons[index][0].getX(), weapons[index][0].getY());
			}
		}
	}
	
	/*public void batalhaLogic(Graphics2D g2d,Rectangle2D r1,Square a[][],int index,Utilities u)
	{
		
	}*/
	
	
	/*
	 * Esta parte referencia todas as funções acionadas
	 * ao se apertar alguma tecla no teclado
	 */
	
	//Funciona bem como um key listener, só que utiliza actions, meio q na gambiarra
    public void setKeyBindings(JPanel p, LoadLogicController l) {
        ActionMap actionMap = p.getActionMap();
        int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = p.getInputMap(condition);

        //Utilizaremos duas teclas, "R" para rotacionar e "Esc" para voltar atrás em uma adição de arma
        String vkR = "VK_R";
        String vkEsc = "VK_ESCAPE";
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), vkEsc);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), vkR);

        //Crio uma ação pra cada tecla
        actionMap.put(vkR, new KeyAction(vkR, p, l));
        actionMap.put(vkEsc, new KeyAction(vkEsc, p, l));

    }
	
	public void rotateAction(JPanel p)
	{
		//Se o numero de vezes q eu apertei R foi maior 3, quer dizer q eu voltei 
 	   //pra estaca zero, ou seja, a peça não esta rotacionada, então o vetor de rotacao
 	   //para a arma que esta sendo clicada vai voltar a ser falso
 	   if(countRotations[actualWeapon] > 3)
 	   {
 		   countRotations[actualWeapon] = 0;
 		   rotate[actualWeapon] = false;
 	   }
 	   //Se eu cliquei em alguma arma e apertei R, o vetor de rotacoes, na posição
 	   //referente à arma que cliquei, vai receber true e o numero de rotacoes feitas
 	   //é incrementado
 	   if(actualWeapon != -1)
 	   {
 		   rotate[actualWeapon] = true;
 		   countRotations[actualWeapon]++;
 		   p.removeAll();
 		   p.repaint();
 	   }
	}
	
	public void escapeAction(JPanel p)
	{
		comeback[actualWeapon] = true;
	}
	
	/*
	 * Espaço para todas as funções get e set
	 * em nome de utilizar variáveis desse módulo
	 */
	
	public double getPreviousX(int i)
	{
		return this.previousX[i];
	}
	
	public double getPreviousY(int i)
	{
		return this.previousY[i];
	}
	
	public Square getWeapons(int i, int j)
	{
		return this.weapons[i][j];
	}
	
	public void atualizarWeapons(Square[][] w)
	{
		for(int i=0; i < 15; i++)
		{
			for(int j=0; j < 6; j++)
			{
				weapons[i][j] = w[i][j];
			}
		}
	}
	
	public int getCountRotations(int i)
	{
		return this.countRotations[i];
	}
	
	public boolean getRegisterWeapons(int i)
	{
		return this.registerWeapons[i];
	}
	
	public boolean getRotate(int i)
	{
		return this.rotate[i];
	}
	
	public boolean getComeback(int i)
	{
		return this.comeback[i];
	}
	
	public int getActualWeapon()
	{
		return actualWeapon;
	}
	
	public void resetIndex()
	{
		this.index = 0;
	}
}
