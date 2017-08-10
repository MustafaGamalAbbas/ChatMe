package android.mobilestudio.chatme.authentication.forgotPassword;

/**
 * Created by pisoo on 7/30/2017.
 */

public interface ForgotPasswordInteractor {

    interface OnSentEmailFinishedListener {

        void onUsernameError();
        void failToSent () ;
        void onSuccess();
    }
    void sentEmailToRegisteredAccount( String email , OnSentEmailFinishedListener onsentListener ) ;

}
