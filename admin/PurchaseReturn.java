package admin;

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
import java.sql.Statement;
import java.sql.SQLException;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class PurchaseReturn extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JComboBox<String> comboBox;
	
	Connection con = null;
	String driver = "com.mysql.jdbc.Driver";
	String  url = "jdbc:mysql://localhost:3306/jackdb";
	String dbuser = "root";
	String dbpass = "root";
	
	private String sSQL,sSQL1,sSQL2,sSQL3;
	private String prodid="";
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PurchaseReturn frame = new PurchaseReturn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PurchaseReturn() {
		setTitle("PURCHASE RETURN");
		setResizable(false);
		
		setBounds(200, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 205, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		SimpleDateFormat myformat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = myformat.format(date);
		
		JLabel lblPurchaseDetails = new JLabel("Purchase Return details");
		lblPurchaseDetails.setFont(new Font("Script MT Bold", Font.BOLD, 30));
		lblPurchaseDetails.setForeground(Color.WHITE);
		lblPurchaseDetails.setBounds(130, 25, 340, 30);
		contentPane.add(lblPurchaseDetails);
		
		JLabel lblProductId = new JLabel("Product ID");
		lblProductId.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblProductId.setForeground(Color.BLACK);
		lblProductId.setBounds(120, 150, 102, 17);
		contentPane.add(lblProductId);
		
		JLabel lblSuplierId = new JLabel("Suplier ID");
		lblSuplierId.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSuplierId.setBounds(120, 270, 102, 20);
		contentPane.add(lblSuplierId);
		
		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblProductName.setBounds(120, 210, 113, 20);
		contentPane.add(lblProductName);
		
		JLabel lblSuplierName = new JLabel("Suplier Name");
		lblSuplierName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSuplierName.setBounds(120, 330, 113, 20);
		contentPane.add(lblSuplierName);
		
		JLabel lblQuantities = new JLabel("Quantity");
		lblQuantities.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblQuantities.setBounds(120, 390, 113, 20);
		contentPane.add(lblQuantities);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnSave.setBounds(89, 510, 100, 25);
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
		btnClose.setBounds(430, 510, 100, 25);
		contentPane.add(btnClose);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblDate.setBounds(353, 90, 40, 22);
		contentPane.add(lblDate);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblAmount.setBounds(120, 450, 86, 20);
		contentPane.add(lblAmount);
		
		Vector<String> item = new Vector<String>();
		final DefaultComboBoxModel<String> model=new DefaultComboBoxModel<String>(item);
		comboBox = new JComboBox<String>(model);
		comboBox.setBounds(290, 150, 160, 20);
		contentPane.add(comboBox);
		comboBox.addItem("Select");
		comboBox.addActionListener(this);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(403, 90, 150, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(today);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(290, 210, 160, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(290, 270, 160, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(290, 330, 160, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(290, 390, 160, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(290, 450, 160, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				comboBox.setSelectedIndex(0);
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");				
			}
		});
		btnClear.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnClear.setBounds(258, 510, 100, 25);
		contentPane.add(btnClear);
		
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
		
		try
		{
			
			Statement stmt   = con.createStatement();
			
			sSQL = "select ProductID from purchase";
			ResultSet rs = stmt.executeQuery(sSQL);
			while(rs.next())
			{		

				comboBox.addItem(rs.getString("ProductID"));
				
			}
			stmt.close();
		}
		catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null,ex.getMessage(), "error",JOptionPane.INFORMATION_MESSAGE);
		}
			
	}
	
	public void actionPerformed(ActionEvent e)
	{
		prodid = (String)comboBox.getSelectedItem();
		
		try
		{
			Statement stmt   = con.createStatement();
			sSQL1= "select * from purchase where ProductID='"+prodid+"';";
			ResultSet rst1 = stmt.executeQuery(sSQL1);
			boolean record1 = rst1.next();
			if(record1)
			{
				textField_1.setText(rst1.getString("ProductName"));
				textField_2.setText(rst1.getString("SupID"));
				textField_3.setText(rst1.getString("SupName"));
			}
			stmt.close();
			
		}
		catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null,ex.getMessage(), "error",JOptionPane.INFORMATION_MESSAGE);
		}
		
		String action = e.getActionCommand();
		if(action.equals("Save"))
		{
			RecordSave();
		}
	}
	
	public void RecordSave()
	{
		String returndate = textField.getText();
		String productid = prodid;
		String prodname = textField_1.getText();
		String supid = textField_2.getText();
		String supname = textField_3.getText();
		String quantity = textField_4.getText();
		String amount = textField_5.getText();
		
		if(returndate.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Date");
			
		}
		else if((productid.equals("Select"))||(productid.length()==0))
		{
			JOptionPane.showMessageDialog(this,"Please enter Product ID");
		}
		else if(prodname.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Product Name");
		}
		else if(supid.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Supplier Id");
		}
		else if(supname.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Supplier Name");
		}
		else if(quantity.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Quantity");
		}
		else if(amount.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Amount");
		}
		else
		{
			try
			{
			
				Statement stmt   = con.createStatement();
				sSQL = "insert into purchasereturn(SupplierID,SupplierName,ProductID,ProductName,Quantity,Amount,Date) values('"+supid+"','"+supname+"','"+productid+"','"+prodname+"','"+quantity+"','"+amount+"','"+returndate+"');";
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
	}
}
