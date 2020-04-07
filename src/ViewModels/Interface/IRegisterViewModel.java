package ViewModels.Interface;

import Exceptions.MyException;
import Models.Source.UserDetails;

public interface IRegisterViewModel {

	/**
	 * on function -
	 * First - it checks if there are any felds that need to be filled
	 * 		if the required fields are full so moves on all user to Database (mongoDB)
	 * 				return like a return addUser( in DBService class)
	 * 		else throws an error
	 *
	 * @param userDetails is user (all Details of user)
	 * @return	 True if the user is added to a database or False if not (for most, the user with same email/username exists).
	 * @throws MyException if one or more details is Empty
	 */
	public boolean doRegister(UserDetails userDetails) throws MyException;

}
