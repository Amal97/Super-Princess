package SuperPrincess.view;
import SuperPrincess.controller.SceneManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

import SuperPrincess.GUI;
import SuperPrincess.Main;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HighScore {
	private SceneManager sceneManager;
	StackPane layout = new StackPane();
	GUI gui = new GUI();
	int scores;
	ArrayList<String> highScore = new ArrayList<>();
	Label score = new Label();
	AudioClip buttonSound = new AudioClip("file:res/buttonClick.mp3");
	Image backPink = new Image("file:res/backPink.png");
	Image back = new Image("file:res/back.png");
	String highscoreName;
	int scoreInt = FinalScene.score;
	String scoreFormat = String.format("%06d", scoreInt);
	CharacterScreen characterScreen;
	
	
	public HighScore(SceneManager sceneManager){
		this.sceneManager = sceneManager;
	}
	
	//Reads the highscore.txt file and adds ll scores and names to the arraylist "highscore"
	//sorts the arraylist from highest to lowest and assigns it as text on the score Label
	public void setHighScore() {
		score.setTextFill(Color.WHITE);
		score.setFont(new Font("Arial", 40));

		try {
			FileReader fre = new FileReader("highscore.txt");
			BufferedReader bre = new BufferedReader(fre);
	        String line;
	        while((line = bre.readLine()) != null) {
	        	highScore.add((line));
	        }
	        bre.close();
	      }catch(IOException e){
	    	  System.out.println(e);   
	      }
		
		Collections.sort(highScore);
		Collections.reverse(highScore);
		
		
		if (highScore.indexOf(scoreFormat + " " + Main.name) > -1) {
			System.out.println("Same score as one before");
		} else {
			addToFile(highScore);
		}	
		//gets all highscores and dis-playes in one label
		StringBuilder msg = new StringBuilder();
		for(int i = 0; i < highScore.size();i++) {
			msg.append(highScore.get(i));
			msg.append("\n");
		}		
		score.setText(msg.toString());
		layout.getChildren().add(score);
	}
	
	//writes the most recent highscore array to the highscore.txt file
	public void addToFile(ArrayList<String> list) {
		try {
			FileWriter fr = new FileWriter("highscore.txt");
			BufferedWriter br = new BufferedWriter(fr);
			for(int i=0; i<4; i++){
	        	br.write(list.get(i) + "\n");
			}
			if (Integer.toString(scoreInt).length() < 6) {
				DecimalFormat df = new DecimalFormat("000000");
			    String numberAsString = df.format(scoreInt);
				br.write(numberAsString);
			}else {
				br.write(Integer.toString(scoreInt));
			}
		   	br.write(" " + Main.name);
		   	
	        br.close();
	      }catch(IOException e){
	    	  System.out.println(e);   
	      }
	}
	
	//adds all objects to the scene
	public Scene makeScene(int setWidth, int setHeight){
		Scene scene = new Scene(layout, setWidth, setHeight);
		background();
		title();
		backButton();
		setHighScore();
		return scene;
	}
	
	//gets the highscore title image and displays it
	public void title(){
		ImageView showTitle = gui.title("file:res/highscore.png");
		layout.getChildren().add(showTitle);	
	}

	//creates abckground image
	public void background(){
		ImageView showBackgroundImage = gui.background() ;
		layout.getChildren().add(showBackgroundImage);	
	}

	//creates a back button to return to MenuScreen
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
}

