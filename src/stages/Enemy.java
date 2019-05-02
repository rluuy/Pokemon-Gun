package stages;

/**
 * Class that represents an enemy sprite in the game
 */
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.lang.*;

public class Enemy extends Sprite {
	private int health = 3;

	public int type;
	private int posX;
	private int posY;
	private int deltaX = 0;
	private int deltaY = 0;
	private int ticks;

	public int projectileDir;
	public boolean hasBloom = false;
	public boolean hasProjectileDir = false;
	public boolean hasAI = false;
	public boolean hasKey = false;

	Image image;

	public Enemy(int type, int posX, int posY) {

		super();
		this.type = type;
		this.posX = posX;
		this.posY = posY;
		this.ticks = 0;

		if (type == 1)
			image = new Image("file:images/enemy1_down_rest.png");
		else if (type == 2)
			image = new Image("file:images/enemychar2_down_rest.png");
		else
			image = new Image("file:images/enemy1_down_rest.png");
		super.setImage(image);

		super.setPosition(posX, posY);
	}

	/**
	 * draws enemy image on the canvas based on the type of enemy
	 */
	public void render(GraphicsContext gc) {
		if (type == 0)
			renderStill(gc);
		else if (type == 1)
			renderOne(gc);
		else if (type == 2)
			renderTwo(gc);
		else if (type == 3)
			renderThree(gc);
		else if (type == 4)
			renderFour(gc);
		else if (type == 5)
			renderAIMove(gc);
		else
			super.render(gc);
	}

	private void renderStill(GraphicsContext gc) {
		if (direction == 1) {
			image = new Image("file:images/enemy1_up_rest.png");
			super.setImage(image);
		} else if (direction == 2) {
			image = new Image("file:images/enemy1_right_rest.png");
			super.setImage(image);
		} else if (direction == 3) {
			image = new Image("file:images/enemy1_down_rest.png");
			super.setImage(image);
		} else if (direction == 4) {
			image = new Image("file:images/enemy1_left_rest.png");
			super.setImage(image);
		}
		super.render(gc);
	}

	/**
	 * Up Down Movement defines motion for enemy type 1 and draws the enemy on the
	 * canvas
	 * 
	 * @param gc javafx.scene.canvas.GraphicsContext
	 */
	private void renderOne(GraphicsContext gc) {
		checkBounds();
		if (direction == 1) {
			image = new Image("file:images/enemychar2_up_rest.png");
			super.setImage(image);
			positionY -= velocity;
			deltaY += velocity;
//			if (deltaY == 400) {
//				deltaY = 0;
//				direction = 3;
//			}
		} else if (direction == 2)
			positionX += velocity;
		else if (direction == 3) {
			image = new Image("file:images/enemychar2_down_rest.png");
			super.setImage(image);
			positionY += velocity;
			deltaY += velocity;
//			if (deltaY == 400) {
//				deltaY = 0;
//				direction = 1;
//			}
		} else if (direction == 4)
			positionX -= velocity;
		gc.drawImage(image, positionX, positionY, 100 * .43, 150 * .43);
	}

	/**
	 * Left Right Motion defines motion for enemy type 2 and draws the enemy on the
	 * canvas
	 * 
	 * @param gc javafx.scene.canvas.GraphicsContext
	 */
	private void renderTwo(GraphicsContext gc) {
		checkBounds();
		if (direction == 1) {
			positionY -= velocity;
		} else if (direction == 2) {
			image = new Image("file:images/enemychar2_right_rest.png");
			super.setImage(image);
			positionX += velocity;
			deltaX += velocity;
			if (deltaX == 300) {
				deltaX = 0;
				direction = 4;
			}
		} else if (direction == 3) {
			positionY += velocity;
		} else if (direction == 4) {
			image = new Image("file:images/enemychar2_left_rest.png");
			super.setImage(image);
			positionX -= velocity;
			deltaX += velocity;
			if (deltaX == 300) {
				deltaX = 0;
				direction = 2;
			}
		}
		gc.drawImage(image, positionX, positionY, 100 * .43, 150 * .43);
	}

