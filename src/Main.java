public class Main {
	public static void main(String[] args)
	{
		MainPanel p = new MainPanel();
		Janela f = Janela.getJanela();
		f.draw(p);
		f.setTitle("Batalha Naval Delícia");
		f.setVisible(true);
	}
	
	public void load()
	{
		
	}
}
