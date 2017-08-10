package android.mobilestudio.chatme.screens;

import android.mobilestudio.chatme.models.Person;
import android.mobilestudio.chatme.R;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditProfile extends AppCompatActivity {
    Person userToEdit;
    private EditText mFirstName, mLastName, mEmailAddress, mPassword, mConPassword, mBirthDate, mPosition, mPhone;
    private Button mSave;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private String oldPassword, oldEmail;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        if (getIntent() != null) {
            userToEdit = (Person) getIntent().getExtras().get("userToEdit");
            oldEmail = userToEdit.getEmail();
            oldPassword = userToEdit.getPassword();
        }
        firebaseDatabase = FirebaseDatabase.getInstance();
        linkViews();
        setView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            SaveClicked();
            Toast.makeText(this, R.string.saved, Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void linkViews() {
        mFirstName = (EditText) findViewById(R.id.ed_FirstName);
        mLastName = (EditText) findViewById(R.id.ed_LastName);
        mEmailAddress = (EditText) findViewById(R.id.ed_EmailAddress);
        mPassword = (EditText) findViewById(R.id.ed_password);
        mBirthDate = (EditText) findViewById(R.id.ed_birthDatee);
        mPosition = (EditText) findViewById(R.id.ed_position);
        mPhone = (EditText) findViewById(R.id.ed_phone);
        mSave = (Button) findViewById(R.id.bt_save);
    }

    private void setView() {
        mFirstName.setText(userToEdit.getFirstName());
        mLastName.setText(userToEdit.getLastName());
        mEmailAddress.setText(userToEdit.getEmail());
        mPassword.setText(userToEdit.getPassword());
        mBirthDate.setText(userToEdit.getBirthDate());
        mPosition.setText(userToEdit.getPosition());
        mPhone.setText(userToEdit.getPhone());
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveClicked();
                Toast.makeText(EditProfile.this, R.string.saved, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void SaveClicked() {
        if (!validatePassword(mPassword.getText().toString())) {
            mPassword.setError("Password too short, enter minimum 6 characters!");
            return;
        }
        if (!validateEmail(mEmailAddress.getText().toString())) {
            mEmailAddress.setError("Email Address is invalid .");
            return;
        }
        if (!oldPassword.equals(mPassword.getText().toString())) {
            /// change password
            changePassword();
        }
        if (!oldEmail.equals(mEmailAddress.getText().toString())) {
            /// change Email
            changeEmail();
        }
        updatePersonData();
        uploadAccountData(userToEdit);
    }

    private void changePassword() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.updatePassword(mPassword.getText().toString().trim())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(EditProfile.this, R.string.password_updated, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(EditProfile.this, R.string.failed_to_update_pass, Toast.LENGTH_SHORT).show();
                            // progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

    private boolean validateEmail(String email) {
        Matcher matcher;
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void changeEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.updateEmail(mEmailAddress.getText().toString().trim())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(EditProfile.this, R.string.email_updated, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(EditProfile.this, R.string.failed_to_update, Toast.LENGTH_LONG).show();
                            //  Log.v("Errrrror",task.getException().getMessage().toString());
                        }
                    }
                });
    }

    private boolean validatePassword(String password) {
        return password.length() > 5;
    }

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

    private void updatePersonData() {
        userToEdit.setFirstName(mFirstName.getText().toString().trim());
        userToEdit.setLastName(mLastName.getText().toString().trim());
        userToEdit.setEmail(mEmailAddress.getText().toString().trim());
        userToEdit.setPassword(mPassword.getText().toString().trim());
        userToEdit.setPosition(mPosition.getText().toString().trim());
        userToEdit.setPhone(mPhone.getText().toString().trim());
        userToEdit.setBirthDate(mBirthDate.getText().toString().trim());

    }

}
