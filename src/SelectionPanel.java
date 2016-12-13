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
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class SelectionPanel extends JPanel implements  MouseListener, MouseMotionListener{
	
	private Janela janelaAtual;
	private JTextField[] jtext;
	private final int dimensao = 15;
    private final double tamquadrado = 32;
	private double mouseX;
	private double mouseY;
	private DrawWeaponsFrame d = new DrawWeaponsFrame();
	private Square weapons[][] = new Square[15][6];
	private int qtdTipo[] = new int[5];
	private Color colorTipo[] = new Color[5];
	private Matrix m = new Matrix(dimensao,dimensao, tamquadrado);
	private Utilities u = Utilities.getUtilities();
	LoadLogicController l = new LoadLogicController();
	MainController main = MainController.getMainController();
	private Player p;
    public SelectionPanel(Janela j1, JTextField[] j) {
        this.janelaAtual = j1;
        this.jtext = j;
    }
    
    public void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
        JButton b1 = new JButton("Confirmar/Próximo");
		JButton b2 = new JButton("Confirmar e Batalhar");
        this.addMouseListener(this);
        this.addMouseMotionListener(this); 
        l.setKeyBindings(this, l);
        p = main.getActualPlayer();
        Graphics2D g2d = (Graphics2D)g;
        
        l.load_qdTipo(this.qtdTipo);
        
        l.load_colorTipo(this.colorTipo);
        
        l.load_weapons(this.qtdTipo, this.colorTipo, u, tamquadrado);
    	
        g2d.drawString(p.getName()+",selecione uma arma na lista", 500, 635);
        
        d.drawHidroPlanes(g2d, tamquadrado,l);
        d.drawSubmarinos(g2d, tamquadrado,l);
        d.drawDestroyers(g2d, tamquadrado, l);
        d.drawCruzadores(g2d, tamquadrado, l);
        d.drawCouracado(g2d, tamquadrado, l);
        
        m.drawMatrix(g, this, this.mouseX, this.mouseY, 700.0, 80.0, l, p);
        
		b1.setBounds(520, 638, 100, 50);
		b2.setBounds(520, 638, 100, 50);
		
		if(main.getActualPlayerIndex() == 0)
		{
			this.add(b1);
			b1.addActionListener(new SelectionJanela(this.janelaAtual, this.jtext, main.getActualPlayerIndex()));
		}
		else
		{
			this.add(b2);
			b2.addActionListener(new BattleJanela(this.janelaAtual));
		}
    }
    
	public void mousePressed(MouseEvent me) 
	{
		 Point point = me.getPoint();
		 //System.out.println("mousePressed at " + point);
		 this.mouseX = point.x;
		 this.mouseY = point.y;
		 p = main.getActualPlayer();
		 l.mousePressedLogic(this, me, p, this.mouseX, this.mouseY, tamquadrado, u);
	}

	public void mouseMoved(MouseEvent me) {
		 Point point = me.getPoint();
		 //System.out.println("mouseMoved at " + point);
		 this.mouseX = point.x;
		 this.mouseY = point.y;
		 p = main.getActualPlayer();
		 l.mouseMovedLogic(this, me, p, this.mouseX, this.mouseY, tamquadrado);
	}
	
	//Not used from the interfaces implemented
    public void mouseClicked(MouseEvent me) {}

	public void mouseExited(MouseEvent me) {}

	public void mouseEntered(MouseEvent me) {}

	public void mouseReleased(MouseEvent me) {}

	public void mouseDragged(MouseEvent me) {}
}