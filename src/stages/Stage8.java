package stages;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Stage8 {
	private ArrayList<Rectangle> obstalces = new ArrayList<Rectangle>() ;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>() ;
	private ArrayList<Item> items = new ArrayList<Item>() ;

	
	int[][] tileMap = {
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	}; //tileMap - may move on to text reading method instead

	Image regTile = new Image("file:images/oceantile.png");
	Image grassTile = new Image("file:images/tile2.png");
	Image cactusTile = new Image("file:images/cactus.png");

	int tileLength = 48;
	int tileWidth = 48;

	public void generateTiles(GraphicsContext gc) {

		int mapLength = tileMap.length;
		int mapWidth = tileMap[0].length;

		createEnemies();
		Item boots = new Item(2, 40, 400);
		items.add(boots);

		for (int i = 0; i < mapLength; i++) { 
			for (int j = 0; j < mapWidth; j++) {

				if (tileMap[i][j] == 0) {
					gc.drawImage(regTile, j * tileWidth, i * tileLength);
				}
				if (tileMap[i][j] == 1) {
					gc.drawImage(regTile, j * tileWidth, i * tileLength);
					gc.drawImage(cactusTile, j * tileWidth, i * tileLength);
					obstalces.add( new Rectangle(j * tileLength, i * tileWidth, tileLength, tileWidth));
				}
				if (tileMap[i][j] == 7) {
				}
			}
		}
	}
	public ArrayList<Rectangle> getObstacles(){
		return obstalces;
	}
	public ArrayList<Rectangle> getD() {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	public ArrayList<Item> getItems() {
		return items;
	}
	private void createEnemies() {
		Enemy enemy = new Enemy(2, 600, 400);
		enemy.direction = 1;
		enemy.hasProjectileDir = true;
		enemy.projectileDir = 4;
		enemy.setVelocity(2);
		enemies.add( enemy );
	}
}