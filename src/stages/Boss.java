package stages;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Boss extends Enemy {
	private int health = 20;
	Image emerald_down_rest = new Image("file:images/enemy1_down_rest.png");
	
	private int deltaY = 0;
	public Boss(int type, int x, int y) {
		super(type, x, y);
		this.hasProjectileDir = true;
		this.projectileDir = 2;
		super.setImage(emerald_down_rest);
		//super.setPosition(10, 10);
	}
	
	public void render(GraphicsContext gc) {
		checkBounds();
		
		if (direction == 1) {
			image = new Image("file:images/enemyf_up_rest.png");
			super.setImage(image);
			positionY -= velocity;
		}
		else if (direction == 2) {
			image = new Image("file:images/enemyf_right_rest.png");
			super.setImage(image);
			positionX += velocity;
		}
		else if (direction == 3) {
			image = new Image("file:images/enemyf_down_rest.png");
			super.setImage(image);
			positionY += velocity;
		}
		else if (direction == 4) {
			image = new Image("file:images/enemyf_left_rest.png");
			super.setImage(image);
			positionX -= velocity;
		}
		super.render(gc);
	}
	
	private void checkBounds() {
		if (positionY > 420) {
			direction = 1;
		}
		if (positionY < 58) {
			direction = 3;
		}
		if (positionX > 420) {
			direction = 4;
		}
		if (positionX < 58) {
			direction = 2;
		}
	}
	
//	public void AIDirection(int x, int y) {
//		int xDiff = (int) (x - this.positionX);
//		int yDiff = (int) (y - this.positionY);
//		if (xDiff > yDiff) {
//			if (xDiff > 0)
//				direction = 2;
//		}
//	}

	public void loseHealth() {
		health--;
	}
	public int getHealth() {
		return health;
	}
	

}