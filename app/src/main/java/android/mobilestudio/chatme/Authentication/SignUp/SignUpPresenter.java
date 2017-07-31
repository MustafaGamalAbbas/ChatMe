package android.mobilestudio.chatme.Authentication.SignUp;

import android.mobilestudio.chatme.Models.Person;

/**
 * Created by pisoo on 7/30/2017.
 */

public interface SignUpPresenter  {
    void  onDestroy () ;
    void  SignUpUser (Person person ,  String ConfrimPassword ) ;
    void  GenderClicked () ;
    void  BirthdateClickd () ;
    void  onCreate () ;
}
