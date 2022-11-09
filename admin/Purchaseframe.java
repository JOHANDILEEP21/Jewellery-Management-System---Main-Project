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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

public class Purchaseframe extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	
	Connection con = null;
	String driver = "com.mysql.jdbc.Driver";
	String  url = "jdbc:mysql://localhost:3306/jackdb";
	String dbuser = "root";
	String dbpass = "root";
	
	String sSQL,sSQL1,sSQL2,sSQL3,sSQL4,sSQL5;
	String supid1="",prodid1="";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Purchaseframe frame = new Purchaseframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Purchaseframe() {
		setResizable(false);
		setTitle("PURCHASE ");
	
		setBounds(200, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 205, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		SimpleDateFormat myformat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = myformat.format(date);
		
		JLabel lblPurchaseEntry = new JLabel("PURCHASE  ENTRY");
		lblPurchaseEntry.setForeground(new Color(255, 255, 255));
		lblPurchaseEntry.setFont(new Font("Lucida Handwriting", Font.BOLD, 30));
		lblPurchaseEntry.setBounds(225, 35, 340, 35);
		contentPane.add(lblPurchaseEntry);
		
		JLabel lblCustomerId = new JLabel("Supplier ID");
		lblCustomerId.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCustomerId.setBounds(200, 160, 125, 20);
		contentPane.add(lblCustomerId);
		
		Vector<String> item = new Vector<String>();
		final DefaultComboBoxModel<String> model=new DefaultComboBoxModel<String>(item);
		comboBox = new JComboBox<String>(model);
		comboBox.setBounds(355, 160, 160, 20);
		contentPane.add(comboBox);
		comboBox.addItem("Select");
		comboBox.addActionListener(this);
		
		JLabel lblProductId = new JLabel("Product ID");
		lblProductId.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblProductId.setBounds(200, 260, 125, 20);
		contentPane.add(lblProductId);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblDate.setBounds(520, 100, 50, 20);
		contentPane.add(lblDate);
		
		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblProductName.setBounds(200, 310, 125, 20);
		contentPane.add(lblProductName);
		
		JLabel lblProductCatagory = new JLabel("Product Catagory");
		lblProductCatagory.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblProductCatagory.setBounds(200, 360, 140, 20);
		contentPane.add(lblProductCatagory);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblAmount.setBounds(200, 510, 125, 20);
		contentPane.add(lblAmount);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnSave.setBounds(610, 190, 100, 25);
		contentPane.add(btnSave);
		btnSave.addActionListener(this);
		
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
		btnClose.setBounds(610, 285, 100, 25);
		contentPane.add(btnClose);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				comboBox.setSelectedIndex(0);
				textField_2.setText("");
				comboBox_1.setSelectedIndex(0);
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");
				textField_7.setText("");
				
			}
		});
		btnClear.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnClear.setBounds(610, 455, 100, 25);
		contentPane.add(btnClear);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblQuantity.setBounds(200, 460, 125, 20);
		contentPane.add(lblQuantity);
		
		Vector<String> item1 = new Vector<String>();
		DefaultComboBoxModel<String> model1=new DefaultComboBoxModel<String>(item1);
		comboBox_1 = new JComboBox<String>(model1);
		comboBox_1.setBounds(355, 260, 160, 20);
		contentPane.add(comboBox_1);
		comboBox_1.addItem("Select");
		comboBox_1.addActionListener(this);
	
		JLabel lblRate = new JLabel("Rate");
		lblRate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblRate.setBounds(200, 410, 125, 14);
		contentPane.add(lblRate);
		
		JLabel lblSupplierName = new JLabel("Supplier Name");
		lblSupplierName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSupplierName.setBounds(200, 210, 125, 20);
		contentPane.add(lblSupplierName);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(580, 100, 130, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(today);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(355, 210, 160, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(355, 310, 160, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(355, 360, 160, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setBounds(355, 410, 160, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(355, 460, 160, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setBounds(355, 510, 160, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnCalculate.setBounds(610, 375, 100, 25);
		contentPane.add(btnCalculate);
		btnCalculate.addActionListener(this);
		
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
			JOptionPane.showMessageDialog(null,e.getMessage(), "Error",JOptionPane.INFORMATION_MESSAGE);
		}
		
		try
		{
			
			Statement stmt   = con.createStatement();
			
			sSQL = "select SupID from supplier";
			ResultSet rs = stmt.executeQuery(sSQL);
			while(rs.next())
			{		

				comboBox.addItem(rs.getString("supid"));
				
			}
			
			sSQL1 = "select proid from product;";
			ResultSet rs1 = stmt.executeQuery(sSQL1);
			while(rs1.next())
			{
				
				comboBox_1.addItem(rs1.getString("proid"));
				
			}
			
			stmt.close();
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage(), "Error",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	public void actionPerformed(ActionEvent arg0) 
	{
		
		supid1= (String)comboBox.getSelectedItem();
		prodid1 = (String)comboBox_1.getSelectedItem();
		
		try
		{
			Statement stmt   = con.createStatement();
			sSQL2 = "select * from supplier where supid='"+supid1+"';";
			ResultSet rst = stmt.executeQuery(sSQL2);
			boolean record = rst.next();
			if(record)
			{		
				textField_2.setText(rst.getString("supname"));
			}
			
			sSQL3 = "select * from product where proid='"+prodid1+"';";
			ResultSet rst1 = stmt.executeQuery(sSQL3);
			boolean record1 = rst1.next();
			if(record1)
			{
				textField_3.setText(rst1.getString("proname"));
				textField_4.setText(rst1.getString("category"));
				textField_5.setText(rst1.getString("rate"));
			}
			stmt.close();
			
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage(), "Error",JOptionPane.INFORMATION_MESSAGE);
		}
		
		String action = arg0.getActionCommand();
		if(action.equals("Calculate"))
		{
			double rate = Double.parseDouble(textField_5.getText());
			int quantity = Integer.parseInt(textField_6.getText());
			double amount = rate*quantity;
			textField_7.setText(String.valueOf(amount));
			
		}
		
		if(action.equals("Save"))
		{
			RecordSave();
		}
	}
	
	public void RecordSave()
	{
	
		String purdate = textField_1.getText();
		String supplierid = supid1;
		String supname = textField_2.getText();
		String productid = prodid1;
		String prodname = textField_3.getText();
		String prodcatagory = textField_4.getText();
		String prodrate = textField_5.getText();
		String purquantity = textField_6.getText();
		String puramount= textField_7.getText();
		
		if(purdate.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Date is empty");
			
		}
		else if((supplierid.equals("Select"))||(supplierid.length()==0))
		{
			JOptionPane.showMessageDialog(this,"Please Select SupplierID");
		}
		else if(supname.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please Enter Suppliername Name");
		}
		else if((productid.equals("Select"))||(productid.length()==0))
		{
			JOptionPane.showMessageDialog(this,"Please Select ProductID");
		}
		else if(prodname.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please Enter Product Name");
		}
		else if(prodcatagory.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please Enter Product Catagory");
		}
		else if(prodrate.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please Enter Rate");
		}
		else if(purquantity.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please Enter Quantity");
		}
		else if(puramount.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please Enter Calculate Button Because Amount Is Empty ");
		}
		else
		{
			try
			{
			
				Statement stmt   = con.createStatement();
				sSQL = "insert into purchase(supid,date,supname,productid,productname,procat,rate,quantity,amount) values('"+supplierid+"','"+purdate+"','"+supname+"','"+productid+"','"+prodname+"','"+prodcatagory+"','"+prodrate+"','"+purquantity+"','"+puramount+"');";
				stmt.executeUpdate(sSQL);
				stmt.close();
				JOptionPane.showMessageDialog(null, "Record inserted Successfully");
				
			}
			
			catch(SQLException e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,e.getMessage(), "Error",JOptionPane.INFORMATION_MESSAGE);
			}
			
			try
			{
				Statement stmt1   = con.createStatement();
				sSQL1 = "select supid from purchase where productid = '"+productid+"';";
				ResultSet rst = stmt1.executeQuery(sSQL1);
				boolean record = rst.next();
				if(record)
				{
					try
					{
						sSQL2 = "select Quantity from purchase where ProductID = '"+productid+"';";
						ResultSet rst1 = stmt1.executeQuery(sSQL2);
						boolean record1 = rst1.next();
						if(record1)
						{
							int a = Integer.parseInt(rst1.getString("Quantity"));
							int b = Integer.parseInt(purquantity);
							int c = a + b;
							try
							{
								sSQL3 = "update purchase set Quantity='"+c+"' where productid = '"+productid+"';";
								stmt1.executeUpdate(sSQL3);
								JOptionPane.showMessageDialog(null, "Record Update Successfully");
								
							}
							catch(SQLException exy)
							{
								exy.printStackTrace();
								JOptionPane.showMessageDialog(null,exy.getMessage(), "Error",JOptionPane.INFORMATION_MESSAGE);
							}
							
						}
					}
					catch(SQLException e)
					{
						e.printStackTrace();
						JOptionPane.showMessageDialog(null,e.getMessage(), "error",JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
				else
				{
					try
					{
						sSQL4 = "select Weight from product where proid ='"+productid+"';";
						ResultSet rst2 = stmt1.executeQuery(sSQL4);
						boolean record2 = rst2.next();
						if(record2)
						{
							try
							{
								sSQL5 = "insert into purchase(ProductID,ProductName,SupID,SupName,procat,Quantity) values('"+productid+"','"+prodname+"','"+supplierid+"','"+supname+"','"+prodcatagory+"','"+purquantity+"');";
								stmt1.executeUpdate(sSQL5);
								JOptionPane.showMessageDialog(this, "Record Insert Successfully");
							}	
							catch(SQLException ex)
							{
								ex.printStackTrace();
								JOptionPane.showMessageDialog(null,ex.getMessage(), "error",JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
					catch(SQLException e)
					{
						e.printStackTrace();
						JOptionPane.showMessageDialog(null,e.getMessage(), "error",JOptionPane.INFORMATION_MESSAGE);
					}
				}
				stmt1.close();
			
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null,ex.getMessage(), "error",JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
	}
	
}
