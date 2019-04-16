
public class Movement {
	Direction DOWN = Direction.DOWN;
	int xSpeed;
	int ySpeed;
	int posX, posY;
	int totalPosX, totalPosY;
	int width, height;
	Direction direction;
	
	// Default Implmentation for Generic Movement
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
	
	public void moveLeft() {
		posX = posX - xSpeed;
		totalPosX = totalPosX - xSpeed;
	}

	public void moveRight() {
		posX = posX + xSpeed;
		totalPosX = totalPosX + xSpeed;
	}

	public void moveUp() {
		posY = posY - ySpeed;
		totalPosY = totalPosY - ySpeed;
	}

	public void moveDown() {
		posY = posY + ySpeed;
		totalPosY = totalPosY - ySpeed;
	}
	
}
