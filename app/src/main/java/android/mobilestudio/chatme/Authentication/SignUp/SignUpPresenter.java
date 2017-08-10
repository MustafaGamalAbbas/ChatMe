package android.mobilestudio.chatme.authentication.signUp;

import android.mobilestudio.chatme.models.Person;

/**
 * Created by pisoo on 7/30/2017.
 */

public interface SignUpPresenter {
    void onDestroy();

    void SignUpUser(Person person, String ConfrimPassword);

    void GenderClicked();

    void BirthdateClickd();

    void onCreate();
}
