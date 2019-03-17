import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

public class Block extends Rectangle 
{

	private int row, col;
	public int value;
	public Color color;
	public Color textColor;
	Random rnd = new Random();
	
	public Block(int row, int col, int level) 
	{
		super();
		width = 90;
		height = 90;
		this.row = row;
		this.col = col;
		value = setValue(level);
		textColor = Color.BLACK;
		setPos();
	}
	
	private int setValue(int level) {
		if (level < 3) 
			return (rnd.nextBoolean())? level : level * 2;
		return (rnd.nextBoolean())? level : (rnd.nextBoolean())? level/2 : level*2;
		
	}

	private void setPos() {
		x = col * 100 + 5;
		y = (row + 1) * 100 + 5;
	}
	
	public void draw(Graphics g) {
		if (value > 99)
			color = Color.GRAY;
		else if (value > 49)
			color = Color.RED;
		else if (value > 19)
			color = Color.ORANGE;
		else if (value > 9)
			color = Color.YELLOW;
		else if (value > 4)
			color = Color.CYAN;
		else
			color = Color.GREEN;
		
		g.setColor(color);
		g.fillRect(x, y, width, height);
		g.setColor(textColor);
		g.drawRect(x, y, width, height);
		
		if (value > 99)
			g.drawString(Integer.toString(value), x + 12, y + 55);
		else if (value > 9)
			g.drawString(Integer.toString(value), x + 24, y + 55);
		else
			g.drawString(Integer.toString(value), x + 36, y + 55);

	}
	
	public boolean bounce(Ball ball) {
		
		Point ballPoint = new Point((int)ball.x + 15, (int)ball.y + 15);
		
		Point left = new Point(x, y+45);
		Point right = new Point(x+90, y+45);
		Point top = new Point(x+45, y);
		Point bottom = new Point(x+45, y+90);
		
		double leftDist = left.distance(ballPoint);
		double rightDist = right.distance(ballPoint);
		double topDist = top.distance(ballPoint);
		double bottomDist = bottom.distance(ballPoint);
		
		double min = Math.min(Math.min(leftDist, rightDist), Math.min(topDist, bottomDist));
		
		if (min == leftDist) {
			ball.x = this.x - ball.width;
			ball.xSpeed = -ball.xSpeed;
		}
		
		else if (min == rightDist) {
			ball.x = this.x + this.width;
			ball.xSpeed = -ball.xSpeed;
		}
		
		else if (min == topDist) {
			ball.y = this.y - ball.height;
			ball.ySpeed = -ball.ySpeed;   
		}
		
		else {
			ball.y = this.y + this.height;
			ball.ySpeed = -ball.ySpeed;
		}
		
		value--;
		return value == 0;
			
		
	}
	
	public boolean descend() {
		row++;
		setPos();
		return row >= 7;
	}

	public void drawLevelBlock(Graphics g, int level) {
		g.setColor(Color.WHITE);
		g.drawOval(x, y, width, height);
		if (level > 99)
			g.drawString(Integer.toString(level), x + 8, y + 60);
		else if (level > 9)
			g.drawString(Integer.toString(level), x + 20, y + 60);
		else
			g.drawString(Integer.toString(level), x + 32, y + 60);
	}
	
	
}
