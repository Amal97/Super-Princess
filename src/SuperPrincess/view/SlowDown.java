package SuperPrincess.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class SlowDown {

	Image slowdownImage = new Image("file:res/slowdown.png");
	ImageView slowdownView = new ImageView();
	Circle slowdown = new Circle();

	// Makes the slow down power down
	public Circle slowDown(int x, int y) {
		slowdown.setRadius(25);
		slowdown.setFill(new ImagePattern(slowdownImage));
		slowdown.setLayoutY(130);
		slowdown.setLayoutX(120);
		slowdown.setCenterX(x);
		slowdown.setCenterY(y);
		return slowdown;
	}
	
	// Returns the power up
	public Circle returnSlowDown(){
		return slowdown;
	}
}
