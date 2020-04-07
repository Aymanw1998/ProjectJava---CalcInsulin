package Views.Source;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Services.Source.DBService;
import ViewModels.Source.UserViewModel;
import ViewModels.Source.CalculateInsulinViewModel;
import ViewModels.Source.ViewHistoryViewModel;
import Views.Interface.IUserView;

@SuppressWarnings("serial")
public class UserView extends JFrame implements IUserView {
	// UI elements
	private JLabel fullNameLabel;

	private JButton logout;
	private JButton viewHistoryButton;
	private JButton calculateInsulinButton;

	private DBService db;
	public UserView(String userId) {
		db = new DBService();
		try{ fullNameLabel = new JLabel(db.getName(userId));}
		catch(NullPointerException e) { fullNameLabel = new JLabel(userId); }
		initializeUI();
		commond(userId);

	}

	public void commond(String userId){
		viewHistoryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewHistoryView frame = new ViewHistoryView(new ViewHistoryViewModel(userId));
				frame.setVisible(true);
				setVisible(false);
			}
		});
		calculateInsulinButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CalculateInsulinView frame = new CalculateInsulinView(new CalculateInsulinViewModel(userId));
				frame.setVisible(true);
				setVisible(false);
			}
		});
		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginView frame = new LoginView(new UserViewModel());
				frame.setVisible(true);
				setVisible(false);
			}
		});
	}

	public void initializeUI() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Insulin Calculator");

		this.logout = new JButton("Logout");
		this.viewHistoryButton = new JButton("View History");
		this.calculateInsulinButton = new JButton("Calculate Insulin");
		JPanel north = new JPanel();
		north.setLayout(new FlowLayout());
		north.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		north.add(fullNameLabel);

		// center Panel
		JPanel center = new JPanel();
		center.setLayout(new FlowLayout());
		center.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		//logout.setBackground(Color.RED);
		center.add(logout);
		center.add(viewHistoryButton);
		center.add(calculateInsulinButton);

		// add panels to main frame
		this.setLocationRelativeTo(null);
		this.setBounds(500, 200, 350, 100);
		this.setLayout(new BorderLayout());
		this.add(north, BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);
	}
}
