package loginForm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import mainForm.Mainframe;

import java.sql.*;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
		
	//private ActionEvent arg0; String action = arg0.getActionCommand();
	
	String username;
	String password;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Login() {
		setResizable(false);
		setTitle("JESUS CALLS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(230, 130, 400, 330);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(200, 153, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel default1 = new JLabel("Default Username and Password is: jack");
		default1.setFont(new Font("agency fb", Font.PLAIN, 14));
		default1.setBounds(50, 260, 200, 33);
		contentPane.add(default1);
		
		JLabel lblUserName = new JLabel("Username");
		lblUserName.setFont(new Font("agency fb", Font.PLAIN, 18));
		lblUserName.setBounds(56, 72, 100, 20);
		contentPane.add(lblUserName);
		
		textField = new JTextField();
		textField.setBounds(217, 72, 124, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("agency fb", Font.PLAIN, 16));
		lblPassword.setBounds(56, 145, 91, 20);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(217, 145, 124, 20);
		contentPane.add(passwordField);
		
		JButton button = new JButton("Login");
		button.setBackground(new Color(0, 153, 153));
		button.setBorder(new EmptyBorder(5,5,5,5));
		button.setFocusPainted(true);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
			String action = arg0.getActionCommand();
			if(action =="Login"){
				Perform();
			}
			}			
		});
		button.setBounds(149, 210, 89, 23);
		contentPane.add(button);
	}
	@SuppressWarnings("deprecation")
	protected void Perform(){
		try{
					Class.forName("com.mysql.jdbc.Driver");
					}
				catch(ClassNotFoundException e){
					
					JOptionPane.showMessageDialog(null,e.getMessage(), "Could not find the database driver" ,JOptionPane.PLAIN_MESSAGE);
				}
				try{
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jackdb", "root", "root");
					Statement stmt   = con.createStatement();
					String sSQL = "SELECT * FROM login  WHERE USERNAME ='"+ textField.getText() +"'AND PASSWORD='"+ passwordField.getText()+"'";
					stmt.execute(sSQL);
					ResultSet rs = stmt.executeQuery(sSQL);
					boolean recordfound=rs.next();
					if(recordfound){
						JOptionPane.showMessageDialog(null, "Login Successfull");
						dispose();
						Mainframe ask = new Mainframe();
						ask.setVisible(true);
						ask.show();	
					}
					else{
						JOptionPane.showMessageDialog(null, "The Username and Password is false");	
						textField.setText("");
						passwordField.setText("");
						}
					con.close();
					}
				catch(SQLException e){
					JOptionPane.showMessageDialog(null,e.getMessage(),"Could not connect to the database",JOptionPane.INFORMATION_MESSAGE);
				}
			}
}
		
