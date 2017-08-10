package android.mobilestudio.chatme.screens.chat;

import android.mobilestudio.chatme.models.Person;

/**
 * Created by pisoo on 7/31/2017.
 */

public interface ChatPresenter {
    void addTextMessage(String message);

    void onDestroy();

    void onCreate(Person person);
}
