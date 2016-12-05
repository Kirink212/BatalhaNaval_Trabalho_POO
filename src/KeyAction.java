import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JPanel;

//Classe criada pq o KeyListener nao funcionava de jeito algum
public class KeyAction extends AbstractAction {
	private JPanel p;
	private LoadLogicController l;
	
    public KeyAction(String actionCommand, JPanel p, LoadLogicController l) {
       putValue(ACTION_COMMAND_KEY, actionCommand);
       this.p = p;
       this.l = l;
    }

    public void actionPerformed(ActionEvent actionEvt) {
       //Se eu estiver pressionando R
       if(actionEvt.getActionCommand() == "VK_R")
       {   
    	   l.rotateAction(p);
       }
       
       if(actionEvt.getActionCommand() == "VK_ESCAPE")
       {   
    	   l.escapeAction(p);
       }
    }
 }