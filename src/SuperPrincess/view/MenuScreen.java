package SuperPrincess.view;

import SuperPrincess.GUI;
import SuperPrincess.Main;
import SuperPrincess.controller.SceneManager;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;

public class MenuScreen {
	private SceneManager sceneManager;
	StackPane layout = new StackPane();
	GUI gui = new GUI();
	private int menuNum = 0;
	
	Scene scene;

	Image storyModeWhite = new Image("file:res/storyModeWhite.png");
	Image storyModePink = new Image("file:res/storyModePink.png");
	ImageView storyMode = new ImageView();

	Image singlePlayerWhite = new Image("file:res/singlePlayer.png");
	Image singlePlayerPink = new Image("file:res/singlePlayerPink.png");
	ImageView showPlay = new ImageView();

	Image multiplayerWhite = new Image("file:res/multiplayer.png");
	Image multiplayerPink = new Image("file:res/multiplayerPink.png");
	ImageView showMultiplayer = new ImageView();

	Image highscoreWhite = new Image("file:res/highscore.png");
	Image highscorePink = new Image("file:res/highscorePink.png");
	ImageView showHighscore = new ImageView();

	Image helpWhite = new Image("file:res/helpWhite.png");
	Image helpPink = new Image("file:res/helpPink.png");
	ImageView showCredits = new ImageView();

	Image exitWhite = new Image("file:res/exit.png");
	Image exitPink = new Image("file:res/exitPink.png");
	ImageView showExit = new ImageView();

	AudioClip buttonSound = new AudioClip("file:res/buttonClick.mp3");
	
	public MenuScreen(SceneManager sceneManager){
		this.sceneManager = sceneManager;
	}

	// Makes the menu screen
	public Scene makeScene(int setWidth, int setHeight){
		Main.storyLine = 1;
		Main.levelNum = 1;
		Main.score = 0;
		Main.lifes = 3;
		Main.youLost = false;
		Scene thisScene = new Scene(layout,setWidth, setHeight);
		scene = thisScene;
		background();
		title();
		handleInputs();
		storyModeButton();
		singlePlayerButton();
		multiPlayerButton();
		highscoreButton();
		creditsButton();
		exitButton();
		return scene;
	}

	// Makes the title for the menu screen
	public void title(){
		ImageView title = gui.superPrincessTitle(0,-320);
		layout.getChildren().add(title);	
	}

	// Makes the backgorund for the menu screen
	public void background(){
		ImageView showBackgroundImage = gui.background() ;
		layout.getChildren().add(showBackgroundImage);	
	}
	
	// Creates a button for story mode
	public void storyModeButton(){
		storyMode.setImage(storyModeWhite);
		storyMode.setScaleX(0.5);
		storyMode.setScaleY(0.5);
		storyMode.setTranslateY(-180);
		storyMode.setCursor(Cursor.HAND);

		storyMode.setOnMouseEntered(new EventHandler<MouseEvent>
		() {
			@Override
			public void handle(MouseEvent t) {
				buttonSound.play();
				defaultColours();
				storyMode.setImage(storyModePink);
				menuNum = 0;
			}
		});
		storyMode.setOnMouseExited(new EventHandler<MouseEvent>
		() {
			@Override
			public void handle(MouseEvent t) {
				storyMode.setImage(storyModeWhite);
				menuNum = 0;

			}
		});
		storyMode.addEventHandler(MouseEvent.MOUSE_CLICKED , event ->{
			sceneManager.StoryMode(sceneManager);
		});
		layout.getChildren().add(storyMode);	
	}

	// Creates a button for single player mode
	public void singlePlayerButton(){
		showPlay.setImage(singlePlayerWhite);
		showPlay.setScaleX(0.5);
		showPlay.setScaleY(0.5);
		showPlay.setTranslateY(-80);
		showPlay.setCursor(Cursor.HAND);

		showPlay.setOnMouseEntered(new EventHandler<MouseEvent>
		() {
			@Override
			public void handle(MouseEvent t) {
				buttonSound.play();
				defaultColours();
				showPlay.setImage(singlePlayerPink);
				menuNum = 1;
			}
		});
		showPlay.setOnMouseExited(new EventHandler<MouseEvent>
		() {
			@Override
			public void handle(MouseEvent t) {
				showPlay.setImage(singlePlayerWhite);
				menuNum = 0;

			}
		});
		showPlay.addEventHandler(MouseEvent.MOUSE_CLICKED , event ->{
			sceneManager.CharacterScreen(sceneManager);
		});
		layout.getChildren().add(showPlay);	
	}

	// Creates a button for multiplayer mode
	public void multiPlayerButton(){
		showMultiplayer.setImage(multiplayerWhite);
		showMultiplayer.setScaleX(0.5);
		showMultiplayer.setScaleY(0.5);
		showMultiplayer.setTranslateY(20);
		showMultiplayer.setCursor(Cursor.HAND);
		showMultiplayer.setOnMouseEntered(new EventHandler<MouseEvent>
		() {
			@Override
			public void handle(MouseEvent t) {
				buttonSound.play();
				defaultColours();
				showMultiplayer.setImage(multiplayerPink);
				menuNum = 2;
			}
		});
		showMultiplayer.setOnMouseExited(new EventHandler<MouseEvent>
		() {
			@Override
			public void handle(MouseEvent t) {
				showMultiplayer.setImage(multiplayerWhite);
			}
		});
		showMultiplayer.addEventHandler(MouseEvent.MOUSE_CLICKED , event ->{
			sceneManager.Multiplayer(sceneManager);
		});
		layout.getChildren().add(showMultiplayer);	
	}

