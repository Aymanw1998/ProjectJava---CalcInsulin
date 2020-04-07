package ViewModels.Source;

import Exceptions.MyException;
import Models.Source.UserDetails;
import Services.Source.DBService;
import ViewModels.Interface.IRegisterViewModel;
import org.apache.log4j.Logger;

public class RegisterViewModel implements IRegisterViewModel {
	private DBService dbService;
	final static Logger logger = Logger.getLogger(RegisterViewModel.class);

	public RegisterViewModel() {
		this.dbService = new DBService();
	}

	public boolean doRegister(UserDetails userDetails) throws MyException {
		logger.info("Registering with Email=" + userDetails.getEmail()
				+ " Password=" + userDetails.getPass());
		boolean fNB = userDetails.getFirstName().isEmpty();
		boolean lNB = userDetails.getLastName().isEmpty();
		boolean passB = userDetails.getPass().isEmpty();
		boolean emailB = userDetails.getEmail().isEmpty();

		if (fNB	|| lNB || passB	|| emailB) {
			throw new MyException();
		}
		return dbService.addUser(userDetails);
	}

}
