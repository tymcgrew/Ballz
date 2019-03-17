import java.awt.Color;
import java.awt.Graphics;

public class Line {

	int startX, startY, endX, endY;
	double angle;
	Color color;
	int mouseX, mouseY;

	public Line() {
		startX = 340;
		startY = 900;
		angle = 90;
		color = Color.WHITE;
	}

	public void mouse(int x, int y) {
		mouseX = x - 20;
		mouseY = y - 55;
		if (mouseY > 885)
			mouseY = 885;
		angle = 180.0 - (Math.toDegrees(Math.atan((mouseX - 340.0) / (900.0 - mouseY))) + 90.0);
	}

	public void draw(Graphics g) {
		int width, height;
		width = height = 30;

		g.setColor(color);
		g.fillOval(startX, startY, width, height);

		g.fillOval((int)(0.33 * (mouseX-startX) + startX), (int)(0.33 * (mouseY-startY) + startY), width, height);
		g.fillOval((int)(0.67 * (mouseX-startX) + startX), (int)(0.67 * (mouseY-startY) + startY), width, height);

		g.fillOval(mouseX, mouseY, width, height);
	}

}
