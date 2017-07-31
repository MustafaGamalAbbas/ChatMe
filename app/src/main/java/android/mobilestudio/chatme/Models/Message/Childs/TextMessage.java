package android.mobilestudio.chatme.Models.Message.Childs;

import android.mobilestudio.chatme.Models.Message.Parent.Message;
import android.mobilestudio.chatme.Screens.Chat.ChatInteractor;
import android.mobilestudio.chatme.Screens.Chat.ChatInteractorImpl;

/**
 * Created by pisoo on 7/31/2017.
 */

public class TextMessage extends Message {

    private ChatInteractor  interactor ;
    public TextMessage() {
        interactor = new ChatInteractorImpl();
    }

    public void sentMessage(){
        interactor.addMessage(this);
    }
    public void editMessage(){
    }
    public void deleteMessage(){
    }
}
