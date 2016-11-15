
public class MainController {
	private int NUMTOTAL;
	private int actualPlayer;
	private static MainController m = null;
	
	public MainController()
	{
		
	}
	
	public static MainController getMainController(){
		if(m == null)
		{
			m = new SelectionController();
		}
	   
		return m;
	}
	
	public void actions()
	{
		
	}
	
	public int getActualPlayer()
	{
		return 1; //placeholder para o indice do player corrente
	}
	
	public int changeActualPlayer()
	{
		return this.actualPlayer++;
	}
	
	public int getTotalNumberOfPlayers()
	{
		return this.NUMTOTAL;
		
	}
	
	public void addPlayer()
	{
		this.NUMTOTAL++;
	}
}
