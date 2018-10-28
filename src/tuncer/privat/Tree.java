package tuncer.privat;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class Tree {
	
	private ArrayList<Leaf> leaves;
	private ArrayList<Branch> branches;
	
	private int max_dist = 100, min_dist = 10;
	
	public Tree(GraphicsContext gc) {
		this.leaves = new ArrayList<Leaf>();
		this.branches = new ArrayList<Branch>();
		
		for (int i = 0;i < 50;i++) {
			this.leaves.add(new Leaf(gc));
		}
		Point2D pos = new Point2D(gc.getCanvas().getWidth()/2, gc.getCanvas().getHeight());
		Point2D dir = new Point2D(0, -1);
		Branch root = new Branch(gc, null, pos, dir);
		this.branches.add(root);
		Branch current = root;
		boolean found = false;
		while (!found) {
			for (int i = 0;i < this.leaves.size();i++) {
				double d = current.getPos().distance(this.leaves.get(i).getPos());
				if (d < this.max_dist) {
					found = true;
				}
			}
			
			if (!found) {
				Branch branch = current.next();
				current = branch;
				this.branches.add(current);
			}
		}
	}
	
	public void grow() {
		for (int i = 0;i < this.leaves.size();i++) {
			Leaf leaf = this.leaves.get(i);
			Branch closestBranch = null;
			double record = 100000;
			for (int j = 0;j < this.branches.size();j++) {
				Branch branch = this.branches.get(j);
				double d = leaf.getPos().distance(branch.getPos());
				if (d < this.min_dist) {
					leaf.setReached(true);
					closestBranch = null;
					break;
				} else
					if (closestBranch == null || d < record) {
						closestBranch = branch;
						record = d;
					}
			}
			
			if (closestBranch != null) {
				Point2D newDir = leaf.getPos().subtract(closestBranch.getPos());
				newDir = newDir.normalize();
				closestBranch.setDir(closestBranch.getDir().add(newDir));
				closestBranch.setCount(closestBranch.getCount() + 1);
			}
		}
		
		for (int i = this.leaves.size() - 1; i >= 0;i--) {
			if (this.leaves.get(i).getReached()) {
				this.leaves.remove(i);
			}
		}
		
		for (int i = this.branches.size() - 1; i >= 0;i--) {
			Branch branch = this.branches.get(i);
			if (branch.getCount() > 0) {
				branch.setDir(branch.getDir().multiply(1.0d/(branch.getCount() + 1)));
				this.branches.add(branch.next());
			}
			branch.reset();
		}	
	}
	
	public void show() {
		for (int i = 0;i < this.leaves.size();i++) {
			this.leaves.get(i).show();
		}
		
		for (int i = 0;i < this.branches.size();i++) {
			this.branches.get(i).show();
		}
	}
}