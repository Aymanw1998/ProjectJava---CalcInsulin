package Main;

import ViewModels.Source.UserViewModel;
import Views.Source.LoginView;

/**
 * Main class just for starting the main frame
 **/

public class mainSource {
    public static void main(String[] args) {
        LoginView loginFrame = new LoginView(new UserViewModel());
        loginFrame.setVisible(true);
    }
}
