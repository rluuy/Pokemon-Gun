package stages;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//Stage7 contains the Entrence to the place needed for the key to open up the boss room 
public class Stage7 {
	private ArrayList<Rectangle> obstalces = new ArrayList<Rectangle>() ;
	private ArrayList<Rectangle> duengon = new ArrayList<Rectangle>() ;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>() ;
	
	int[][] tileMap = {
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 7, 0, 0, 0},
			{0, 1, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 1},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 7, 0, 0, 1},
			{1, 1, 1, 1, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 1},
			{1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 7, 0, 0, 0, 1},
			{2, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	}; //tileMap - may move on to text reading method instead

	Image regTile = new Image("file:images/oceantile.png");
	Image grassTile = new Image("file:images/oceantile_grass.png");
	Image cactusTile = new Image("file:images/cactus.png");
	Image stairTile = new Image("file:images/stairs.png");
	int tileLength = 48;
	int tileWidth = 48;

	public void generateTiles(GraphicsContext gc) {

		int mapLength = tileMap.length;
		int mapWidth = tileMap[0].length;

		createEnemies();

		for (int i = 0; i < mapLength; i++) { 
			for (int j = 0; j < mapWidth; j++) {
				//gc.fillRect(j * tileLength, i * tileWidth, 48, 48);

				if (tileMap[i][j] == 0) {
					gc.drawImage(regTile, j * tileWidth, i * tileLength);
				}
				if (tileMap[i][j] == 1) {
					gc.drawImage(regTile, j * tileWidth, i * tileLength);
					gc.drawImage(cactusTile, j * tileWidth, i * tileLength);
					obstalces.add( new Rectangle(j * tileLength, i * tileWidth, tileLength, tileWidth));

				}
				if (tileMap[i][j] == 2) {
					gc.drawImage(stairTile, j * tileWidth, i * tileLength);
					duengon.add( new Rectangle(j * tileLength, i * tileWidth, tileLength, tileWidth));
				}
				if (tileMap[i][j] == 7) {
					gc.drawImage(regTile, j * tileWidth, i * tileLength);
					gc.drawImage(grassTile, j * tileWidth, i * tileLength);

				}
			}}
	}
	/**
	 * Gets Obstacles 
	 * @return Lists of Rectangle Obstacles 
	 */
	public ArrayList<Rectangle> getObstacles(){
		return obstalces;
	}
	/**
	 * Gets Dungon Entrences 
	 * @return Lists of Rectangle Dungeon Entrences 
	 */
	public ArrayList<Rectangle> getD() {
		return duengon;
	}
	/**
	 * Gets Enemies 
	 * @return List of Enemy on Stage 
	 */
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	/**
	 * CreateEnemies Makes the Enemies on this stage 
	 */
	private void createEnemies() {
		Enemy enemy = new Enemy(2, 350, 300);
		enemy.direction = 2;
		enemy.setVelocity(1);
		enemies.add( enemy );
	}
}