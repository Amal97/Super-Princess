package SuperPrincess.view;

import SuperPrincess.GUI;
import SuperPrincess.Main;
import SuperPrincess.controller.SceneManager;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;

public class CharacterScreen {
	private Princess princessOne = new Princess();
	private Princess princessTwo = new Princess();
	private Princess princessThree = new Princess();
	private Image princessOneSelected = new Image ("file:res/Amalia_selected.png");
	private Image princessTwoSelected = new Image ("file:res/Kunalia_selected.png");
	private Image princessThreeSelected = new Image ("file:res/Dhariyana_selected.png");
	private ImageView showPrincessOne;
	private ImageView showPrincessTwo;
	private ImageView showPrincessThree;
	private StackPane layout = new StackPane();
	private GUI gui = new GUI();
	private SceneManager sceneManager;
	private Scene scene;
	private int characterNum = 0;

	TextField username = new TextField();
	private AlertBox namealert = new AlertBox();
	private HighScore highscore = new HighScore(this.sceneManager);

	AudioClip buttonSound = new AudioClip("file:res/buttonClick.mp3");

	public CharacterScreen(SceneManager sceneManager){
		this.sceneManager = sceneManager;
	}

	// Creates the character screen
	public Scene makeScene(int setWidth, int setHeight){
		Scene scene = new Scene(layout, setWidth, setHeight);
		this.scene = scene;
		background();
		title();
		username();
		backButton();
		handleInputs();
		princessOne();
		princessTwo();
		princessThree();
		return scene;
	}

	// Creates the title for the character screen
	public void title(){
		Image title = new Image("file:res/chooseCharacter.png");
		ImageView showTitle = new ImageView();
		showTitle.setImage(title);
		showTitle.setScaleX(0.7);
		showTitle.setScaleY(0.7);	
		showTitle.setTranslateY(-250);
		layout.getChildren().add(showTitle);	
	}

	// Sets a text box for the user to enter their name
	public void username() {
		username.setMinWidth(160);
		username.setMaxWidth(160);
		username.setTranslateY(150);
		layout.getChildren().add(username);	
	}

	// Checks if the text box has been filled with name, if not it gives an error
	public void checkName(int princessNum) {
		if(!(username.getText().trim().equals(""))) { 
			sceneManager.MainGame(sceneManager,princessNum);
			Main.name = username.getText();
			System.out.println("Name = " + username.getText());
		}else {
			namealert.nameAlertBox();
			System.out.println("No Name");
		}
	}

	// Displays the first princess, clicking takes you to the game
	public void princessOne(){
		showPrincessOne = princessOne.makePrincess(1,200,200,0,0);
		showPrincessOne.setCursor(Cursor.HAND);
		showPrincessOne.setOnMouseEntered(new EventHandler<MouseEvent>
		() {
			@Override
			public void handle(MouseEvent t) {
				buttonSound.play();
				showPrincessOne.setImage(princessOneSelected);
			}
		});
		showPrincessOne.setOnMouseExited(new EventHandler<MouseEvent>
		() {
			@Override
			public void handle(MouseEvent t) {
				showPrincessOne.setImage(princessOne.princess());
			}
		});
		showPrincessOne.addEventHandler(MouseEvent.MOUSE_CLICKED , event ->{
			checkName(1);
		});

		showPrincessOne.setTranslateY(0);
		showPrincessOne.setTranslateX(-325);
		layout.getChildren().add(showPrincessOne);	
	}

