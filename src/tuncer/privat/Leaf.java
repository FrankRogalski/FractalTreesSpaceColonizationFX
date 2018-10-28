package tuncer.privat;

import java.util.concurrent.ThreadLocalRandom;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Leaf {

	private Point2D pos;
	private boolean reached;
	private GraphicsContext gc;
	
	public Leaf(GraphicsContext gc) {
		final double x = ThreadLocalRandom.current().nextDouble(gc.getCanvas().getWidth());
		final double y = ThreadLocalRandom.current().nextDouble(gc.getCanvas().getHeight() - 100);
		
		this.pos = new Point2D(x, y);
		this.reached = false;
		
		this.gc = gc;
	}
	
	public void show() {
		this.gc.setFill(Color.WHITE);
		this.gc.fillOval(this.pos.getX(), this.pos.getY(), 4, 4);
	}
	
	public Point2D getPos() {
		return this.pos;
	}
	
	public void setReached(boolean reached) {
		this.reached = reached;
	}
	
	public boolean getReached() {
		return this.reached;
	}
}