package SuperPrincess;
	
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import SuperPrincess.controller.SceneManager;


public class Main extends Application{
	public static final int WIDTH = 1440;
    public static final int HEIGHT = 900;
    public static int gameMode = 0;
    public static int score = 5;
    public static int princessNum = 0;
    public static int levelNum = 1;
    public static int storyLine = 1;
    public static int lifes = 3;
    public static String name;
    public static boolean youLost = false;
	int setHeight = 1440;
	int setWidth = 720;
	private Image princessOne = new Image ("file:res/Amalia_selected.png");

	
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		primaryStage.setTitle(" Super Princess");
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(princessOne);
		SceneManager sceneManager = new SceneManager(primaryStage);
		sceneManager.StartScreen(sceneManager);
	}
	public static void main(String[] args) {
		launch(args);
	}
}
