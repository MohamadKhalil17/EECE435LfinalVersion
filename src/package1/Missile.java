package package1;

public class Missile extends Sprite {

    private final int BOARD_TOP = 10;
    private final int MISSILE_SPEED = 6;
    private double MISSILE_ANGLE;
    private int MISSILE_DIRECTION;
    
    
    
    
    
    public double getMISSILE_ANGLE() {
		return MISSILE_ANGLE;
	}

	public void setMISSILE_ANGLE(double mISSILE_ANGLE) {
		MISSILE_ANGLE = mISSILE_ANGLE;
	}

	public int getMISSILE_DIRECTION() {
		return MISSILE_DIRECTION;
	}

	public void setMISSILE_DIRECTION(int mISSILE_DIRECTION) {
		MISSILE_DIRECTION = mISSILE_DIRECTION;
	}

	public Missile(int x, int y) {
        super(x, y);

        initMissile();
    }
    
    private void initMissile() {
    	
        loadImage("pictures/missile.png");
        getImageDimensions();        
    }

    /**
   	 * Function to move missile on screen
   	 */
    public void move() {
    	
        y -= MISSILE_SPEED;
        if (MISSILE_DIRECTION>0) {
        	x += MISSILE_SPEED - 3;
        }
        else {
        	x -= MISSILE_SPEED - 3;
        }
        if (y < BOARD_TOP)
            visible = false;
    }
    
}