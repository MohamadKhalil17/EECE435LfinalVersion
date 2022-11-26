package package1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Spaceship extends Sprite {
	
	private List<Alien> aliens;
	public Spaceship(int x, int y) {
		super(x, y);
		initSpaceship();
	}

	private void initSpaceship() {
		aliens = new ArrayList<>();
		loadImage("pictures/spaceship.png");
        getImageDimensions();
		
	}
	
	public List<Alien> getAliens() {
        return aliens;
    }
	
	
	
//	public void fire() {
//		
//		Alien a = new Alien(x + width + 27, y + height / 2);
//		a.setALIEN_ANGLE(angle());
//		a.setALIEN_DIRECTION(direction());
//        aliens.add(a);
//    }
	
	/**
	 * Function to fire an alien
	 */
	public Alien fire() {
		
		Alien a = new Alien(x + width + 27, y + height / 2);
		a.setALIEN_ANGLE(angle());
		a.setALIEN_DIRECTION(direction());
        aliens.add(a);
        return a;
    }
	
	public double angle() {
		Random rand = new Random();

		int randomNum = rand.nextInt((8) + 1);
		randomNum*=5;//to make it multiple of 5
		return randomNum;
	}
	
	public int direction() {
		Random rand = new Random();

		int randomNum = rand.nextInt((8) + 1);
		if (randomNum>4) return -1;
		else return 1;
	}

}
