package android.mobilestudio.chatme.Screens.PeopleList;

import android.mobilestudio.chatme.Models.Person;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.google.android.gms.internal.zzt.TAG;

/**
 * Created by pisoo on 7/30/2017.
 */

public class PeopleListInteractorImpl implements PeopleListInteractor {

    FirebaseAuth auth ;
    private FirebaseDatabase database ;
   public  PeopleListInteractorImpl (){
       database  = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
   }

    @Override
    public List<Person> getListOfPerson(final OnGetFinishedListener listener) {
        final List<Person> list = new ArrayList<>();
        DatabaseReference ref = database.getReference("/people");

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                list.add(dataSnapshot.getValue(Person.class) ) ;
                listener.OnItemAdded();
                Log.v("dataSnapshot " , dataSnapshot.getValue(Person.class).getEmail()) ;
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                listener.onItemEdited();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                listener.onItemRemoved();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        } ;
        ref.addChildEventListener(childEventListener);
        return list ;
    }

    @Override
    public void Logout() {
        auth.signOut();
     /*   startActivity(new Intent(MainScreen.this, LoginActivity.class));
        finish();*/
    }

    @Override
    public Person getPerson(  final OnGetFinishedListener listener ) {
        final Person[] person = {null};
        DatabaseReference ref = database.getReference("/people/"+FirebaseAuth.getInstance().getCurrentUser().getUid());
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                  listener.onGetPerson(dataSnapshot.getValue(Person.class));
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        ref.addValueEventListener(postListener);
        return person[0];
    }


}
