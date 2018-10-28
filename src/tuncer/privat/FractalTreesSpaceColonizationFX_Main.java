package tuncer.privat;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FractalTreesSpaceColonizationFX_Main extends Application {
	
	private Canvas can;
	private Tree tree;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() throws Exception {
		Timeline tl_draw = new Timeline(new KeyFrame(Duration.millis(1000/60), e -> {
			this.draw();
		}));
		tl_draw.setCycleCount(Timeline.INDEFINITE);
		tl_draw.play();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 400, 400);
		
		root.setStyle("-fx-background-color: #515151");
		
		this.can = new Canvas(scene.getWidth(), scene.getHeight());
		
		this.tree = new Tree(this.can.getGraphicsContext2D());
		
		root.setCenter(this.can);
		
		primaryStage.setTitle("FractalTreesSpaceColonizationFX");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void draw() {
		this.can.getGraphicsContext2D().clearRect(0, 0, this.can.getWidth(), this.can.getHeight());
		
		this.tree.grow();
		this.tree.show();
	}
}