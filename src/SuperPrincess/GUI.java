package SuperPrincess;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class GUI {
	AudioClip buttonSound = new AudioClip("file:res/buttonClick.mp3");
	public GUI() {
		// TODO Auto-generated constructor stub
	}



	public ImageView superPrincessTitle(int Xdirection, int Ydirection){
		Image title = new Image("file:res/SuperPrincessTitle.png");
		ImageView showTitle = new ImageView();
		showTitle.setImage(title);
		showTitle.setTranslateX(Xdirection);
		showTitle.setTranslateY(Ydirection);
		showTitle.setScaleX(0.8);
		showTitle.setScaleY(0.8);
		return showTitle;
	}

	public ImageView backButton(){
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
				showBack.setImage(backButtonPink);
			}
		});
		showBack.setOnMouseExited(new EventHandler<MouseEvent>
		() {
			@Override
			public void handle(MouseEvent t) {
				showBack.setImage(backButtonPink);
			}
		});
		return showBack;
	}

	public ImageView background(){
		Image backgroundImage = new Image("file:res/bg.png");
		ImageView showBackgroundImage = new ImageView();
		showBackgroundImage.setImage(backgroundImage);
		showBackgroundImage.setFitWidth(Main.WIDTH);
		showBackgroundImage.setFitHeight(Main.HEIGHT);
		return showBackgroundImage;
	}

	public ImageView SP(){
		Image SP = new Image("file:res/SP1.png");
		ImageView showSP = new ImageView();
		showSP.setImage(SP);
		showSP.setFitWidth(80);
		showSP.setFitHeight(80);
		showSP.setX(700);
		showSP.setY(10);
		return showSP;
	}

	public ImageView title(String source){
		Image title = new Image(source);
		ImageView showTitle = new ImageView();
		showTitle.setImage(title);
		showTitle.setScaleX(0.7);
		showTitle.setScaleY(0.7);
		showTitle.setTranslateY(-250);
		return showTitle;
	}

	public ImageView paused(){
		Image paused = new Image("file:res/paused.png");
		ImageView showPaused = new ImageView();
		showPaused.setImage(paused);
		showPaused.setY(450);
		showPaused.setX(700);
		return showPaused;
	}

	public ImageView gameOver(){
		Image congratulations = new Image("file:res/gameOver.png");
		ImageView showCongratulations = new ImageView();
		showCongratulations.setImage(congratulations);
		showCongratulations.setX(500);
		showCongratulations.setY(100);
		return showCongratulations;
	}

	public Text makeText(String text, double x, double y, int fontSize, String color) {
		Text showText = new Text();
		showText.setText(text);
		showText.setX(x);
		showText.setY(y);
		if(color == "pink"){
			showText.setFill(Color.DEEPPINK);
		}
		else{
			showText.setFill(Color.WHITE);
		}
		showText.setTextAlignment(TextAlignment.LEFT);
		try {
			showText.setFont(Font.loadFont(new FileInputStream(new File("pamega.ttf")), fontSize));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
		return showText;
	}

	public Button makeButton(String text, double x, double y, double width, double height, int fontSize) {
		Button showButton = new Button();
		showButton.setText(text);
		showButton.setBackground(Background.EMPTY);
		showButton.setTranslateX(x);
		showButton.setTranslateY(y);
		showButton.setMinWidth(width);
		showButton.setMinHeight(height);
		showButton.setCursor(Cursor.HAND);

		showButton.setOnMouseEntered(new EventHandler<MouseEvent>
		() {
			@Override
			public void handle(MouseEvent t) {
				buttonSound.play();
				showButton.setTextFill(Color.DEEPPINK);
			}
		});
		showButton.setOnMouseExited(new EventHandler<MouseEvent>
		() {
			@Override
			public void handle(MouseEvent t) {
				showButton.setTextFill(Color.WHITE);
			}
		});

		try {
			showButton.setFont(Font.loadFont(new FileInputStream(new File("pamega.ttf")), fontSize));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  

		showButton.setTextFill(Color.WHITE);	
		return showButton;
	}
}