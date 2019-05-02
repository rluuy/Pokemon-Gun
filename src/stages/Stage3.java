package stages;
/**
 * Class that represents stage 3 of the game
 */

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Stage3{
	private ArrayList<Rectangle> obstalces = new ArrayList<Rectangle>() ;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>() ;
	private ArrayList<Item> items = new ArrayList<Item>() ;


	private ArrayList<Rectangle> duengon = new ArrayList<Rectangle>() ;
	int[][] tileMap = {
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
			{1, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0},
			{1, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 7, 0, 0, 0},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0},
			{1, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	};

	Image regTile = new Image("file:images/reg_tile_scaled.png");
	Image stairTile = new Image("file:images/stairs.png");
	Image flowerTile = new Image("file:images/flower_tile.png");
	Image rockTile = new Image("file:images/rock.png");

	int tileLength = 48;
	int tileWidth = 48;

	public void generateTiles(GraphicsContext gc) {

		int mapLength = tileMap.length;
		int mapWidth = tileMap[0].length;
		
		createEnemies();

//		Item key = new Item(1, 40, 350);
//		items.add(key);

		for (int i = 0; i < mapLength; i++) { 
			for (int j = 0; j < mapWidth; j++) {
				if (tileMap[i][j] == 0) {
					gc.drawImage(regTile, j * tileWidth, i * tileLength);
				}
				if (tileMap[i][j] == 1) {
					gc.drawImage(regTile, j * tileWidth, i * tileLength);
					gc.drawImage(rockTile, j * tileWidth, i * tileLength);
					obstalces.add( new Rectangle(j * tileLength, i * tileWidth, tileLength, tileWidth));
				}
				if (tileMap[i][j] == 2) {
					gc.drawImage(stairTile, j * tileWidth, i * tileLength);
					duengon.add( new Rectangle(j * tileLength, i * tileWidth, tileLength, tileWidth));

				}
				if (tileMap[i][j] == 7) {
					gc.drawImage(flowerTile, j * tileWidth, i * tileLength);
				}
			}
		}
	}
	
	/**
	 * Populates the stage with obstacles that the player cannot move through
	 * @return  ArrayList of rectangle objects
	 */
	public ArrayList<Rectangle> getObstacles(){
		return obstalces;
	}
	/**
	 * returns the dungeons if the map contains one
	 * @return
	 */
	public ArrayList<Rectangle> getD() {
		return duengon;
	}
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	// Getter for Items in Stage 
	public ArrayList<Item> getItems() {
		return items;
	}
	private void createEnemies() {
		Enemy enemy = new Enemy(5, 300, 300);
		enemy.direction = 1;
		enemy.setVelocity(1);
		enemy.hasKey = true;
		enemies.add(enemy);
	}
}
