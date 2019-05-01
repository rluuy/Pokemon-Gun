package stages;

/**
 * Class that represents an enemy sprite in the game
 */
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Enemy extends Sprite {
	private int health = 3;

	private int type;
	private int posX;
	private int posY;
	private int deltaX = 0;
	private int deltaY = 0;
	private int ticks;

	public int projectileDir;
	public boolean hasBloom = false;
	public boolean hasProjectileDir = false;

	Image emerald_down_rest = new Image("file:images/enemy1_down_rest.png");
	
	//public Enemy(int type, int posX, int posY) {
	
	public Enemy() {
		super();
		this.type = type;
		this.posX = posX;
		this.posY = posY;
		this.ticks = 0;
		super.setImage(emerald_down_rest);

		super.setPosition(posX, posY);
	}
	
	/**
	 * draws enemy image on the canvas based on the type of enemy
	 */
	public void render(GraphicsContext gc) {
		if (type == 1) 
			renderOne(gc);
		else if (type == 2) 
			renderTwo(gc); 
		else if (type == 3)
			renderThree(gc);
		else
			renderFour(gc);
	}
	
	/**
	 * defines motion for enemy type 1 and draws the enemy on the canvas
	 * 
	 * @param gc  javafx.scene.canvas.GraphicsContext
	 */
	private void renderOne(GraphicsContext gc) {
		if (direction == 1) {
			positionY -= velocity;
			if (positionY == 0) {
				deltaY = 0;
				direction = 3;
			}
		}
		else if (direction == 2)
			positionX += velocity;
		else if (direction == 3) {
			positionY += velocity;
			deltaY += velocity;
			if (positionY + emerald_down_rest.getHeight() == gc.getCanvas().getHeight()) {
				deltaY = 0;
				direction = 1;
			}
		}
		else if (direction == 4)
			positionX -= velocity;
		super.render(gc);
	}
	
	/**
	 * defines motion for enemy type 2 and draws the enemy on the canvas
	 * 
	 * @param gc  javafx.scene.canvas.GraphicsContext
	 */
	private void renderTwo(GraphicsContext gc) {
		if (direction == 1) {
			positionY -= velocity;
		}
		else if (direction == 2) {
			positionX += velocity;
			if (positionX + emerald_down_rest.getWidth() == gc.getCanvas().getWidth()) {
				direction = 4;
			}
		}
		else if (direction == 3) {
			positionY += velocity;
		}
		else if (direction == 4) {
			positionX -= velocity;
			if (positionX == 0) {
				direction = 2;
			}
		}
		super.render(gc);
	}
	
	/**
	 * defines motion for enemy type 3 and draws the enemy on the canvas
	 * 
	 * @param gc  javafx.scene.canvas.GraphicsContext
	 */
	private void renderThree(GraphicsContext gc) {
		if (this.ticks == 300) {
			if (direction == 5) {
				direction = 1;
				ticks = 0;
			}
			else {
				ticks = 0;
				direction++;
			}
		}
		ticks++;
		super.render(gc);
	}
	
	/**
	 * defines motion for enemy type 4 and draws the enemy on the canvas
	 * 
	 * @param gc  javafx.scene.canvas.GraphicsContext
	 */
	private void renderFour(GraphicsContext gc) {
		if (direction == 1) {
			positionY -= velocity;
			deltaY += velocity;
			if (deltaY == 50) {
				deltaY = 0;
				direction = 4;
			}
		}
		else if (direction == 2) {
			positionX += velocity;
			deltaX += velocity;
			if (deltaX == 50) {
				deltaX = 0;
				direction = 1;
			}
		}
		else if (direction == 3) {
			positionY += velocity;
			deltaY += velocity;
			if (deltaY == 50) {
				deltaY = 0;
				direction = 2;
			}
		}
		else if (direction == 4) {
			positionX -= velocity;
			deltaX += velocity;
			if (deltaX == 50) {
				deltaX = 0;
				direction = 3;
			}
		}
		super.render(gc);
	}

	public void loseHealth() {
		health--;
	}
	public int getHealth() {
		return health;
	}
}