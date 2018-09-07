package SuperPrincess.view;

import SuperPrincess.GUI;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Level{
	Scene scene;
	Group layoutOfLevel = new Group();
	GUI gui = new GUI();
	int[][] maps;
	public ArrayList<Walls> levelWall = new ArrayList<Walls>();
	public ArrayList<Pellet> levelPellet = new ArrayList<Pellet>();
	public ArrayList<Sword> swordList = new ArrayList<Sword>();
	public ArrayList<SpeedUp> speedUpList = new ArrayList<SpeedUp>();
	public ArrayList<SlowDown> slowDownList = new ArrayList<SlowDown>();
	public int[][] levelMap = new int[16][32];

	public Level(){
	}
	
	// Loads the level one map
	public void levelOneMap(){
		loadMap("level1.txt", levelMap);
	}

	// Loads the level two map
	public void levelTwoMap(){
		loadMap("level2.txt", levelMap);
	}

	// Loads the level three map
	public void levelThreeMap(){
		loadMap("level3.txt", levelMap);
	}

	// Reads the file
	private void loadMap(String file, int[][] map) {
		try {
			InputStream resource = new FileInputStream("res/" + file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(resource));
			String line = null;

			int row = 0;
			while((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				for (int column = 0; column < data.length; column++) {
					map[row][column] = Integer.parseInt(data[column]);
				}
				row++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Makes the background for the game
	public ImageView background(){
		ImageView showBackgroundImage = gui.background() ;
		return showBackgroundImage;
	}

	// Puts all the graphics together
	public Group makeLevel(){
		layoutOfLevel.getChildren().add(background());
		mapLevel();
		return layoutOfLevel;
	}

	// Makes the level by putting walls, pellets and randomly generated power ups
	public void mapLevel(){
		for (int x = 0; x < 32; x++) {
			for (int y = 0; y < 16; y++) {
				if(levelMap[y][x] == 1){
					Walls wall = new Walls();
					levelWall.add(wall);
					layoutOfLevel.getChildren().add(wall.makeWall((x*45),(y*45)));
				}
				if(levelMap[y][x]==0){
					Pellet pellet = new Pellet();
					levelPellet.add(pellet);
					layoutOfLevel.getChildren().add(pellet.makePellet((x*45),(y*45)));
				}
			}
		}
		
		// Generates a random position in the map which is not a wall and places a sword
		Random rand1 = new Random();
		int numX1, numY1;
		numX1 = rand1.nextInt(30) + 1; //gives you range of 1 to 31
		numY1 = rand1.nextInt(14) + 1; //gives a range of 1 to 15
		if(levelMap[numY1][numX1] == 0) {
			Sword sword = new Sword();
			swordList.add(sword);
			layoutOfLevel.getChildren().add(sword.sword((numX1*45)-110, (numY1*45)));
		}

		// Generates a random position in the map which is not a wall and places a speed up
		Random rand2 = new Random();
		int numX2, numY2;
		numX2 = rand2.nextInt(30) + 1; //gives you range of 1 to 31
		numY2 = rand2.nextInt(14) + 1; //gives a range of 1 to 15				
		if((levelMap[numY2][numX2] == 0) && (numX1 != numX2) && (numY1 != numY2)) {
			SpeedUp powerUp2 = new SpeedUp();
			speedUpList.add(powerUp2);
			layoutOfLevel.getChildren().add(powerUp2.speedUp((numX2*45)-110, (numY2*45)));
		}

		// Generates a random position in the map which is not a wall and places a slow down
		Random rand3 = new Random();
		int numX3, numY3;
		numX3 = rand3.nextInt(30) + 1; //gives you range of 1 to 31
		numY3 = rand3.nextInt(14) + 1; //gives a range of 1 to 15	
		if((levelMap[numY3][numX3] == 0) && 
				(numX1 != numX3) && (numY1 != numY3) &&
				(numX2 != numX3) && (numY2 != numY3)){
			SlowDown powerUp3 = new SlowDown();
			slowDownList.add(powerUp3);
			layoutOfLevel.getChildren().add(powerUp3.slowDown((numX3*45)-110, (numY3*45)));
		}
	} 

	// Returns an array list of walls in the level
	public ArrayList<Walls> Wall(){
		return levelWall;
	}
	
	// Returns an array list of pellets in the level
	public ArrayList<Pellet> Pellet(){
		return levelPellet;
	}
	
	// Returns an array list of sword power up in the level
	public ArrayList<Sword> sword(){
		return swordList;
	}
	
	// Returns an array list of speedup power up in the level
	public ArrayList<SpeedUp> speedUp(){
		return speedUpList;
	}
	
	// Returns an array list of slow down power up in the level
	public ArrayList<SlowDown> slowDown(){
		return slowDownList;
	}

}
