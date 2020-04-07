package Views.Source;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Exceptions.MyException;
import ViewModels.Source.UserViewModel;
import ViewModels.Source.CalculateInsulinViewModel;
import Views.Interface.ICalculateInulinView;

@SuppressWarnings("serial")
public class CalculateInsulinView extends JFrame implements ICalculateInulinView {
	// UI elements
	private JTextField bloodSugarField;
	private JTextField carbsAmountField;
	private JLabel bloodSugarLabel;
	private JLabel carbsAmountLabel;
	private JLabel insulinLevelLabel;
	private JButton calcButton;

	private JButton logoutButton;
	private JButton backButton;

	public CalculateInsulinView(CalculateInsulinViewModel viewModel) {
		initializeUI();
		commond(viewModel);
	}

	public void commond (CalculateInsulinViewModel viewModel){
		calcButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					double bs= Double.parseDouble(bloodSugarField.getText()),
							ca = Double.parseDouble(carbsAmountField.getText());
							double resultInsulinLevel = viewModel.calculate(bs,ca);
					handleCalculateResult(resultInsulinLevel);
				} catch (NumberFormatException |MyException.NegNumNotAllowed w){
					w.printStackTrace();
					JOptionPane.showMessageDialog(null, w.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginView frame = new LoginView(new UserViewModel());
				frame.setVisible(true);
				setVisible(false);
			}
		});
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserView frame = new UserView(viewModel.getUserId());
				frame.setVisible(true);
				setVisible(false);
			}
		});
	}

	public void handleCalculateResult(double res) {
		this.insulinLevelLabel.setText("Result: " + String.valueOf(res));
	}

	 public void initializeUI() {
		this.setTitle("Insulin Calculator - Calculate");

		// init UI elements
		final Dimension fieldSize = new Dimension(200, 24);
		this.bloodSugarLabel = new JLabel("Blood Sugar Amount: ");
		this.carbsAmountLabel = new JLabel("Carbs Amount: ");
		this.insulinLevelLabel = new JLabel("Result: ");
		 this.logoutButton = new JButton("Logout");
		 this.backButton = new JButton("Back");
		this.bloodSugarField = new JTextField();
		this.carbsAmountField = new JTextField();
		this.bloodSugarField.setPreferredSize(fieldSize);
		this.carbsAmountField.setPreferredSize(fieldSize);

		// north Panel
		JPanel north = new JPanel();
		north.setLayout(new FlowLayout());
		north.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		north.add(bloodSugarLabel);
		north.add(bloodSugarField);

		// center Panel
		 this.calcButton = new JButton("Calculate");
		 JPanel center = new JPanel();
		 center.setLayout(new FlowLayout());
		 center.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		 center.add(carbsAmountLabel);
		 center.add(carbsAmountField);
		 center.add(calcButton);
		 center.add(insulinLevelLabel);
		 // south panel
		 JPanel south = new JPanel();
		 this.setLayout(new BorderLayout());
		south.add(backButton);
		south.add(logoutButton);

		// add panels to main frame
		this.setLocationRelativeTo(null);
		this.setBounds(500, 200, 350, 180);
		this.setLayout(new BorderLayout());
		this.add(north, BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);
		this.add(south, BorderLayout.SOUTH);
	}
}
