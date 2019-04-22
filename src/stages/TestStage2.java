package stages;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class TestStage2 {

	int[][] tileMap = {
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 7, 0, 0, 0, 1, 0, 0, 0, 1, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	}; //tileMap - may move on to text reading method instead

	Image regTile = new Image("file:images/reg_tile_scaled.png");
	Image grassTile = new Image("file:images/anakin_small.png");
	Image flowerTile = new Image("file:images/obiwan.png");
	Image darthMaul = new Image("file:images/darthmaul.jpeg");


	int tileLength = 48;
	int tileWidth = 48;

	
	
	public void generateTiles(GraphicsContext gc) {
		

		int mapLength = tileMap.length;
		int mapWidth = tileMap[0].length;
		gc.drawImage(darthMaul, 0, 0, 720, 480);

//		for (int i = 0; i < mapLength; i++) { 
//			for (int j = 0; j < mapWidth; j++) {
//			
//				if (tileMap[i][j] == 0) {
//
//					gc.drawImage(regTile, j * tileWidth, i * tileLength);
//				}
//				if (tileMap[i][j] == 1) {
//
//					gc.drawImage(grassTile, j * tileWidth, i * tileLength);
//				}
//				if (tileMap[i][j] == 7) {
//
//					gc.drawImage(darthMaul, j * tileWidth, i * tileLength);
//				}
//			}
//		}
	}

	public void readTileMap() {
	}
	
}
