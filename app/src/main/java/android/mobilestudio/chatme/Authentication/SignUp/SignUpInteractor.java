package android.mobilestudio.chatme.authentication.signUp;

import android.mobilestudio.chatme.models.Person;

/**
 * Created by pisoo on 7/30/2017.
 */

public interface SignUpInteractor  {
    interface OnSignUpFinishedListener {

        void onUsernameError();
        void onPasswordError();
        void failedToSignUp() ;
        void onSuccess();
    }
    void SignUp (String email , String Password , OnSignUpFinishedListener listener);
    void  uploadAccountData (Person person) ;
}
