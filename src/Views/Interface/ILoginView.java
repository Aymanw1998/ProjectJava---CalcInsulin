package Views.Interface;

import Models.Source.UserDetails;

public interface ILoginView {

    public void handleLoginWithGoogleResult(UserDetails res);

    public void handleRegister();

    public void handleLoginResult(String res);

    public void initializeUI();
}
