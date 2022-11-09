package adminLogin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import admin.Admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdLogin extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JPasswordField passwordField;
	
	Connection con = null;

	private String sSQL;

	public static void main(String[] args) {
		try {
			AdLogin dialog = new AdLogin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AdLogin() {
		setTitle("ADMIN LOGIN");
		setBounds(200, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(173, 216, 230));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel default1 = new JLabel("Default Username and Password is: jack");
		default1.setFont(new Font("agency fb", Font.PLAIN, 14));
		default1.setBounds(50, 190, 200, 33);
		contentPanel.add(default1);
		
		JLabel lblUserName = new JLabel("User Name ");
		lblUserName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblUserName.setBounds(72, 64, 81, 20);
		contentPanel.add(lblUserName);
		
		textField = new JTextField();
		textField.setBounds(226, 64, 136, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPassword.setBounds(72, 151, 81, 14);
		contentPanel.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(226, 148, 136, 20);
		contentPanel.add(passwordField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(173, 216, 230));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						Perform();
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					
					}
				});
				
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void Perform()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException ex)
		{

			JOptionPane.showMessageDialog(null,ex.getMessage(), "Could not find the database driver" ,JOptionPane.PLAIN_MESSAGE);
		}
		try
		{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jackdb","root" ,"root");
			Statement stmt   = con.createStatement();
			sSQL = "SELECT * FROM adminlog WHERE adminname ='"+ textField.getText() +"'AND PASSWORD='"+ passwordField.getText()+"'";
			
			stmt.execute(sSQL);
			ResultSet rs = stmt.getResultSet();
			boolean recordfound = rs.next();
			
			if(recordfound)
			{
				dispose();
				Admin ask = new Admin();
				ask.setVisible(true);
				ask.show();
				
			}
			
			else
			{
				JOptionPane.showMessageDialog(null, "The Username and password is false");	
				textField.setText("");
				passwordField.setText("");
			}
			con.close();
			
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage(),"Could not connect to the database",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
}
