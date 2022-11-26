package package1;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Shooter extends Sprite {

    private int dx;
    private List<Missile> missiles;
    int count =2 ;
    int previousX = 307;
    int nbHits = 0;

    public Shooter(int x, int y) {
        super(x, y);

        initCraft();
    }

    private void initCraft() {
        
        missiles = new ArrayList<>();
        loadImage("pictures/shooter.png");
        getImageDimensions();
    }

    
    /**
   	 * Function to move shooter on screen
   	 */
    public void move() {

        x += dx;

        if (x < 1) {
            x = 1;
        }
        else if (x>540) {
        	x = 540;
        }
        if (Math.abs(x-previousX)>11) {
        	previousX = x;
        	count = 2;//reset the count of shots
        }
    }

    public List<Missile> getMissiles() {
        return missiles;
    }

    public void keyPressed(KeyEvent e) {
    	
//        int key = e.getKeyCode();
//
//        if (key == KeyEvent.VK_SPACE) {
//        	
//            if (count>0) {
//            	fire();
//            	count--;
//            	
//            }
//           
//            
//        }
//
//        if (key == KeyEvent.VK_LEFT) {
//            dx = -6;
//        }
//
//        if (key == KeyEvent.VK_RIGHT) {
//            dx = 6;
//        }
    }
    /**
     * this functions make the spaceship fire aliens
     * @param alien Alien to be fired
     */
    public void fire(Alien alien) {
    	try {
		    File wavFile = new File("sounds/"+"shot.wav");
		    Clip clip = AudioSystem.getClip();
		    clip.open(AudioSystem.getAudioInputStream(wavFile));
		    clip.start();
		} catch (Exception e1) {
		    System.out.println(e1);
		}
    	Missile m = new Missile(x + width + 27, y + height / 2);
    	m.setMISSILE_ANGLE(alien.getALIEN_ANGLE());
    	m.setMISSILE_DIRECTION(alien.getALIEN_DIRECTION());
        missiles.add(m);
        nbHits++;
    }
    
    public int getNbHits() {
    	return nbHits;
    }
    public void setNbHits(int a) {
    	nbHits = a;
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }
}