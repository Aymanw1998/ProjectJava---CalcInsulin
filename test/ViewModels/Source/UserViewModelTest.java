package ViewModels.Source;

import Models.Source.UserDetails;
import Services.Source.DBService;
import Services.Source.GoogleAuthenticator;
import Views.Source.UserView;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.apache.log4j.Logger;

import static org.junit.Assert.*;

public class UserViewModelTest {
    private UserViewModel userViewModel;
    private DBService dbService;
    private UserDetails userDetails;
    final static Logger logger = Logger.getLogger(UserView.class);
    @Before
    public void setUp() throws Exception {
        userViewModel = new UserViewModel();
        dbService= new DBService();
        userDetails = new UserDetails("ayman@test.com","test","Ayman","Test","0508241000");
    }

    @After
    public void tearDown() throws Exception {
        userViewModel =null;
        dbService = null;
        userDetails = null;
    }

    @Test
    public void doLogin() {
        logger.info("Trying to login");
        if (!dbService.isUserExist(userDetails)) {
            logger.error("User does not exist");
        }

        String email = dbService.getEmail(userDetails);
        String email2 = userViewModel.doLogin(userDetails);
        assertEquals(email,email2);
        logger.info("success! userId=" + email);
    }

    @Test
    public void doRegister() {
        logger.info("Registering with Email=" + userDetails.getEmail()
                + " Password=" + userDetails.getPass());
        boolean b=dbService.addUser(userDetails);
        boolean b2=userViewModel.doRegister(userDetails);
        assertEquals(b,b2);

    }

    @Test
    public void doLoginWithGoogle() {
        logger.info("Trying to login with google credentials");
        UserDetails user = new GoogleAuthenticator().authenticate();
        UserDetails user2 = userViewModel.doLoginWithGoogle();
        assertEquals(user.getEmail(),user2.getEmail());
    }
}