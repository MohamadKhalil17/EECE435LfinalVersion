package package1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Start extends JFrame {
	static int score;
	int highscore;
	private static String name;
	static public String defaultlevel="level1";

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start frame = new Start(score,name);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Start(int score,String userName) throws SQLException {
		Start.name=userName;
		this.score=score;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 624, 516);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setForeground(new Color(0, 255, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 468, 10, 10);
		contentPane.add(panel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(new Color(90, 237, 193));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 30));
 		comboBox.setModel(new DefaultComboBoxModel(new String[] {"level1", "level2", "level3"}));
 		comboBox.setBounds(225, 185, 168, 63);
 		contentPane.add(comboBox);
 		
		JButton btnNewButton = new JButton("START");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(90, 237, 193));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				defaultlevel = (String) comboBox.getItemAt(comboBox.getSelectedIndex());
				System.out.println(defaultlevel);
				Game2 frame = new Game2();
                frame.setVisible(true);
                
				
			}
		});
		btnNewButton.setBounds(69, 349, 216, 31);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Quit");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1.setBackground(new Color(255, 0, 0));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(332, 349, 216, 31);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setForeground(new Color(90, 237, 193));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setText(String.valueOf(score));
		lblNewLabel.setBounds(167, 102, 71, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Score");
		lblNewLabel_1.setForeground(new Color(90, 237, 193));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setBounds(157, 61, 108, 31);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("HighScore");
		lblNewLabel_2.setForeground(new Color(90, 237, 193));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_2.setBounds(310, 51, 185, 50);
		contentPane.add(lblNewLabel_2);
		
		
		boolean change= false;
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new FileReader("database/Database.txt"));
			//BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("package1/Database.txt")));
			String s="";
			while ((s=br.readLine())!=null) {
				List<String> data = Arrays.asList(s.split(","));
    			if (data.get(0).equals(userName)) {
    				highscore=Integer.parseInt(data.get(2));
    				if (score>(Integer.parseInt(data.get(2)))){
    					change=true;
    					String row= data.get(0) + "," + data.get(1)+","+ score + "\n";
    					sb.append(row);
    					highscore = score;
    					
    				}
    			}else {
    				sb.append(s);
    				sb.append("\n");
    			}
			}
			if (change) {
			File f = new File("database/Database.txt");
			PrintWriter pw = new PrintWriter(new FileOutputStream(f,false));
			System.out.print(sb);
			pw.write(sb.toString());
			pw.close();}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
//		 Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/login_info",
//                 "root", "rootpassword123");
//
//             PreparedStatement st = (PreparedStatement) connection
//                 .prepareStatement("SELECT highscore FROM player WHERE name=?");
//            st.setString(1,name);
//
//        // Condition check
//             ResultSet rs = st.executeQuery();
//             if (rs.next()) {
//            	 highscore = rs.getInt("highscore");
//            	 if (score>=highscore) {
//            		 PreparedStatement st2 = (PreparedStatement) connection
//                             .prepareStatement("update player set highscore=? where name =?");
//            		 st2.setLong(1,score);
//            		 st2.setString(2,name);
//
//                        
//                        boolean rs2 = st2.execute();
//                       highscore=score;
//            	 }
//            	 
//             }
            
         JLabel lblNewLabel_3 = new JLabel("New label");
         lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 30));
         lblNewLabel_3.setForeground(new Color(90, 237, 193));
         lblNewLabel_3.setText(String.valueOf(highscore));
 		lblNewLabel_3.setBounds(350, 99, 80, 37);
 		contentPane.add(lblNewLabel_3);  
 		
 		JLabel lblNewLabel_4 = new JLabel("/100");
 		lblNewLabel_4.setForeground(new Color(90, 237, 193));
 		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 30));
 		lblNewLabel_4.setBounds(398, 99, 101, 37);
 		contentPane.add(lblNewLabel_4);
 		
 		JLabel lblNewLabel_4_1 = new JLabel("/100");
 		lblNewLabel_4_1.setForeground(new Color(90, 237, 193));
 		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 30));
 		lblNewLabel_4_1.setBounds(198, 99, 87, 37);
 		contentPane.add(lblNewLabel_4_1);
 		
 		JLabel lblNewLabel_5 = new JLabel("New label");
 		lblNewLabel_5.setForeground(new Color(90, 237, 193));
 		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 30));
 		lblNewLabel_5.setBounds(119, 8, 108, 31);
 		lblNewLabel_5.setText(String.valueOf(UserLogin.username));
 		contentPane.add(lblNewLabel_5);
 		
 		JLabel lblNewLabel_6 = new JLabel("User");
 		lblNewLabel_6.setForeground(new Color(90, 237, 193));
 		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 30));
 		lblNewLabel_6.setBounds(10, 10, 79, 31);
 		contentPane.add(lblNewLabel_6);
 		
 
	}
}
