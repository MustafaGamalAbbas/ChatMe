package android.mobilestudio.chatme.authentication.login;

/**
 * Created by pisoo on 7/30/2017.
 */

public interface LoginInteractor {
    interface OnLoginFinishedListener {

        void onUsernameError();

        void onPasswordError();

        void failedToLogin();

        void onSuccess();
    }

    void login(String email, String password, OnLoginFinishedListener listener);

    void onStart();

    void OnStop();

    void onResume(OnLoginFinishedListener listener);

    void onCreate(OnLoginFinishedListener listener);
}
