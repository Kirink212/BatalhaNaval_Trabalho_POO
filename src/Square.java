
public class Square {
	private double x = -1;
	private double y = -1;
	private double w = -1;
	private double h = -1;
	private boolean collided = false;
	
	public Square(double x, double y, double w, double h, boolean col)
	{
		this.collided = col;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public double getX()
	{
		return this.x;
	}
	
	public double getY()
	{
		return this.y;
	}
	
	public double getWidth()
	{
		return this.w;
	}
	
	public double getHeight()
	{
		return this.h;
	}
	
	public boolean getCollided()
	{
		return this.collided;
	}
	
	public void setX(double x)
	{
		this.x = x;
	}
	
	public void setY(double y)
	{
		this.y = y;
	}
	
	public void setCollided(boolean b)
	{
		this.collided = b;
	}

	public boolean isEmpty() {
		if(this.getX() == -1 && this.getY() == -1)
		{
			return true;
		}
		return false;
	}
}
