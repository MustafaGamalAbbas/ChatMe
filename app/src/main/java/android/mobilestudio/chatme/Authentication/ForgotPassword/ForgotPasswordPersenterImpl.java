package android.mobilestudio.chatme.authentication.forgotPassword;

import android.mobilestudio.chatme.R;

/**
 * Created by pisoo on 7/30/2017.
 */

public class ForgotPasswordPersenterImpl implements ForgotPasswordPresenter, ForgotPasswordInteractor.OnSentEmailFinishedListener {
    private ForgotPasswordView forgotPasswordView;
    private ForgotPasswordInteractor forgotPasswordInteractor;

    public ForgotPasswordPersenterImpl(ForgotPasswordView forgotPasswordView) {
        this.forgotPasswordView = forgotPasswordView;
        this.forgotPasswordInteractor = new ForgotPasswordInteractorImpl();
    }

    @Override
    public void onUsernameError() {
        forgotPasswordView.hideProgress();
        forgotPasswordView.setUsernameError();
    }

    @Override
    public void failToSent() {
        forgotPasswordView.hideProgress();
        forgotPasswordView.displayToast(R.string.fail_sent_email);
    }

    @Override
    public void onSuccess() {
        if (forgotPasswordInteractor != null) {
            forgotPasswordView.displayToast(R.string.forgot_password_msg);
            forgotPasswordView.hideProgress();
            forgotPasswordView.returnToSignInScreen();
        }
    }

    @Override
    public void onDestroy() {
        forgotPasswordView = null;
    }

    @Override
    public void validateCredentials(String email) {
        if (forgotPasswordInteractor != null) {
            forgotPasswordView.showProgress();
        }

        forgotPasswordInteractor.sentEmailToRegisteredAccount(email, this);
    }
}
