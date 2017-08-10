package android.mobilestudio.chatme.screens;

import android.content.Intent;
import android.mobilestudio.chatme.models.Person;
import android.mobilestudio.chatme.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Profile extends AppCompatActivity {
    ImageView editButton;
    Person currentProfile;
    TextView name, position, email, phone, age, birthdate;
    Switch mASwitch;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        if (getIntent() != null) {
            currentProfile = (Person) getIntent().getExtras().get("CurrentUser");
        }
        firebaseDatabase = FirebaseDatabase.getInstance();

        linkViews();
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, EditProfile.class).putExtra("userToEdit", currentProfile));
            }
        });
        setViews();
        mASwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    DatabaseReference databaseReference = firebaseDatabase.getReference("/people/" + id);
                    databaseReference.child("active").setValue(isChecked);
                } else {
                    Log.e("Current User ", "is Null");
                }
            }
        });
    }

    private void setViews() {
        name.setText(currentProfile.getFirstName() + " " + currentProfile.getLastName());
        position.setText(currentProfile.getPosition());
        email.setText(currentProfile.getEmail());
        phone.setText(currentProfile.getPhone());
        String age_str = currentProfile.getAge() +getResources().getString(R.string.years);
        age.setText(age_str);
        birthdate.setText(currentProfile.getBirthDate());
        mASwitch.setChecked(currentProfile.getActive());
    }

    private void linkViews() {
        editButton = (ImageView) findViewById(R.id.edit_btn);
        name = (TextView) findViewById(R.id.tv_Name);
        position = (TextView) findViewById(R.id.tv_position);
        email = (TextView) findViewById(R.id.tv_email);
        phone = (TextView) findViewById(R.id.tv_phone);
        age = (TextView) findViewById(R.id.tv_age);
        birthdate = (TextView) findViewById(R.id.tv_birthdate);
        mASwitch = (Switch) findViewById(R.id.switchButton);

    }


}
