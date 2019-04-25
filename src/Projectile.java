
public class Projectile {

	private int x, y, speedX, speedY;
	private boolean visible;
	
	public Projectile() {
		
	}
	
	public Projectile(int startX, int startY){
		x = startX;
		y = startY;
		speedX = 30;
		speedY = 20;
		visible = true;
	}
	
	public void update(Direction direction) {
		if (direction.toString() == "DOWN") {
			y += speedY;
		} else 	if (direction.toString() == "UP") {
			y -= speedY;
		} else 	if (direction.toString() == "RIGHT") {
			x += speedX;
		} else 	if (direction.toString() == "LEFT") {
			x -= speedX;
			}
	}
	
	
	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}


	public int getSpeedX() {
		return speedX;
	}


	public boolean isVisible() {
		return visible;
	}


	public void setX(int x) {
		this.x = x;
	}


	public void setY(int y) {
		this.y = y;
	}


	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}


	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	
	
}
