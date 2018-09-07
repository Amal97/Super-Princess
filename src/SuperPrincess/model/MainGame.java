package SuperPrincess.model;

import SuperPrincess.GUI;
import SuperPrincess.Main;
import SuperPrincess.controller.SceneManager;
import SuperPrincess.view.AlertBox;
import SuperPrincess.view.Enemy;
import SuperPrincess.view.GeneralGame;
import SuperPrincess.view.Level;
import SuperPrincess.view.Princess;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MainGame {

	Scene scene;
	GUI gui = new GUI();
	Group layout = new Group();
	Level level = new Level();
	Enemy enemyOne = new Enemy();
	Enemy enemyTwo = new Enemy();
	Enemy enemyThree = new Enemy();
	Enemy enemyFour = new Enemy();
	Princess princess = new Princess();
	AlertBox alertBox = new AlertBox();
	GeneralGame generalGame = new GeneralGame(princess, enemyOne, level);
	SceneManager sceneManager;
	ImageView showPrincess, paused, showEnemyOne, showEnemyTwo, showEnemyThree, showEnemyFour;
	int moveSpeed = 5, lifeLeft, gameMode, princessNum = 0, setWidth, setHeight;
	int levelNum = Main.levelNum;
	boolean gameOver = false;
	Button nextLevelButton = new Button();
	Button gameOverButton = new Button();
	Button exitButton = new Button();

	// Constructor
	public MainGame(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}

	// Constructor
	public MainGame(SceneManager sceneManager, int princessNum) {
		this.sceneManager = sceneManager;
		this.princessNum = princessNum;
	}

	// Makes the main game scene
	public Scene makeScene(int setWidth, int setHeight) {
		scene = new Scene(layout, setWidth, setHeight);
		showLevel(Main.levelNum);
		showPrincess(princessNum, level);
		showEnemyOne();
		showEnemyTwo();
		showEnemyThree();
		showEnemyFour();
	    generalGame.setCountdown();
	    layout.getChildren().add(generalGame.showCountdown());
		generalGame.setTimer();
		gamePause();
		generalGame.handleInput(sceneManager, scene, princess);
		layout.getChildren().add(generalGame.showTimer());
		layout.getChildren().add(gui.SP());
		updateGame();

		return scene;
	}

	// Makes the level and shows it to the screen
	public void showLevel(int levelNum) {
		switch (levelNum) {
		case 1:
			level.levelOneMap();
			layout.getChildren().add(level.makeLevel());
			break;
		case 2:
			level.levelTwoMap();
			layout.getChildren().add(level.makeLevel());
			break;
		case 3:
			level.levelThreeMap();
			layout.getChildren().add(level.makeLevel());
			break;
		}
	}

	// Makes the princess and shows it to the screen
	public void showPrincess(int princessNum, Level level) {
		Random rand1 = new Random();
		int numX1 = 0, numY1 = 0;
		while (level.levelMap[numY1][numX1] != 0) {
			numX1 = rand1.nextInt(30) + 1; //gives you range of 1 to 31
			numY1 = rand1.nextInt(14) + 1; //gives a range of 1 to 15
			if (level.levelMap[numY1][numX1] == 0) {
				numX1 = (int) Math.floor((numX1 * 45));
				numY1 = (int) Math.floor(numY1 * 45 + 90);
				if (Main.gameMode == 1) {
					showPrincess = princess.makePrincess(2, 35, 35, numX1, numY1);
					break;
				} else {
					showPrincess = princess.makePrincess(princessNum, 25, 25, numX1, numY1);
					break;
				}
			}
		}
		layout.getChildren().add(showPrincess);
	}
	
	// Shows the first enemy
	public void showEnemyOne() {
		showEnemyOne = enemyOne.makeEnemy(2, level);
		layout.getChildren().add(showEnemyOne);
	}
	// Shows the second enemy
	public void showEnemyTwo() {
		showEnemyTwo = enemyTwo.makeEnemy(2, level);
		layout.getChildren().add(showEnemyTwo);
	}
	// Shows the third enemy
	public void showEnemyThree() {
		showEnemyThree = enemyThree.makeEnemy(2, level);
		layout.getChildren().add(showEnemyThree);
	}
	// Shows the fourth enemy
	public void showEnemyFour() {
		showEnemyFour = enemyFour.makeEnemy(2, level);
		layout.getChildren().add(showEnemyFour);
	}
	// Moves the first enemy
	public void moveSmartEnemy() {
		enemyOne.moveSmartEnemy(showPrincess, level, 4);
	}
	// Moves the second enemy
	public void moveEnemyTwo() {
		enemyTwo.moveSmartEnemy(showPrincess, level, 3);
	}
	// Moves the third enemy
	public void moveEnemyThree() {
		enemyThree.moveRandomEnemy(level);
	}
	// Moves the fourth enemy
	public void moveEnemyFour() {
		enemyFour.moveRandomEnemy(level);
	}
	
	// Pauses the game
	public void gamePause() {
		paused = gui.paused();
		layout.getChildren().add(paused);
	}

	// Makes the buttons used in the game
	public void gameButton(String text) {
		nextLevelButton = gui.makeButton(text, 550, 500, 20, 20, 36);
		nextLevelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event->{
			if (Main.gameMode == 2) {
				if (gameOver && generalGame.isDead()) {
					generalGame.resetPelletsEaten();
					sceneManager.StoryMode(sceneManager);
				} else if (Main.storyLine == 2) {
					generalGame.resetPelletsEaten();
					sceneManager.StoryMode(sceneManager);
				} else if (Main.storyLine == 3) {
					generalGame.resetPelletsEaten();
					sceneManager.StoryMode(sceneManager);
				} else if (Main.storyLine == 4) {
					generalGame.resetPelletsEaten();
					sceneManager.StoryMode(sceneManager);
				}
			} else if (gameOver) {
				generalGame.resetPelletsEaten();
				sceneManager.FinalScene(sceneManager);
			} else {
				sceneManager.MainGame(sceneManager, princessNum);
			}
		});
		layout.getChildren().add(nextLevelButton);
	}

	// Makes the exit button
	public void exitButton() {
		exitButton = gui.makeButton("Exit to main screen", 550, 500, 20, 20, 36);
		exitButton.setCursor(Cursor.HAND);
		exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event->{
			sceneManager.MenuScreen(sceneManager);
		});
		layout.getChildren().add(exitButton);
	}

	// Updates the game every 60 fps
	public void updateGame() {
		layout.getChildren().add(generalGame.score());
		layout.getChildren().add(generalGame.numLife());

		new AnimationTimer() {
			long lastUpdate = 0;

			@Override
			public void handle(long currentNanoTime) {
				if (currentNanoTime - lastUpdate >= 16666666) {
					lastUpdate = currentNanoTime;
					
					// Checks if the user quit the game, if so stop this
					if(generalGame.gameQuit()){
						this.stop();
					}
					
					// Checks if you are caught be the enemy
					generalGame.checkEatenByEnemy(princess, enemyOne);
					generalGame.checkEatenByEnemy(princess, enemyTwo);
					generalGame.checkEatenByEnemy(princess, enemyThree);
					generalGame.checkEatenByEnemy(princess, enemyFour);

					// Updates the game score
					generalGame.updateScore();
					
					// Updates the life
					generalGame.updateNumLife();

					
					// Checks if the pellets or power ups are picked up
					generalGame.checkPellet(princess, level.Pellet());
					generalGame.checkPowerUp(princess, level.sword());
					generalGame.checkPowerUp2(princess, level.speedUp());
					generalGame.checkPowerUp3(princess, level.slowDown());
					
					// Makes the enemy move in single player mode
					if(Main.gameMode != 1 && !generalGame.isPaused() && !generalGame.isDead() && generalGame.isCounting()){
						moveSmartEnemy();
						moveEnemyTwo();
						moveEnemyThree();
						moveEnemyFour();
					}
					
					// Makes the enemy move in multiplayer mode
					if(Main.gameMode == 1 && !generalGame.isPaused() && !generalGame.isDead() && generalGame.isCounting()){
					//	moveSmartEnemy();
						moveEnemyTwo();
						moveEnemyThree();
						moveEnemyFour();
					}
					
					// Resets the position of the enemies when the player dies
					if(generalGame.didLoseLife()){
						enemyOne.resetPosition();
						enemyTwo.resetPosition();
						enemyThree.resetPosition();
						enemyFour.resetPosition();
					}
					
					// Sets the pause
					if (generalGame.isPaused() && generalGame.allPelletsEaten()) {
						paused.setVisible(true);
					} else if (generalGame.isPaused() && !generalGame.isDead()) {
						paused.setVisible(true);
					} else {
						paused.setVisible(false);
					}
					
					// Checks if the player dies or the timer is up
					if (generalGame.isDead() || generalGame.timerUp()) {
						gameOver = true;
						if (Main.gameMode == 1) {
							layout.getChildren().add(gui.makeText("Player 2 won", 400, 450, 150, "pink"));
							gameButton("Finish");
						} else if (Main.gameMode == 2) {
							Main.youLost = true;
							Main.storyLine++;
							layout.getChildren().add(generalGame.gameOver());
							gameButton("Finish");
						} else {
							layout.getChildren().add(generalGame.gameOver());
							gameButton("Finish");
						}
						this.stop();
					}
					
					// Checks if all the pellets have been eaten and increases the level or finishes the game
					if (generalGame.allPelletsEaten()) {
						generalGame.stopTimer();
						layout.getChildren().add(generalGame.youWon());
						if (Main.levelNum < 3) {
							if (Main.gameMode == 2) {
								Main.storyLine++;
							}
							Main.levelNum++;
							gameButton("Next Level");
							this.stop();
						} else if (Main.levelNum == 3) {
							gameOver = true;
							if (Main.gameMode == 2) {
								if (Main.storyLine < 4) {
									Main.storyLine++;
								}
							}
							gameButton("Game Over");
							this.stop();
						}
					}
				}
			}
		}.start();
	}

}
