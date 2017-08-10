package android.mobilestudio.chatme.authentication.login;

/**
 * Created by pisoo on 7/30/2017.
 */

public interface LoginPresenter  {
    void onDestroy () ;
    void onStart () ;
    void OnStop();
    void onResume();
    void onCreate ();
    void validateCredentials (String email ,String password ) ;
}
