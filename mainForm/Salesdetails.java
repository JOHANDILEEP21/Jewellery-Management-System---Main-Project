package mainForm;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class Salesdetails extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	
	Connection con = null;
	String driver = "com.mysql.jdbc.Driver";
	String  url = "jdbc:mysql://localhost:3306/jackdb";
	String dbuser = "root";
	String dbpass = "root";
	
	String sSQL,sSQL1,sSQL2,sSQL3,sSQL4;
	
	String empid="",prodid="";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Salesdetails frame = new Salesdetails();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Salesdetails() {
		setTitle("SALES DETAILS");
		setResizable(false);
	
		setBounds(200, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 205, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		SimpleDateFormat myformat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = myformat.format(date);
		
		JLabel lblSalesDetails = new JLabel("Sales Details");
		lblSalesDetails.setForeground(Color.WHITE);
		lblSalesDetails.setFont(new Font("Script MT Bold", Font.BOLD, 35));
		lblSalesDetails.setBounds(285, 10, 220, 40);
		contentPane.add(lblSalesDetails);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblDate.setBounds(570, 60, 46, 20);
		contentPane.add(lblDate);
		
		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCustomerName.setBounds(150, 110, 125, 20);
		contentPane.add(lblCustomerName);
		
		JLabel lblMobileNo = new JLabel("Mobile No");
		lblMobileNo.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMobileNo.setBounds(150, 150, 125, 20);
		contentPane.add(lblMobileNo);
		
		JLabel lblCustomerAddress = new JLabel("Customer Address");
		lblCustomerAddress.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCustomerAddress.setBounds(150, 190, 139, 20);
		contentPane.add(lblCustomerAddress);
		
		JLabel lblEmployeeId = new JLabel("Employee ID");
		lblEmployeeId.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblEmployeeId.setBounds(150, 250, 139, 20);
		contentPane.add(lblEmployeeId);
		
		JLabel lblProductId = new JLabel("Product ID");
		lblProductId.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblProductId.setBounds(150, 290, 125, 20);
		contentPane.add(lblProductId);
		
		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblProductName.setBounds(150, 330, 125, 20);
		contentPane.add(lblProductName);
		
		JLabel lblProductCatagories = new JLabel("Product Catagories");
		lblProductCatagories.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblProductCatagories.setBounds(150, 370, 144, 20);
		contentPane.add(lblProductCatagories);
		
		JLabel lblNetWeightg = new JLabel("Net Weight(g)");
		lblNetWeightg.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNetWeightg.setBounds(150, 410, 125, 20);
		contentPane.add(lblNetWeightg);
		
		JLabel lblRate = new JLabel("Rate (gm)");
		lblRate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblRate.setBounds(150, 450, 100, 20);
		contentPane.add(lblRate);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblAmount.setBounds(150, 530, 100, 20);
		contentPane.add(lblAmount);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(630, 60, 120, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(today);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_2.setBounds(345, 110, 160, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(345, 150, 160, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(345, 190, 160, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		Vector<String> item = new Vector<String>();
		DefaultComboBoxModel<String> model=new DefaultComboBoxModel<String>(item);
		comboBox = new JComboBox<String>(model);
		comboBox.setBounds(345, 250, 160, 20);
		contentPane.add(comboBox);
		comboBox.addItem("Select");
		comboBox.addActionListener(this);
		
		Vector<String> item1 = new Vector<String>();
		DefaultComboBoxModel<String> model1=new DefaultComboBoxModel<String>(item1);
		comboBox_1 = new JComboBox<String>(model1);
		comboBox_1.setBounds(345, 290, 160, 20);
		contentPane.add(comboBox_1);
		comboBox_1.addItem("Select");
		comboBox_1.addActionListener(this);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setBounds(345, 330, 160, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setBounds(345, 370, 160, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setBounds(345, 410, 160, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setEditable(false);
		textField_8.setBounds(345, 450, 160, 20);
		contentPane.add(textField_8);
		textField_8.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setBounds(345, 490, 160, 20);
		contentPane.add(textField_9);
		textField_9.setColumns(10);
		
		JButton btnSve = new JButton("Save");
		btnSve.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnSve.setBounds(605, 185, 100, 25);
		contentPane.add(btnSve);
		btnSve.addActionListener(this);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				comboBox.setSelectedIndex(0);
				comboBox_1.setSelectedIndex(0);
				textField_5.setText("");
				textField_6.setText("");
				textField_7.setText("");
				textField_8.setText("");
				textField_9.setText("");
				textField_10.setText("");
			}
		});
		btnClear.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnClear.setBounds(605, 263, 100, 25);
		contentPane.add(btnClear);
		
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
		btnClose.setBounds(605, 485, 100, 25);
		contentPane.add(btnClose);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblQuantity.setBounds(150, 490, 107, 20);
		contentPane.add(lblQuantity);
		
		textField_10 = new JTextField();
		textField_10.setEditable(false);
		textField_10.setBounds(345, 530, 160, 20);
		contentPane.add(textField_10);
		textField_10.setColumns(10);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnCalculate.setBounds(605, 341, 100, 25);
		contentPane.add(btnCalculate);
		btnCalculate.addActionListener(this);
		
		JButton btnFindRate = new JButton("Find Rate");
		btnFindRate.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnFindRate.setBounds(605, 411, 100, 25);
		contentPane.add(btnFindRate);
		btnFindRate.addActionListener(this);
		
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
			JOptionPane.showMessageDialog(null,e.getMessage(), "error",JOptionPane.INFORMATION_MESSAGE);
		}
		
		try
		{
			
			Statement stmt   = con.createStatement();
			
			sSQL = "select EmpID from empdetails";
			ResultSet rs = stmt.executeQuery(sSQL);
			while(rs.next())
			{		

				comboBox.addItem(rs.getString("EmpID"));
				
			}
			
			sSQL1 = "select ProID from product;";
			ResultSet rs1 = stmt.executeQuery(sSQL1);
			while(rs1.next())
			{
				
				comboBox_1.addItem(rs1.getString("ProID"));
				
			}
			
			stmt.close();
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage(), "error",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		prodid = (String)comboBox_1.getSelectedItem();
		empid = (String)comboBox.getSelectedItem();
		try
		{
			Statement stmt   = con.createStatement();
			sSQL2 = "select * from product where ProID='"+prodid+"';";
			ResultSet rst = stmt.executeQuery(sSQL2);
			boolean record1 = rst.next();
			if(record1)
			{
				textField_5.setText(rst.getString("ProName"));
				textField_6.setText(rst.getString("category"));
				textField_7.setText(rst.getString("Weight"));
				
				
			}
			stmt.close();
		}
		catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null,ex.getMessage(), "error",JOptionPane.INFORMATION_MESSAGE);
		}
			
		String action = e.getActionCommand();
		
		if(action.equals("Find Rate"))
		{
			String date = textField_1.getText();
			String catagory = textField_6.getText();
			
			try
			{
				Statement stmt1 = con.createStatement();
				if(catagory.equalsIgnoreCase("gold"))
				{
					sSQL3 = "select GoldRate from currentrate where curDate = '"+date+"';";
					ResultSet rst1 = stmt1.executeQuery(sSQL3);
					boolean record2 = rst1.next();
					if(record2)
					{
						textField_8.setText(rst1.getString("GoldRate"));
					}
				}
				else
				{
					sSQL4 = "select SilverRate from currentrate where curDate = '"+date+"';";
					ResultSet rst2 = stmt1.executeQuery(sSQL4);
					boolean record3 = rst2.next();
					if(record3)
					{
						textField_8.setText(rst2.getString("SilverRate"));
					}
				}
				stmt1.close();
			}
			catch(SQLException ex)
			{
				JOptionPane.showMessageDialog(null,ex.getMessage(), "error",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		if(action.equals("Calculate"))
		{
			float weight = Float.parseFloat(textField_7.getText());
			double rate = Double.parseDouble(textField_8.getText());
			int quantity = Integer.parseInt(textField_9.getText());
			double amount = weight*rate*quantity;
			textField_10.setText(String.valueOf(amount));
			
		}
		
		if(action.equals("Save"))
		{
			RecordSave();
			
		}
	}
	
	public void RecordSave()
	{
		String salesdate = textField_1.getText();
		String cusname   = textField_2.getText();
		String cusmobileno = textField_3.getText();
		String cusaddress  = textField_4.getText();
		String employeeid    = empid;
		String productid     = prodid;
		String prodname = textField_5.getText();
		String prodcatagory = textField_6.getText();
		String prodweight   = textField_7.getText();
		String salesrate = textField_8.getText();
		String quantity  = textField_9.getText();
		String amount = textField_10.getText();
		
		if(salesdate.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Date is empty");
			
		}
		else if(cusname.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please Enter customer Name");
		}
		else if(cusmobileno.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please Enter Customer mobile Number");
		}
		else if(cusaddress.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please Enter Customer Address");
		}
		else if((employeeid.equals("Select")||(employeeid.length()==0)))
		{
			JOptionPane.showMessageDialog(this,"Please SelectEmployee ID");
		}
		else if((productid.equals("Select")||(productid.length()==0)))
		{
			JOptionPane.showMessageDialog(this,"Please Select Product ID");
		}
		else if(prodname.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please Enter Product Name");
		}
		else if(prodcatagory.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please Enter Catagory");
		}
		else if(prodweight.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please Enter Weight");
		}
		else if(salesrate.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please Enter Find Rate Button Because Rate Is Empty ");
		}
		else if(quantity.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please Enter Quantity");
		}
		else if(amount.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please Enter Calculate Button Because Amount Is Empty ");
		}
		
		else if((cusmobileno.length()>9)&&(cusmobileno.length()<11))
		{
			try
			{
			
				Statement stmt   = con.createStatement();
				sSQL = "insert into sales(Date,CusName,MobNO,CusAddress,EmpID,ProductID,ProductNamr,Procat,Weight,Rate,Quantity,Amount) values('"+salesdate+"','"+cusname+"','"+cusmobileno+"','"+cusaddress+"','"+employeeid+"','"+productid+"','"+prodname+"','"+prodcatagory+"','"+prodweight+"','"+salesrate+"','"+quantity+"','"+amount+"');";
				stmt.executeUpdate(sSQL);
				stmt.close();
				JOptionPane.showMessageDialog(null, "record inserted Successfully");
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,e.getMessage(), "error",JOptionPane.INFORMATION_MESSAGE);
			}
			
			try
			{
				Statement stmt1   = con.createStatement();
				sSQL2 = "select Quantity from purchase where ProductID = '"+productid+"';";
				ResultSet rst1 = stmt1.executeQuery(sSQL2);
				boolean record1 = rst1.next();
				if(record1)
				{
					int a = Integer.parseInt(rst1.getString("Quantity"));
					int b = Integer.parseInt(quantity);
					int c = a - b;
					try
					{
						sSQL3 = "update purchase set Quantity='"+c+"' where ProductID = '"+productid+"';";
						stmt1.executeUpdate(sSQL3);
						JOptionPane.showMessageDialog(null, "record Update Successfully");
						
					}
					catch(SQLException exy)
					{
						exy.printStackTrace();
						JOptionPane.showMessageDialog(null,exy.getMessage(), "error",JOptionPane.INFORMATION_MESSAGE);
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
			JOptionPane.showMessageDialog(null, "Please Enter Correct Customer Mobile Number");
		}

	}
}
