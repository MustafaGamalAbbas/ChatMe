package android.mobilestudio.chatme.authentication.forgotPassword;

/**
 * Created by pisoo on 7/30/2017.
 */

public interface ForgotPasswordPresenter {
    void onDestroy();

    void validateCredentials(String username);
}
