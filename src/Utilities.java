
public class Utilities {
	private static Utilities u = null;
	
	public Utilities()
	{
		
	}
	
	public static Utilities getUtilities(){
		if(u == null)
		{
			u = new Utilities();
		}
	   
		return u;
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
    		if(i>= 0 && i<= 4)
    		{
				if(mouseCollided( mouseX, mouseY, s[i][0].getX(), s[i][0].getY(),  s[i][1].getWidth()*3,  s[i][1].getHeight()*2))
	    		{
	    			return i;
	    		}
    		}
    		
    		else if(i != 0 && mouseCollided( mouseX, mouseY, s[i][0].getX(), s[i][0].getY(),  s[i][0].getWidth(),  s[i][0].getHeight()))
    		{
    			return i;
    		}
    	}
		return -1;
    }
    
    public boolean mouseCollided( double mouse_x, double mouse_y, double x, double y, double w, double h) 
	{
		return (mouse_x < x+w && mouse_y < y+h && y < mouse_y+1 && x < mouse_x+1);
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
