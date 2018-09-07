package SuperPrincess.view;

import SuperPrincess.controller.SceneManager;
import SuperPrincess.GUI;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;

public class StartScreen {
	private SceneManager sceneManager;
	GUI gui = new GUI();
	StackPane layout = new StackPane();
	Scene scene;
	AudioClip buttonSound = new AudioClip("file:res/buttonClick.mp3");

	public StartScreen(SceneManager sceneManager){
		this.sceneManager = sceneManager;
	}

	// Makes the start scree
	public Scene makeScene(int setWidth, int setHeight){
		Scene scene = new Scene(layout, setWidth, setHeight);
		this.scene = scene;
		background();
		title();
		playButton();
		handleInputs();
		return scene;
	}

	// Makes the title for the start screen
	public void title(){
		ImageView title = gui.superPrincessTitle(0,-80);
		layout.getChildren().add(title);	
	}

	// Makes the play button in the start screen which take you to the menu screne
	public void playButton(){
		Image play = new Image("file:res/playBut.png");
		ImageView showPlay = new ImageView();
		showPlay.setImage(play);
		showPlay.setScaleX(0.07);
		showPlay.setScaleY(0.07);
		showPlay.setTranslateY(80);
		showPlay.setCursor(Cursor.HAND);
		showPlay.setOnMouseEntered(new EventHandler<MouseEvent>
		() {
			@Override
			public void handle(MouseEvent t) {
				buttonSound.play();
			}
		});
		showPlay.addEventHandler(MouseEvent.MOUSE_CLICKED , event ->{
			buttonSound.play();
			sceneManager.MenuScreen(sceneManager);
		});

		layout.getChildren().add(showPlay);	
	}

	// Makes the background for the start screen
	public void background(){
		ImageView showBackgroundImage = gui.background() ;
		layout.getChildren().add(showBackgroundImage);	
	}

	// Handles the users input (to able able to press enter to go to the menu screen)
	private void handleInputs(){
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				switch (keyEvent.getCode()) {
				case ENTER:
					buttonSound.play();
					sceneManager.MenuScreen(sceneManager);
				default:
					break;
				}
			}
		});
	}

}


