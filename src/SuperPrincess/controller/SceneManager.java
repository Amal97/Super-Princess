package SuperPrincess.controller;

import SuperPrincess.Main;
import SuperPrincess.model.MainGame;
import SuperPrincess.view.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class SceneManager extends Application {

	private Stage stage;
	
	public SceneManager(Stage primaryStage) {
		this.stage = primaryStage;
		stage.show();	
	}

	public void StartScreen(SceneManager sceneManager){
		StartScreen StartScreen = new StartScreen(sceneManager);
		stage.setScene(StartScreen.makeScene(Main.WIDTH, Main.HEIGHT));
	}
	public void MenuScreen(SceneManager sceneManager){
		MenuScreen MenuScreen = new MenuScreen(sceneManager);
		stage.setScene(MenuScreen.makeScene(Main.WIDTH, Main.HEIGHT));
	}
	public void StoryMode(SceneManager sceneManager){
		StoryMode StoryMode = new StoryMode(sceneManager);
		Main.gameMode = 2;
		stage.setScene(StoryMode.makeScene(Main.WIDTH, Main.HEIGHT));
	}
	public void Multiplayer(SceneManager sceneManager){
		MainGame mainGame = new MainGame(sceneManager);
		Main.gameMode = 1;
		stage.setScene(mainGame.makeScene(Main.WIDTH, Main.HEIGHT));
	}
	public void HighScore(SceneManager sceneManager){
		HighScore HighScore = new HighScore(sceneManager);
		stage.setScene(HighScore.makeScene(Main.WIDTH, Main.HEIGHT));
	}
	public void Help(SceneManager sceneManager){
		Help Help = new Help(sceneManager);
		stage.setScene(Help.makeScene(Main.WIDTH, Main.HEIGHT));
	}
	public void CharacterScreen(SceneManager sceneManager){
		CharacterScreen CharacterScreen = new CharacterScreen(sceneManager);
		Main.gameMode = 0;
		stage.setScene(CharacterScreen.makeScene(Main.WIDTH, Main.HEIGHT));
	}
	public void MainGame(SceneManager sceneManager, int princessNum){
		MainGame mainGame = new MainGame(sceneManager,princessNum);
		stage.setScene(mainGame.makeScene(Main.WIDTH, Main.HEIGHT));
	}
	public void FinalScene(SceneManager sceneManager){
		FinalScene finalScene = new FinalScene(sceneManager);
		stage.setScene(finalScene.makeScene(Main.WIDTH, Main.HEIGHT));
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
	}

}
