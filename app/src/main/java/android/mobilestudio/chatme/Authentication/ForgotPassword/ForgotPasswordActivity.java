package android.mobilestudio.chatme.authentication.forgotPassword;

import android.graphics.Typeface;
import android.mobilestudio.chatme.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener, ForgotPasswordView {
    private EditText mEmailAddress;
    private Button mSent;
    private ProgressBar progressBar;
    private ForgotPasswordPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().setTitle("Forgot Password");
        linkViews();
        mSent.setOnClickListener(this);
        presenter = new ForgotPasswordPersenterImpl(this);
    }

    private void linkViews() {
        TextView text = (TextView) findViewById(R.id.tv_forgotPasswordMsg);
        Typeface tf = Typeface.createFromAsset(getAssets(), "Fonts/Gabriola.ttf");
        text.setTypeface(tf);
        mEmailAddress = (EditText) findViewById(R.id.ed_EmailAddress);
        mSent = (Button) findViewById(R.id.bt_sent);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View v) {
        presenter.validateCredentials(mEmailAddress.getText().toString().trim());
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
    public void setUsernameError() {
        mEmailAddress.setError(getString(R.string.invalid_email));
    }

    @Override
    public void displayToast(int message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void returnToSignInScreen() {
        finish();
    }
}