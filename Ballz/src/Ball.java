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
	
	public Ball(double angle) {
		//x,y,width,height
		super(340, 800, 30, 30);
		xDouble = 340.0;
		yDouble = 800.0;
		color = Color.RED;
		xSpeed = 12.0 * Math.cos(Math.toRadians(angle));
		ySpeed = -12.0 * Math.sin(Math.toRadians(angle));	
	}
	
	public boolean move() {
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
		if (y + height > 900)
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
