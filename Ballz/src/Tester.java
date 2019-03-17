import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Tester extends JPanel {

	JFrame window = new JFrame("Title");
	Timer tmr = null;
	Random rnd = new Random();	
	ArrayList<Block> blocks = new ArrayList<>();
	ArrayList<Ball> balls = new ArrayList<>();
	boolean waiting = true;
	Line line = new Line();
	long startTime;
	int ballsLeft;
	boolean fast = false;
	int level = 1;
	Block levelBlock = new Block(7,0,0);



	public Tester() {
		window.setBounds(460, 0, 720, 1000);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().add(this);
		window.setAlwaysOnTop(true);
		window.setVisible(true);



		for (int j = 0; j < 7; j++) {
			if (rnd.nextBoolean())
				blocks.add(new Block(0,j,level));
		}


		//======================================== Events		
		tmr = new Timer(15, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!waiting) {
					if (ballsLeft > 0) {
						if (fast && System.currentTimeMillis() - startTime > 67) {
							balls.add(new Ball(line.angle, fast));
							startTime = System.currentTimeMillis();
							ballsLeft--;
						}
						else if (!fast && System.currentTimeMillis() - startTime > 200) {
							balls.add(new Ball(line.angle, fast));
							startTime = System.currentTimeMillis();
							ballsLeft--;
						}
					}
					if (ballsLeft == 0 && balls.size() == 0) {
						nextLevel();
					}
					for (int i = balls.size()-1; i >= 0; i--) {
						Ball ball = balls.get(i);
						if (ball.move(fast)) {
							balls.remove(ball);
						}
						else {
							for (Block block : blocks) {

								if (block.intersects(ball)) {

									if (block.bounce(ball)) 
										blocks.remove(block);
									break;	

								}

							}
						}
					}
				}
				repaint();
			}

			private void nextLevel() {
				level++;
				waiting = true;
				for (Block block : blocks) {
					if (block.descend()) {
						repaint();
						tmr.stop();
						JOptionPane.showOptionDialog(window, "Level Reached: " + --level, "Game Over", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);					
						System.exit(0);
					}
				}
				for (int j = 0; j < 7; j++) {
					if (rnd.nextBoolean())
						blocks.add(new Block(0,j,level));
				}
			}
		});

		window.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (waiting) {
					fast = false;
					waiting = false;
					balls.add(new Ball(line.angle, fast));
					startTime = System.currentTimeMillis();
					ballsLeft = level - 1;
				}
				else {
					fast = true;
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});

		window.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if (waiting)
					line.mouse(e.getX(), e.getY());
			}

			@Override
			public void mouseDragged(MouseEvent e) {

			}
		});

		window.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});

		//======================================== End Events
		tmr.start();
	}



	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Font f = new Font("Dialog", Font.BOLD, 32);
		g.setFont(f);

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 700, 1000);


		for (Block block : blocks)
		{
			block.draw(g);
		}
		for (Ball ball : balls)
		{
			ball.draw(g);
		}

		if (waiting)
			line.draw(g);

		Font f2 = new Font("Dialog", Font.BOLD, 48);
		g.setFont(f2);
		levelBlock.drawLevelBlock(g, level);
		if (!waiting && ballsLeft > 0)
			g.drawString(Integer.toString(ballsLeft), 340, 900);

	}

	public static void main(String[] args) {
		new Tester();
	}
}