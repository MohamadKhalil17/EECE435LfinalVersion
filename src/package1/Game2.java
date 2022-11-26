package package1;

import java.awt.EventQueue;
import java.sql.Connection;

import javax.swing.JFrame;

public class Game2 extends JFrame {
    
	/**
	 * constructor of the game
	 */
	public Game2() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new Board());
        
        setResizable(false);
        pack();
        
        setTitle("Shooting Flying Objects");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            Game2 frame = new Game2();
            frame.setVisible(true);
        });
    }
}