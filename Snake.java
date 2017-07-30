import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Snake {
	private Node head = null;
	private Node tail = null;
	private int size = 0;
	private Yard yard;
	private Node n = new Node(20, 30, Direction.LEFT);

	public Snake(Yard yard) {
		this.head = n;
		this.tail = n;
		this.size = 1;
		this.yard = yard;
	}

	public void addToTail() {
		Node newTail = null;
		switch (tail.direction) {
			case LEFT:
				newTail = new Node(tail.row, tail.col + 1, tail.direction);
				break;
			case RIGHT:
				newTail = new Node(tail.row, tail.col - 1, tail.direction);
				break;
			case UP:
				newTail = new Node(tail.row - 1, tail.col, tail.direction);
				break;
			case DOWN:
				newTail = new Node(tail.row + 1, tail.col, tail.direction);
				break;
		}
		tail.next = newTail;
		newTail.prev = tail;
		tail = newTail;
		size++;
	}

	public void addToHead() {
		Node newHead = null;
		switch (head.direction) {
			case LEFT:
				newHead = new Node(head.row, head.col - 1, head.direction);
				break;
			case RIGHT:
				newHead = new Node(head.row, head.col + 1, head.direction);
				break;
			case UP:
				newHead = new Node(head.row - 1, head.col, head.direction);
				break;
			case DOWN:
				newHead = new Node(head.row + 1, head.col, head.direction);
				break;
		}
		newHead.next = head;
		head.prev = newHead;
		head = newHead;
		size++;
	}

	public void draw(Graphics g) {
		if (size <= 0)
			return;
		move();
		for (Node n = head; n != null; n = n.next) {
			n.draw(g);
		}

	}

	private void move() {
		addToHead();
		deleteFromTail();
		checkDead();
	}

	private void checkDead() {
		if (head.row < 1 || head.row > Yard.ROWS - 1 || head.col < 0
				|| head.col > Yard.COLS) {
			yard.stop();
		}
		for (Node n = head.next; n != null; n = n.next) {
			if (head.row == n.row && head.col == n.col) {
				yard.stop();
			}
		}
	}

	private void deleteFromTail() {
		if (size == 0)
			return;
		tail = tail.prev;
		tail.next = null;
		size--;
	}

	private class Node {
		int w = Yard.BLOCK_SIZE;
		int h = Yard.BLOCK_SIZE;
		int row, col;
		Direction direction = Direction.LEFT;
		Node next = null;
		Node prev = null;

		Node(int row, int col, Direction direction) {
			this.row = row;
			this.col = col;
			this.direction = direction;
		}

		void draw(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.BLACK);
			g.fillRect(Yard.BLOCK_SIZE * col, Yard.BLOCK_SIZE * row, w, h);
			g.setColor(c);

		}

	}

	public void eat(Egg e) {
		if (this.getRect().intersects(e.getRect())) {
			e.reAppear();
			addToHead();
			yard.setScore(yard.getScore() + 5);
			if (yard.getSpeed() > 0)
				yard.paintThread.speedUp();
		}

	}

	private Rectangle getRect() {
		return new Rectangle(Yard.BLOCK_SIZE * head.col, Yard.BLOCK_SIZE
				* head.row, head.w, head.h);

	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
			case KeyEvent.VK_LEFT:
				if (head.direction != Direction.RIGHT)
					head.direction = Direction.LEFT;
				break;
			case KeyEvent.VK_RIGHT:
				if (head.direction != Direction.LEFT)
					head.direction = Direction.RIGHT;
				break;
			case KeyEvent.VK_UP:
				if (head.direction != Direction.DOWN)
					head.direction = Direction.UP;
				break;
			case KeyEvent.VK_DOWN:
				if (head.direction != Direction.UP)
					head.direction = Direction.DOWN;
				break;
		}

	}

}
