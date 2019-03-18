import java.awt.Color;
import java.awt.Graphics;
/*
 * Class for the dotted line that the user uses to aim and shoot
 */
public class Line {

	int startX, startY, endX, endY;
	double angle;
	Color color;
	int mouseX, mouseY;

	public Line() {
		startX = 340;
		startY = 800;
		angle = 90;
		color = Color.WHITE;
	}

	public void mouse(int x, int y) {
		mouseX = x - 20;
		mouseY = y - 55;
		if (mouseY > 785)
			mouseY = 785;
		angle = 180.0 - (Math.toDegrees(Math.atan((mouseX - 340.0) / (800.0 - mouseY))) + 90.0);
	}

	public void draw(Graphics g) {
		int width, height;
		width = height = 30;

		g.setColor(color);
		g.fillOval(startX, startY, width, height);

		g.fillOval((int)(0.2 * (mouseX-startX) + startX), (int)(0.2 * (mouseY-startY) + startY), width, height);
		g.fillOval((int)(0.4 * (mouseX-startX) + startX), (int)(0.4 * (mouseY-startY) + startY), width, height);
		g.fillOval((int)(0.6 * (mouseX-startX) + startX), (int)(0.6 * (mouseY-startY) + startY), width, height);
		g.fillOval((int)(0.8 * (mouseX-startX) + startX), (int)(0.8 * (mouseY-startY) + startY), width, height);

		g.fillOval(mouseX, mouseY, width, height);
	}

}
