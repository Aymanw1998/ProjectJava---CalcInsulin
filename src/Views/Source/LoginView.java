package Views.Source;

import Models.Source.UserDetails;
import ViewModels.Source.RegisterViewModel;
import ViewModels.Source.UserViewModel;
import Views.Interface.ILoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame implements ILoginView {

    // UI elements
    private JPasswordField passField;
    private JTextField userField;
    private JLabel loginLabel;
    private JLabel passLabel;
    private JLabel userLabel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton loginWithGoogleButton;

    public LoginView(UserViewModel userViewModel) {
        initializeUI();
        commond(userViewModel);
    }

    public void commond(UserViewModel userViewModel){

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());
                UserDetails userDetails = new UserDetails(username, password);
                String doLogin = userViewModel.doLogin(userDetails);
                handleLoginResult(doLogin);
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegister();
                setVisible(false);
            }
        });

        loginWithGoogleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 UserDetails doLoginGoogle = userViewModel.doLoginWithGoogle();
                handleLoginWithGoogleResult(doLoginGoogle);
            }
        });
    }

    public void handleLoginWithGoogleResult(UserDetails res) {
        if (res == null) {
            JOptionPane.showMessageDialog(null, "Bad google credentials", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            new UserViewModel().doRegister(res);
            UserView userFrame = new UserView(res.getFirstName()+" "+res.getLastName());
            this.dispose();
            userFrame.setVisible(true);
            this.setVisible(false);
        }
    }

    public void handleRegister() {
        RegisterView registerFrame = new RegisterView(new RegisterViewModel());
        registerFrame.setVisible(true);
        this.setVisible(false);
    }

    public void handleLoginResult(String res) {
        if (res == null) {
            JOptionPane.showMessageDialog(null, "Bad credentials", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            UserView userFrame = new UserView(res);
            this.dispose();
            userFrame.setVisible(true);
            this.setVisible(false);
        }
    }

    public void initializeUI() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Insulin Calculator - Login");

        // init UI elements
        final Dimension fieldSize = new Dimension(200, 24);
        this.loginLabel = new JLabel("Login");
        this.userLabel = new JLabel("Email:         ");
        this.passLabel = new JLabel("Password: ");
        this.userField = new JTextField();
        this.passField = new JPasswordField();
        this.userField.setPreferredSize(fieldSize);
        this.passField.setPreferredSize(fieldSize);

        loginLabel.setFont(new Font("Serif",Font.PLAIN,20));
        // north Panel
        JPanel north = new JPanel();
        north.setLayout(new FlowLayout());
        north.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        north.add(loginLabel);


        // center Panel
        JPanel center = new JPanel();
        center.setLayout(new FlowLayout());
        center.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        center.add(userLabel);
        center.add(userField);
        center.add(passLabel);
        center.add(passField);
        // south panel
        JPanel south = new JPanel();
        this.loginButton = new JButton("Login");
        this.registerButton = new JButton("Register");
        this.loginWithGoogleButton = new JButton("Login(Google)");
        south.add(loginButton);
        south.add(registerButton);
        south.add(loginWithGoogleButton);

        // add panels to main frame
        this.setLocationRelativeTo(null);
        this.setBounds(500, 200, 300, 200);
        this.setLayout(new BorderLayout());
        this.add(north, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        this.add(south, BorderLayout.SOUTH);
    }
}
