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
	private Square weapons[] = new Square[15];
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
    	int i;
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
        
    	System.out.println(weapons[0]);
    	if(ArrayisEmpty(weapons, 15))
    	{
	        for(i=0; i<5; i++)
	    	{
	    		while(aux_index < this.qtdTipo[i])
	    		{
	    			if(i == 0)
	    			{
	    				weapons[this.index] = new Square(30, 115, 3*tamquadrado, 2*tamquadrado, false, i, this.colorTipo[i]);
	    			}
	    			else
	    			{
	    				weapons[this.index] = new Square(30, 115, i*tamquadrado, tamquadrado, false, i, this.colorTipo[i]);
	    			}
	    			increment = (i == 3 && aux_index == 1)? 125 : 0;
	    			aux_x = weapons[this.index].getX()+125*(aux_index)+increment ;
	    			aux_y = weapons[this.index].getY()+80*i;
	    			weapons[this.index].setX(aux_x);
	    			weapons[this.index].setY(aux_y);
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
        
        m.drawMatrix(g, this.mouseX, this.mouseY, 700.0, 80.0, (this.actualWeapon != -1)? weapons[this.actualWeapon].getWidth() : 0, (this.actualWeapon != -1)? weapons[this.actualWeapon].getHeight() : 0,(this.actualWeapon != -1)? weapons[this.actualWeapon].getColor() : Color.RED);
    }
    
    public boolean ArrayisEmpty(Square s[], int tam)
    {
    	for(int i=0; i<tam; i++)
    	{
    		if(s[i] != null)
    		{
    			return false;
    		}
    	}
    	return true;
    }
    
    public int findWeapon(Square s[], int tam, double mouseY, double mouseX)
    {
    	for(int i=0; i<15; i++)
    	{
    		if(mouseCollided( this.mouseX, this.mouseY, s[i].getX(), s[i].getY(),  s[i].getWidth(),  s[i].getHeight()))
    		{
    			return i;
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
		 int index;
		 Point point = me.getPoint();
		 System.out.println("mousePressed at " + point);
		 this.mouseX = point.x;
		 this.mouseY = point.y;
		 index = findWeapon(weapons, 15, this.mouseY, this.mouseX);
		 if(index != -1)
		 {
			weapons[index].setX(this.mouseX);
			weapons[index].setY(this.mouseY);
			weapons[index].setCollided(true);
			this.actualWeapon = index;
		 }
	}
	public void mouseReleased(MouseEvent me)
	{
		 
	}

	public void mouseDragged(MouseEvent me) {
		 
	}

	public void mouseMoved(MouseEvent me) {
		 Point point = me.getPoint();
		 System.out.println("mouseMoved at " + point);
		 this.mouseX = point.x;
		 this.mouseY = point.y;
		 if(this.actualWeapon != -1)
		 {
			 if(weapons[this.actualWeapon].getCollided())
			 {
				 weapons[this.actualWeapon].setX(this.mouseX);
				 weapons[this.actualWeapon].setY(this.mouseY);
			 }
		 }
		 this.repaint();
	}
	
	public boolean mouseCollided( double mouse_x, double mouse_y, double x, double y, double w, double h) 
	{
		return (mouse_x < x+w && mouse_y < y+h && y < mouse_y+1 && x < mouse_x+1);
	}
}