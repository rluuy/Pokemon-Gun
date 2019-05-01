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
			positionY -= velocity;
			deltaY += velocity;
			if (deltaY == 300) {
				deltaY = 0;
				direction = 3;
			}
		}
		else if (direction == 2)
			positionX += velocity;
		else if (direction == 3) {
			positionY += velocity;
			deltaY += velocity;
			if (deltaY == 300) {
				deltaY = 0;
				direction = 1;
			}
		}
		else if (direction == 4)
			positionX -= velocity;
		super.render(gc);
	}
	
	private void checkBounds() {
		if (positionY > 470) {
			direction = 1;
		}
		if (positionY < 10) {
			direction = 3;
		}
		
	}

	public void loseHealth() {
		health--;
	}
	public int getHealth() {
		return health;
	}
	

}