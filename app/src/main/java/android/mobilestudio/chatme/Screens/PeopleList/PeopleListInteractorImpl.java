package android.mobilestudio.chatme.Screens.PeopleList;

import android.mobilestudio.chatme.Models.Person;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

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
        DatabaseReference ref = database.getReference("/Persons");

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


}
