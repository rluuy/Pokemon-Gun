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

	public void setImage(Image i) {
		image = i;
		width = i.getWidth();
		height = i.getHeight();
	}

	public void setImage(String filename) {
		Image i = new Image(filename);
		setImage(i);
	}

	public void setPosition(double x, double y) {
		positionX = x;
		positionY = y;
	}

	public void setVelocity(double v) {
		velocity = v;
	}

	public void addVelocity(double x, double y) {
		System.out.print("HERE");
		velocity += x;
	}

	public void update(double time) {
		positionX += velocity * time;
	}

	public void render(GraphicsContext gc) {
		gc.drawImage(image, positionX, positionY);
	}

	public Rectangle2D getBoundary() {
		return new Rectangle2D(positionX, positionY, width, height);
	}

	public boolean intersects(Sprite s) {
		return s.getBoundary().intersects(this.getBoundary());
	}

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

