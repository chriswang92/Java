import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Yard extends Frame {
	public static final int ROWS = 30;
	public static final int COLS = 30;
	public static final int BLOCK_SIZE = 15;
	// private static boolean isAlive = true;
	private static boolean gameOver = false;
	PaintThread paintThread = new PaintThread();
	private int score = 0;
	private int speed = 100;

	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * @param speed
	 *            the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	Image offScreenImage = null;
	Snake snake = new Snake(this);
	Egg egg = new Egg();

	public void launch() {
		this.setLocation(200, 200);
		this.setSize(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});
		this.setVisible(true);
		this.addKeyListener(new KeyMonitor());
		new Thread(paintThread).start();
	}

	public static void stop() {
		gameOver = true;
	}

	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
		g.setColor(Color.DARK_GRAY);
		for (int i = 1; i < ROWS; i++) {
			g.drawLine(0, BLOCK_SIZE * i, COLS * BLOCK_SIZE, BLOCK_SIZE * i);
		}
		for (int i = 1; i < COLS; i++) {
			g.drawLine(BLOCK_SIZE * i, 0, BLOCK_SIZE * i, ROWS * BLOCK_SIZE);
		}
		g.setColor(Color.BLUE);
		g.drawString("Score: " + score, 10, 60);
		g.drawString("Speed: " + speed, 10, 80);
		if (gameOver) {
			Font font = new Font("Verdana", Font.BOLD, 50);
			g.setFont(font);
			g.setColor(Color.YELLOW);
			g.drawString("game over", 100, 100);
			paintThread.gameOver();
		}
		g.setColor(c);
		snake.eat(egg);
		egg.draw(g);
		snake.draw(g);
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	class PaintThread implements Runnable {
		private boolean running = true;

		@Override
		public void run() {
			while (running) {
				repaint();
				try {
					Thread.sleep(speed);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			gameOver();
		}

		public void gameOver() {
			running = false;
		}

		public void speedUp() {
			speed -= 5;
		}
	}

	private class KeyMonitor extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			snake.keyPressed(e);
		}
	}

	public static void main(String[] args) {
		new Yard().launch();
	}
}
