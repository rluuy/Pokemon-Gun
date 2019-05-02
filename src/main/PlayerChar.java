package main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * PlayerChar represents the player in the game.
 */
public class PlayerChar extends Movement implements Serializable
{
	private int health; //the health of the player
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

	/**
	 * Parameterised constructor
	 * @param health the integer variable for how much health this player has.
	 */
	public PlayerChar(int health)
	{
		this.health = health;
	}

	/**
	 * Default Constructor
	 */
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

	/**
	 * Parameterized Constructor to create a new PlayerChar with these specific parameters.
	 * @param inPosX the initial X position of the player 
	 * @param inPosY the inital Y position of the player
	 * @param speed the speed with which the player can run
	 * @param whMult multiplier used to place the image according to the screen
	 * @param health the player's health
	 */
	public PlayerChar(int inPosX, int inPosY, int speed, double whMult, int health) {
		super(inPosX, inPosY, speed, whMult);
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
		this.health = health;
	}
	
	/**
	 * Parameterised Constructor to create a new player character based on the parameters.
	 * @param speed the speed with which the player can run
	 * @param whMult multiplier used to place the image according to the screen
	 * @param health the player's health
	 * @param posx the initial X position of the player
	 * @param posy the inital Y position of the player
	 * @param totx the total X position of the player
	 * @param toty the total Y position of the player
	 */
	public PlayerChar( int speed, double whMult, int health, int posx, int posy, int totx, int toty) 
	{
		super(posx, posy, speed, whMult);
		super.totalPosX =totx;
		super.totalPosY=toty;
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
		this.health = health;
	}
	
	/**
	 * This function is called when the player is impacted to reduce its health.
	 */
	public void loseHealth() {
		health--;
		if (health < 0)
			health = 0;
	}
	/**
	 * Accessor method that returns the health of the player.
	 * @return
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * mutator method to set the health of the player to zero.
	 */
	public void setHealth() {
		health =0;
	}

	/**
	 * this function is used to increase the player speed.
	 * 
	 */
	public void increaseSpeed() {
		xSpeed += 5;
		ySpeed += 5;
		
	}
	/**
	 * this function is used to incease the player health. 
	 */
	public void increaseHealth() {
		health++;
	}


}