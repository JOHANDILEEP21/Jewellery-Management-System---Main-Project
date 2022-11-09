package admin;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class CP extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private JTextField textField;
	
	Connection con = null;
	String driver = "com.mysql.jdbc.Driver";
	String  url = "jdbc:mysql://localhost:3306/jackdb";
	String dbuser = "root";
	String dbpass = "root";
	
	String sSQL,sSQL1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CP frame = new CP();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CP() {
		setTitle("CHANGE USER PASSWORD");
		setResizable(false);
		
		setBounds(200, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 205, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblChangeYourUser = new JLabel("Change Your User PassWord");
		lblChangeYourUser.setForeground(Color.WHITE);
		lblChangeYourUser.setFont(new Font("Script MT Bold", Font.BOLD, 25));
		lblChangeYourUser.setBounds(60, 20, 331, 31);
		contentPane.add(lblChangeYourUser);
		
		JLabel lblCurrentPassword = new JLabel("Current PassWord");
		lblCurrentPassword.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCurrentPassword.setBounds(35, 150, 144, 14);
		contentPane.add(lblCurrentPassword);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewPassword.setBounds(35, 200, 144, 14);
		contentPane.add(lblNewPassword);
		
		JLabel lblOldPassword = new JLabel("Old Password");
		lblOldPassword.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblOldPassword.setBounds(35, 250, 144, 14);
		contentPane.add(lblOldPassword);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PerForm();
			}
		});
		btnSave.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnSave.setBounds(60, 310, 100, 25);
		contentPane.add(btnSave);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				try {
					con.close();
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
			}
		});
		btnClose.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnClose.setBounds(248, 310, 100, 25);
		contentPane.add(btnClose);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(235, 200, 160, 20);
		contentPane.add(passwordField_1);
		
		passwordField_2= new JPasswordField();
		passwordField_2.setBounds(235, 250, 160, 20);
		contentPane.add(passwordField_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(234, 150, 160, 20);
		contentPane.add(passwordField);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblUserName.setBounds(35, 100, 144, 19);
		contentPane.add(lblUserName);
		
		textField = new JTextField();
		textField.setBounds(235, 100, 160, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		try
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url,dbuser ,dbpass);
			JOptionPane.showMessageDialog(this,"Database Connected");
		}
		catch(ClassNotFoundException ex)
		{

			JOptionPane.showMessageDialog(null,ex.getMessage(), "Could not find the Database Driver" ,JOptionPane.PLAIN_MESSAGE);
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage(),"Could not connect to the Database",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void PerForm()
	{
		String user = textField.getText();
		@SuppressWarnings("deprecation")
		String oldpassword   = passwordField.getText();
		@SuppressWarnings("deprecation")
		String newpassword   = passwordField_1.getText();
		@SuppressWarnings("deprecation")
		String retypenewpass = passwordField_2.getText();
		
		if(user.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Username");
			
		}
		else if(oldpassword.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Old Password");
		}
		else if(newpassword.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter New Password");
		}
		else if(retypenewpass.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Retype New Password");
		}
		else
		{
			if(newpassword.equals(retypenewpass))
			{
				
				try
				{
					Statement stmt   = con.createStatement();
					sSQL1 = "select * from userlog where username='"+user+"' and password = '"+oldpassword+"';";
					ResultSet rs = stmt.executeQuery(sSQL1);
					boolean record = rs.next();
					if(record)
					{
						try
						{
							
							sSQL = "update userlog set password='"+newpassword+"' where username ='"+user+"';";
							stmt.executeUpdate(sSQL);
							stmt.close();
							textField.setText("");
							passwordField.setText("");
							passwordField_1.setText("");
							passwordField_2.setText("");
							JOptionPane.showMessageDialog(this,"Password Changed Successfully");
						}
						catch(SQLException e)
						{
							e.printStackTrace();
							JOptionPane.showMessageDialog(null,e.getMessage(), "error",JOptionPane.INFORMATION_MESSAGE);
						}

					}
					else 
					{
						JOptionPane.showMessageDialog(this,"Username and Current Password is wrong");
					}
				}
				catch(SQLException ez)
				{
					ez.printStackTrace();
					JOptionPane.showMessageDialog(null,ez.getMessage(), "error",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Please Enter Correctly New and Retype Password");
				passwordField_1.setText("");
				passwordField_2.setText("");
			}
		}
		
	}
	
}
