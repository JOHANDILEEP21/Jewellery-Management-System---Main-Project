package admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Admin extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Admin() {
		setTitle("ADMIN");
		setSize(screen);
		setBounds(20, 30, 1000,700);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setMnemonic('f');
		menuBar.add(mnFile);
		
		JMenuItem mntmChangeAdminPassword = new JMenuItem("Change Admin Password");
		mnFile.add(mntmChangeAdminPassword);
		mntmChangeAdminPassword.addActionListener(this);
		
		JMenuItem mntmUserPassword = new JMenuItem("Change User Password");
		mnFile.add(mntmUserPassword);
		mntmUserPassword.addActionListener(this);
		
		JMenuItem mntmCurrentGoldRate = new JMenuItem("Current Gold Rate");
		mnFile.add(mntmCurrentGoldRate);
		mntmCurrentGoldRate.addActionListener(this);
		
		JMenu mnEmployeeDetails = new JMenu("Employee Details");
		mnEmployeeDetails.setMnemonic('e');
		menuBar.add(mnEmployeeDetails);
		
		JMenuItem mntmNewEmployee = new JMenuItem("Add");
		mnEmployeeDetails.add(mntmNewEmployee);
		mntmNewEmployee.addActionListener(this);
		
		JMenuItem mntmNewEmployee1 = new JMenuItem("Change");
		mnEmployeeDetails.add(mntmNewEmployee1);
		mntmNewEmployee1.addActionListener(this);
		
		JMenu mnPurchaseDetails = new JMenu("Purchase Details");
		mnPurchaseDetails.setMnemonic('p');
		menuBar.add(mnPurchaseDetails);
		
		JMenuItem mntmPurchaseEntry = new JMenuItem("Purchase Entry");
		mnPurchaseDetails.add(mntmPurchaseEntry);
		mntmPurchaseEntry.addActionListener(this);
		
		JMenuItem mntmNewCustomer = new JMenuItem("Supplier Details");
		mnPurchaseDetails.add(mntmNewCustomer);
		mntmNewCustomer.addActionListener(this);
		
		JMenuItem mntmProductMost = new JMenuItem("Product Details");
		mnPurchaseDetails.add(mntmProductMost);
		mntmProductMost.addActionListener(this);
		
		JMenuItem mntmPurchaseReturn = new JMenuItem("Purchase Return");
		mnPurchaseDetails.add(mntmPurchaseReturn);
		mntmPurchaseReturn.addActionListener(this);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(143, 188, 143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("E:\\dcc.jpg"));
		label.setBounds(0, 0, 650, 700);
		contentPane.add(label);
	
	}
	
	public void actionPerformed(ActionEvent event)
	{
		String srcObject = event.getActionCommand();

		if(srcObject=="Change Admin Password")
		{
			CAP	ask1 = new CAP();
			ask1.setVisible(true);
			
		}
		else if(srcObject=="Change User Password")
		{
			CP ask2 = new CP();
			ask2.setVisible(true);
			
		}
		else if(srcObject=="Current Gold Rate")
		{
			Goldrate ask3 = new Goldrate();
			ask3.setVisible(true);
	
		}
		else if(srcObject=="Add")
		{
			EmployeeDetails ask4 = new EmployeeDetails();
			ask4.setVisible(true);
			
		}
		else if(srcObject=="Change")
		{
			EmployeeDetails ask4 = new EmployeeDetails();
			ask4.setVisible(true);
			
		}
		else if(srcObject=="Purchase Entry")
		{
			Purchaseframe ask5 = new Purchaseframe();
			ask5.setVisible(true);
			
		}
		else if(srcObject=="Supplier Details")
		{
			Supplierframe ask6= new Supplierframe();
			ask6.setVisible(true);
			
		}
		else if(srcObject=="Product Details")
		{
			Productdetails ask7 = new Productdetails();
			ask7.setVisible(true);
			
		}
		else if(srcObject=="Purchase Return")
		{
			PurchaseReturn ask8 = new PurchaseReturn();
			ask8.setVisible(true);
		}
	}

}
