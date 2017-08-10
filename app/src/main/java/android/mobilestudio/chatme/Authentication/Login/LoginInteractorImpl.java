package android.mobilestudio.chatme.authentication.login;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pisoo on 7/30/2017.
 */

public class LoginInteractorImpl implements LoginInteractor {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public LoginInteractorImpl (){
        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void login(String email, String password, OnLoginFinishedListener listener) {
        if (!validateEmail(email)) {
            listener.onUsernameError();
            return;
        }
        if (!validatePassword(password)) {
            listener.onPasswordError();
            return;
        }
        LoginToFireBase(email, password, listener);
    }

    @Override
    public void onStart() {
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void OnStop() {
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onResume(OnLoginFinishedListener listener) {
        if (mAuth.getCurrentUser() != null) {
            // The Current user is Signed in
            listener.onSuccess();
        }
    }

    @Override
    public void onCreate(OnLoginFinishedListener listener) {
        if (mAuth.getCurrentUser() != null) {
            // The Current user is Signed in
            listener.onSuccess();
        }
    }

    private void LoginToFireBase(String email, String password, final OnLoginFinishedListener listener) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            // there was an error
                             listener.failedToLogin();

                        } else {
                            listener.onSuccess();
                        }
                    }
                });

    }

    private boolean validateEmail(String email) {
        Matcher matcher;
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean validatePassword(String password) {
        return password.length() > 5;
    }


}
