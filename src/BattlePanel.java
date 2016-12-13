import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BattlePanel extends JPanel implements  MouseListener, MouseMotionListener{
	
	private double mouseX;
	private double mouseY;
	private double tamquadrado = 32.0;
	private double dimensao = 15.0;
	private boolean verifica =false;
	private int count = 0;
	private int rodada = 0;
	private MainController main = MainController.getMainController();
	private LoadLogicController l = new LoadLogicController();
	private Utilities u = Utilities.getUtilities();
	private Player[] p = main.getPlayerVector();
	private Matrix x = new Matrix(dimensao,dimensao,tamquadrado);
	private Matrix y = new Matrix(dimensao,dimensao,tamquadrado);
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.addMouseListener(this);
        this.addMouseMotionListener(this); 
        JButton comeca = new JButton("Feito");
        comeca.setBounds(550,625, 100, 50);
        comeca.addActionListener(new Trocatelas());
        this.add(comeca);
        comeca.setEnabled(true);
        
 
        DesenhaMatrix(g);
        
        repaint();
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		
		 Point point = me.getPoint();
		 //System.out.println("mousePressed at " + point);
		 this.mouseX = point.x;
		 this.mouseY = point.y;
		 
		 if(u.mouseCollided(this.mouseX, this.mouseY, 700.0, 80.0, dimensao*tamquadrado, dimensao*tamquadrado))
		 {
			
			//System.out.println(Double.toString(this.mouseX));
			//System.out.println(Double.toString(this.mouseY));
			
			if(count == 1)
			{
				for(int i=0;i<15;i++)
				{
					for(int j=0;j<15;j++)
					{
						if(u.mouseCollided(this.mouseX,this.mouseY,p[1].getMatrix()[i][j].getX(),p[1].getMatrix()[i][j].getY(),
										   p[1].getMatrix()[i][j].getWidth(),p[1].getMatrix()[i][j].getHeight()))
						{
							p[1].getMatrix()[i][j].setCollided(true);
							
							
							if(p[1].getMatrix()[i][j].getColor() != Color.WHITE && p[1].getMatrix()[i][j].getTipo()>-1 )
							{
								System.out.println(Double.toString(p[1].getMatrix()[i][j].getX()));
								System.out.println(Double.toString(p[1].getMatrix()[i][j].getY()));
							}
							else
							{
								System.out.println(p[1].getMatrix()[i][j].getTipo());
								p[1].getMatrix()[i][j].setCollided(true);
							}
						}
					}
				}
				
				contaClicados(p[1].getMatrix(),15,15);
				
				//System.out.println(Double.toString(this.mouseX));
				//System.out.println(Double.toString(this.mouseY));
			}
			else if(count == 3)
			{
				for(int i=0;i<15;i++)
				{
					for(int j=0;j<15;j++)
					{
						if(u.mouseCollided(this.mouseX,this.mouseY,p[0].getMatrix()[i][j].getX(),p[0].getMatrix()[i][j].getY(),
										   p[0].getMatrix()[i][j].getWidth(),p[0].getMatrix()[i][j].getHeight()))
						{
							
							p[0].getMatrix()[i][j].setCollided(true);
							
							if(p[0].getMatrix()[i][j].getColor() != Color.WHITE && p[0].getMatrix()[i][j].getTipo()>-1  )
							{
								System.out.println(Double.toString(p[0].getMatrix()[i][j].getX()));
								System.out.println(Double.toString(p[0].getMatrix()[i][j].getY()));
							}
							else
							{
								System.out.println();
							}
						}
					}
					
				}
				//System.out.println(Double.toString(this.mouseX));
				//System.out.println(Double.toString(this.mouseY));
			}
			
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private class Trocatelas implements ActionListener 
	{
	    public void actionPerformed(ActionEvent e){
	    	
	    	count++;
	    	
	    	if(count == 4)
	    	{
	    		count =0;
	    		rodada++;
	    		System.out.println(rodada);
	    	}
	    	
	    	
	    	
	    }
	}
	
	public void DesenhaMatrix(Graphics g)
	{
		if(count ==0)
    	{
			x.drawMatrixBattle(g, mouseX, mouseY, 40.0, 80.0,null);
    		x.drawMatrixBattle(g, mouseX, mouseY, 700.0, 80.0,null);
    	}
    	else if(count == 1)
    	{
    		x.drawMatrix(g, mouseX, mouseY, 40.0, 80.0,p[0].getMatrix());
    		x.drawMatrixBattle(g, mouseX, mouseY, 700.0, 80.0,p[1].getMatrix());
    		
    	}
    	else if (count == 2)
    	{
    		x.drawMatrixBattle(g, mouseX, mouseY, 40.0, 80.0,null);
    		x.drawMatrixBattle(g, mouseX, mouseY, 700.0, 80.0,null);
    	}
    	else if( count == 3)
    	{
    		x.drawMatrix(g,mouseX,mouseY,40.0,80.0,p[1].getMatrix());
    		x.drawMatrixBattle(g, mouseX, mouseY, 700.0, 80.0,p[0].getMatrix());
    	}
		
	}
	
	public int contaClicados(Square [][]s,int n1,int n2)
	{
		int conta = 0;
		
		for(int i= 0;i<n1;i++)
		{
			for(int j = 0;j<n2;j++)
			{
				if(s[i][j].getCollided() ==true)
				{
					conta++;
				}
			}
		}
		return conta;
	}

}
