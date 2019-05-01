package stages;

import java.util.ArrayList;

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


	public void generateTiles(GraphicsContext gc) {
		Sprite pokeballS = new Sprite();
		pokeballS.setImage(pokeball);
		double px = 350 * Math.random() + 50;
		double py = 350 * Math.random() + 50;
		pokeballS.setPosition(300,300);
		items.add( pokeballS );

		createEnemies();

		int mapLength = tileMap.length;
		int mapWidth = tileMap[0].length;


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

					//	gc.drawImage(grassTile, j * tileWidth, i * tileLength);
				}
				if (tileMap[i][j] == 7) {

				}
			}
		}
	}
	public ArrayList<Rectangle> getObstacles(){
		return obstacles;
	}
	public ArrayList<Sprite> getItems() {
		// TODO Auto-generated method stub
		return items;
	}
	public ArrayList<Enemy> getEnemies() {
		// TODO Auto-generated method stub
		return enemies;
	}
	private void createEnemies() {
		Enemy enemy = new Enemy(1, 300, 300);
		enemy.direction = 1;
		enemy.setVelocity(1);
		enemies.add( enemy );

		Enemy enemy2 = new Enemy(2, 200, 200);
		enemy2.direction = 2;
		enemy2.setVelocity(1);
		enemies.add( enemy2 );
	}
}
