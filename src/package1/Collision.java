package package1;

public class Collision extends Sprite {

	public Collision(int x, int y) {
		super(x, y);
		initCollision();
	}

	private void initCollision() {
		loadImage("pictures/explosion.png");
        getImageDimensions();
	}

}
