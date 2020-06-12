import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class Egg {
	

	int row,col;
	int w = Yard.BLOCK_SIZE;
	int h = Yard.BLOCK_SIZE;
	private static Random r = new Random();
	private static Color color = Color.GREEN;
	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @param col the col to set
	 */
	public void setCol(int col) {
		this.col = col;
	}
	
	public Egg(int row,int col){
		this.row = row;
		this.col = col;
	}
	
	public Egg(){
		this(r.nextInt(Yard.ROWS - 2) + 2, r.nextInt(Yard.COLS - 3) + 3);
	}
	
	public void reAppear(){
		this.row = r.nextInt(Yard.ROWS - 2) + 2;
		this.col = r.nextInt(Yard.COLS - 2) + 2;
	}
	
	public Rectangle getRect(){
		return new Rectangle(Yard.BLOCK_SIZE * this.col, Yard.BLOCK_SIZE * this.row
				, this.w, this.h);
		
	}
	
	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(color);
		g.fillOval(Yard.BLOCK_SIZE * col, Yard.BLOCK_SIZE * row, w, h);
		g.setColor(c);
		if(color == Color.GREEN) color = Color.RED;
		else color = Color.GREEN;
	}
}
