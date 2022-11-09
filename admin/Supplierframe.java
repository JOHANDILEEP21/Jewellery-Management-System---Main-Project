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

public class Supplierframe extends JFrame {

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
	
	private String sSQL;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Supplierframe frame = new Supplierframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Supplierframe() {
		setTitle("SUPPLIER");
		setResizable(false);
		
		setBounds(200, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 205, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSupplierDetails = new JLabel("Supplier Details");
		lblSupplierDetails.setForeground(Color.WHITE);
		lblSupplierDetails.setFont(new Font("Lucida Calligraphy", Font.BOLD, 30));
		lblSupplierDetails.setBounds(253, 39, 313, 51);
		contentPane.add(lblSupplierDetails);
		
		JLabel lblSupplierId = new JLabel("Supplier ID(2XXX)");
		lblSupplierId.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSupplierId.setBounds(100, 125, 150, 20);
		contentPane.add(lblSupplierId);
		
		textField = new JTextField();
		textField.setBounds(350, 125, 160, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblCompanyName = new JLabel("Company Name");
		lblCompanyName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCompanyName.setBounds(100, 180, 133, 20);
		contentPane.add(lblCompanyName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(350, 180, 160, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblTinNumber = new JLabel("Tin NO(2XXXXXX)");
		lblTinNumber.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTinNumber.setBounds(100, 235, 160, 20);
		contentPane.add(lblTinNumber);
		
		textField_2 = new JTextField();
		textField_2.setBounds(350, 235, 160, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblSupplierName = new JLabel("Supplier Name");
		lblSupplierName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSupplierName.setBounds(100, 290, 121, 20);
		contentPane.add(lblSupplierName);
		
		textField_3 = new JTextField();
		textField_3.setBounds(350, 290, 160, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblSupplierAddress = new JLabel("Supplier Address");
		lblSupplierAddress.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSupplierAddress.setBounds(100, 345, 133, 20);
		contentPane.add(lblSupplierAddress);
		
		textField_4 = new JTextField();
		textField_4.setBounds(350, 345, 160, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Mobile Number");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel.setBounds(100, 400, 133, 20);
		contentPane.add(lblNewLabel);
		
		textField_5 = new JTextField();
		textField_5.setBounds(350, 400, 160, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecordSave();
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton.setBounds(169, 486, 100, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Close");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					con.close();
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton_1.setBounds(376, 486, 100, 25);
		contentPane.add(btnNewButton_1);
		
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
		btnClear.setBounds(575, 486, 100, 25);
		contentPane.add(btnClear);
		
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecordFind();
			}
		});
		btnFind.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnFind.setBounds(615, 155, 100, 25);
		contentPane.add(btnFind);
		
		JButton btnAlter = new JButton("Update");
		btnAlter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecordUpdate();
			}
		});
		btnAlter.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnAlter.setBounds(615, 235, 100, 25);
		contentPane.add(btnAlter);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecordDelete();
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnDelete.setBounds(615, 315, 100, 25);
		contentPane.add(btnDelete);
		
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
		String supid = textField.getText();
		String supcompname = textField_1.getText();
		String suptinno = textField_2.getText();
		String supname = textField_3.getText();
		String supaddress = textField_4.getText();
		String supmobileno = textField_5.getText();
		
		
		if(supid.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter SupplierID");
			
		}
		else if(supcompname.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Company Name");
		}
		else if(suptinno.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Tin Number");
		}
		else if(supname.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Supplier Name");
		}
		else if(supaddress.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Address");
		}
		else if(supmobileno.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Mobile No");
		}
		else if((supmobileno.length()>9)&&(supmobileno.length()<11))
		{
			try
			{
			
				Statement stmt   = con.createStatement();
				sSQL = "insert into supplier(SupID,CompName,TinNO,SupName,SupAddress,MobNO) values('"+supid+"','"+supcompname+"','"+suptinno+"','"+supname+"','"+supaddress+"','"+supmobileno+"');";
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
			sSQL = "select * from supplier where SupID='"+find+"';";
			ResultSet rs = stmt.executeQuery(sSQL);
		
			boolean record = rs.next();
			if(record)
			{		
				String supcompname= rs.getString("CompName");
				String suptinno = rs.getString("TinNO");
				String supname = rs.getString("SupName");
				String supaddress= rs.getString("SupAddress");
				String supmobileno = rs.getString("Mobno");
			
				textField_1.setText(supcompname);
				textField_2.setText(suptinno);
				textField_3.setText(supname);
				textField_4.setText(supaddress);
				textField_5.setText(supmobileno);
			}
			else
			{
				
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				
				JOptionPane.showMessageDialog(this,"Not Valid Supplier ID");
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
		
		String supid = textField.getText();
		String supcompname = textField_1.getText();
		String suptinno = textField_2.getText();
		String supname = textField_3.getText();
		String supaddress = textField_4.getText();
		String supmobileno = textField_5.getText();
		
		
		if(supid.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter SupplierID");
			
		}
		else if(supcompname.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Company Name");
		}
		else if(suptinno.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Tin Number");
		}
		else if(supname.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Supplier Name");
		}
		else if(supaddress.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Address");
		}
		else if(supmobileno.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter Mobile No");
		}
		else if((supmobileno.length()>9)&&(supmobileno.length()<11))
		{
			try
			{
			
				Statement stmt   = con.createStatement();
				sSQL = "update supplier set CompName='"+supcompname+"',TinNo='"+suptinno+"',SupName='"+supname+"',SupAddress='"+supaddress+"',Mobno='"+supmobileno+"' where SupID ='"+supid+"' ;";
				stmt.executeUpdate(sSQL);
				stmt.close();
				JOptionPane.showMessageDialog(null, "record Updated Successfully");
				
			}
			
			catch(SQLException e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,e.getMessage(), "error",JOptionPane.INFORMATION_MESSAGE);
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
		String find2 = textField_2.getText();
		try
		{
		
			Statement stmt   = con.createStatement();
			sSQL = "delete from supplier where SupID='"+find+"' and TinNo='"+find2+"';";
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
