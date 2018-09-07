package SuperPrincess.view;

import SuperPrincess.Main;
import SuperPrincess.controller.SceneManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class GeneralGame {

	private Text youWon = new Text();

	private Text numLife = new Text();

	private Text displayScore = new Text();

	private Group layout = new Group();

	private AlertBox alertBox = new AlertBox();

	private Label timerLabel = new Label();

	private Timeline gameTimer;

	private Label countdownLabel = new Label();

	private Timeline gameCountdown;

	private Scene scene;

	private SceneManager sceneManager;

	private Princess princess;

	private Enemy enemy;

	private Level level;

	private Timeline speedChange;
	private Timeline swordGone;

	private boolean goUp, goDown, goRight, goLeft, gamePaused, pageDown;

	private boolean enemyGoUp, enemyGoDown, enemyGoRight, enemyGoLeft;

	private boolean cantGoUp, cantGoDown, cantGoRight, cantGoLeft;

	private boolean enemyCantGoUp, enemyCantGoDown, enemyCantGoRight, enemyCantGoLeft;

	private boolean quit;

	private boolean countdown = false;

	private boolean isDead, levelWon;

	private int countSec = 3;

	private int min = 1, sec = 59;

	private int moveSpeed = 5, enemyMoveSpeed = 3;

	private int numOfPellets = 0, numOfPelletsEaten = 0;

	private int actualSpeed = moveSpeed;

	private int actualEnemySpeed = enemyMoveSpeed;

	private int prevLife = Main.lifes;

	private int canKillEnemy = 0;

	static int score;

	AudioClip bgSound = new AudioClip("file:res/bg2.mp3");

	AudioClip popSound = new AudioClip("file:res/pop.mp3");

	AudioClip powerupSound = new AudioClip("file:res/powerup.mp3");

	AudioClip dieSound = new AudioClip("file:res/die2.mp3");

	AudioClip killSound = new AudioClip("file:res/kill.mp3");

	// Makes the general game
	public GeneralGame(Princess princess, Enemy enemy, Level level) {
		this.princess = princess;
		this.enemy = enemy;
		this.level = level;
		bgSound.setVolume(0.1);
		bgSound.play();
	}

	// Sets the timer for the game which counts down from 2 mins
	public void setTimer() {
		timerLabel.setTextFill(Color.WHITE);
		timerLabel.setFont(new Font("Arial", 30));
		timerLabel.setText("02:00");
		gameTimer = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (countdown) {
					if (sec >= 10) {
						timerLabel.setText("0" + Integer.toString(min) + ":" + Integer.toString(sec));
					} else {
						timerLabel.setText("0" + Integer.toString(min) + ":0" + Integer.toString(sec));
					}
					if (sec > 0) {
						sec = sec - 1;
					} else if (sec == 0) {
						sec = 59;
						min = min - 1;
					}
				}
			}
		}));
		gameTimer.setCycleCount(Animation.INDEFINITE);
		gameTimer.play();
	}

	// Counts down from 3 to indictae how many seocnds before the game starts
	public void setCountdown() {
		countdownLabel.setTextFill(Color.WHITE);
		countdownLabel.setFont(new Font("Arial", 60));

		gameCountdown = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (countSec > 0) {
					countdown = false;
					countdownLabel.setText(Integer.toString(countSec));
					countSec = countSec - 1;
				} else if (countSec == 0) {
					countdown = true;
					countdownLabel.setVisible(false);
				}
			}
		}));
		gameCountdown.setCycleCount(Animation.INDEFINITE);
		gameCountdown.play();
	}

	// Stops the game timer
	public void stopTimer() {
		bgSound.stop();
		gameTimer.stop();
	}

	// Checks if the user has quit the game
	public boolean gameQuit() {
		if (quit) {
			bgSound.stop();
			return true;
		}
		return false;
	}

	// Checks if the time is up for the game
	public boolean timerUp() {
		if (min == 0 && sec == 0) {
			bgSound.stop();
			gameTimer.stop();
			timerLabel.setText("00:00");
			isDead = true;
			return true;
		}
		return false;
	}

	// Checks if the game is paused
	public boolean isPaused() {
		if (gamePaused == true) {
			bgSound.stop();
			return true;
		}
		return false;
	}

	// Checks if the 3 seconds timer is up
	public boolean isCounting() {
		return countdown;
	}

	// Shows the game timer in the screen
	public Label showTimer() {
		try {
			timerLabel.setFont(Font.loadFont(new FileInputStream(new File("pamega.ttf")), 36));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		timerLabel.setLayoutX(1295);
		timerLabel.setLayoutY(840);
		timerLabel.setTextFill(Color.DEEPPINK);
		return timerLabel;
	}

	// Shows the 3 seconds timer
	public Label showCountdown() {
		try {
			countdownLabel.setFont(Font.loadFont(new FileInputStream(new File("pamega.ttf")), 90));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		countdownLabel.setLayoutX(720);
		countdownLabel.setLayoutY(400);
		countdownLabel.setTextFill(Color.DEEPPINK);
		return countdownLabel;
	}

	// Hnaldes the input from the user (e.g used to control the player and the
	// enemy)
	public void handleInput(SceneManager sceneManager, Scene scene, Princess princess) {
		this.scene = scene;
		this.sceneManager = sceneManager;
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case UP:
					goUp = true;
					if (!cantGoUp) {
						goDown = false;
						goRight = false;
						goLeft = false;
					}
					break;
				case DOWN:
					goDown = true;
					if (!cantGoDown) {
						goUp = false;
						goRight = false;
						goLeft = false;
					}
					break;
				case LEFT:
					goLeft = true;
					if (!cantGoLeft) {
						goUp = false;
						goDown = false;
						goRight = false;
					}
					break;
				case RIGHT:
					goRight = true;
					if (!cantGoRight) {
						goUp = false;
						goDown = false;
						goLeft = false;
					}
					break;
				case PAGE_DOWN:
					pageDown = true;
					if (pageDown) {
						Main.lifes = 0;
						min = 0;
						sec = 0;
						timerLabel.setText("00:00");
					}
					break;
				case ESCAPE:
					gamePaused = true;
					gameTimer.stop();
					if (alertBox.replyAlertBox()) {
						quit = true;
						sceneManager.MenuScreen(sceneManager);
					}
					break;
				case P:
					if (gamePaused) {
						gamePaused = false;
						gameTimer.play();

					} else {
						gamePaused = true;
						gameTimer.stop();
					}
					break;
				case W:
					if (Main.gameMode == 1) {
						enemyGoUp = true;
						if (!enemyCantGoUp) {
							enemyGoRight = false;
							enemyGoDown = false;
							enemyGoLeft = false;
						}
					}
					break;
				case D:
					if (Main.gameMode == 1) {
						enemyGoRight = true;
						if (!enemyCantGoRight) {
							enemyGoUp = false;
							enemyGoDown = false;
							enemyGoLeft = false;
						}
					}
					break;
				case S:
					if (Main.gameMode == 1) {
						enemyGoDown = true;
						if (!enemyCantGoDown) {
							enemyGoRight = false;
							enemyGoUp = false;
							enemyGoLeft = false;
						}
					}
					break;
				case A:
					if (Main.gameMode == 1) {
						enemyGoLeft = true;
						if (!enemyCantGoLeft) {
							enemyGoRight = false;
							enemyGoDown = false;
							enemyGoUp = false;
						}
					}
					break;
				default:
					break;
				}
			}
		});

		// This is from where the movement of the character function is called
		// from
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (!levelWon) {
					checkCollisions(princess, level.Wall());
					if (goUp && !cantGoUp && !gamePaused && !isDead && countdown) {
						princess.moveUp(actualSpeed);
					} else if (goDown && !cantGoDown && !gamePaused && !isDead && countdown) {
						princess.moveDown(actualSpeed);
					} else if (goRight && !cantGoRight && !gamePaused && !isDead && countdown) {
						princess.moveRight(actualSpeed);
					} else if (goLeft && !cantGoLeft && !gamePaused && !isDead && countdown) {
						princess.moveLeft(actualSpeed);
					}
					if (Main.gameMode == 1) {
						checkEnemyCollisions(enemy.showEnemy(), level.Wall());
						if (enemyGoUp && !enemyCantGoUp && !gamePaused && !isDead && countdown) {
							enemy.moveUp(actualEnemySpeed);
						} else if (enemyGoDown && !enemyCantGoDown && !gamePaused && !isDead && countdown) {
							enemy.moveDown(actualEnemySpeed);
						} else if (enemyGoRight && !enemyCantGoRight && !gamePaused && !isDead && countdown) {
							enemy.moveRight(actualEnemySpeed);
						} else if (enemyGoLeft && !enemyCantGoLeft && !gamePaused && !isDead && countdown) {
							enemy.moveLeft(actualEnemySpeed);
						}
					}
				}
			}
		};
		timer.start();
	}

	// Checks if the player is colliding with the wall
	public void checkCollisions(Princess princess, ArrayList<Walls> wallAdd) {
		cantGoUp = false;
		cantGoDown = false;
		cantGoRight = false;
		cantGoLeft = false;
		actualSpeed = moveSpeed;
		double princessWidth = princess.showPrincess().getFitWidth();
		double princessHeight = princess.showPrincess().getFitHeight();
		double gap = 1;
		for (int i = 0; i < wallAdd.size(); i++) {
			for (int j = 0; j < actualSpeed; j++) {
				if (goUp) {
					if (wallAdd.get(i).showWall.getBoundsInParent().contains(princess.showPrincess().getX(),
							princess.showPrincess().getY() - (j + gap))
							|| (wallAdd.get(i).showWall.getBoundsInParent().contains(
									princess.showPrincess().getX() + princessWidth,
									princess.showPrincess().getY() - (j + gap)))) {
						actualSpeed = j;
						if (actualSpeed == 0) {
							cantGoUp = true;
						}
						break;

					}
				}
				if (goDown) {
					if ((wallAdd.get(i).showWall.getBoundsInParent().contains(princess.showPrincess().getX(),
							princess.showPrincess().getY() + (princessHeight + j + gap)))
							|| (wallAdd.get(i).showWall.getBoundsInParent().contains(
									princess.showPrincess().getX() + princessWidth,
									princess.showPrincess().getY() + (princessHeight + j + gap)))) {
						actualSpeed = j;
						if (actualSpeed == 0) {
							cantGoDown = true;
						}
						break;

					}
				}
				if (goRight) {
					if (wallAdd.get(i).showWall.getBoundsInParent().contains(
							princess.showPrincess().getX() + (princessWidth + j + gap), princess.showPrincess().getY())
							|| (wallAdd.get(i).showWall.getBoundsInParent().contains(
									princess.showPrincess().getX() + (princessWidth + j + gap),
									princess.showPrincess().getY() + princessHeight))) {
						actualSpeed = j;
						if (actualSpeed == 0) {
							cantGoRight = true;
						}
						break;
					}
				}
				if (goLeft) {
					if (wallAdd.get(i).showWall.getBoundsInParent().contains(princess.showPrincess().getX() - (j + gap),
							princess.showPrincess().getY())
							|| wallAdd.get(i).showWall.getBoundsInParent().contains(
									princess.showPrincess().getX() - (j + gap),
									princess.showPrincess().getY() + princessHeight)) {
						actualSpeed = j;
						if (actualSpeed == 0) {
							cantGoLeft = true;
						}
						break;
					}
				}
			}
		}
	}

	// Checks if the enemy is colliding with the wall
	public void checkEnemyCollisions(ImageView enemy, ArrayList<Walls> wallAdd) {
		enemyCantGoUp = false;
		enemyCantGoDown = false;
		enemyCantGoRight = false;
		enemyCantGoLeft = false;
		actualEnemySpeed = enemyMoveSpeed;
		double enemyWidth = enemy.getFitWidth();
		double enemyHeight = enemy.getFitHeight();
		double gap = 1;
		for (int i = 0; i < wallAdd.size(); i++) {
			for (int j = 0; j < actualEnemySpeed; j++) {
				if (enemyGoUp) {
					if (wallAdd.get(i).showWall.getBoundsInParent().contains(enemy.getX(), enemy.getY() - (j + gap))
							|| (wallAdd.get(i).showWall.getBoundsInParent().contains(enemy.getX() + enemyWidth,
									enemy.getY() - (j + gap)))) {
						actualEnemySpeed = j;
						if (actualEnemySpeed == 0) {
							enemyCantGoUp = true;
						}
						break;
					}
				}
				if (enemyGoDown) {
					if ((wallAdd.get(i).showWall.getBoundsInParent().contains(enemy.getX(),
							enemy.getY() + (enemyHeight + j + gap)))
							|| (wallAdd.get(i).showWall.getBoundsInParent().contains(enemy.getX() + enemyWidth,
									enemy.getY() + (enemyHeight + j + gap)))) {
						actualEnemySpeed = j;
						if (actualEnemySpeed == 0) {
							enemyCantGoDown = true;
						}
						break;

					}
				}
				if (enemyGoRight) {
					if (wallAdd.get(i).showWall.getBoundsInParent().contains(enemy.getX() + (enemyWidth + j + gap),
							enemy.getY())
							|| (wallAdd.get(i).showWall.getBoundsInParent()
									.contains(enemy.getX() + (enemyWidth + j + gap), enemy.getY() + enemyHeight))) {
						actualEnemySpeed = j;
						if (actualEnemySpeed == 0) {
							enemyCantGoRight = true;
						}
						break;
					}
				}
				if (enemyGoLeft) {
					if (wallAdd.get(i).showWall.getBoundsInParent().contains(enemy.getX() - (j + gap), enemy.getY())
							|| wallAdd.get(i).showWall.getBoundsInParent().contains(enemy.getX() - (j + gap),
									enemy.getY() + enemyHeight)) {
						actualEnemySpeed = j;
						if (actualEnemySpeed == 0) {
							enemyCantGoLeft = true;
						}
						break;
					}
				}
			}
		}
	}

	// Checks if the pellets have been consumed and disappers from the screen
	public void checkPellet(Princess princess, ArrayList<Pellet> pelletAdd) {
		numOfPellets = pelletAdd.size();
		for (int i = 0; i < pelletAdd.size(); i++) {
			if ((pelletAdd.get(i).showDiamond().getBoundsInParent()
					.intersects(princess.showPrincess().getBoundsInParent()))
					&& (pelletAdd.get(i).showDiamond().isVisible())) {
				popSound.setVolume(0.09);
				popSound.play();

				pelletAdd.get(i).showDiamond().setVisible(false);
				Main.score = Main.score + 25;
				numOfPelletsEaten = numOfPelletsEaten + 1;
			}
		}
	}

	// Checks if the sword has been picked up
	public void checkPowerUp(Princess princess, ArrayList<Sword> powerUp) {
		for (int i = 0; i < powerUp.size(); i++) {
			if ((powerUp.get(i).sword.getBoundsInParent().intersects(princess.showPrincess().getBoundsInParent()))
					&& (powerUp.get(i).sword.isVisible())) {
				powerupSound.play();
				canKillEnemy = 1;
				powerUp.get(i).sword.setVisible(false);
				Main.score = Main.score + 50;
				swordGone = new Timeline(new KeyFrame(Duration.seconds(10), new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						canKillEnemy = 0;
						powerupSound.play();
					}
				}));
				swordGone.play();
			}
		}
		Image sword = new Image("file:res/sword.png");
		ImageView swordView = new ImageView();
		swordView.setImage(sword);
		swordView.setFitHeight(400);
		swordView.setFitWidth(400);
		layout.getChildren().add(swordView);
	}

	// Checks if the speed up has been picked up
	public void checkPowerUp2(Princess princess, ArrayList<SpeedUp> powerUp) {
		for (int i = 0; i < powerUp.size(); i++) {
			if ((powerUp.get(i).speedUp.getBoundsInParent().intersects(princess.showPrincess().getBoundsInParent()))
					&& (powerUp.get(i).speedUp.isVisible())) {
				powerupSound.play();
				powerUp.get(i).speedUp.setVisible(false);
				Main.score = Main.score + 50;
				moveSpeed = 15;
				speedChange = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						moveSpeed = 5;
					}
				}));
				speedChange.play();
			}
		}
	}

	// Checks if the slow down has been picked up
	public void checkPowerUp3(Princess princess, ArrayList<SlowDown> powerUp) {
		for (int i = 0; i < powerUp.size(); i++) {
			if ((powerUp.get(i).slowdown.getBoundsInParent().intersects(princess.showPrincess().getBoundsInParent()))
					&& (powerUp.get(i).slowdown.isVisible())) {
				powerupSound.play();

				powerUp.get(i).slowdown.setVisible(false);
				Main.score = Main.score + 50;
				moveSpeed = 1;
				speedChange = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						moveSpeed = 5;
					}
				}));
				speedChange.play();
			}
		}
	}

	// Returns the score
	public static int sendScore() {
		return Main.score;
	}

	// Checks if all the pellets have been eaten, if so the game finishes
	public boolean allPelletsEaten() {
		if (numOfPellets == numOfPelletsEaten) {
			levelWon = true;
			return true;
		}
		levelWon = false;
		return false;
	}

	// Resets all the number of pellets consumed
	public void resetPelletsEaten() {
		numOfPelletsEaten = 0;
	}

	// Checks if the player intersects with the enemy and if so the player loses
	// a life
	public void checkEatenByEnemy(Princess princess, Enemy enemy) {
		if(canKillEnemy ==0){
			if ((princess.showPrincess().getBoundsInParent().intersects(enemy.showEnemy().getBoundsInParent()))) {
				dieSound.play();

				goLeft = false;
				goRight = false;
				goUp = false;
				goDown = false;
				princess.showPrincess().setX(princess.startX());
				princess.showPrincess().setY(princess.startY());
				princess.removeMoveHorizontally();
				princess.removeMoveVertically();
				Main.lifes--;
				if (Main.lifes < 0) {
					Main.lifes = 0;
				}
			}
		}else {
			if((princess.showPrincess().getBoundsInParent().intersects(enemy.showEnemy().getBoundsInParent()))){
				killSound.play();
				Main.score = Main.score + 100;
				enemy.resetPosition();
			}
		}
	}

	// Checks if the life has been changed
	public boolean didLoseLife() {
		if (Main.lifes != prevLife) {
			prevLife = Main.lifes;
			return true;
		}
		return false;
	}

	// Checks if the player is dead
	public boolean isDead() {
		if (Main.lifes == 0) {
			isDead = true;
			gameTimer.stop();
			return true;
		}
		return false;
	}

	// Shows the user if "you won" if they collected all the pellets
	public Text youWon() {
		bgSound.stop();
		youWon.setText("You Won");
		try {
			youWon.setFont(Font.loadFont(new FileInputStream(new File("pamega.ttf")), 150));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		youWon.setFill(Color.DEEPPINK);
		youWon.setX(500);
		youWon.setY(450);
		youWon.setTextAlignment(TextAlignment.CENTER);
		layout.getChildren().add(youWon);
		return youWon;
	}

	// Shows the user that the game is over
	public Text gameOver() {
		bgSound.stop();
		Text gameOver = new Text();
		gameOver.setText("Game Over");
		try {
			gameOver.setFont(Font.loadFont(new FileInputStream(new File("pamega.ttf")), 150));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		gameOver.setFill(Color.DEEPPINK);
		gameOver.setX(480);
		gameOver.setY(450);
		gameOver.setTextAlignment(TextAlignment.CENTER);
		layout.getChildren().add(gameOver);
		return gameOver;
	}

	// Shows and updates the number of lifes the user has in the screen
	public Text numLife() {
		updateNumLife();

		try {
			numLife.setFont(Font.loadFont(new FileInputStream(new File("pamega.ttf")), 36));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		numLife.setFill(Color.DEEPPINK);
		numLife.setX(60);
		numLife.setY(80);
		numLife.setTextAlignment(TextAlignment.CENTER);

		return numLife;
	}

	// Shows and updates the score in the screen
	public Text score() {
		updateScore();

		try {
			displayScore.setFont(Font.loadFont(new FileInputStream(new File("pamega.ttf")), 36));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		displayScore.setFill(Color.DEEPPINK);
		displayScore.setX(1220);
		displayScore.setY(80);
		displayScore.setTextAlignment(TextAlignment.CENTER);

		return displayScore;
	}

	// Updates the number of lifes
	public void updateNumLife() {
		numLife.setText(Main.lifes + " lifes left");
	}

	// Updates the score
	public void updateScore() {
		displayScore.setText("Score: " + Main.score);
	}

}
