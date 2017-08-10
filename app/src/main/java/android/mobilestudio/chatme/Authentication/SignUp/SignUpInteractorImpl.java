package android.mobilestudio.chatme.authentication.signUp;

import android.mobilestudio.chatme.models.Person;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pisoo on 7/30/2017.
 */

public class SignUpInteractorImpl implements SignUpInteractor {
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase  ;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public SignUpInteractorImpl (){

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

    }
    @Override
    public void SignUp(String email, String password, OnSignUpFinishedListener listener) {
        if (!validateEmail(email)) {
            listener.onUsernameError();
            return;
        }
        if (!validatePassword(password)) {
            listener.onPasswordError();
            return;
        }
        signUpToFireBase(email, password, listener);
    }

    @Override
    public void uploadAccountData(Person person) {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
            DatabaseReference databaseReference = firebaseDatabase.getReference("/people/" + id);
            person.setId(id);
            databaseReference.setValue(person);
        } else {
            Log.e("Current User ", "is Null");
        }
    }

    private void signUpToFireBase(String email , String password , final OnSignUpFinishedListener listener) {
        Log.v("email ", email);
        Log.v("pa ", password);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.v("message", task.getException().getMessage());
                           listener.failedToSignUp();
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
