package stages;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Stage4 {
	//a
	private ArrayList<Rectangle> obstalces = new ArrayList<Rectangle>() ;

	int[][] tileMap = {
			
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 7, 7, 7, 0, 0, 0, 0, 0, 1},
	}; //tileMap - may move on to text reading method instead

	Image regTile = new Image("file:images/reg_tile_scaled.png");
	Image sandTile = new Image("file:images/oceantile.png");
	Image potTile = new Image("file:images/pot.png");
	Image rockTile = new Image("file:images/rock.png");

	int tileLength = 48;
	int tileWidth = 48;

	public void generateTiles(GraphicsContext gc) {

		int mapLength = tileMap.length;
		int mapWidth = tileMap[0].length;


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
					gc.drawImage(regTile, j * tileWidth, i * tileLength);
					gc.drawImage(potTile, j * tileWidth, i * tileLength);
					obstalces.add( new Rectangle(j * tileLength, i * tileWidth, tileLength, tileWidth));
					
				}
				if (tileMap[i][j] == 7) {
					gc.drawImage(sandTile, j * tileWidth, i * tileLength);
				}
			}
		}
	}
	public ArrayList<Rectangle> getObstacles(){
		return obstalces;
	}


}