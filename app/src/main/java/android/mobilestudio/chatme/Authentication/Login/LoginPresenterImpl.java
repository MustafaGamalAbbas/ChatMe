package android.mobilestudio.chatme.authentication.login;

import android.mobilestudio.chatme.R;

/**
 * Created by pisoo on 7/30/2017.
 */

public class LoginPresenterImpl implements LoginPresenter , LoginInteractor.OnLoginFinishedListener {
     LoginView mView ;
     LoginInteractor mInteractor ;
      public LoginPresenterImpl (LoginView view){

          this.mView = view ;
          this.mInteractor =new LoginInteractorImpl();
      }
    @Override
    public void onUsernameError() {
        if(mView != null){
            mView.hideProgress();
            mView.setEmailError();
        }
    }

    @Override
    public void onPasswordError() {
        if(mView != null){
            mView.hideProgress();
            mView.setPasswordError();
        }
    }

    @Override
    public void failedToLogin() {
        if(mView != null){
            mView.hideProgress();
            mView.diplayToast(R.string.auth_failed);
        }
    }

    @Override
    public void onSuccess() {
        if(mView !=null){
            mView.navigateToHome();
        }
    }
    @Override
    public void onDestroy() {
        mView = null ;
    }

    @Override
    public void onStart() {
      mInteractor.onStart();
    }

    @Override
    public void OnStop() {
        mInteractor.OnStop();
    }

    @Override
    public void onResume() {
        mInteractor.onResume(this);
    }

    @Override
    public void onCreate() {
        mInteractor.onCreate(this);
        mView.setTiltetoToolbar(R.string.login);
    }

    @Override
    public void validateCredentials(String email ,String password ) {
        if(mView !=null){
            mView.showProgress();
            mView.hideKeyboard();
        }
        mInteractor.login(email,password,this);
    }
}
