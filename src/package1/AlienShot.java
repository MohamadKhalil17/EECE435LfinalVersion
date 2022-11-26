package package1;
/**
 * 
 * @author Mohamad Khalil Yara Hassan
 *made  a class AlienShot 
 *
 */
public class AlienShot extends Sprite{
	
    private final int BOARD_BOTTOM = 580;
    private final int ALIEN_SHOT_SPEED = 3;
    public boolean collision = false;
    public boolean alive = true;
    
    
    
	
	/**
	 * getter function to check if a shot is alive or not
	 * @return alive boolean describe if it alive
	 */
	public boolean isAlive() {
		return alive;
	}
	
	
	/**
	 * setter function to set the alive status of an alien shot
	 * @param alive boolean value to describe if it is alive
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isCollision() {
		return collision;
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}

	public int getBOARD_BOTTOM() {
		return BOARD_BOTTOM;
	}

	public int getALIEN_SHOT_SPEED() {
		return ALIEN_SHOT_SPEED;
	}

	public AlienShot(int x, int y) {
        super(x, y);

        initAlienShot();
    }
    
	
	/**
	 * initalizes the alien shot and the image of it and its dimensions
	 */
    private void initAlienShot() {
    	
        loadImage("pictures/alienShot.png");
        getImageDimensions();        
    }
    
    /**
     * function to move the alien shot from up to down
     */
    public void move() {
    	y+=ALIEN_SHOT_SPEED;

    	if (y>BOARD_BOTTOM) {
    		visible = false;
    	}
    	//System.out.println(y);
    }
    
    
}
