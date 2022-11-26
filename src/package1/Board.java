package package1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javax.swing.JPanel;
import javax.swing.Timer;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Board extends JPanel implements ActionListener {
	private Timer timer;
    private Shooter shooter;
    private Spaceship spaceship;
    private Collision collision;
    private double totalScore = 0;
    private double maxScore = 0;// to be integrated with database
    private int nbCollisions = 0;

    private boolean ingame;
    private final int ICRAFT_X = 280;
    private final int ICRAFT_Y = 540;
    private final int B_WIDTH = 600;
    private final int B_HEIGHT = 600;
    private final int DELAY = 50;
    private  int FIRE_DELAY=5000;
    private final int ISPACESHIP_X = 280;
    private final int ISPACESHIP_Y = 420;
    private int timeLeft = 60;
    JButton btnStart;
    private int ALIEN_FIRE_DELAY = 3000;
    private Alien currentAlien;
    private int previousPosition = 0;
   

    public Board() {
 
        initBoard();
    }
    
    /**
	 * Function to initialize board
	 */
    private void initBoard() {
    	
        //addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        ingame = true;

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        shooter = new Shooter(ICRAFT_X, ICRAFT_Y);
        spaceship = new Spaceship(ISPACESHIP_X, ISPACESHIP_Y);
        collision = new Collision(300,300);
        timer = new Timer(DELAY, this);
        
        ActionListener alist=new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                timeLeft-=1;                
                if (timeLeft<55 && timeLeft%2 == 0  && currentAlien.getX() <500 && currentAlien.getX() > 100) {                	
                	currentAlien.fire(currentAlien);
                }
                
            }
        };
        Timer time = new Timer(1000,alist);
        
        
        

        
        
        
        ActionListener al=new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                currentAlien = spaceship.fire();//returns the alien fired
                shooter.fire(currentAlien);
                //System.out.println(timeLeft);

                //System.out.println("we are firing aliens");
                
            }
        };
        
        if (Start.defaultlevel=="level2") {
        	FIRE_DELAY=4000;
        }
        if (Start.defaultlevel=="level3") {
        	FIRE_DELAY=1800;
        }
        Timer mytimer=new Timer(FIRE_DELAY,al);//create the timer which calls the actionperformed method for every 2000 millisecond(1 second=1000 millisecond)
        System.out.println(FIRE_DELAY);
        
        
