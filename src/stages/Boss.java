package stages;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Boss is the main enemy in the game at the final stage. 
 */
public class Boss extends Enemy {
	private int health = 20;//represents the health of the enemy
	Image emerald_down_rest = new Image("file:images/enemy1_down_rest.png");
	
	private int deltaY = 0;
	
	/**
	 * Parameterised constructor for the Boss class that sets the location and the type of the enemy.
	 * @param type the type of the enemy 
	 * @param x the x-coordinate of the Enemy
	 * @param y the y-coordinate of the Enemy 
	 */
	public Boss(int type, int x, int y) {
		super(type, x, y);
		this.hasProjectileDir = true;
		this.projectileDir = 2;
		super.setImage(emerald_down_rest);
	}
	
	/**
	 * renders the image of the Boss Enemy.
	 */
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

	/**
	 * This method is called each time Boss gets hit by the player.
	 */
	public void loseHealth() {
		health--;
	}
	
	/**
	 * This method is an accessor method that returns the Boss' health.
	 */
	public int getHealth() {
		return health;
	}
	

}