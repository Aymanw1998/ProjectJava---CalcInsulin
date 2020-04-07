package ViewModels.Source;

import Models.Source.UserDetails;
import Services.Source.DBService;
import Services.Source.GoogleAuthenticator;
import ViewModels.Interface.IUserViewModel;
import Views.Source.UserView;

import org.apache.log4j.Logger;

public class UserViewModel implements IUserViewModel {

    private DBService dbService;
    final static Logger logger = Logger.getLogger(UserView.class);

    public UserViewModel() {
        this.dbService = new DBService();
    }

    public String doLogin(UserDetails userDetails) {
        logger.info("Trying to login");
        if (!dbService.isUserExist(userDetails)) {
            logger.error("User does not exist");
            return null;
        }

        String emailuser= dbService.getEmail(userDetails);
        logger.info("success! email=" + emailuser);
        return dbService.getName(emailuser);
    }

    public boolean doRegister(UserDetails userDetails) {
        logger.info("Registering with Email=" + userDetails.getEmail()
                + " Password=" + userDetails.getPass());
        return dbService.addUser(userDetails);
    }

    public UserDetails doLoginWithGoogle() {
        logger.info("Trying to login with google credentials");
        UserDetails  user = new GoogleAuthenticator().authenticate();

        return user;
    }
}
