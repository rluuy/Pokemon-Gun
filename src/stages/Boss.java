package stages;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Boss extends Enemy {
	private int health = 20;
	Image emerald_down_rest = new Image("file:images/enemy1_down_rest.png");
	
	private int deltaY = 0;
	public Boss() {
		super();
		this.hasProjectileDir = true;
		this.projectileDir = 2;
		super.setImage(emerald_down_rest);
		super.setPosition(10, 10);
	}
	
	public void render(GraphicsContext gc) {
		checkBounds();
		
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