//        
//        ActionListener alAlienShot=new ActionListener() {
//            public void actionPerformed(ActionEvent ae) {
//                
//            	currentAlien.fire(currentAlien);
//                //System.out.println("we are firing aliens");
//                
//            }
//        };
//        
//        Timer alienShotTimer = new Timer(ALIEN_FIRE_DELAY, alAlienShot);
        
        
        
        

        time.start();
        timer.start(); 
		mytimer.start();//start the task
		
		//alienShotTimer.start();
		
		
		
        setLayout(null);
        


    }

    /**
	 * Function to show graphics
	 */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (ingame) {

            drawObjects(g);

        }
        else {
        	try {
				drawGameOver(g);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        Toolkit.getDefaultToolkit().sync();
    }
    /**
	 * Function to draw end of game
	 */
    private void drawGameOver(Graphics g) throws SQLException {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2);
        
        try {
		    File wavFile = new File("sounds/"+"bravo.wav");
		    Clip clip = AudioSystem.getClip();
		    clip.open(AudioSystem.getAudioInputStream(wavFile));
		    clip.start();
		} catch (Exception e1) {
		    System.out.println(e1);
		}
		 Start frame = new Start((int)nbCollisions*100/shooter.getNbHits(),UserLogin.username);
         frame.setVisible(true);
    }

    /**
   	 * Function to draw shooter and missiles
   	 * it also draws a BOOM in case of a crash between a missile and alien or missile and alien shot
   	 */
    private void drawObjects(Graphics g) {
        if (shooter.isVisible()) {
            g.drawImage(shooter.getImage(), shooter.getX(), shooter.getY(),
                    this);
        }

        List<Missile> ms = shooter.getMissiles();

        for (Missile missile : ms) {
            if (missile.isVisible()) {
                g.drawImage(missile.getImage(), missile.getX(), 
                        missile.getY(), this);
            }
        }
        
        if (spaceship.isVisible()) {
            g.drawImage(spaceship.getImage(), spaceship.getX(), spaceship.getY(),
                    this);
        }
        
        List<Alien> al = spaceship.getAliens();

        for (Alien alien : al) {
            if (alien.isVisible()) {
                g.drawImage(alien.getImage(), alien.getX(), 
                        alien.getY(), this);
                for (AlienShot as: alien.getAlienShots()) {
                	if (as.isVisible()) {
                		g.drawImage(as.getImage(), as.getX(), 
                            as.getY(), this);
                	}
                	else if (!as.isVisible() && as.isCollision() && as.isAlive()) {
                    	try {
        				    File wavFile = new File("sounds/"+"collision.wav");
        				    Clip clip = AudioSystem.getClip();
        				    clip.open(AudioSystem.getAudioInputStream(wavFile));
        				    clip.start();
        				} catch (Exception e1) {
        				    System.out.println(e1);
        				}
                    	as.setAlive(false);
                    	//g.drawImage(collision.getImage(), alien.getX(), alien.getY(), this);
                	}
                }
                
            }
            else if (!alien.isVisible() && alien.isCollision()) {
//            	System.out.println("drawing collision");
//            	System.out.println("alien:"+alien.getX());  
            	try {
				    File wavFile = new File("sounds/"+"collision.wav");
				    Clip clip = AudioSystem.getClip();
				    clip.open(AudioSystem.getAudioInputStream(wavFile));
				    clip.start();
				} catch (Exception e1) {
				    System.out.println(e1);
				}
            	g.drawImage(collision.getImage(), alien.getX(), 
                        alien.getY(), this);
            	
            	//alien.setCollision(false);
            }
        }

        g.setColor(Color.WHITE);
        g.drawString("Time left: " + getTime(), 5, 15);
        g.drawString("Total Score: " + nbCollisions + "/" + shooter.getNbHits(), 5, 30);
    }
    

    
    public int getTime() {
    	if (timeLeft<=0) {
    		ingame=false;
    		return 0;
    		
    	}
    	return timeLeft;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        inGame();

        updateShooter();
        updateMissiles();
        updateAliens();
        checkCollisions();
        repaint();
    }
    
    /**
	 * Function to check for collisions
	 * the function checks if there is a collision between an alien and a missile with a proximity of 15 px
	 * the function checks if there is a collision between an alien shot and a missile with a proximity of 15 px
	 */
    public void checkCollisions() {

        List<Alien> aliens = spaceship.getAliens();
        List<Missile> missiles = shooter.getMissiles();

        for (Missile m : missiles) {

//            Rectangle r1 = m.getBounds();
//            System.out.println("r1="+m.x+" "+m.y);

            for (Alien alien : aliens) {
            	boolean cont = true;
//                Rectangle r2 = alien.getBounds();
//                System.out.println("r2="+alien.x+" "+alien.y);
//                System.out.println(r1.intersects(r2));
            	//check for collision
            	
            	for (AlienShot as: alien.getAlienShots()) {
            		if (as.x<=(m.x+15) && as.x>=(m.x-15) && as.y<=(m.y+15) && as.y>=(m.y-15)) {
            			System.out.println("collision made with an alien shot");
            			m.setVisible(false);
            			as.setVisible(false);
            			as.setCollision(true);
            			cont = false;
            		}
            	}
            	
            	
                if (alien.x<=(m.x+15) && alien.x>=(m.x-15) && alien.y<=(m.y+15) && alien.y>=(m.y-15) && cont) {
                    System.out.println("collision made with an alien");
                    m.setVisible(false);
                    alien.setVisible(false);
                    alien.setCollision(true);
                    nbCollisions++;
                }
            }
        }
    }

    private void inGame() {
        if (!ingame) {
            timer.stop();
        }
    }
    
    /**
   	 * Function to move shooter
   	 */
    private void updateShooter() {

        if (shooter.isVisible()) {
            
            //shooter.move();
        }
    }
    
    /**
   	 * Function to move missiles
   	 */
    private void updateMissiles() {
    
        List<Missile> ms = shooter.getMissiles();

        for (int i = 0; i < ms.size(); i++) {

            Missile m = ms.get(i);

            if (m.isVisible()) {
                m.move();
            } else {
                ms.remove(i);
            }
        }
    	
    }
    /**
	 * Function to update aliens positions
	 */
    private void updateAliens() {
        
    	List<Alien> al = spaceship.getAliens();

        for (int i = 0; i < al.size(); i++) {

            Alien a = al.get(i);
//            List<AlienShot> alienShots = a.getAlienShots();
//            
//            for (int j = 0 ; j < alienShots.size() ;i ++) {
//            	AlienShot as = alienShots.get(j);
//
//            	if (as.isVisible()) {
//            		as.move();
//            	}
//            	else {
//            		alienShots.remove(j);
//            	}
//            }
            if (a.isVisible()) {
                a.move();
            }
            else if (a.getCounter()>0) {
            	a.setCounter(a.getCounter()-1);
            }
            else {
                al.remove(i);
            }
        }
    	
    }
    



//    private class TAdapter extends KeyAdapter {
//
//        @Override
//        public void keyReleased(KeyEvent e) {
//            shooter.keyReleased(e);
//        }
//
//        @Override
//        public void keyPressed(KeyEvent e) {
//        	shooter.keyPressed(e);
//            System.out.println("pressed");
//           
//        }
//    }
}
