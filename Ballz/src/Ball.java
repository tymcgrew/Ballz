import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
/*
 * Class for a ball that bounces around the game
 */
public class Ball extends Rectangle {

	public double xSpeed, ySpeed;
	Color color;
	boolean fast;
	double xDouble, yDouble;
	
	public Ball(double angle, boolean fast) {
		//x,y,width,height
		super(340, 900, 30, 30);
		xDouble = 340.0;
		yDouble = 900.0;
		color = Color.RED;
		this.fast = fast;
		if (fast) {
			xSpeed = 36.0 * Math.cos(Math.toRadians(angle));
			ySpeed = -36.0 * Math.sin(Math.toRadians(angle));
		}
		else {
			xSpeed = 12.0 * Math.cos(Math.toRadians(angle));
			ySpeed = -12.0 * Math.sin(Math.toRadians(angle));
		}
		
	}
	
	public boolean move(boolean fast) {
		if (!this.fast && fast) {
			this.fast = true;
			xSpeed = 3.0 * xSpeed;
			ySpeed = 3.0 * ySpeed;
		}
		xDouble += xSpeed;
		x = (int) xDouble;
		if (x < 0) {
			x = 0;
			xDouble = 0.0;
			xSpeed = -xSpeed;
		}
		if (x + width > 700) {
			x = 700 - width;
			xDouble = 700.0 - width;
			xSpeed = -xSpeed;
		}
		
		yDouble += ySpeed;
		y = (int) yDouble;
		if (y < 0) {
			y = 0;
			yDouble = 0.0;
			ySpeed = -ySpeed;
		}
		if (y + height > 1000)
			return true;
		
		return false;
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawOval(x, y, width, height);
	}
	
}
