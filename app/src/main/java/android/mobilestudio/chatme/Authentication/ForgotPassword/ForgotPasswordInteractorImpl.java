package android.mobilestudio.chatme.Authentication.ForgotPassword;

import android.mobilestudio.chatme.R;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pisoo on 7/30/2017.
 */

public class ForgotPasswordInteractorImpl implements ForgotPasswordInteractor {

    private final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private FirebaseAuth auth;

    @Override
    public void sentEmailToRegisteredAccount(String email, OnSentEmailFinishedListener onsentListener) {

        if (validateEmail(email)) {
            sentToEmail(email,onsentListener);
        }
        else{
            onsentListener.onUsernameError();
          }
    }
    private void sentToEmail (String email , final OnSentEmailFinishedListener onSentEmailFinishedListener ){
        auth = FirebaseAuth.getInstance();
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            onSentEmailFinishedListener.onSuccess();
                        } else {
                            onSentEmailFinishedListener.failToSent();
                        }
                    }
                });
    }
    private boolean validateEmail(String email) {
        Matcher matcher;
        matcher = pattern.matcher(email);
        return matcher.matches();
    }


}
