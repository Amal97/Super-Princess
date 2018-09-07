package SuperPrincess.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Sword {	
	Image swordImage = new Image("file:res/sword.png");
	ImageView swordView = new ImageView();
	Circle sword = new Circle();

	// Makes the sword power up in the game
	public Circle sword(int x, int y) {
		sword.setRadius(20);
		sword.setFill(new ImagePattern(swordImage));
		sword.setLayoutY(130);
		sword.setLayoutX(120);
		sword.setCenterX(x);
		sword.setCenterY(y);
		return sword;
	}
	
	// Returns the sword
	public Circle returnSword(){
		return sword;
	}

}
