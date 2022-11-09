package admin;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
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

public class CAP extends JFrame {

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
	String sSQL,sSQL1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CAP frame = new CAP();
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
	public CAP() {
		setTitle("Change Admin Password");
		setResizable(false);
	
		setBounds(200, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 205, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOldPassword = new JLabel("CHANGE ADMIN PASSWORD");
		lblOldPassword.setForeground(Color.BLACK);
		lblOldPassword.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblOldPassword.setBounds(39, 20, 400, 42);
		contentPane.add(lblOldPassword);
		
		JLabel lblCurrentPassword = new JLabel("Current PassWord");
		lblCurrentPassword.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCurrentPassword.setBounds(35, 150, 143, 20);
		contentPane.add(lblCurrentPassword);
		
		JLabel lblNewPassword = new JLabel("New PassWord");
		lblNewPassword.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewPassword.setBounds(35, 200, 143, 20);
		contentPane.add(lblNewPassword);
		
		JLabel lblConfir = new JLabel("Confirm PassWord");
		lblConfir.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblConfir.setBounds(35, 250, 143, 20);
		contentPane.add(lblConfir);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				PerForm();
			}
		});
		btnSave.setBounds(70, 310, 100, 25);
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
		btnClose.setBounds(250, 310, 100, 25);
		contentPane.add(btnClose);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(225, 200, 160, 20);
		contentPane.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(225, 250, 160, 20);
		contentPane.add(passwordField_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(225, 150, 160, 20);
		contentPane.add(passwordField);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblUserName.setBounds(35, 100, 135, 19);
		contentPane.add(lblUserName);
		
		textField = new JTextField();
		textField.setBounds(222, 100, 163, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jackdb","root" ,"root");
			JOptionPane.showMessageDialog(this,"database connected");
		}
		catch(ClassNotFoundException ex)
		{

			JOptionPane.showMessageDialog(null,ex.getMessage(), "Could not find the database driver" ,JOptionPane.PLAIN_MESSAGE);
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage(),"Could not connect to the database",JOptionPane.INFORMATION_MESSAGE);
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
			JOptionPane.showMessageDialog(this,"Please enter User Name");
			
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
					sSQL1 = "select * from adminlog where adminname='"+user+"' and password = '"+oldpassword+"';";
					ResultSet rs = stmt.executeQuery(sSQL1);
					boolean record = rs.next();
					if(record)
					{
						try
						{
							
							sSQL = "update adminlog set password='"+newpassword+"' where username ='"+user+"';";
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
						JOptionPane.showMessageDialog(this,"UserName and current Password is wrong");
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
