package ViewModels.Interface;

import Models.Source.UserDetails;

public interface IUserViewModel {
    /**
     * on function -
     * first - checks if user with userDetails exists
     *          if not return null
     * second - if the user exists so save the id of user on value and return it
     *
     * @param userDetails is all Details of user
     * @return the id of the user in database if he exists or null if not
     */
    public String doLogin(UserDetails userDetails);

    /**
     *
     *
     * @return the details of user from her account in google
     */
    public UserDetails doLoginWithGoogle();
}
