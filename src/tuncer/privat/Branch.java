package tuncer.privat;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Branch {
	
	private Branch parent;
	private Point2D pos, dir, origDir;
	private int count, len;
	
	private GraphicsContext gc;

	
	
	public Branch(GraphicsContext gc, Branch parent, Point2D pos, Point2D dir) {
		this.pos = pos;
		this.parent = parent;
		this.dir = dir;
		this.origDir = dir;		//copy
		this.count = 0;
		this.len = 5;
		
		this.gc = gc;
	}
	
	public void reset() {
		this.dir = this.origDir;	//copy
		this.count = 0;
	}
	
	public Branch next() {
		Point2D nextDir = this.dir.multiply(this.len);
		Point2D nextPos = this.pos.add(nextDir);
		Branch nextBranch = new Branch(this.gc, this, nextPos, this.dir);	//copy
		return nextBranch;
	}
	
	public void show() {
		if (this.parent != null) {
			this.gc.setStroke(Color.WHITE);
			this.gc.strokeLine(this.pos.getX(), this.pos.getY(), this.parent.getPos().getX(), this.parent.getPos().getY());
		}
	}
	
	public Point2D getPos() {
		return this.pos;
	}
	
	public void setDir(Point2D dir) {
		this.dir = dir;
	}
	
	public Point2D getDir() {
		return this.dir;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public int getCount() {
		return this.count;
	}
}