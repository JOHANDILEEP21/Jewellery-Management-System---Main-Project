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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeDetails extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	
	Connection con = null;
	String driver = "com.mysql.jdbc.Driver";
	String  url = "jdbc:mysql://localhost:3306/jackdb";
	String dbuser = "root";
	String dbpass = "root";
	
	String sSQL;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeDetails frame = new EmployeeDetails();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EmployeeDetails() {
		setTitle("EMPLOYEE DETAILS");
		setResizable(false);
		
		setBounds(200, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 205, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblEmployeeDetails = new JLabel("Employee Details");
		lblEmployeeDetails.setForeground(Color.WHITE);
		lblEmployeeDetails.setFont(new Font("Monotype Corsiva", Font.BOLD, 35));
		lblEmployeeDetails.setBounds(270, 35, 258, 36);
		contentPane.add(lblEmployeeDetails);
		
		JLabel lblEmployeeId = new JLabel("Employee ID(1XXX)");
		lblEmployeeId.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblEmployeeId.setBounds(125, 125, 180, 20);
		contentPane.add(lblEmployeeId);
		
		JLabel lblEmpoyeeName = new JLabel("Employee Name");
		lblEmpoyeeName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblEmpoyeeName.setBounds(125, 180, 125, 20);
		contentPane.add(lblEmpoyeeName);
		
		JLabel lblMobileNo = new JLabel("Mobile NO");
		lblMobileNo.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMobileNo.setBounds(125, 290, 125, 20);
		contentPane.add(lblMobileNo);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblAddress.setBounds(125, 345, 125, 20);
		contentPane.add(lblAddress);
		
		JLabel lblSalary = new JLabel("Salary");
		lblSalary.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSalary.setBounds(125, 455, 125, 20);
		contentPane.add(lblSalary);
		
		JLabel lblDateOfJoining = new JLabel("Date Of Joining (yyyy/mm/dd) ");
		lblDateOfJoining.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblDateOfJoining.setBounds(125, 400, 227, 20);
		contentPane.add(lblDateOfJoining);
		
		JLabel lblDateOfBirth = new JLabel("Date Of Birth (yyyy/mm/dd)");
		lblDateOfBirth.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblDateOfBirth.setBounds(125, 235, 215, 20);
		contentPane.add(lblDateOfBirth);
		
		textField = new JTextField();
		textField.setBounds(350, 125, 160, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(350, 180, 160, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(350, 235, 160, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(350, 290, 160, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(350, 345, 160, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setText(" ");
		textField_5.setBounds(350, 400, 160, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(350, 455, 160, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RecordSave();			
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton.setBounds(605, 150, 100, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Close");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				try {
					con.close();
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton_1.setBounds(605, 205, 100, 25);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Clear");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton_2.setBounds(605, 260, 100, 25);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Find");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RecordFind();
			}
		});
		btnNewButton_3.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton_3.setBounds(605, 365, 100, 25);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Update");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RecordUpdate();
			}
		});
		btnNewButton_4.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton_4.setBounds(605, 420, 100, 25);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Delete");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RecordDelete();
			}
		});
		btnNewButton_5.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton_5.setBounds(605, 475, 100, 25);
		contentPane.add(btnNewButton_5);
		
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
	
	public void RecordSave()
	{
		
		String empid = textField.getText();
		String empname = textField_1.getText();
		String dateofbirth = textField_2.getText();
		String mobno = textField_3.getText();
		String address = textField_4.getText();
		String dateofjoining = textField_5.getText();
		String salary = textField_6.getText();
				
		if(empid.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Employee ID");
			
		}
		else if(empname.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Employee Name");
		}
		else if(dateofbirth.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Date Of Birth ");
		}
		else if(mobno.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Mobile Number");
		}
		else if(address.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Address");
		}
		else if(dateofjoining.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Date Of Joining");
		}
		else if(salary.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Salary");
		}
		else if((mobno.length()>9)&&(mobno.length()<11))
		{
			try
			{
			
				Statement stmt   = con.createStatement();
				sSQL = "insert into empdetails(Empid,EmpName,DateOfBirth,mobno,Address,Doj,Salary) values('"+empid+"','"+empname+"','"+dateofbirth+"','"+mobno+"','"+address+"','"+dateofjoining+"','"+salary+"');";
				stmt.executeUpdate(sSQL);
				stmt.close();
				JOptionPane.showMessageDialog(null, "Record Inserted Successfully");
				
			}
			
			catch(SQLException e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR",JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		else
		{
			JOptionPane.showMessageDialog(this,"Please enter correct number");
		}
	}
	
	public void RecordFind()
	{
		
		String find = textField.getText();
		
		try
		{
		
			Statement stmt   = con.createStatement();
			sSQL = "select * from empdetails where EmpID='"+find+"';";
			ResultSet rs = stmt.executeQuery(sSQL);
		
			boolean record = rs.next();
			if(record)
			{		
				String empname= rs.getString("EmpName");
				String dateofbirth = rs.getString("DateOfBirth");
				String mobno= rs.getString("MobNO");
				String address= rs.getString("Address");
				String dateofjoining = rs.getString("DOJ");
				String salary = rs.getString("Salary");
			
				textField_1.setText(empname);
				textField_2.setText(dateofbirth);
				textField_3.setText(mobno);
				textField_4.setText(address);
				textField_5.setText(dateofjoining);
				textField_6.setText(salary);
			}
			else
			{
				
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");
				
				JOptionPane.showMessageDialog(this,"Not Valid Employee ID");
			}
			stmt.close();
		}
		catch(SQLException e)
		{			
			JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void RecordUpdate()
	{
		String empid = textField.getText();
		String empname = textField_1.getText();
		String dateofbirth = textField_2.getText();
		String mobno = textField_3.getText();
		String address = textField_4.getText();
		String dateofjoining = textField_5.getText();
		String salary = textField_6.getText();
				
		if(empid.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Employee ID");
			
		}
		else if(empname.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Employee Name");
		}
		else if(dateofbirth.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Date Of Birth ");
		}
		else if(mobno.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Mobile Number");
		}
		else if(address.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Address");
		}
		else if(dateofjoining.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Date Of Joining");
		}
		else if(salary.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Salary");
		}
		else if((mobno.length()>9)&&(mobno.length()<11))
		{
			try
			{
			
				Statement stmt   = con.createStatement();
				sSQL = "update empdetails set EmpName='"+empname+"',DateOfBirth='"+dateofbirth+"',Mobno='"+mobno+"',Address='"+address+"',Doj='"+dateofjoining+"',Salary='"+salary+"' where EmpID = '"+empid+"';";
				stmt.executeUpdate(sSQL);
				stmt.close();
				JOptionPane.showMessageDialog(null, "Record Update Successfully");
				
			}
			
			catch(SQLException e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR",JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		else
		{
			JOptionPane.showMessageDialog(this,"Please enter correct number");
		}
	}
	
	public void RecordDelete()
	{
		String find = textField.getText();
		String find2 = textField_1.getText();
		try
		{
		
			Statement stmt   = con.createStatement();
			sSQL = "delete from empdetails where EmpID='"+find+"' and EmpName='"+find2+"';";
			stmt.executeUpdate(sSQL);
			
			textField.setText("");
			textField_1.setText("");
			textField_2.setText("");
			textField_3.setText("");
			textField_4.setText("");
			textField_5.setText("");
			textField_6.setText("");
			
			JOptionPane.showMessageDialog(null, "Record Deleted Successfully");
			stmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR",JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
