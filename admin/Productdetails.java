package admin;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

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

public class Productdetails extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	Connection con = null;
	String driver = "com.mysql.jdbc.Driver";
	String  url = "jdbc:mysql://localhost:3306/jackdb";
	String dbuser = "root";
	String dbpass = "root";
	
	String sSQL,item="";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Productdetails frame = new Productdetails();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Productdetails() {
		
		setTitle("PRODUCT DETAILS");
		setResizable(false);
		setBounds(200, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 205, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProductMast = new JLabel("Product dETAILS");
		lblProductMast.setForeground(Color.WHITE);
		lblProductMast.setBackground(Color.WHITE);
		lblProductMast.setFont(new Font("Lucida Handwriting", Font.BOLD, 30));
		lblProductMast.setBounds(230, 40, 318, 35);
		contentPane.add(lblProductMast);
		
		JLabel lblProductId = new JLabel("Product ID(3XXXX)");
		lblProductId.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblProductId.setBounds(100, 125, 170, 20);
		contentPane.add(lblProductId);
		
		textField = new JTextField();
		textField.setBounds(350, 125, 160, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblProductName.setBounds(100, 180, 110, 20);
		contentPane.add(lblProductName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(350, 180, 160, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblModelName = new JLabel("Model Name");
		lblModelName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblModelName.setBounds(100, 235, 110, 20);
		contentPane.add(lblModelName);
		
		textField_2 = new JTextField();
		textField_2.setBounds(350, 235, 160, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblCatagory = new JLabel("Catagory");
		lblCatagory.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCatagory.setBounds(100, 290, 89, 20);
		contentPane.add(lblCatagory);
		
		textField_3 = new JTextField();
		textField_3.setBounds(350, 290, 160, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblWeight = new JLabel("Weight(gram)");
		lblWeight.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblWeight.setBounds(100, 345, 110, 20);
		contentPane.add(lblWeight);
		
		textField_4 = new JTextField();
		textField_4.setBounds(350, 345, 160, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				RecordSave();
			}
		});
		btnSave.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnSave.setBounds(125, 480, 100, 25);
		contentPane.add(btnSave);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				try {
					con.close();
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
			}
		});
		btnClose.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnClose.setBounds(370, 480, 100, 25);
		contentPane.add(btnClose);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				
			}
		});
		btnClear.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnClear.setBounds(625, 480, 100, 25);
		contentPane.add(btnClear);
		
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RecordFind();
			}
		});
		btnFind.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnFind.setBounds(625, 125, 100, 25);
		contentPane.add(btnFind);
		
		JButton btnAlter = new JButton("Update");
		btnAlter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RecordUpdate();
			}
		});
		btnAlter.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnAlter.setBounds(625, 200, 100, 25);
		contentPane.add(btnAlter);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RecordDelete();
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnDelete.setBounds(625, 270, 100, 25);
		contentPane.add(btnDelete);
		
		JLabel lblRate = new JLabel("Rate");
		lblRate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblRate.setBounds(100, 400, 78, 14);
		contentPane.add(lblRate);
		
		textField_5 = new JTextField();
		textField_5.setBounds(350, 400, 160, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
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
		
		String prodid = textField.getText();
		String prodname = textField_1.getText();
		String modelname = textField_2.getText();
		String catagory = textField_3.getText();
		String weight = textField_4.getText();
		String rate = textField_5.getText();
				
		if(prodid.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Product ID");
			
		}
		else if(prodname.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Product Name");
		}
		else if(modelname.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Model Name");
		}
		else if(catagory.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Catagory");
		}
		else if(weight.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Weight");
		}
		else if(rate.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Rate");
		}
		else
		{
			try
			{
			
				Statement stmt   = con.createStatement();
				sSQL = "insert into product(ProID,ProName,ModelName,Category,Weight,Rate) values('"+prodid+"','"+prodname+"','"+modelname+"','"+catagory+"','"+weight+"','"+rate+"');";
				stmt.executeUpdate(sSQL);
				stmt.close();
				JOptionPane.showMessageDialog(null, "record inserted Successfully");
				
			}
			
			catch(SQLException e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,e.getMessage(), "error",JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		
	}
	
	public void RecordFind()
	{
		String find = textField.getText();
		
		try
		{
		
			Statement stmt   = con.createStatement();
			sSQL = "select * from product where ProID='"+find+"';";
			ResultSet rs = stmt.executeQuery(sSQL);
		
			boolean record = rs.next();
			if(record)
			{		
				String prodname= rs.getString("ProName");
				String modelname = rs.getString("ModelName");
				String catagory= rs.getString("Category");
				String weight= rs.getString("Weight");
				String rate = rs.getString("Rate");
			
				textField_1.setText(prodname);
				textField_2.setText(modelname);
				textField_3.setText(catagory);
				textField_4.setText(weight);
				textField_5.setText(rate);
			}
			else
			{
				
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				
				JOptionPane.showMessageDialog(this,"Not Valid Product ID");
			}
			stmt.close();
		}
		catch(SQLException e)
		{			
			JOptionPane.showMessageDialog(null,e.getMessage(), "error",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void RecordUpdate()
	{
		String prodid = textField.getText();
		String prodname = textField_1.getText();
		String modelname = textField_2.getText();
		String catagory = textField_3.getText();
		String weight = textField_4.getText();
		String rate = textField_5.getText();
				
		if(prodid.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Product ID");
			
		}
		else if(prodname.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Product Name");
		}
		else if(modelname.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Model Name");
		}
		else if(catagory.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Catagory");
		}
		else if(weight.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Weight");
		}
		else if(rate.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Rate");
		}
		else
		{
			try
			{
			
				Statement stmt   = con.createStatement();
				sSQL = "update product set ProName='"+prodname+"',ModelName='"+modelname+"',Category='"+catagory+"',Weight='"+weight+"',Rate='"+rate+"' where ProID ='"+prodid+"' ;";
				stmt.executeUpdate(sSQL);
				stmt.close();
				JOptionPane.showMessageDialog(null, "record Update Successfully");
				
			}
			
			catch(SQLException e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,e.getMessage(), "error",JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
	}
	
	public void RecordDelete()
	{
		
		String find = textField.getText();
		String find2 = textField_2.getText();
		try
		{
		
			Statement stmt   = con.createStatement();
			sSQL = "delete from product where ProID='"+find+"' and ModelName='"+find2+"';";
			stmt.executeUpdate(sSQL);
			
			textField.setText("");
			textField_1.setText("");
			textField_2.setText("");
			textField_3.setText("");
			textField_4.setText("");
			textField_5.setText("");
			
			JOptionPane.showMessageDialog(null, "record Deleted Successfully");
			stmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,e.getMessage(), "error",JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
