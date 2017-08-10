package android.mobilestudio.chatme.screens.chat;

import android.mobilestudio.chatme.models.message.parent.Message;

import java.util.List;

/**
 * Created by pisoo on 7/31/2017.
 */

public interface ChatInteractor  {
    interface OnMessagesListener{
        void  OnItemAdded () ;
        void onItemRemoved ();
        void onItemEdited ();
    }
    List<Message> getPreviousMessages (OnMessagesListener listener , String id ) ;
     void addMessage (Message message);
}
