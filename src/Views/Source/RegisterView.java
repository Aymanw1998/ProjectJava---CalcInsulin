package Views.Source;

import Exceptions.MyException;
import Models.Source.UserDetails;
import ViewModels.Interface.IRegisterViewModel;
import ViewModels.Source.UserViewModel;
import ViewModels.Source.RegisterViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterView extends JFrame {

	// UI elements
	private JTextField passField;
	private JTextField firstnameField;
	private JTextField lastnameField;
	private JTextField emailField;
	private JTextField phoneField;

	private JLabel passLabel;
	private JLabel firstnameLabel;
	private JLabel lastnameLabel;
	private JLabel emailLabel;
	private JLabel phoneLabel;

	private JButton registerButton;
	private JButton backButton;


	public RegisterView(RegisterViewModel viewModel) {
        initializeUI();
        commond (viewModel);
	}

    public void commond(RegisterViewModel viewModel){
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserDetails userDetails = new UserDetails(emailField.getText(),passField.getText(),firstnameField.getText(),
						lastnameField.getText(),phoneField.getText());
				try {
					viewModel.doRegister(userDetails);
				} catch (MyException ex) {
					ex.printStackTrace();
				}

				new LoginView(new UserViewModel()).handleLoginResult(new UserViewModel().doLogin(userDetails));
				setVisible(false);

			}
		});

		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginView frame = new LoginView(new UserViewModel());
				frame.setVisible(true);
				setVisible(false);
			}
		});
	}

	private void handleRegisterResult(boolean res) {
		if (!res) {
			JOptionPane.showMessageDialog(null, "Failed to register", "Error",
					JOptionPane.ERROR_MESSAGE);
			this.setVisible(false);
			LoginView login = new LoginView(new UserViewModel());
			login.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null,
					"Register success! now you can login.", "Success",
					JOptionPane.OK_OPTION | JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
			this.setVisible(false);

			UserDetails userDetails = new UserDetails(emailField.getText(),passField.getText());

			UserViewModel userViewModel = new UserViewModel();
			String s =userViewModel.doLogin(userDetails);

			UserView userFrame = new UserView(s);

			this.dispose();

			userFrame.setVisible(true);
		}
	}

	private void initializeUI() {
		this.setTitle("Insulin Calculator - Register");
		// init UI elements
		final Dimension fieldSize = new Dimension(200, 24);
		this.emailLabel = new JLabel("E-mail:        ");
		this.passLabel = new JLabel("Password: ");
		this.firstnameLabel = new JLabel("FirstName: ");
		this.lastnameLabel = new JLabel("LastName: ");
		this.phoneLabel = new JLabel("Phone:       ");

		this.emailField = new JTextField();
		this.passField = new JTextField();
		this.firstnameField = new JTextField();
		this.lastnameField = new JTextField();
		this.phoneField = new JTextField();

		this.emailField.setPreferredSize(fieldSize);
		this.passField.setPreferredSize(fieldSize);
		this.firstnameField.setPreferredSize(fieldSize);
		this.lastnameField.setPreferredSize(fieldSize);
		this.phoneField.setPreferredSize(fieldSize);

		// north Panel
		JPanel panelT = new JPanel();
		panelT.setLayout(new FlowLayout());
		panelT.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panelT.add(emailLabel);
		panelT.add(emailField);
		panelT.add(passLabel);
		panelT.add(passField);
		panelT.add(firstnameLabel);
		panelT.add(firstnameField);
		panelT.add(lastnameLabel);
		panelT.add(lastnameField);
		panelT.add(phoneLabel);
		panelT.add(phoneField);

		JPanel panelR = new JPanel();
		panelR.setLayout(new FlowLayout());
		panelR.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.registerButton = new JButton("Register");
		this.backButton = new JButton("Back");

		this.registerButton.setBounds(500, 200, 20, 20);
		this.backButton.setBounds(500,200,20,20);
		panelT.add(backButton);
		panelT.add(registerButton);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(panelT);
		// panel.add(panelR);

		// add panels to main frame
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setBounds(500, 200, 300, 300);
		this.setContentPane(panel);
	}
}
