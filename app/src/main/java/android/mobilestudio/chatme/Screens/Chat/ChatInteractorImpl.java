package android.mobilestudio.chatme.Screens.Chat;

import android.mobilestudio.chatme.Models.Message.Parent.Message;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by pisoo on 7/31/2017.
 */

public class ChatInteractorImpl implements ChatInteractor {
    private FirebaseDatabase database ;
    DatabaseReference databaseReference ;

    private static String Id  , MyID ;
    public ChatInteractorImpl() {
        database  = FirebaseDatabase.getInstance();
        MyID = FirebaseAuth.getInstance().getCurrentUser().getUid();

    }

    @Override
    public List<Message> getPreviousMessages(final OnMessagesListener listener, String id ) {
        Id = id ;
        final List<Message> list = new ArrayList<>();
        databaseReference = database.getReference("/NanoChats/"+MyID+"/"+id);

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                list.add(dataSnapshot.getValue(Message.class) ) ;
                listener.OnItemAdded();
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
        databaseReference.addChildEventListener(childEventListener);
        return list ;    }

    @Override
    public void addMessage(Message message) {
        String Time = Calendar.getInstance().getTime().getYear() +""+
                Calendar.getInstance().getTime().getMonth()  +""+
                Calendar.getInstance().getTime().getDay() +""+
                Calendar.getInstance().getTime().getHours() +""+
                Calendar.getInstance().getTime().getMinutes() +""+
                Calendar.getInstance().getTime().getSeconds();

        databaseReference =  database.getReference("/NanoChats/"+MyID+"/"+Id+ "/"+Time);
        message.setIt_is_myMessage(true);
        databaseReference.setValue(message);
        databaseReference =  database.getReference("/NanoChats/"+Id+"/"+MyID+ "/"+Time);
        message.setIt_is_myMessage(false);
        databaseReference.setValue(message);

    }
}
