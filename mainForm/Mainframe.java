package mainForm;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import adminLogin.AdLogin;

public class Mainframe extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	
	Connection con = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainframe frame = new Mainframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Mainframe() {
		
		setTitle("WELCOME");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200,30,900,700);
		setSize(screen);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcomeToJewellery = new JLabel("Welcome To Jewellery Shop ");
		lblWelcomeToJewellery.setForeground(Color.BLACK);
		lblWelcomeToJewellery.setFont(new Font("Vivaldi", Font.BOLD, 45));
		lblWelcomeToJewellery.setBounds(700, 50, 548, 60);
		contentPane.add(lblWelcomeToJewellery);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(600, 135, 900, 480);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.setBackground(new Color(53,100,73));
		btnAdmin.setForeground(Color.WHITE);
		//btnAdmin.setBorder(new EmptyBorder(15, 15, 15, 15));
		btnAdmin.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		panel.add(btnAdmin);
		btnAdmin.setBounds(250, 43, 270, 40);
		btnAdmin.addActionListener(this);
		
		JButton btnNewButton = new JButton("Sales Details");
		btnNewButton.setBackground(new Color(50,100,70));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnNewButton.setBounds(250, 158, 270, 40);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		/*JButton btnNewButton_1 = new JButton("Report");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnNewButton_1.setBounds(50, 393, 270, 40);
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(this);*/
		
		JButton btnSalesReturn = new JButton("Sales Return");
		btnSalesReturn.setBackground(new Color(50,100,70));
		btnSalesReturn.setForeground(Color.WHITE);
		btnSalesReturn.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnSalesReturn.setBounds(250, 278, 270, 40);
		panel.add(btnSalesReturn);
		btnSalesReturn.addActionListener(this);
		
		JButton btnClose = new JButton("Close");
		btnClose.setBackground(new Color(180,55,55));
		btnClose.setForeground(Color.white);
		btnClose.setBorderPainted(true);
		btnClose.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnClose.setBounds(250, 390, 270, 40);
		panel.add(btnClose);
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
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("E:\\dcc.jpg"));
		label.setBounds(5, 5, 590, 700);
		contentPane.add(label);
		
		}
	
		public void actionPerformed(ActionEvent event)
		{
			String action = event.getActionCommand();
			
			if(action=="Admin")
			{
				AdLogin in = new AdLogin();
				in.setVisible(true);
			}
			else if(action=="Sales Details")
			{
				Salesdetails in1 = new Salesdetails();
				in1.setVisible(true);
			}
			else if(action=="Sales Return")
			{
				SalesReturn in2 = new SalesReturn();
				in2.setVisible(true);
			}
		}
}
