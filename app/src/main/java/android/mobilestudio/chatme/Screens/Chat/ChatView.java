package android.mobilestudio.chatme.Screens.Chat;

import android.mobilestudio.chatme.Models.Message.Parent.Message;

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
