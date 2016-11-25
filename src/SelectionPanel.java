import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class SelectionPanel extends JPanel implements  MouseListener, MouseMotionListener {
	
	private  final double dimensao = 15;
    private final double tamquadrado = 32;
    private boolean pressed = false;
	private double mouseX;
	private double mouseY;
	private Square weapons[][] = new Square[15][6];
	private int actualWeapon = -1;
	private DrawWeaponsFrame d = new DrawWeaponsFrame();
	private double dx=0,dy=0;
	private int index = 0;
	private int tipo_atual = 0;
	private int qtdTipo[] = new int[5];
	private Color colorTipo[] = new Color[5];
	
    public SelectionPanel() {
        
    }
    
    public void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
    	double aux_x, aux_y;
    	int aux_tipo, aux_index = 0;
    	double increment;
    	int i, j;
        MainController main = MainController.getMainController();
        Matrix m = new Matrix(dimensao,dimensao, tamquadrado);
        this.addMouseListener(this);
        this.addMouseMotionListener(this); 
        Player p = main.getActualPlayer();
        Graphics2D g2d = (Graphics2D)g;
        for(i=0; i<5; i++)
        {
        	this.qtdTipo[i] = (5-i);
        }
        
        this.colorTipo[0] = Color.GREEN;
        this.colorTipo[1] = Color.CYAN;
        this.colorTipo[2] = Color.YELLOW;
        this.colorTipo[3] = Color.ORANGE;
        this.colorTipo[4] = Color.RED;
        
    	if(ArrayisEmpty(weapons, 15))
    	{
	        for(i=0; i<5; i++)
	    	{
	    		while(aux_index < this.qtdTipo[i])
	    		{
	    			if(i == 0)
	    			{
	    				for(j=0; j<6; j++)
	    				{
	    					if(j < 3)
	    					{
	    						weapons[this.index][j] = new Square(30+j*tamquadrado, 115, tamquadrado, tamquadrado, false, i, this.colorTipo[i]);
	    					}
	    					else
	    					{
	    						weapons[this.index][j] = new Square(30+(j-3)*tamquadrado, 115+tamquadrado, tamquadrado, tamquadrado, false, i, this.colorTipo[i]);
	    					}
	    				}
	    			}
	    			else
	    			{
	    				weapons[this.index][0] = new Square(30, 115, i*tamquadrado, tamquadrado, false, i, this.colorTipo[i]);
	    			}
	    			increment = (i == 3 && aux_index == 1)? 125 : 0;
	    			if(i==0)
	    			{
	    				for(j=0; j<6; j++)
	    				{
			    			aux_x = weapons[this.index][j].getX()+125*(aux_index) + increment;
			    			aux_y = weapons[this.index][j].getY()+80*i;
			    			weapons[this.index][j].setX(aux_x);
			    			weapons[this.index][j].setY(aux_y);
	    				}
	    			}
	    			else
	    			{
	    				aux_x = weapons[this.index][0].getX()+125*(aux_index) + increment;
		    			aux_y = weapons[this.index][0].getY()+80*i;
		    			weapons[this.index][0].setX(aux_x);
		    			weapons[this.index][0].setY(aux_y);
	    			}
	    			this.index++;
	    			aux_index++;
	    		}
	    		aux_index = 0;
	    	}
    	}

        g2d.drawString(p.getName()+",selecione uma arma na lista", 550, 635);
        d.drawHidroPlanes(g2d, tamquadrado,weapons);
        d.drawSubmarinos(g2d, tamquadrado,weapons);
        d.drawDestroyers(g2d, tamquadrado, weapons);
        d.drawCruzadores(g2d, tamquadrado, weapons);
        d.drawCouracado(g2d, tamquadrado, weapons);
        
        m.drawMatrix(g, this.mouseX, this.mouseY, 700.0, 80.0, (this.actualWeapon != -1)? weapons[this.actualWeapon][0].getWidth() : 0, (this.actualWeapon != -1)? weapons[this.actualWeapon][0].getHeight() : 0,(this.actualWeapon != -1)? weapons[this.actualWeapon][0].getColor() : Color.RED);
    }
    
    public boolean ArrayisEmpty(Square s[][], int tam)
    {
    	for(int i=0; i<tam; i++)
    	{
    		if(s[i][0] != null)
    		{
    			return false;
    		}
    	}
    	return true;
    }
    
    public int findWeapon(Square s[][], int tam, double mouseY, double mouseX)
    {
    	for(int i=0; i<15; i++)
    	{
    		if(i != 0 && mouseCollided( this.mouseX, this.mouseY, s[i][0].getX(), s[i][0].getY(),  s[i][0].getWidth(),  s[i][0].getHeight()))
    		{
    			return i;
    		}
    		else if(i == 0)
    		{
				if(mouseCollided( this.mouseX, this.mouseY, s[i][0].getX(), s[i][0].getY(),  s[i][0].getWidth()*3,  s[i][0].getHeight()*2))
	    		{
	    			return i;
	    		}
    		}
    	}
		return -1;
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
		 int index, j;
		 Point point = me.getPoint();
		 System.out.println("mousePressed at " + point);
		 this.mouseX = point.x;
		 this.mouseY = point.y;
		 index = findWeapon(weapons, 15, this.mouseY, this.mouseX);
		 if(index != -1)
		 {
			weapons[index][0].setX(this.mouseX);
			weapons[index][0].setY(this.mouseY);
			weapons[index][0].setCollided(true);
			this.actualWeapon = index;
		 }
		 else if(index == 0)
		 {
			 for(j=0; j<6; j++)
			 {
				if(j < 3)
				{
					weapons[index][j].setX(this.mouseX+j*this.tamquadrado);
					weapons[index][j].setY(this.mouseY);
				}
				else
				{
					weapons[index][j].setX(this.mouseX+(j-3)*this.tamquadrado);
					weapons[index][j].setY(this.mouseY+this.tamquadrado);
				}
				weapons[index][j].setCollided(true);
				this.actualWeapon = index;
			 }
		 }
	}
	public void mouseReleased(MouseEvent me)
	{
		 
	}

	public void mouseDragged(MouseEvent me) {
		 
	}

	public void mouseMoved(MouseEvent me) {
		 int j;
		 Point point = me.getPoint();
		 System.out.println("mouseMoved at " + point);
		 this.mouseX = point.x;
		 this.mouseY = point.y;
		 if(this.actualWeapon != -1)
		 {
			 if(weapons[this.actualWeapon][0].getCollided())
			 {
				 weapons[this.actualWeapon][0].setX(this.mouseX);
				 weapons[this.actualWeapon][0].setY(this.mouseY);
			 }
		 }
		 else if(this.actualWeapon == 0)
		 {
			 for(j=0; j<6; j++)
			 {
				 if(weapons[this.actualWeapon][j].getCollided())
				 {
					 if(j < 3)
					 {
						weapons[index][j].setX(this.mouseX+j*this.tamquadrado);
						weapons[index][j].setY(this.mouseY);
					 }
					 else
					 {
						weapons[index][j].setX(this.mouseX+(j-3)*this.tamquadrado);
						weapons[index][j].setY(this.mouseY+this.tamquadrado);
					 }
				 }
			 }
		 }
		 this.removeAll(); 
		 this.repaint();
	}
	
	public boolean mouseCollided( double mouse_x, double mouse_y, double x, double y, double w, double h) 
	{
		return (mouse_x < x+w && mouse_y < y+h && y < mouse_y+1 && x < mouse_x+1);
	}
}