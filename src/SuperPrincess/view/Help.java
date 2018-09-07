package SuperPrincess.view;

import SuperPrincess.GUI;
import SuperPrincess.controller.SceneManager;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;


public class Help {
	private SceneManager sceneManager;
	StackPane layout = new StackPane();
	GUI gui = new GUI();
	AudioClip buttonSound = new AudioClip("file:res/buttonClick.mp3");
	Image backPink = new Image("file:res/backPink.png");
	Image back = new Image("file:res/back.png");
	Image userMove = new Image("file:res/playerMove.png");
	Image enemyMove = new Image("file:res/enemyMove.png");
	public Help(SceneManager sceneManager){
		this.sceneManager = sceneManager;
	}

	public Scene makeScene(int setWidth, int setHeight){
		Scene scene = new Scene(layout, setWidth, setHeight);
		background();
		title();
		backButton();
		imageEnemyMovement();
		imageUserMovement();
		return scene;
	}
	
	public void title(){
		ImageView showTitle = gui.title("file:res/helpWhite.png");
		layout.getChildren().add(showTitle);	
	}

	public void background(){
		ImageView showBackgroundImage = gui.background() ;
		layout.getChildren().add(showBackgroundImage);	
	}

	public void backButton(){
		ImageView backButton = gui.backButton();
		backButton.setOnMouseEntered(new EventHandler<MouseEvent>
		() {
			@Override
			public void handle(MouseEvent t) {
				buttonSound.play();
				backButton.setImage(backPink);
			}
		});
		backButton.setOnMouseExited(new EventHandler<MouseEvent>
		() {
			@Override
			public void handle(MouseEvent t) {
				backButton.setImage(back);
			}
		});
		backButton.addEventHandler(MouseEvent.MOUSE_CLICKED , event ->{
			buttonSound.play();
			sceneManager.MenuScreen(sceneManager);
		});
		layout.getChildren().add(backButton);			
	}
	
	public void imageUserMovement(){
			ImageView showImageUserMovement = new ImageView();
			showImageUserMovement.setImage(userMove);
			showImageUserMovement.setFitHeight(200);
			showImageUserMovement.setFitWidth(200);
			showImageUserMovement.setTranslateY(0);
			showImageUserMovement.setTranslateX(-200);
			layout.getChildren().add(showImageUserMovement);
		}
	public void imageEnemyMovement(){
		ImageView showImageEnemyMovement = new ImageView();
		showImageEnemyMovement.setImage(enemyMove);
		showImageEnemyMovement.setFitHeight(200);
		showImageEnemyMovement.setFitWidth(200);
		showImageEnemyMovement.setTranslateY(0);
		showImageEnemyMovement.setTranslateX(200);
		layout.getChildren().add(showImageEnemyMovement);
	}
}

