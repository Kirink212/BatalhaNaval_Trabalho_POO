
public class Square {
	private double x=0.0;
	private double y = 0.0;
	private boolean collided = false;
	
	public Square(double x, double y, boolean col)
	{
		this.collided = col;
		this.x = x;
		this.y = y;
	}
	
	public double getX()
	{
		return this.x;
	}
	
	public double getY()
	{
		return this.y;
	}
	
	public boolean getCollided()
	{
		return this.collided;
	}
}
