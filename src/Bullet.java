public class Bullet extends Projectile{
    private Direction direction;
    private float speed = 1.2f;
    private int x;
    private int y;

    public Bullet(int x, int y){
    	super(x,y);
    	this.x =x;
    	this.y=y;        
    }

    public void launchBullet(Direction direction){
        this.direction=direction;
        

    }

    public void moveInDirection() {
    	
    }
}