package android.mobilestudio.chatme.screens.chat;

import android.mobilestudio.chatme.models.message.parent.Message;

import java.util.List;

/**
 * Created by pisoo on 7/31/2017.
 */

public interface ChatView {

    void setAdapter(List<Message> list);
    void notifyAdapter ();
    void hideKeyboard();
    void  erase_EditTexy_Message ();
     void setTitleOfToolbar (String name);
}
