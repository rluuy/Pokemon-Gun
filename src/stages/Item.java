
package stages;

/**
 * Class that represents an enemy sprite in the game
 */
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.lang.*;

public class Item extends Sprite{
	public int type;
	Image key = new Image("file:images/key.png");
	//Image health = new Image("file:images/rock.png");
	Image boots = new Image("file:images/boots.png");
	
	Item(int type, int x, int y) {
		this.type = type;
		if (type == 1)
			super.setImage(key);
		else if (type == 2)
			super.setImage(boots);
		super.setPosition(x, y);
	}
}