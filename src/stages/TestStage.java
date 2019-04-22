package stages;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class TestStage {

	int[][] tileMap = {
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	}; //tileMap - may move on to text reading method instead

	Image regTile = new Image("file:images/reg_tile_scaled.png");
	Image grassTile = new Image("file:images/reg_tile_scaled.png");
	Image flowerTile = new Image("file:images/grass-tile2.png");

	int tileLength = 48;
	int tileWidth = 48;

	public void generateTiles(GraphicsContext gc) {

		int mapLength = tileMap.length;
		int mapWidth = tileMap[0].length;


		for (int i = 0; i < mapLength; i++) { 
			for (int j = 0; j < mapWidth; j++) {
				//gc.fillRect(j * tileLength, i * tileWidth, 48, 48);
			
				if (tileMap[i][j] == 0) {
					gc.setFill(Color.BLACK);
					gc.fillRect(j * tileLength, i * tileWidth, tileLength, tileWidth);

				//	gc.drawImage(regTile, j * tileWidth, i * tileLength);
				}
				if (tileMap[i][j] == 1) {
					gc.setFill(Color.RED);
					gc.fillRect(j * tileLength, i * tileWidth, tileLength, tileWidth);

				//	gc.drawImage(grassTile, j * tileWidth, i * tileLength);
				}
				if (tileMap[i][j] == 7) {
				//	gc.setFill(Color.AQUAMARINE);
				//	gc.fillRect(j * tileLength, i * tileWidth, tileLength, tileWidth);
				//	gc.drawImage(flowerTile, j * tileWidth, i * tileLength);
				}
			}
		}
	}

	public void readTileMap() {
	}

}
