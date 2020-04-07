package Models.Source;

import Models.Interface.IUserDetails;

public class UserDetails implements IUserDetails {

    private String pass;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public UserDetails(String email, String pass) {
        super();
        this.email = email;
        this.pass = pass;
    }

    public UserDetails(String email, String pass, String firstname,String lastName,String phone) {
        super();
        this.email = email;
        this.pass = pass;
        this.firstName = firstname;
        this.lastName = lastName;
        this.phone = phone;
    }

    public String getPass() { return pass; }

    public void setPass(String pass) { this.pass = pass; }

    public String getFirstName() { return this.firstName; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() { return this.lastName; }

    @Override
    public void setLastName(String lastName) { this.lastName = lastName; }

    @Override
    public String getEmail() { return this.email; }

    @Override
    public void setEmail(String email) { this.email = email; }

    @Override
    public String getPhone() { return this.phone; }

    @Override
    public void setPhone(String phone) { this.phone = phone; }

}
