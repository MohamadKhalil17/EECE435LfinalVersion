package package1;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


/**
 * 
 * @author Mohamad Khalil Yara Hassan
 * See <a href="https://github.com/MohamadKhalil17">GitHub</a> 
 */

public class UserLogin extends JFrame {
	/**
	 * username String that holds the username
	 */
	public static String username;
	/**
	 * highscore int of highscore with inital value 0
	 */
	public static int highscore=0;
    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JPanel contentPane;
    private JButton btnNewButton_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserLogin frame = new UserLogin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public UserLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 0, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setBackground(new Color(0, 0, 0));
        lblNewLabel.setBounds(423, 13, 273, 93);
        lblNewLabel.setForeground(new Color(90, 237, 193));
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(481, 170, 339, 68);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(481, 286, 339, 68);
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        contentPane.add(passwordField);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(250, 166, 193, 52);
        lblUsername.setBackground(new Color(0, 0, 0));
        lblUsername.setForeground(new Color(90, 237, 193));
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 31));
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(250, 286, 193, 52);
        lblPassword.setForeground(new Color(90, 237, 193));
        lblPassword.setBackground(new Color(0, 0, 0));
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 31));
        contentPane.add(lblPassword);

        btnNewButton = new JButton("Login");
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setBackground(new Color(32, 141, 232));
        btnNewButton.setBounds(658, 385, 162, 73);
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String userName = textField.getText();
                username= userName;
	            String password = passwordField.getText();
	            try {
	            	boolean found = false;
	            	BufferedReader br = new BufferedReader(new FileReader("database/Database.txt"));
	    			//BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("package1/Database.txt")));

	            	String s = "";
	            		while ((s=br.readLine())!=null) {
	            			
	            			List<String> data = Arrays.asList(s.split(","));
	            			System.out.println(data);
	            			for (int i=0;i<3;i++) {
	            				System.out.print(data.get(0).equals(userName)&&data.get(1).equals(password));
	            				
	            				if (data.get(0).equals(userName)&&data.get(1).equals(password)) {
	            					highscore= Integer.parseInt(data.get(2));
	            					Start frame = new Start(0,userName);
	                                frame.setVisible(true);
	                                JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
	                                found = true;
	                                break;
	            					
	            				}
	            				
	            			}
	            			if (!found) {
	            			JOptionPane.showMessageDialog(btnNewButton, "Wrong Username & Password");}
	            		
	            		}} catch(Exception f) {}   
        }});

        contentPane.add(btnNewButton);
        
        btnNewButton_1 = new JButton("Sign Up");
        btnNewButton_1.setForeground(new Color(255, 255, 255));
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton_1.setBackground(new Color(32, 141, 232));
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		SignIn frame = new SignIn();
                frame.setVisible(true);
        	}
        });
        btnNewButton_1.setBounds(481, 385, 162, 73);
        contentPane.add(btnNewButton_1);
    }
}
