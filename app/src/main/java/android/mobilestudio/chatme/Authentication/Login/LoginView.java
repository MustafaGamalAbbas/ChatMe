package android.mobilestudio.chatme.authentication.login;

/**
 * Created by pisoo on 7/30/2017.
 */

public interface LoginView  {
    void showProgress();

    void hideProgress();

    void setEmailError();

    void diplayToast (int message );

    void setPasswordError();

    void navigateToHome();

    void hideKeyboard();
    void setTiltetoToolbar (int id);
}
