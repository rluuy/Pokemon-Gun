package stages;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Stage5 {
	private ArrayList<Rectangle> obstalces = new ArrayList<Rectangle>() ;
	private ArrayList<Rectangle> duengon = new ArrayList<Rectangle>() ;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>() ;

	int[][] tileMap = {
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{0, 0, 0, 0, 7, 7, 7, 7, 7, 7, 7, 7, 0, 0, 1},
			{0, 0, 0, 0, 7, 7, 0, 2, 0, 0, 7, 7, 0, 0, 1},
			{0, 0, 0, 0, 7, 7, 0, 0, 0, 0, 7, 7, 0, 0, 1},
			{0, 0, 0, 0, 7, 7, 0, 0, 0, 0, 7, 7, 0, 0, 1},
			{0, 0, 0, 0, 7, 7, 0, 0, 0, 0, 7, 7, 0, 0, 1},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	}; //tileMap - may move on to text reading method instead

	Image regTile = new Image("file:images/reg_tile_scaled.png");
	Image grassTile = new Image("file:images/reg_tile_scaled.png");
	Image rockTile = new Image("file:images/rock.png");
	Image waterTile = new Image("file:images/water_tile.png");
	Image stairTile = new Image("file:images/stairs.png");

	int tileLength = 48;
	int tileWidth = 48;

	public void generateTiles(GraphicsContext gc, boolean a) {
		if ( a == true) {
			int[][] tileMap = {
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
					{0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 7, 7, 0, 0, 1},
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 0, 0, 1},
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 0, 0, 1},
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 0, 0, 1},
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
					{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			};
		}

		int mapLength = tileMap.length;
		int mapWidth = tileMap[0].length;

		createEnemies();

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
					gc.drawImage(waterTile, j * tileWidth, i * tileLength);
					obstalces.add( new Rectangle(j * tileLength, i * tileWidth, tileLength, tileWidth));
				}
			}
		}
	}

	public ArrayList<Rectangle> getObstacles(){
		return obstalces;
	}

	public ArrayList<Rectangle> getD(){
		return duengon;
	}
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	private void createEnemies() {
		Enemy enemy = new Enemy(4, 0, 0);
		enemy.direction = 3;
		enemy.setVelocity(5);
		enemies.add( enemy );
	}
}