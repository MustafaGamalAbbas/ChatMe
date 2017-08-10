package android.mobilestudio.chatme.models.message.childs;

import android.mobilestudio.chatme.models.message.parent.Message;
import android.mobilestudio.chatme.screens.chat.ChatInteractor;
import android.mobilestudio.chatme.screens.chat.ChatInteractorImpl;

/**
 * Created by pisoo on 7/31/2017.
 */

public class TextMessage extends Message {

    private ChatInteractor interactor;

    public TextMessage() {
        interactor = new ChatInteractorImpl();
    }

    public void sentMessage() {
        interactor.addMessage(this);
    }

    public void editMessage() {
    }

    public void deleteMessage() {
    }
}
