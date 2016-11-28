import java.awt.Color;

public class Square {
	private double x = -1;
	private double y = -1;
	private double w = -1;
	private double h = -1;
	private boolean collided = false;
	private int tipo;
	private Color cor;
	
	public Square(double x, double y, double w, double h, boolean col, int tipo, Color cor)
	{
		this.collided = col;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.tipo = tipo;
		this.cor = cor;
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
	
	public int getTipo()
	{
		return this.tipo;
	}
	
	public Color getColor()
	{
		return this.cor;
	}
	
	public void setX(double x)
	{
		this.x = x;
	}
	
	public void setY(double y)
	{
		this.y = y;
	}
	
	public void setWidth(double w)
	{
		this.w = w;
	}
	
	public void setHeight(double h)
	{
		this.h = h;
	}
	
	public void setTipo(int t)
	{
		this.tipo = t;
	}
	
	public void setCollided(boolean b)
	{
		this.collided = b;
	}
	
	public void setColor(Color c)
	{
		this.cor = c;
	}

	public boolean isEmpty() {
		if(this.getX() == -1 && this.getY() == -1)
		{
			return true;
		}
		return false;
	}
}
