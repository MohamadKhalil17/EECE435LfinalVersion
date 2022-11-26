package package1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
/**
 * 
 * @author Mohamad Khalil, Yara Hassan
 * 
 * See <a href="https://github.com/MohamadKhalil17">GitHub</a> 
 */
public class Alien extends Sprite {
	
	private final int BOARD_TOP = 10;
    private final int ALIEN_SPEED = 3;
    private double ALIEN_ANGLE;
    private int ALIEN_DIRECTION;
    /**
     * collision Boolean to check if a collision happened
     */
    public boolean collision = false;
    /**
     * counter int to check if number of aliens
     */
    public int counter = 5;
    
    private List<AlienShot> alienShots;
    


    /**
     * getter function
     * @return int counter
     */
    public int getCounter() {
		return counter;
	}
    /**
     * setter function
     * @return int counter
     */
	public void setCounter(int counter) {
		this.counter = counter;
	}
    /**
     * getter function
     * @return boolean collision
     */
	public boolean isCollision() {
		return collision;
	}
    /**
     * setter function
     * @return boolean collision
     */
	public void setCollision(boolean collision) {
		this.collision = collision;
	}
    /**
     * getter function
     * @return double alien angle
     */
	public double getALIEN_ANGLE() {
		return ALIEN_ANGLE;
	}
    /**
     * setter function
     * @return int alien angle
     */
	public void setALIEN_ANGLE(double aLIEN_ANGLE) {
		ALIEN_ANGLE = aLIEN_ANGLE;
	}
	
    /**
     * getter function
     * @return int alien direction
     */
	public int getALIEN_DIRECTION() {
		return ALIEN_DIRECTION;
	}
    /**
     * setter function
     * @return int alien direction
     */
	public void setALIEN_DIRECTION(int aLIEN_DIRECTION) {
		ALIEN_DIRECTION = aLIEN_DIRECTION;
	}
	/**
	 * constructor to initialize alien
	 * @param x
	 * @param y
	 */
	public Alien(int x, int y) {
        super(x, y);

        initAlien();
    }
	/**
	 * function to initialize and load the image of aliens
	 */
    private void initAlien() {
    	alienShots = new ArrayList<>();
        loadImage("pictures/alien.png");
        getImageDimensions();
    }
    /**
     * function that returns a list of aliens to be fired
     * @return aliens to be fired
     */
    public List<AlienShot> getAlienShots(){
    	return alienShots;
    }
    /**
     * funtion to fire an alien which returns the alien to be fired
     * @param a
     * @return alien
     */
    public AlienShot fire(Alien a) {
    	AlienShot as = new AlienShot(a.getX() + width , a.getY() + height / 2);
    	//AlienShot as  = new AlienShot(300,10);
    	alienShots.add(as);
    	return as;
    }
    

    /**
	 * Function to move aliens 
	 * used the sin function on the angle multiplied by the speed and direction to determine how should the alien move
	 */
    public void move() {
    	Random rand = new Random();
    	int randomNum = rand.nextInt((2) + 1)-1;
   
        x += ALIEN_DIRECTION*ALIEN_SPEED;
        if (ALIEN_DIRECTION<0) {
        	y -= (int) ALIEN_SPEED* Math.sin(Math.toRadians(ALIEN_ANGLE))*2;
        }
        else {
        	y -=( int) ALIEN_DIRECTION*ALIEN_SPEED*Math.sin(Math.toRadians(ALIEN_ANGLE))*2;
        }
        
        //System.out.println(x+ " "+y);
    	List<AlienShot> aslist = this.getAlienShots();
    	for (AlienShot as : aslist) {
    		as.move();
    	}
        
        if (y < BOARD_TOP)
            visible = false;
    }
    
}