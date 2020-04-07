package Services.Interface;

import com.google.api.client.auth.oauth2.Credential;
import Models.Source.UserDetails;

public interface IGoogleAuthenticator {

	/**
	 * on function -
	 * Connection to google account
	 *
	 * @return all Details of user from google account
	 */
	public UserDetails authenticate();

	/**
	 * on function -
	 * Authorizes the installed application to access user's protected data.
	 *
	 * @return Credential for connection with google
	 * @throws Exception if have exception when connection with google
	 */
	public Credential authorize() throws Exception;
}
