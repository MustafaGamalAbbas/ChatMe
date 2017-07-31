package android.mobilestudio.chatme.Screens.Chat;

import android.mobilestudio.chatme.Models.Person;

/**
 * Created by pisoo on 7/31/2017.
 */

public interface ChatPresenter {
    void addTextMessage(String message );
    void onDestroy();
    void onCreate(Person person) ;
}
