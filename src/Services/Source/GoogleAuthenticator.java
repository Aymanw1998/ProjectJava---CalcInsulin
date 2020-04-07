package Services.Source;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Tokeninfo;
import Models.Source.UserDetails;
import Services.Interface.IGoogleAuthenticator;

public class GoogleAuthenticator implements IGoogleAuthenticator {
	private final String APPLICATION_NAME = "InsulinApp";

	/** Directory to store user credentials. */
	private File DATA_STORE_DIR = new File(System.getProperty("/"), ".store/oauth2_sample");
	private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private FileDataStoreFactory dataStoreFactory;
	private HttpTransport httpTransport;

	private final List<String> SCOPES = Arrays.asList("https://www.googleapis.com/auth/userinfo.profile",
			"https://www.googleapis.com/auth/userinfo.email");

	private Oauth2 oauth2;
	private GoogleClientSecrets clientSecrets;
	private String fname;
	private String lastName;
	private String email;
	private String phone;


	public UserDetails authenticate() {
		try {

			httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);
			// authorization
			Credential credential = authorize();
			// set up global Oauth2 instance
			oauth2 = new Oauth2.Builder(httpTransport, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
			// run commands
			fname = oauth2.userinfo().get().execute().getGivenName();
			lastName = oauth2.userinfo().get().execute().getFamilyName();
			email = oauth2.userinfo().get().execute().getEmail();
			String pass = oauth2.userinfo().get().execute().getLocale();

			/**
			 * delete te file to save the connection with google and App
			 * Why?
			 * 		because We always want the user to choose which google account they want to connect to
			 */
			File file = new File(DATA_STORE_DIR, StoredCredential.DEFAULT_DATA_STORE_ID);
			if (file.exists()) {
				java.nio.file.Files.delete(file.toPath());
			}

			return new UserDetails(email,null,fname,lastName,phone);
			// success!

		} catch (IOException e) {
			return null;
		} catch (Throwable t) {
			return null;
		}
	}

	/** Authorizes the installed application to access user's protected data. */
	 public Credential authorize() throws Exception {
	 	// load client secrets
		 String json = "{\r\n" +
				 			"  \"installed\": {\r\n" +
				 				"    \"client_id\": \"497713811114-c5ac3i1tsar9f3aslmpkirje0ucsq4dg.apps.googleusercontent.com\"," +
				 				"\r\n" +
				 				"    \"client_secret\": \"0KrqEjONEzgQYy1fqMF-yibw\"\r\n" +
				 						"  }\r\n" +
				 		"}";
		 clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new StringReader(json));
		 if (clientSecrets.getDetails().getClientId().startsWith("Enter")
				 || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
			 System.out.println("Enter Client ID and Secret from https://code.google.com/apis/console/ "
					 + "into oauth2-cmdline-sample/src/main/resources/client_secrets.json");
			 System.exit(1);
		 }
		 // set up authorization code flow
		 GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY,
				 clientSecrets, SCOPES).setDataStoreFactory(dataStoreFactory).build();
		 // authorize
		 return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
	}

}
