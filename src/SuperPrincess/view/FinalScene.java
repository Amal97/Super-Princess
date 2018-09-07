package SuperPrincess.view;

import SuperPrincess.GUI;
import SuperPrincess.Main;
import SuperPrincess.controller.SceneManager;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class FinalScene {
	private SceneManager sceneManager;
	Group layout = new Group();
	GUI gui = new GUI();
	static int score;
	
	public FinalScene(SceneManager sceneManager){
		this.sceneManager = sceneManager;
		 score = Main.score;
	}

	public Scene makeScene(int setWidth, int setHeight){
		Scene scene = new Scene(layout, setWidth, setHeight);
		background();
		title();
		text();
		showScore();
		exitButton();
		return scene;
	}
	
	public void showScore(){
		Label scoreLabel = new Label();
		scoreLabel.setText(Integer.toString(Main.score));
		layout.getChildren().add(scoreLabel);
	}
	
	public static int sendScore() {
		return Main.score;
	}
	
	public void title(){
		ImageView showTitle = gui.gameOver();
		layout.getChildren().add(showTitle);	
	}


	public void background(){
		ImageView showBackgroundImage = gui.background() ;
		layout.getChildren().add(showBackgroundImage);	
	}

	public void exitButton(){
		Button exitButton = new Button();
		exitButton = gui.makeButton("Exit to main screen", 100,700,20,20,36);
		exitButton.setCursor(Cursor.HAND);
		exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED , event ->{
			sceneManager.MenuScreen(sceneManager);
		});
		layout.getChildren().add(exitButton);				
	}
	
	public void text(){
	layout.getChildren().add(gui.makeText("Your score is " + Main.score, 300, 300, 36, "white"));
	}

}

