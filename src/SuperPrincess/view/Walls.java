package SuperPrincess.view;

import javafx.scene.image.*;

public class Walls  {
	
	Image wall = new Image("file:res/hedge.png");
	ImageView showWall = new ImageView();

	public Walls(){
	}
	
	// Makes walls
	public ImageView makeWall(int x, int y) {
		showWall.setImage(wall);
		showWall.setFitHeight(45);
		showWall.setFitWidth(45);
		showWall.setX(x);
		showWall.setY(y+90);
		return showWall;
	}
}