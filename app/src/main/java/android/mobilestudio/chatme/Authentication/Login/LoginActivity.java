package android.mobilestudio.chatme.authentication.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.mobilestudio.chatme.authentication.forgotPassword.ForgotPasswordActivity;
import android.mobilestudio.chatme.authentication.signUp.SignUpActivity;
import android.mobilestudio.chatme.R;
import android.mobilestudio.chatme.screens.peopleList.PeopleList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    private ProgressBar progressBar;
    private Button mCreateAccount, mLogin;
    private EditText mEmailAddress, mPassword;
    private TextView mForgotPassword;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        linkViews();
        presenter = new LoginPresenterImpl(this);
        mLogin.setOnClickListener(this);
        mCreateAccount.setOnClickListener(this);
        mForgotPassword.setOnClickListener(this);
        presenter.onCreate();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // presenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.OnStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    private void linkViews() {
        //tv_Or
        TextView text = (TextView) findViewById(R.id.tv_Or);
        Typeface tf = Typeface.createFromAsset(getAssets(), "Fonts/Gabriola.ttf");
        text.setTypeface(tf);
        mForgotPassword = (TextView) findViewById(R.id.tv_forgotPassword);
        mCreateAccount = (Button) findViewById(R.id.bt_CreateAccount);
        mLogin = (Button) findViewById(R.id.bt_Login);
        mEmailAddress = (EditText) findViewById(R.id.ed_EmailAddress);
        mPassword = (EditText) findViewById(R.id.ed_password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

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
    public void setTiltetoToolbar(int id) {
        getSupportActionBar().setTitle(id);
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
    public void setEmailError() {
        mEmailAddress.setError(getResources().getString(R.string.invalid_email));
    }

    @Override
    public void displayToast(int message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPasswordError() {
        mPassword.setError(getResources().getString(R.string.invalid_password));
    }

    @Override
    public void navigateToHome() {
        startActivity(new Intent(LoginActivity.this, PeopleList.class));
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // ForgotPassword clicked
            case R.id.tv_forgotPassword:
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
                break;
            case R.id.bt_Login:
                presenter.validateCredentials(mEmailAddress.getText().toString().trim()
                        , mPassword.getText().toString().trim());
                break;
            case R.id.bt_CreateAccount:
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                break;
        }
    }
}