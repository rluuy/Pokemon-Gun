package main;

/**
 * 
 * @author Ryan Luu 
 * Movement Class that contains the necessary data for moving the 
 * player char
 *
 */

class Movement {
	Direction DOWN = Direction.DOWN;
	int xSpeed;
	int ySpeed;
	int posX, posY;
	int totalPosX, totalPosY;
	int width, height;
	Direction direction;
	
	/**
	 * default implmentation for default movement
	 */
	public Movement() {
		xSpeed = 5;
		ySpeed = 5;

		posX = 50;
		posY = 50;
		
		totalPosX = 50;
		totalPosY = 50;

		direction = DOWN;

		width = 100;
		height = 150;
	}
	
	/**
	 * Constructor for Movement Class 
	 * @param inPosX is the initial x  position of the player 
	 * @param inPosY is the initial y position of the player
	 * @param speed is the speed at which the player is moving
	 * @param whMult is the parameter used to determine the width
	 */
	public Movement(int inPosX, int inPosY, int speed, double whMult) {
		xSpeed = speed;
		ySpeed = speed;

		posX = inPosX;
		posY = inPosY;
		
		totalPosX = inPosX;
		totalPosY = inPosY;

		width = (int) (100 * whMult);
		height = (int) (150 * whMult);
		
		direction = DOWN;
	}

	/**
	 * Moves the player Left 
	 */
	public void moveLeft() {
		posX = posX - xSpeed;
		totalPosX = totalPosX - xSpeed;
	}

	/**
	 * Moves the player Right
	 */
	public void moveRight() {
		posX = posX + xSpeed;
		totalPosX = totalPosX + xSpeed;
	}

	/**
	 * Moves the player up
	 */
	public void moveUp() {
		posY = posY - ySpeed;
		totalPosY = totalPosY - ySpeed;
	}

	/** 
	 * Moves the player Down 
	 */
	public void moveDown() {
		posY = posY + ySpeed;
		totalPosY = totalPosY + ySpeed;
	}
	
	/**
	 * Returns the Hitbox Vertically
	 * @return
	 */
	public int hitboxY() {
		return posY + 48;
	}
	/**
	 * Returns the Hitbox Horizontally
	 * @return
	 */
	public int hitboxX() {
		return posX + 48;
	}
	
}
