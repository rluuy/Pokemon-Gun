package main;


import javafx.scene.canvas.GraphicsContext;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;


public class PlayerChar extends Movement{
	private int health;
	Direction DOWN = Direction.DOWN;
	Image emerald_down_rest;
	Image emerald_down_1;
	Image emerald_down_2;
	Image emerald_left_rest;
	Image emerald_left_1;
	Image emerald_left_2;
	Image emerald_right_rest;
	Image emerald_right_1;
	Image emerald_right_2;
	Image emerald_up_rest;
	Image emerald_up_1;
	Image emerald_up_2;


	public PlayerChar() {
		super();
		emerald_down_rest = new Image("file:images/emerald_down_rest.png");
		emerald_down_1 = new Image("file:images/emerald_down_1.png");
		emerald_down_2 = new Image("file:images/emerald_down_2.png");
		emerald_left_rest = new Image("file:images/emerald_left_rest.png");
		emerald_left_1 = new Image("file:images/emerald_left_1.png");
		emerald_left_2 = new Image("file:images/emerald_left_2.png");
		emerald_right_rest = new Image("file:images/emerald_right_rest.png");
		emerald_right_1 = new Image("file:images/emerald_right_1.png");
		emerald_right_2 = new Image("file:images/emerald_right_2.png");
		emerald_up_rest = new Image("file:images/emerald_up_rest.png");
		emerald_up_1 = new Image("file:images/emerald_up_1.png");
		emerald_up_2 = new Image("file:images/emerald_up_2.png");

	}

	public PlayerChar(int inPosX, int inPosY, int speed, double whMult) {
		super(inPosX, inPosY, speed, whMult);
		emerald_down_rest = new Image("file:images/enemy1_down_rest.png");
		emerald_down_1 = new Image("file:images/enemy1_down_1.png");
		emerald_down_2 = new Image("file:images/enemy1_down_2.png");
		emerald_left_rest = new Image("file:images/enemy1_left_rest.png");
		emerald_left_1 = new Image("file:images/enemy1_left_1.png");
		emerald_left_2 = new Image("file:images/enemy1_left_2.png");
		emerald_right_rest = new Image("file:images/enemy1_right_rest.png");
		emerald_right_1 = new Image("file:images/enemy1_right_1.png");
		emerald_right_2 = new Image("file:images/enemy1_right_2.png");
		emerald_up_rest = new Image("file:images/enemy1_up_rest.png");
		emerald_up_1 = new Image("file:images/enemy1_up_1.png");
		emerald_up_2 = new Image("file:images/enemy1_up_2.png");
		health = 3;

	}
	
	public void loseHealth() {
		health--;
		if (health < 0)
			health = 0;
	}
	
	public int getHealth() {
		return health;
	}


}