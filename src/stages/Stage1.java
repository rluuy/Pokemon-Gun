package stages;

import java.util.ArrayList;

/**
 * Code for Stage 1-1. This stage is meant to be blank with no obstacles and a few enemies as to ease the player into the game. 
 * Contains paths that lead to Stage 1-2 and Stage 2-1. 
 */

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Stage1 {
	private ArrayList<Rectangle> obstacles = new ArrayList<Rectangle>() ;
	private ArrayList<Sprite> items = new ArrayList<Sprite>() ;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>() ;

	Image pokeball = new Image("file:images/pokeball.png");


	int[][] tileMap = {
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	}; //tileMap - may move on to text reading method instead

	Image regTile = new Image("file:images/reg_tile_scaled.png");
	Image grassTile = new Image("file:images/reg_tile_scaled.png");
	Image flowerTile = new Image("file:images/grass-tile2.png");
	Image paveTile = new Image("file:images/pavement.png");

	int tileLength = 48;
	int tileWidth = 48;


	/**
	 * generateTiles's purpose is to generate the stage based on the TileMap.
	 * @param gc is the graphics context that we are drawing on.
	 */
	public void generateTiles(GraphicsContext gc) {
		Sprite pokeballS = new Sprite();
		pokeballS.setImage(pokeball);
		pokeballS.setPosition(100,300);
		items.add( pokeballS );


		//createEnemies();
		Boss boss = new Boss(1,10,10);
		boss.direction = 1;
		boss.setVelocity(1);
		enemies.add( boss );
		Enemy enemy = new Enemy(1,200,200);
		enemy.direction = 1;
		enemy.setVelocity(1);
		enemies.add( enemy );
		

		int mapLength = tileMap.length;
		int mapWidth = tileMap[0].length;


		// For Loops that modifies the stage based on the value in the TileMap
		for (int i = 0; i < mapLength; i++) { 
			for (int j = 0; j < mapWidth; j++) {
				gc.fillRect(j * tileLength, i * tileWidth, 48, 48);
				if (tileMap[i][j] == 0) {
					gc.drawImage(regTile, j * tileWidth, i * tileLength);
				}
				if (tileMap[i][j] == 1) {
					gc.setFill(Color.RED);
					gc.fillRect(j * tileLength, i * tileWidth, tileLength, tileWidth);
					obstacles.add( new Rectangle(j * tileLength, i * tileWidth, tileLength, tileWidth));
				}
			}
		}
	}
	
	// Getter for Obstacles in Stage
	public ArrayList<Rectangle> getObstacles(){
		return obstacles;
	}
	
	// Getter for Items in Stage 
	public ArrayList<Sprite> getItems() {
		// TODO Auto-generated method stub
		return items;
	}
	
	// Getter for Enemies in Stage 
	public ArrayList<Enemy> getEnemies() {
		// TODO Auto-generated method stub
		return enemies;
	}
	
	// Getter for if the Exist a Dungeon Entrance
	public ArrayList<Rectangle> getD() {
		return null;
	}
	
	// Method for creating the eneimies on the stage 
	private void createEnemies() {
//		Enemy enemy = new Enemy(1, 300, 300);
//		enemy.direction = 1;
//		enemy.setVelocity(1);
//		enemies.add( enemy );
//
//		Enemy enemy2 = new Enemy(2, 200, 200);
//		enemy2.direction = 2;
//		enemy2.setVelocity(1);
//		enemies.add( enemy2 );
	}
}
