import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class SelectionPanel extends JPanel implements  MouseListener, MouseMotionListener{
	
	private int count_rotations[] =new int[15];
	private  final double dimensao = 15;
    private final double tamquadrado = 32;
    private boolean pressed = false;
	private double mouseX;
	private double mouseY;
	private Square weapons[][] = new Square[15][6];
	//Utilize este vetor de rotação quando alguma peça tiver sido clicada
	private boolean rotate[] = new boolean[15];
	private int actualWeapon = -1;
	private DrawWeaponsFrame d = new DrawWeaponsFrame();
	private int qtdTipo[] = new int[5];
	private Color colorTipo[] = new Color[5];
	private boolean registerWeapon[] = new boolean [16];
	private boolean comeback[] = new boolean[15];
	private Matrix m = new Matrix(dimensao,dimensao, tamquadrado);
	private int num_click[] = new int[15];
	private Utilities u = Utilities.getUtilities();
	LoadLogicController l = LoadLogicController.getLoadLogicController();
	
    public SelectionPanel() {
        
    }
    
    public void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
        MainController main = MainController.getMainController();
        this.addMouseListener(this);
        this.addMouseMotionListener(this); 
        this.setKeyBindings();
        Player p = main.getActualPlayer();
        Graphics2D g2d = (Graphics2D)g;
        
        l.load_qdTipo(this.qtdTipo);
        
        l.load_colorTipo(this.colorTipo);
        
        l.load_weapons(this.weapons, this.qtdTipo, this.colorTipo, u, tamquadrado);
    	
        g2d.drawString(p.getName()+",selecione uma arma na lista", 550, 635);
        
        d.drawHidroPlanes(g2d, tamquadrado,weapons, rotate,count_rotations,comeback);
        d.drawSubmarinos(g2d, tamquadrado,weapons, rotate,count_rotations,comeback);
        d.drawDestroyers(g2d, tamquadrado, weapons, rotate,count_rotations,comeback);
        d.drawCruzadores(g2d, tamquadrado, weapons, rotate,count_rotations,comeback);
        d.drawCouracado(g2d, tamquadrado, weapons, rotate,count_rotations,comeback);
        
        m.drawMatrix(g, this.mouseX, this.mouseY, 700.0, 80.0,weapons,this.actualWeapon, this.registerWeapon, count_rotations);
  
    }
    
    //Funciona bem como um key listener, só que utiliza actions, meio q na gambiarra
    private void setKeyBindings() {
        ActionMap actionMap = getActionMap();
        int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = getInputMap(condition );

        //Utilizaremos duas teclas, "R" para rotacionar e "Esc" para voltar atrás em uma adição de arma
        String vkR = "VK_R";
        String vkEsc = "VK_ESCAPE";
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), vkEsc);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), vkR);

        //Crio uma ação pra cada tecla
        actionMap.put(vkR, new KeyAction(vkR));
        actionMap.put(vkEsc, new KeyAction(vkEsc));

    }
    
    //Classe criada pq o KeyListener nao funcionava de jeito algum
    private class KeyAction extends AbstractAction {
    	
        public KeyAction(String actionCommand) {
           putValue(ACTION_COMMAND_KEY, actionCommand);
        }

        public void actionPerformed(ActionEvent actionEvt) {
           //Se eu estiver pressionando R
           if(actionEvt.getActionCommand() == "VK_R")
           {   
        	   
        	   System.out.println("R esta sendo apertado");
        	   //Se o numero de vezes q eu apertei R foi maior 3, quer dizer q eu voltei 
        	   //pra estaca zero, ou seja, a peça não esta rotacionada, então o vetor de rotacao
        	   //para a arma que esta sendo clicada vai voltar a ser falso
        	   if(count_rotations[actualWeapon] > 3)
        	   {
        		   count_rotations[actualWeapon] = 0;
        		   rotate[actualWeapon] = false;
        	   }
        	   //Se eu cliquei em alguma arma e apertei R, o vetor de rotacoes, na posição
        	   //referente à arma que cliquei, vai receber true e o numero de rotacoes feitas
        	   //é incrementado
        	   if(actualWeapon != -1)
        	   {
        		   rotate[actualWeapon] = true;
        		   count_rotations[actualWeapon]++;
        		   removeAll();
        		   repaint();
        	   }
           }
           
           if(actionEvt.getActionCommand() == "VK_ESCAPE")
           {   
        	   comeback[actualWeapon] = true;
        	   
           }
        }
     }
    
	public void mousePressed(MouseEvent me) 
	{
		 int index, j;
		 Point point = me.getPoint();
		 System.out.println("mousePressed at " + point);
		 this.mouseX = point.x;
		 this.mouseY = point.y;
		 index = u.findWeapon(weapons, 15, this.mouseY, this.mouseX);
		 
		 if(index >=0 && index <= 4)
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
		 else if(index != -1)
		 {
			weapons[index][0].setX(this.mouseX);
			weapons[index][0].setY(this.mouseY);
			weapons[index][0].setCollided(true);
			this.actualWeapon = index;
		 }
		 
		 if(index != -1)
		 {
			 switch(num_click[index])
			 {
			 	case 0:
					 if(index != -1 && u.mouseCollided( this.mouseX, this.mouseY, 700.0, 80.0, 15*tamquadrado, 15*tamquadrado))
			 		 {
			 			this.registerWeapon[index+1] = true;
			 			num_click[index]++;
			 			this.removeAll(); 
			 			this.repaint();
			 		 }
					 break;
			 	case 1:	 
			 		if(u.mouseCollided( this.mouseX, this.mouseY, 700.0, 80.0, 15*tamquadrado, 15*tamquadrado) && this.registerWeapon[index+1] == true)
			 		{
						this.registerWeapon[index+1] = false;
						num_click[index] = 0;
						if(index >=0 && index <= 4)
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
						else if(index != -1)
						{
							weapons[index][0].setX(this.mouseX);
							weapons[index][0].setY(this.mouseY);
							weapons[index][0].setCollided(true);
							this.actualWeapon = index;
						}
			 		}
			 		break;
			 }
		 }
	}

	public void mouseMoved(MouseEvent me) {
		 int j;
		 Point point = me.getPoint();
		 System.out.println("mouseMoved at " + point);
		 this.mouseX = point.x;
		 this.mouseY = point.y;
		 
		 if(this.actualWeapon >=0 && this.actualWeapon <= 4)
		 {
			 for(j=0; j<6; j++)
			 {
				 if(weapons[this.actualWeapon][j].getCollided())
				 {
					 if(j < 3)
					 {
						weapons[this.actualWeapon][j].setX(this.mouseX+j*this.tamquadrado);
						weapons[this.actualWeapon][j].setY(this.mouseY);
					 }
					 else
					 {
						weapons[this.actualWeapon][j].setX(this.mouseX+(j-3)*this.tamquadrado);
						weapons[this.actualWeapon][j].setY(this.mouseY+this.tamquadrado);
					 }
				 }
			 }
			 
			 if(comeback[this.actualWeapon] == true)
			 {
				 weapons[this.actualWeapon][0].setX(l.getPreviousX(this.actualWeapon));
				 weapons[this.actualWeapon][0].setY(l.getPreviousY(this.actualWeapon));
				 weapons[this.actualWeapon][0].setCollided(false);
				 comeback[this.actualWeapon] = false;
				 this.actualWeapon =-1;
			 }
			 
		 }
		 else if(this.actualWeapon != -1)
		 {
			 if(weapons[this.actualWeapon][0].getCollided())
			 {
				 weapons[this.actualWeapon][0].setX(this.mouseX);
				 weapons[this.actualWeapon][0].setY(this.mouseY);
			 }
			 
			 if(comeback[this.actualWeapon] == true)
			 {
				 weapons[this.actualWeapon][0].setX(l.getPreviousX(this.actualWeapon));
				 weapons[this.actualWeapon][0].setY(l.getPreviousY(this.actualWeapon));
				 weapons[this.actualWeapon][0].setCollided(false);
				 comeback[this.actualWeapon] = false;
				 this.actualWeapon =-1;
			 }
			 
		 }
		 
		 if(this.registerWeapon[this.actualWeapon+1])
		 {
			 weapons[this.actualWeapon][0].setCollided(false);
			 this.actualWeapon = -1;
		 }
		 
		 for(int m=0; m<16; m++)
		 {
			 System.out.println(this.registerWeapon[m]);
		 }
		 
		 this.removeAll(); 
		 this.repaint();
	}
	
	//Not used from the interfaces implemented
    public void mouseClicked(MouseEvent me) {}

	public void mouseExited(MouseEvent me) {}

	public void mouseEntered(MouseEvent me) {}

	public void mouseReleased(MouseEvent me) {}

	public void mouseDragged(MouseEvent me) {}
}