	/**
	 * Stationary, Changes Direction defines motion for enemy type 3 and draws the
	 * enemy on the canvas
	 * 
	 * @param gc javafx.scene.canvas.GraphicsContext
	 */
	private void renderThree(GraphicsContext gc) {
		if (direction == 5) {
			direction = 1;
		}
		if (direction == 1) {
			image = new Image("file:images/enemy1_up_rest.png");
			super.setImage(image);
		} else if (direction == 2) {
			image = new Image("file:images/enemy1_right_rest.png");
			super.setImage(image);
		} else if (direction == 3) {
			image = new Image("file:images/enemy1_down_rest.png");
			super.setImage(image);
		} else if (direction == 4) {
			image = new Image("file:images/enemy1_left_rest.png");
			super.setImage(image);
		}
		if (!hasAI) {
			if (this.ticks == 300) {
				ticks = 0;
				direction++;
			}
			ticks++;
		}
		super.render(gc);
	}

	/**
	 * Enemy Square Motion defines motion for enemy type 4 and draws the enemy on
	 * the canvas
	 * 
	 * @param gc javafx.scene.canvas.GraphicsContext
	 */
	private void renderFour(GraphicsContext gc) {
		if (direction == 1) {
			image = new Image("file:images/enemyf_up_rest.png");
			super.setImage(image);
			positionY -= velocity;
			if (positionY == 0) {
				direction = 4;
			}
		}
		else if (direction == 2) {
			image = new Image("file:images/enemyf_right_rest.png");
			super.setImage(image);
			positionX += velocity;
			if ((positionX + image.getWidth() - 1) == gc.getCanvas().getWidth()) {
				direction = 1;
			}
		}
		else if (direction == 3) {
			image = new Image("file:images/enemyf_down_rest.png");
			super.setImage(image); 
			positionY += velocity;
			System.out.println(positionY);
			if ((positionY + image.getHeight() - 1) == gc.getCanvas().getHeight()) {
				direction = 2;
			}
		}
		else if (direction == 4) {
			image = new Image("file:images/enemyf_left_rest.png");
			super.setImage(image);
			positionX -= velocity;
			deltaX += velocity;
			if (positionX == 0) {
				direction = 3;
			}
		}

		gc.drawImage(image, positionX, positionY, 100 * .43, 150 * .43);
	}

	public void renderAIMove(GraphicsContext gc) {
		checkBounds();

		if (direction == 1) {
			image = new Image("file:images/enemy1_up_rest.png");
			super.setImage(image);
			positionY -= velocity;
		}
		else if (direction == 2) {
			image = new Image("file:images/enemy1_right_rest.png");
			super.setImage(image);
			positionX += velocity;
		}
		else if (direction == 3) {
			image = new Image("file:images/enemy1_down_rest.png");
			super.setImage(image);
			positionY += velocity;
		}
		else if (direction == 4) {
			image = new Image("file:images/enemy1_left_rest.png");
			super.setImage(image);
			positionX -= velocity;
		}
		super.render(gc);
	}

	private void checkBounds() {
		if (positionY > 420) {
			direction = 1;
		}
		if (positionY < 10) {
			direction = 3;
		}
		if (positionX > 710) {
			direction = 4;
		}
		if (positionX < 10) {
			direction = 2;
		}
	}

	public void AIDirection(int x, int y) {
		int xDiff = (int) (x - this.positionX);
		int yDiff = (int) (y - this.positionY);
		if (java.lang.Math.abs(xDiff) > java.lang.Math.abs(yDiff)) {
			if (xDiff > 0)
				direction = 2;
			else if (xDiff < 0)
				direction = 4;
		} else if (java.lang.Math.abs(xDiff) < java.lang.Math.abs(yDiff)) {
			if (yDiff > 0)
				direction = 3;
			if (yDiff < 0)
				direction = 1;
		} else
			System.out.println("Here");
	}

	public void loseHealth() {
		health--;
	}

	public int getHealth() {
		return health;
	}
}