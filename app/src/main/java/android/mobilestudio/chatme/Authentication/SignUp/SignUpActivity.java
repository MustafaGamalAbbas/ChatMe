package android.mobilestudio.chatme.Authentication.SignUp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.mobilestudio.chatme.R;
import android.mobilestudio.chatme.Models.Person;
import android.mobilestudio.chatme.Screens.PeopleList.PeopleList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SignUpActivity extends AppCompatActivity implements SignUpView ,View.OnClickListener {

    private CheckBox mMale, mFemale;
    private EditText mFirstName, mLastName, mEmailAddress, mPassword, mConPassword, mBirthDate ,mPosition ,mPhone;
    private ProgressBar progressBar;
    private ImageView mOrangeImg;
    private Person myOwnData;
    private FirebaseDatabase firebaseDatabase;
    private SignUpPresenter presenter ;
      Calendar myCalendar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        linkViews();
        myOwnData = new Person();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mBirthDate.setOnClickListener(this);
        mMale.setOnClickListener(this);
        mFemale.setOnClickListener(this);

        presenter =  new SignUpPresenterImpl(this);
        presenter.onCreate();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_signup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_done) {
            fillPersonData();
            presenter.SignUpUser(myOwnData,mConPassword.getText().toString().trim());
        }
        return super.onOptionsItemSelected(item);
    }
    private void linkViews() {
        mFirstName = (EditText) findViewById(R.id.ed_FirstName);
        mLastName = (EditText) findViewById(R.id.ed_LastName);
        mEmailAddress = (EditText) findViewById(R.id.ed_EmailAddress);
        mPassword = (EditText) findViewById(R.id.ed_password);
        mConPassword = (EditText) findViewById(R.id.ed_confrimPassword);
        mBirthDate = (EditText) findViewById(R.id.ed_birthDatee);
        mPosition = (EditText) findViewById(R.id.ed_position);
        mPhone = (EditText) findViewById(R.id.ed_phone);
        mMale = (CheckBox) findViewById(R.id.cb_male);
        mMale.setChecked(true);
        mFemale = (CheckBox) findViewById(R.id.cb_female);
        mFemale.setChecked(false);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mOrangeImg = (ImageView) findViewById(R.id.iv_orangeImage);
    }
    private void fillPersonData() {
        myOwnData.setFirstName(mFirstName.getText().toString().trim());
        myOwnData.setLastName(mLastName.getText().toString().trim());
        myOwnData.setEmail(mEmailAddress.getText().toString().trim());
        myOwnData.setPassword(mPassword.getText().toString().trim());
        myOwnData.setPosition(mPosition.getText().toString().trim());
        myOwnData.setPhone(mPhone.getText().toString().trim());
        myOwnData.setActive(true);
        if (mMale.isChecked()) {
            myOwnData.setGender("Male");
        } else if (mFemale.isChecked()) {
            myOwnData.setGender("Female");
        }
        if (!mBirthDate.getText().toString().equals(""))
        {
            myOwnData.setBirthDate(mBirthDate.getText().toString().trim());
            myOwnData.setAge(Calendar.getInstance().getTime().getYear()- myCalendar.getTime().getYear());
        }
        else {
            myOwnData.setBirthDate("Not Entered ");
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setFristNameError() {
        mFirstName.setError("First Name is required .");
    }

    @Override
    public void setLastNameError() {
        mLastName.setError("Last Name is required .");
    }

    @Override
    public void setEmailError() {
        mEmailAddress.setError("Email Address is invalid .");
    }

    @Override
    public void diplayToast(int message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPasswordError() {
        mPassword.setError("Password too short, enter minimum 6 characters!");
    }

    @Override
    public void setConfrimPasswordError() {
        mConPassword.setError("Confirmation password does not match the password. ");
    }

    @Override
    public void navigateToHome() {
        startActivity(new Intent(SignUpActivity.this, PeopleList.class));
        finish();
    }

    @Override
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void releaseDatePickerDialog() {
         myCalendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(SignUpActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, dayOfMonth);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                mBirthDate.setText(sdf.format(myCalendar.getTime()));
            }
        }, 1992, 1, 1);
        dialog.show();
        mBirthDate.setTransformationMethod(null);
    }

    @Override
    public void changeGender() {
        if (mMale.isChecked()) {
            mMale.setChecked(false);
            mFemale.setChecked(true);
        }
        else if (mFemale.isChecked()) {
            mFemale.setChecked(false);
            mMale.setChecked(true);
        }
    }

    @Override
    public void setTiltetoToolbar( int id) {
        getSupportActionBar().setTitle(id);

    }

    @Override
    public void setAnimToOrangeImage() {
        AnimationSet as = new AnimationSet(true);
        as.setFillEnabled(true);
        as.setInterpolator(new BounceInterpolator());

        TranslateAnimation ta = new TranslateAnimation(350, -50, 0, 0);
        ta.setDuration(2000);
        as.addAnimation(ta);

        TranslateAnimation ta2 = new TranslateAnimation(0, 50, 0, 0);
        ta2.setDuration(2000);
        as.addAnimation(ta2);
        mOrangeImg.startAnimation(as);
    }

    @Override
    public void saveUserDataInSharedPreference() {
        SharedPreferences sharedpreferences = this.getSharedPreferences("SharedPre", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedpreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myOwnData);
        prefsEditor.putString("CurrentUser", json);
        prefsEditor.apply();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.ed_birthDatee :
                    presenter.BirthdateClickd();
                break ;
            case R.id.cb_male :
            case R.id.cb_female :
                presenter.GenderClicked();
                break ;
        }
    }

}
