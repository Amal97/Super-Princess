package SuperPrincess.view;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class SpeedUp {

	Image speedUpImage = new Image("file:res/speedup.png");
	Circle speedUp = new Circle();
	
	// Makes the speedup power up
	public Circle speedUp(int x, int y) {
		speedUp.setRadius(25);
		speedUp.setFill(new ImagePattern(speedUpImage));
		speedUp.setLayoutY(130);
		speedUp.setLayoutX(120);
		speedUp.setCenterX(x);
		speedUp.setCenterY(y);
		return speedUp;
	}
	
	// Returns the power up
	public Circle returnSpeedUp(){
		return speedUp;
	}
}
