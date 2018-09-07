package SuperPrincess.view;

import javafx.scene.image.*;

public class Pellet  {

	Image diamond = new Image("file:res/diamond.png");
	ImageView showDiamond = new ImageView();
	
	public ImageView makePellet(int x, int y) {
		showDiamond.setImage(diamond);
		showDiamond.setFitHeight(25);
		showDiamond.setFitWidth(25);
		showDiamond.setX(x+10);
		showDiamond.setY(y+100);
		return showDiamond;
	}
		
	public ImageView showDiamond(){
		return showDiamond;
	}
	
	

}