package android.mobilestudio.chatme.Authentication.SignUp;

/**
 * Created by pisoo on 7/30/2017.
 */

public interface SignUpView {

    void showProgress();

    void hideProgress();

    void setFristNameError();

    void setLastNameError();

    void setEmailError();

    void diplayToast (int message );

    void setPasswordError();

    void setConfrimPasswordError();

    void navigateToHome();

    void hideKeyboard();

    void releaseDatePickerDialog () ;

    void changeGender ();

    void setTiltetoToolbar (int id);
    void setAnimToOrangeImage ();
}
