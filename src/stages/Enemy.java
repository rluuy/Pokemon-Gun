package stages;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Enemy extends Sprite {
	private int health = 3;
	Image emerald_down_rest = new Image("file:images/enemy1_down_rest.png");
	
	public Enemy() {
		super();
		super.setImage(emerald_down_rest);

		super.setPosition(300, 300);
	}
	public void render(GraphicsContext gc) {
		super.render(gc);
	}
	

}