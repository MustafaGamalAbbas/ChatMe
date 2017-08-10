package android.mobilestudio.chatme;

import android.mobilestudio.chatme.Models.Person;
import android.mobilestudio.chatme.Screens.PeopleList.PeopleListInteractor;
import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pisoo on 8/9/2017.
 */

public class   MyJobService extends JobService {
    FirebaseDatabase database ;
    public static GetStateOfPerson stateOfPerson ;
    @Override
    public boolean onStartJob(JobParameters job) {
        database  = FirebaseDatabase.getInstance();
        getListOfPerson();
        return true; // Answers the question: "Is there still work going on?"
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false; // Answers the question: "Should this job be retried?"
    }

    public List<Person> getListOfPerson( ) {
        final List<Person> list = new ArrayList<>();
        DatabaseReference ref = database.getReference("/people");

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                list.add(dataSnapshot.getValue(Person.class) ) ;
               // listener.OnItemAdded();
               // dataSnapshot
                stateOfPerson.onGetState(dataSnapshot.child("active").getValue(Boolean.class));
              //  Log.v("dataSnapshotHere  " ,String.valueOf(dataSnapshot.child("active").getValue(Boolean.class)) ) ;
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
               // listener.onItemEdited();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
               // listener.onItemRemoved();
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
}