	// Displays the second princess, clicking takes you to the game
	public void princessTwo(){
		showPrincessTwo = princessTwo.makePrincess(2,200,200,0,0);
		showPrincessTwo.setCursor(Cursor.HAND);
		showPrincessTwo.setOnMouseEntered(new EventHandler<MouseEvent>
		() {
			@Override
			public void handle(MouseEvent t) {
				buttonSound.play();
				showPrincessTwo.setImage(princessTwoSelected);
			}
		});
		showPrincessTwo.setOnMouseExited(new EventHandler<MouseEvent>
		() {
			@Override
			public void handle(MouseEvent t) {
				showPrincessTwo.setImage(princessTwo.princess());
			}
		});
		showPrincessTwo.addEventHandler(MouseEvent.MOUSE_CLICKED , event ->{
			checkName(2);
		});

		showPrincessTwo.setTranslateY(0);
		showPrincessTwo.setTranslateX(0);
		layout.getChildren().add(showPrincessTwo);	
	}

	// Displays the third princess, clicking takes you to the game
	public void princessThree(){
		showPrincessThree = princessThree.makePrincess(3,200,200,0,0);
		showPrincessThree.setCursor(Cursor.HAND);
		showPrincessThree.setOnMouseEntered(new EventHandler<MouseEvent>
		() {
			@Override
			public void handle(MouseEvent t) {
				buttonSound.play();
				showPrincessThree.setImage(princessThreeSelected);
			}
		});
		showPrincessThree.setOnMouseExited(new EventHandler<MouseEvent>
		() {
			@Override
			public void handle(MouseEvent t) {
				showPrincessThree.setImage(princessThree.princess());
			}
		});
		showPrincessThree.addEventHandler(MouseEvent.MOUSE_CLICKED , event ->{
			checkName(3);
		});
		showPrincessThree.setTranslateY(0);
		showPrincessThree.setTranslateX(325);
		layout.getChildren().add(showPrincessThree);	
	}

	// Creates the background for the character screen
	public void background(){
		ImageView showBackgroundImage = gui.background() ;
		layout.getChildren().add(showBackgroundImage);	
	}

	// Creates the back button
	public void backButton(){
		Image backButtonWhite = new Image("file:res/back.png");
		Image backButtonPink = new Image("file:res/backPink.png");
		ImageView showBack = new ImageView();
		showBack.setImage(backButtonWhite);
		showBack.setScaleX(0.3);
		showBack.setScaleY(0.3);
		showBack.setTranslateY(230);
		showBack.setTranslateX(-350);
		showBack.setCursor(Cursor.HAND);
		showBack.setOnMouseEntered(new EventHandler<MouseEvent>
		() {
			@Override
			public void handle(MouseEvent t) {
				buttonSound.play();
				showBack.setImage(backButtonPink);
			}
		});
		showBack.setOnMouseExited(new EventHandler<MouseEvent>
		() {
			@Override
			public void handle(MouseEvent t) {
				showBack.setImage(backButtonWhite);
			}
		});

		showBack.addEventHandler(MouseEvent.MOUSE_CLICKED , event ->{
			sceneManager.MenuScreen(sceneManager);
		});

		layout.getChildren().add(showBack);			
	}

	// Allows the user to select their character using the keyboard
	private void handleInputs(){
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				switch (keyEvent.getCode()) {
				case LEFT:
					buttonSound.play();
					if (characterNum > 1) {
						characterNum--;
					} else {
						characterNum = 3;
					}
					break;
				case RIGHT:
					buttonSound.play();
					if (characterNum < 3) {
						characterNum++;
					} else {
						characterNum = 0;
					}
					break;
				case ENTER:
					buttonSound.play();
					if(characterNum == 1){
						checkName(1);
					}
					else if(characterNum == 2){
						checkName(2);
					}
					else if(characterNum == 3){
						checkName(3);
					}
					break;
				default:
					break;
				}
				if(characterNum ==1){
					showPrincessOne.setImage(princessOneSelected);
				}
				else{
					showPrincessOne.setImage(princessOne.princess());
				}
				if(characterNum == 2){
					showPrincessTwo.setImage(princessTwoSelected);
				}
				else{
					showPrincessTwo.setImage(princessTwo.princess());
				}
				if(characterNum == 3){
					showPrincessThree.setImage(princessThreeSelected);
				}
				else{
					showPrincessThree.setImage(princessThree.princess());
				}
			}
		});
	}
}

