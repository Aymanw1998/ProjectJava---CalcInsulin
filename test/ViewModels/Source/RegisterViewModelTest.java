package ViewModels.Source;

import Exceptions.MyException;
import Models.Source.UserDetails;
import Services.Source.DBService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import Exceptions.MyException;


public class RegisterViewModelTest {
    private RegisterViewModel registerViewModel;
    private DBService dbService;
    final static Logger logger = Logger.getLogger(RegisterViewModel.class);
    private UserDetails userDetails;

    @Before
    public void setUp() throws Exception {
        registerViewModel = new RegisterViewModel();
        dbService= new DBService();
        userDetails = new UserDetails("ayman@test.com","test","Ayman","Test","0508241000");

    }

    @After
    public void tearDown() throws Exception {
        registerViewModel = null;
        dbService = null;
        userDetails = null;
    }

    @Test
    public void doRegister() throws MyException {
        logger.info("Registering with Email=" + userDetails.getEmail()
                + " Password=" + userDetails.getPass());
        boolean fNB = userDetails.getFirstName().isEmpty();
        boolean lNB = userDetails.getLastName().isEmpty();
        boolean passB = userDetails.getPass().isEmpty();
        boolean emailB = userDetails.getEmail().isEmpty();

        if (fNB	|| lNB || passB	|| emailB) {
            throw new MyException();
        }
        boolean b= dbService.addUser(userDetails), b2 = registerViewModel.doRegister(userDetails);
        assertEquals(b,b2);
    }

}