	// Creates a button for highscore mode
	public void highscoreButton(){
		showHighscore.setImage(highscoreWhite);
		showHighscore.setScaleX(0.5);
		showHighscore.setScaleY(0.5);
		showHighscore.setTranslateY(120);
		showHighscore.setCursor(Cursor.HAND);
		showHighscore.setOnMouseEntered(new EventHandler<MouseEvent>
		() {
			@Override
			public void handle(MouseEvent t) {
				buttonSound.play();
				defaultColours();
				showHighscore.setImage(highscorePink);
				menuNum = 3;
			}
		});
		showHighscore.setOnMouseExited(new EventHandler<MouseEvent>
		() {
			@Override
			public void handle(MouseEvent t) {
				showHighscore.setImage(highscoreWhite);
				menuNum = 0;

			}
		});

		showHighscore.addEventHandler(MouseEvent.MOUSE_CLICKED , event ->{
			sceneManager.HighScore(sceneManager);
		});
		layout.getChildren().add(showHighscore);			
	}

	// Creates a button for credits mode
	public void creditsButton(){
		showCredits.setImage(helpWhite);
		showCredits.setScaleX(0.5);
		showCredits.setScaleY(0.5);
		showCredits.setTranslateY(220);
		showCredits.setCursor(Cursor.HAND);
		showCredits.setOnMouseEntered(new EventHandler<MouseEvent>
		() {
			@Override
			public void handle(MouseEvent t) {
				buttonSound.play();
				defaultColours();
				showCredits.setImage(helpPink);
				menuNum = 4;
			}
		});
		showCredits.setOnMouseExited(new EventHandler<MouseEvent>
		() {
			@Override
			public void handle(MouseEvent t) {
				showCredits.setImage(helpWhite);
				menuNum = 0;

			}
		});
		showCredits.addEventHandler(MouseEvent.MOUSE_CLICKED , event ->{
			sceneManager.Help(sceneManager);
		});
		layout.getChildren().add(showCredits);	
	}

	// Creates a button for the exit
	public void exitButton(){
		showExit.setImage(exitWhite);
		showExit.setScaleX(0.5);
		showExit.setScaleY(0.5);
		showExit.setTranslateY(320);
		showExit.setCursor(Cursor.HAND);
		showExit.setOnMouseEntered(new EventHandler<MouseEvent>
		() {
			@Override
			public void handle(MouseEvent t) {
				buttonSound.play();
				defaultColours();
				showExit.setImage(exitPink);
				menuNum = 5;
			}
		});
		showExit.setOnMouseExited(new EventHandler<MouseEvent>
		() {
			@Override
			public void handle(MouseEvent t) {
				showExit.setImage(exitWhite);
				menuNum = 0;

			}
		});
		showExit.addEventHandler(MouseEvent.MOUSE_CLICKED , event ->{
			Platform.exit();
		});
		layout.getChildren().add(showExit);	
	}
	
	// Sets the default colours for the menu buttons
	private void defaultColours(){
		showPlay.setImage(singlePlayerWhite);
		storyMode.setImage(storyModeWhite);
		showMultiplayer.setImage(multiplayerWhite);
		showHighscore.setImage(highscoreWhite);
		showCredits.setImage(helpWhite);
		showExit.setImage(exitWhite);
	}

	// Handles the inputs from the user
	private void handleInputs(){
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				switch (keyEvent.getCode()) {
				case UP:
					buttonSound.play();
					if (menuNum > 1) {
						menuNum--;
					} else {
						menuNum = 6;
					}
					break;
				case DOWN:
					buttonSound.play();
					if (menuNum < 6) {
						menuNum++;
					} else {
						menuNum = 01;
					}
					break;
				case ENTER:
					buttonSound.play();
					if(menuNum == 1){
						sceneManager.StoryMode(sceneManager);
					}
					if(menuNum == 2){
						sceneManager.CharacterScreen(sceneManager);
					}
					else if(menuNum == 3){
						sceneManager.Multiplayer(sceneManager);
					}
					else if(menuNum == 4){
						sceneManager.HighScore(sceneManager);

					}
					else if(menuNum == 5){
						sceneManager.Help(sceneManager);
					}
					else if(menuNum == 6){
						Platform.exit();
					}
					break;
				}
				if(menuNum ==1){
					storyMode.setImage(storyModePink);
				}
				else{
					storyMode.setImage(storyModeWhite);
				}

				if(menuNum == 2){
					showPlay.setImage(singlePlayerPink);
				}
				else{
					showPlay.setImage(singlePlayerWhite);

				}
				if(menuNum == 3){
					showMultiplayer.setImage(multiplayerPink);
				}
				else{
					showMultiplayer.setImage(multiplayerWhite);
				}
				if(menuNum == 4){
					showHighscore.setImage(highscorePink);
				}
				else{
					showHighscore.setImage(highscoreWhite);
				}

				if(menuNum == 5){
					showCredits.setImage(helpPink);
				}
				else{
					showCredits.setImage(helpWhite);
				}
				if(menuNum == 6){
					showExit.setImage(exitPink);
				}
				else{
					showExit.setImage(exitWhite);
				}
			}

		});

	}

}

