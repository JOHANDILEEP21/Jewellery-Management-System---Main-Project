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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Goldrate extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
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
					Goldrate frame = new Goldrate();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Goldrate() {
		setTitle("CURRENT RATE");
		setResizable(false);
		
		setBounds(200, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 205, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		SimpleDateFormat myformat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = myformat.format(date);
		
		JLabel lblTodayGoldRate = new JLabel("Today Rate details");
		lblTodayGoldRate.setForeground(Color.WHITE);
		lblTodayGoldRate.setFont(new Font("Script MT Bold", Font.PLAIN, 25));
		lblTodayGoldRate.setBounds(150, 25, 201, 31);
		contentPane.add(lblTodayGoldRate);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblDate.setBounds(100, 100, 46, 19);
		contentPane.add(lblDate);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(255, 101, 160, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(today);
		
		JLabel lblGoldRate = new JLabel("Gold Rate (1gram)");
		lblGoldRate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblGoldRate.setBounds(100, 160, 135, 20);
		contentPane.add(lblGoldRate);
		
		textField_1 = new JTextField();
		textField_1.setBounds(255, 160, 160, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				PerForm();
			}
		});
		btnSave.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnSave.setBounds(100, 292, 89, 23);
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
		btnClose.setBounds(269, 292, 89, 23);
		contentPane.add(btnClose);
		
		JLabel lblSilverRate = new JLabel("Silver Rate (1gram)");
		lblSilverRate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSilverRate.setBounds(100, 215, 135, 20);
		contentPane.add(lblSilverRate);
		
		textField_2 = new JTextField();
		textField_2.setBounds(255, 217, 160, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		try
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url,dbuser ,dbpass);
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
		String date       = textField.getText();
		String goldrate   = textField_1.getText();
		String silverrate = textField_2.getText();
		
		if(date.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter date");
			
		}
		else if(goldrate.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Gold Rate");
		}
		else if(silverrate.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Silver Rate");
		}
		else
		{
			try
			{
			
				Statement stmt   = con.createStatement();
				sSQL = "insert into currentrate(GoldRate,SilverRate,Curdate) values('"+goldrate+"','"+silverrate+"','"+date+"');";
				stmt.executeUpdate(sSQL);
				stmt.close();
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				JOptionPane.showMessageDialog(null, "Record Inserted Successfully");
				
			}
			
			catch(SQLException e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,e.getMessage(), "Error",JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}
