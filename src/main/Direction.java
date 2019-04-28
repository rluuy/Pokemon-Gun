package main;
import javafx.scene.image.Image;

import java.util.function.Consumer;
import java.util.function.Function;

public enum Direction {

	UP(e -> e.emerald_up_rest, e -> e.emerald_up_1, e -> e.emerald_up_2, PlayerChar::moveUp),
	DOWN(e -> e.emerald_down_rest, e -> e.emerald_down_1, e -> e.emerald_down_2, PlayerChar::moveDown),
	LEFT(e -> e.emerald_left_rest, e -> e.emerald_left_1, e -> e.emerald_left_2, PlayerChar::moveLeft),
	RIGHT(e -> e.emerald_right_rest, e -> e.emerald_right_1, e -> e.emerald_right_2, PlayerChar::moveRight);

	final Function<PlayerChar, Image> image;
	final Function<PlayerChar, Image> image1;
	final Function<PlayerChar, Image> image2;
	final Consumer<PlayerChar> move;

	Direction(Function<PlayerChar, Image> image, Function<PlayerChar, Image> image1,
	          Function<PlayerChar, Image> image2, Consumer<PlayerChar> move) {
		this.image = image;
		this.image1 = image1;
		this.image2 = image2;
		this.move = move;
	}

	static final Direction[] cachedValues = values();

}