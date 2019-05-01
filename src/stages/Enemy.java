package stages;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Enemy extends Sprite {
	private int health = 3;
	Image emerald_down_rest = new Image("file:images/enemy1_down_rest.png");
	
	private int deltaY = 0;
	public Enemy() {
		super();
		super.setImage(emerald_down_rest);

		super.setPosition(300, 300);
	}
	public void render(GraphicsContext gc) {
		if (direction == 1) {
			positionY -= velocityX;
			deltaY += velocityX;
			if (deltaY == 300) {
				deltaY = 0;
				direction = 3;
			}
		}
		else if (direction == 2)
			positionX += velocityX;
		else if (direction == 3) {
			positionY += velocityX;
			deltaY += velocityX;
			if (deltaY == 300) {
				deltaY = 0;
				direction = 1;
			}
		}
		else if (direction == 4)
			positionX -= velocityX;
		super.render(gc);
	}
	
	public void loseHealth() {
		health--;
	}
	public int getHealth() {
		return health;
	}
	

}