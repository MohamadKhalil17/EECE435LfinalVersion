package package1;
/**
 * sprite class (parent)
 * @author Mohamad Khalil
 * @author Yara Hassan
 * See <a href="https://github.com/MohamadKhalil17">GitHub</a>
 * 
 */
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Sprite {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible;
    protected Image image;
    
    
    /**
   	 * Constructor for sprite class
   	 * @param integer x for location on x axis
   	 * @param integer y for location on y axis
   	 */
    public Sprite(int x, int y) {

        this.x = x;
        this.y = y;
        visible = true;
    }

    protected void getImageDimensions() {

        width = image.getWidth(null);
        height = image.getHeight(null);
    }
    /**
     * 
     * @param imageName String name of image
     * loads the image into the game
     */
    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        if (imageName == "pictures/shooter.png" || imageName == "pictures/spaceship.png" || imageName == "pictures/explosion.png") {
        	image = ii.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        }
        else if (imageName == "pictures/alien.png") {
        	image = ii.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        }
        else if (imageName == "pictures/alienShot.png") {
        	image = ii.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        }
        else {
        	image = ii.getImage().getScaledInstance(10, 10, Image.SCALE_DEFAULT);
        }
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
