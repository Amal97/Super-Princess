package SuperPrincess.view;

import SuperPrincess.GUI;
import SuperPrincess.Main;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy {

	GUI gui = new GUI();
	Group layout = new Group();
	Image enemy;
	Image enemy1 = new Image("file:res/guyEnemy.png");
	Image enemyUp = new Image("file:res/upEnemy.png");
	Image enemyDown = new Image("file:res/downEnemy.png");
	Image enemyLeft = new Image("file:res/leftEnemy.png");
	//Image enemyUp = new Image("file:res/upEnemy.png");

	Image enemy2 = new Image("file:res/guyEnemy.png");
	Image enemy3 = new Image("file:res/guyEnemy.png");
	Group enemyGroup = new Group();
	
	private ImageView showEnemy = new ImageView();
	private ImageView princessView = new ImageView();
	private List<Point> path = null;
	private Direction direction = Direction.DOWN;
	private double moveVertically, moveHorizontally;
	private int pathIndex = 0, xPos = 0, yPos = 0, cantMove=0, num=0;
	private long lastPathEvaluate = System.currentTimeMillis();
		
	public void enemy() {
	}

	// Creates the enemy depending on which enemy the user chooses and creates a enemy randomly in the box
	public ImageView makeEnemy(int enemyNum, Level level) {
		ImageView showEnemyOne = new ImageView();
		showEnemy = showEnemyOne;

		Random rand1 = new Random();
		while(level.levelMap[yPos][xPos] != 9){
			xPos = rand1.nextInt(30) + 1; //gives you range of 1 to 31
			yPos = rand1.nextInt(14) + 1; //gives a range of 1 to 15
			if(level.levelMap[yPos][xPos] == 9){
				xPos = (int) Math.floor((xPos*45));
				yPos = (int)Math.floor(yPos*45+90);
				break;
			}
		}

		switch (enemyNum) {
		case 1:
			enemy = enemy1;
			break;
		case 2:
			enemy = enemy2;
			break;
		case 3:
			enemy = enemy3;
			break;
		}
		showEnemyOne.setImage(enemy);
		showEnemyOne.setFitWidth(35);
		showEnemyOne.setFitHeight(35);
		showEnemyOne.setX(xPos);
		showEnemyOne.setY(yPos);
		
		moveHorizontally = showEnemyOne.getX();
		moveVertically = showEnemyOne.getY();
		return showEnemyOne;
	}

	// Returns the ImageView of the enemy
	public ImageView showEnemy() {
		return showEnemy;
	}

	// Makes the enemy move up in multiplayer mode
	public void moveUp(int moveUpSpeed) {
		if (Main.gameMode == 1) {
			moveVertically = moveVertically - moveUpSpeed;
			showEnemy.setImage(enemyUp);
			showEnemy.setY(moveVertically);
		}
	}

	// Makes the enemy move down in multiplayer mode
	public void moveDown(int moveDownSpeed) {
		if (Main.gameMode == 1) {
			moveVertically = moveVertically + moveDownSpeed;
			showEnemy.setImage(enemyDown);
			showEnemy.setY(moveVertically);
		}
	}
	
	// Makes the enemy move left in multiplayer mode
	public void moveLeft(int moveLeftSpeed) {
		if (Main.gameMode == 1) {
			moveHorizontally = moveHorizontally - moveLeftSpeed;
			showEnemy.setImage(enemyLeft);
			showEnemy.setX(moveHorizontally);
		}
	}
	
	// Makes the enemy move right in multiplayer mode
	public void moveRight(int moveRightSpeed) {
		if (Main.gameMode == 1) {
			moveHorizontally = moveHorizontally + moveRightSpeed;
			showEnemy.setImage(enemy1);
			showEnemy.setX(moveHorizontally);
		}
	}

	// Randomly makes the enemy move
	public void moveRandomEnemy(Level level){
		double enemyX = showEnemy.getX();
		double enemyY = showEnemy.getY();

		if(cantMove==0){ //Creates a random number if the previous move cant be excueted 
			Random rand1 = new Random();
			num = rand1.nextInt(4);
		}

		if(num==0){ //Move Up
			if(!isColliding(level,enemyX, enemyY-3)){
				showEnemy.setImage(enemyUp);
				enemyX = showEnemy.getX();
				enemyY = showEnemy.getY();
				showEnemy.setX(enemyX);
				showEnemy.setY(enemyY-3);
				cantMove = 1;
			}
			else{
				cantMove = 0;
			}
		}
		if(num==1){ //Move Right
			if(!isColliding(level,enemyX+3, enemyY)){
				showEnemy.setImage(enemy1);
				enemyX = showEnemy.getX();
				enemyY = showEnemy.getY();
				showEnemy.setX(enemyX+3);
				showEnemy.setY(enemyY);
				cantMove = 1;
			}
			else{
				cantMove = 0;
			}
		}
		if(num==2){ //Move Left
			if(!isColliding(level,enemyX-3, enemyY)){
				showEnemy.setImage(enemyLeft);
				enemyX = showEnemy.getX();
				enemyY = showEnemy.getY();
				showEnemy.setX(enemyX-3);
				showEnemy.setY(enemyY);
				cantMove = 1;
			}
			else{
				cantMove = 0;
			}
		}
		if(num==3){ //Move Down
			if(!isColliding(level,enemyX, enemyY+3)){
				showEnemy.setImage(enemyDown);
				enemyX = showEnemy.getX();
				enemyY = showEnemy.getY();
				showEnemy.setX(enemyX);
				showEnemy.setY(enemyY+3);
				cantMove = 1;
			}
			else{
				cantMove = 0;
			}
		}
	}

	// Enum for the direction
	private enum Direction {
		LEFT(-1, 0), UP(0, -1), RIGHT(1, 0), DOWN(0, 1);
		final int dx;
		final int dy;

		Direction(int dx, int dy) {
			this.dx = dx;
			this.dy = dy;
		}
	}

	private List<Point> bfs(Level level, ImageView princess) {
		int[][] map = level.levelMap;
		boolean[][] visited = new boolean[map.length][map[0].length];

		LinkedList<Point> queue = new LinkedList<>();

		int startX = (int) (showEnemy.getX() / 45.0 + 0.5);
		int startY = (int) (showEnemy.getY() / 45.0 - 2.0 + 0.5);

		visited[startY][startX] = true;

		queue.add(new Point(startX, startY));

		Map<Point, Point> prev = new HashMap<>();

		Direction[] directions = Direction.values();

		int px = (int) (princess.getX() / 45.0 + 0.5);
		int py = (int) (princess.getY() / 45.0 - 2.0 + 0.5);

		while (!queue.isEmpty()) {
			Point point = queue.pop();

			if (point.x == px && point.y == py) {
				break;
			}

			// Add new points reachable from this location
			for (Direction direction : directions) {
				int x = point.x + direction.dx;
				int y = point.y + direction.dy;

				if (map[y][x] != 1 && !visited[y][x]) {
					queue.add(new Point(x, y));
					// Store the previous node used to access this point
					prev.put(new Point(x, y), point);
				}

				visited[y][x] = true;
			}
		}

		Point backtrace = new Point(px, py);
		List<Point> path = new ArrayList<>();

		while (backtrace != null) {
			// Add the point to the start so we get the source node first
			path.add(0, backtrace);
			// Go backwards in the path
			backtrace = prev.get(backtrace);
		}

		fixMovement(path);

		return path;
	}

	// Helper function to make the enemy move from the correct position
	private void fixMovement(List<Point> path) {
		double x = (showEnemy.getX() / 45.0);
		double y = (showEnemy.getY() / 45.0 - 2.0);

		Point first = path.get(0);
		Point second = path.get(0);

		int dx = Math.abs(x - first.x) < 0.05 ? 0 : (x < first.x ? -1 : 1);
		int dy = Math.abs(y - first.y) < 0.05 ? 0 : (y < first.y ? -1 : 1);

		// If already moving in that direction, don't do anything
		if ((dx > 0 && second.getX() - first.getX() > 0)
				|| (dx < 0 && second.getX() - first.getX() < 0)) {
			dx = 0;
		}

		// If already moving in that direction, don't do anything
		if ((dy > 0 && second.getY() - first.getY() > 0)
				|| (dy < 0 && second.getY() - first.getY() < 0)) {
			dy = 0;
		}

		if (dx != 0 || dy != 0) {
			path.add(0, new Point(first.x + dx, first.y + dy));
		}
	}


	// Moves the AI towards to the player
	public void moveSmartEnemy(ImageView princess, Level level, double speed) {
		// Evaluate a new path every seconds
		if (path == null
				|| pathIndex + 1 >= path.size()
				|| System.currentTimeMillis() - lastPathEvaluate > 1000) {
			path = bfs(level, princess);
			pathIndex = 0;
			lastPathEvaluate = System.currentTimeMillis();
		}

		// Skip to the next node to traverse
		if (pathIndex + 1 < path.size()) {
			Point dst = path.get(pathIndex + 1);
			double projectedX = dst.x * 45.0;
			double projectedY = (dst.y + 2.0) * 45.0;

			double minX = showEnemy.getX();
			double minY = showEnemy.getY();
			double maxX = minX + showEnemy.getFitWidth();
			double maxY = minY + showEnemy.getFitHeight();

			if (minX >= projectedX && minX <= projectedX + 45.0
					&& minY >= projectedY && minY <= projectedY + 45.0
					&& maxX >= projectedX && maxX <= projectedX + 45.0
					&& maxY >= projectedY && maxY <= projectedY + 45.0) {
				pathIndex++;
			}
		}

		// Evaluate the new direction to go towards based on the src & dst
		if (path.size() > 1 && pathIndex + 1 < path.size()) {
			Point src = path.get(pathIndex);
			Point dst = path.get(pathIndex + 1);

			if (src.getX() != dst.getX()) {
				direction = src.getX() < dst.getX() ? Direction.RIGHT : Direction.LEFT;
			} else {
				direction = src.getY() < dst.getY() ? Direction.DOWN : Direction.UP;
			}
		}

		double oldEnemyX = showEnemy.getX();
		double oldEnemyY = showEnemy.getY();
	
		double enemyX = showEnemy.getX() + direction.dx * speed;
		double enemyY = showEnemy.getY() + direction.dy * speed;

		if (isColliding(level, enemyX, enemyY)) {
			return;
		}
		if(oldEnemyX<enemyX){
			showEnemy.setImage(enemy1);
		}
		else if(oldEnemyX>enemyX){
			showEnemy.setImage(enemyLeft);
		}
		else if(oldEnemyY<enemyY){
			showEnemy.setImage(enemyDown);
		}
		else if(oldEnemyY>enemyY){
			showEnemy.setImage(enemyUp);
		}

		showEnemy.setX(enemyX);
		showEnemy.setY(enemyY);
	}

	// Checks if the enemys next position is colliding with a wall
	private boolean isColliding(Level level, double x, double y) {
		int minX = (int) Math.floor(x / 45.0);
		int minY = (int) Math.floor(y / 45.0 - 2.0);
		int maxX = (int) Math.floor((x + showEnemy.getFitWidth()) / 45.0);
		int maxY = (int) Math.floor((y + showEnemy.getFitHeight()) / 45.0 - 2.0);

		return level.levelMap[minY][minX] == 1
				|| level.levelMap[minY][maxX] == 1
				|| level.levelMap[maxY][minX] == 1
				|| level.levelMap[maxY][maxX] == 1;
	}
	
	public void resetPosition(){
		showEnemy.setX(xPos);
		showEnemy.setY(yPos);
		moveHorizontally = xPos;
		moveVertically = yPos;
	}
}
