public class Player {
	private String name;
	private Square matrix[][] = new Square[15][15];
	
	public Player()
	{
		
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public Square[][] getMatrix()
	{
		return this.matrix;
	}
	
	public void setMatrix(Square[][] m, double n1, double n2)
	{
		for(int i=0; i < n1; i++)
		{
			for(int j=0; j < n2; j++)
			{
				this.matrix[i][j] = m[i][j];
			}
		}
	}
}
