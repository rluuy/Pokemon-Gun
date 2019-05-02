package stages;

/**
 * Class that represents the base for a sprite in the game
 */

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public class Sprite {
	private Image image;
	public double positionX;
	public double positionY;
	public int direction = 0;
	protected double velocity;
	public double width;
	public double height;

	public Sprite() {
		positionX = 0;
		positionY = 0;
		velocity = 0;
	}
	
	/**
	 * sets image and image dimensions
	 * 
	 * @param i  javafx.scene.image.Image
	 */
	public void setImage(Image i) {
		image = i;
		width = i.getWidth();
		height = i.getHeight();
	}
	
	public double getWidth() {
		return this.width;
	}
	
	public double getHeight() {
		return this.height;
	}
	
	/**
	 * sets image from a file path
	 * 
	 * @param filename  file path for an image
	 */
	public void setImage(String filename) {
		Image i = new Image(filename);
		setImage(i);
	}

	/**
	 * sets x and y coords of the sprite
	 * 
	 * @param x  x position of the sprite
	 * @param y  y position of the sprite
	 */
	public void setPosition(double x, double y) {
		positionX = x;
		positionY = y;
	}

	/**
	 * sets speed at which the sprite will move
	 * 
	 * @param v  double used for speed
	 */
	public void setVelocity(double v) {
		velocity = v;
	}

	/**
	 * increases speed of the sprite
	 * 
	 * @param x  amount in which to increase the speed
	 */
	public void addVelocity(double x) {
		velocity += x;
	}
	
	public void update(double time) {
		positionX += velocity * time;
	}
	
	/**
	 * 
	 * @param javafx.scene.canvas.GraphicsContext
	 */
	public void render(GraphicsContext gc) {
		gc.drawImage(image, positionX, positionY);
	}
	
	/**
	 * returns rectangle object used to detect collision
	 * 
	 * @return  rectangle object
	 */
	public Rectangle2D getBoundary() {
		return new Rectangle2D(positionX, positionY, width, height);
	}
	
	/**
	 * returns boolean that indicates if this sprite intersects with another sprite
	 * 
	 * @param s  other sprite 
	 * @return   boolean 
	 */
	public boolean intersects(Sprite s) {
		return s.getBoundary().intersects(this.getBoundary());
	}

	/**
	 * calculates motion and draws srite to the canvas in its new location
	 * 
	 * @param gc  javafx.scene.canvas.GraphicsContext
	 */
	public void renderMotion(GraphicsContext gc) {
		if (direction == 1)
			positionY -= velocity;
		else if (direction == 2)
			positionX += velocity;
		else if (direction == 3)
			positionY += velocity;
		else if (direction == 4)
			positionX -= velocity;
		gc.drawImage(image, positionX, positionY);
	}

}

