import java.awt.Color;

public class LoadLogicController {
	private static LoadLogicController l = null;
	private Color transparente = new Color(255,255,255,0);
	private double previousX[] = new double[15];
	private double previousY[] = new double[15];
	private int index = 0;
	
	public LoadLogicController()
	{
		
	}
	
	public static LoadLogicController getLoadLogicController(){
		if(l == null)
		{
			l = new LoadLogicController();
		}
	   
		return l;
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
    
	public void load_weapons(Square[][] weapons, int[] qtdTipo, Color[] colorTipo, Utilities u, double tamquadrado)
	{
		double increment; 
		double aux_x, aux_y;
		int aux_index = 0;
		
		if(u.ArrayisEmpty(weapons, 15))
		{
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
	    						weapons[index][j] = (j%2 != 0)? new Square(30+j*tamquadrado, 115, tamquadrado, tamquadrado, false, i, colorTipo[i]) 
	    								: new Square(30+j*tamquadrado, 115, tamquadrado, tamquadrado, false, i, transparente);
	    					}
	    					else
	    					{
	    						weapons[index][j] = (j%2 != 0)? new Square(30+(j-3)*tamquadrado, 115+tamquadrado, tamquadrado, tamquadrado, false, i, colorTipo[i])
	    								: new Square(30+(j-3)*tamquadrado, 115+tamquadrado, tamquadrado, tamquadrado, false, i, transparente);
	    					}
	    				}
	    			}
	    			else
	    			{
	    				if(i > 3 )
	    				{
	    					weapons[index][0] = new Square(30, 115, (i+1)*tamquadrado, tamquadrado, false, i, colorTipo[i]);
	    				}
	    				else
	    				{
	    					weapons[index][0] = new Square(30, 115, i*tamquadrado, tamquadrado, false, i, colorTipo[i]);
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
	
	public double getPreviousX(int i)
	{
		return this.previousX[i];
	}
	
	public double getPreviousY(int i)
	{
		return this.previousY[i];
	}
}
