package android.mobilestudio.chatme.authentication.forgotPassword;

/**
 * Created by pisoo on 7/30/2017.
 */

public interface ForgotPasswordView {
    void showProgress();

    void hideProgress();

    void setUsernameError(String message);

    void displayToast(int message);

    void returnToSignInScreen();
}
