import javax.swing.JTextField;

public class MainController {
	private int NUMTOTAL = 0;
	private static int actualPlayer = 0;
	private static MainController m = null;
	private Player p[] = new Player[2];
	public static Square matrix[][] = new Square[15][15];
	
	public MainController()
	{
		
	}
	
	public static MainController getMainController(){
		if(m == null)
		{
			m = new MainController();
		}
	   
		return m;
	}
	
	public void createPlayers(JTextField[] j)
	{
		int i;
		for(i=0; i<2; i++)
		{
			this.p[i] = new Player();
			this.p[i].setName(j[i].getText());
			System.out.println(j[i].getText());
		}
	}
	
	public Player getActualPlayer()
	{
		return this.p[MainController.actualPlayer];
	}
	
	public int getActualPlayerIndex()
	{
		return MainController.actualPlayer;
	}
	
	public void changeActualPlayer()
	{
		MainController.actualPlayer++;
	}
	
	public void resetActualPlayer()
	{
		MainController.actualPlayer = 0;
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