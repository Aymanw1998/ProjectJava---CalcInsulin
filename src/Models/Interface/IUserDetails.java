package Models.Interface;

public interface IUserDetails {

    /**
     * @return password of User
     */
    public String getPass();

    /**
     * @param pass is password, for change the password of User
     */
    public void setPass(String pass);

    /**
     * @return First Name of User
     */
    public String getFirstName();

    /**
     * @param firstName is First Name, for change the First Name of User
     */
    public void setFirstName(String firstName);

    /**
     * @return Last Name of User
     */
    public String getLastName();

    /**
     * @param lastName is Last Name, for change the Last Name of User
     */
    public void setLastName(String lastName);

    /**
     * @return Email of User, is the username for user
     */
    public String getEmail();

    /**
     * @param email is Email, for change the email/username of User
     */
    public void setEmail(String email);

    /**
     * @return Phone of User
     */
    public String getPhone();

    /**
     * @param phone is Phone, for change the Phone of User
     */
    public void setPhone(String phone);

}
