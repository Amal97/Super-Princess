package SuperPrincess.view;

import SuperPrincess.GUI;
import SuperPrincess.Main;
import SuperPrincess.controller.SceneManager;
import SuperPrincess.model.MainGame;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class StoryMode {

	private SceneManager sceneManager;
	private Scene scene;
	Group layout = new Group();
	GUI gui = new GUI();
	MainGame mainGame;

	Button buttonStoryA = new Button();
	Button buttonStoryB = new Button();
	Button buttonStoryC = new Button();
	Button buttonStoryD = new Button();

	public StoryMode() {
	}

	public StoryMode(SceneManager sceneManager){
		this.sceneManager = sceneManager;
	}

	// Makes the story mode screens
	public Scene makeScene(int setWidth, int setHeight){
		scene = new Scene(layout, setWidth, setHeight);
		background();
		if(Main.storyLine == 1){
			storyA();
		}
		else if(Main.storyLine == 2){
			storyB();
		}
		else if(Main.storyLine == 3){
			storyC();
		}
		else if(Main.storyLine == 4){
			storyD();
		}
		if(Main.youLost){
			sceneManager.FinalScene(sceneManager);
		}
		return scene;
	}

	// Makes the background for the story mode screen
	public void background(){
		ImageView showBackgroundImage = gui.background() ;
		layout.getChildren().add(showBackgroundImage);	
	}

	// Makes the button for the story mode screen like next level or finish game)
	public void makeButton(String text){
		Button startStoryButton = new Button();
		startStoryButton = gui.makeButton(text, 550, 500, 30, 50,50);

		startStoryButton.setCursor(Cursor.HAND);
		startStoryButton.addEventHandler(MouseEvent.MOUSE_CLICKED , event ->{
			if(Main.youLost || Main.storyLine ==4){
				sceneManager.FinalScene(sceneManager);
			}
			else{
				sceneManager.MainGame(sceneManager,1);
			}
		});
		layout.getChildren().add(startStoryButton);	
	}

	// Makes the exit button for the story mode
	public void exitButtonStory(){
		Button exitStoryButton = new Button();
		exitStoryButton = gui.makeButton("Exit", 100, 700, 30, 50,36);

		exitStoryButton.setCursor(Cursor.HAND);
		exitStoryButton.addEventHandler(MouseEvent.MOUSE_CLICKED , event ->{
			sceneManager.MenuScreen(sceneManager);
		});
		layout.getChildren().add(exitStoryButton);	
	}


	// Makes the story for the first scene
	public void storyA(){
		String storyLineA = "You are the daughter of King Geroge and her wife Queen Elizabeth.\n"
				+ "Your mother passed away when you were young therefore your father got married to Mary \n"
				+ "You have recently gotten married even though your stepmom doesnt like your new husband. \n"
				+ "Therefore your stepmom has kidnapped your husband and hidden him somewhere.\n"
				+ "You are trying to look for him, but your stepmom has orederd the knights to stop you from finding him. \n"
				+ "You have to go through different maze to find your husband. \n"
				+ "Will you able able to defeat the knights and find your husband? \n";

		Text storyA = 	gui.makeText(storyLineA, 50, 100, 36,"white");
		makeButton("Start Game");
		exitButtonStory();
		layout.getChildren().add(storyA);
	}

	// Makes the story for the second scene
	public void storyB(){
		String storyLineB;
		if(Main.youLost){
			storyLineB = "Oh no you lost. The knights have captured you and has locked you up as well. \n"; 

		}
		else{
			storyLineB = "The first maze wasn't too hard and you have completed the first maze. \n"
					+ "You are closer than before to finding your husband. But the maze will get harder as you go one. \n"
					+ "Are you ready for the next maze?";
		}
		Text storyB = 	gui.makeText(storyLineB, 50, 100, 36,"white");
		if(Main.youLost){
			makeButton("Finish");
		}
		else{
			makeButton("Next Level");
		}
		exitButtonStory();
		layout.getChildren().add(storyB);
	}

	// Makes the story for the third scene
	public void storyC(){
		String storyLineC;
		if(Main.youLost){
			storyLineC = "Oh no you lost. The knights have captured you and has locked you up as well. \n"; 
		}
		else{
			storyLineC = "You have completed the second maze as well. \n"
					+"But you still need to go through one more maze to find your husband \n"
					+ "Are you ready for the next maze? \n";
		}
		Text storyC = 	gui.makeText(storyLineC, 50, 100, 36,"white");
		if(Main.youLost){
			makeButton("Finish");
		}
		else{
			makeButton("Next Level");
		}
		exitButtonStory();
		layout.getChildren().add(storyC);
	}

	// Makes the story for the fourth scene
	public void storyD(){
		String storyLineD;
		if(Main.youLost){
			storyLineD = "Oh no you lost. The knights have captured you and has locked you up as well. \n"; 
		}
		else{
			storyLineD = "Congratulations \n"
					+ "You have completed the third and the final maze as well \n"
					+ "and you find him in lying in the maze. You help him up and get him back to the castle \n"
					+ "Your father finds out about what Mary has done and he orders the knight to capture \n"
					+ "Mary and put her in cell. \n";
		}
		Text storyD = 	gui.makeText(storyLineD, 50, 100, 36,"white");
		makeButton("Finish");

		exitButtonStory();
		layout.getChildren().add(storyD);
	}






}
