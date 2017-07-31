package android.mobilestudio.chatme.Authentication.SignUp;

import android.mobilestudio.chatme.Models.Person;
import android.mobilestudio.chatme.R;
import android.support.v7.view.menu.MenuView;

/**
 * Created by pisoo on 7/30/2017.
 */

public class SignUpPresenterImpl implements SignUpPresenter, SignUpInteractor.OnSignUpFinishedListener {
    SignUpView mView;
    SignUpInteractor mInteractor;

    public SignUpPresenterImpl(SignUpView view) {
        this.mView = view;
        this.mInteractor = new SignUpInteractorImpl();
    }

    @Override
    public void onUsernameError() {
        if (mView != null) {
            mView.setEmailError();
        }
    }

    @Override
    public void onPasswordError() {
        if (mView != null) {
            mView.setPasswordError();
        }
    }

    @Override
    public void failedToSignUp() {
        if (mView != null) {
            mView.diplayToast(R.string.auth_failed);
        }
    }

    @Override
    public void onSuccess() {
        mView.navigateToHome();
    }

    @Override
    public void onDestroy() {
        mView = null;
    }

    @Override
    public void SignUpUser(Person person , String confirmPassword ) {
        if (mView == null)
            return;

        if (!person.getPassword().equals(confirmPassword)) {
            mView.setConfrimPasswordError();
            return;
        }
        if (person.getFirstName().isEmpty()) {
            mView.setFristNameError();
            return;
        }
        if(person.getLastName().isEmpty()){
            mView.setLastNameError();
            return;
        }
        mView.showProgress();
        mInteractor.SignUp(person.getEmail(), person.getPassword(), this);
        mInteractor.uploadAccountData(person);
    }

    @Override
    public void GenderClicked() {
        if(mView !=null){
            mView.changeGender();
        }
    }
    @Override
    public void BirthdateClickd() {
        if(mView!=null){
            mView.releaseDatePickerDialog();
        }
    }

    @Override
    public void onCreate() {
        if(mView!=null){
            mView.setTiltetoToolbar(R.string.signup);
            mView.setAnimToOrangeImage();
        }
    }

}
