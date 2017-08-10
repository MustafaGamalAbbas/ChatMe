package android.mobilestudio.chatme.screens.chat;

import android.mobilestudio.chatme.models.Person;
import android.mobilestudio.chatme.models.message.childs.TextMessage;

/**
 * Created by pisoo on 7/31/2017.
 */

public class ChatPresenterImpl implements ChatPresenter ,ChatInteractor.OnMessagesListener {

    private  ChatView  mView ;
    private  ChatInteractor interactor ;
    public ChatPresenterImpl(ChatView view) {
        this.mView = view ;
        interactor = new ChatInteractorImpl() ;
    }

    @Override
    public void OnItemAdded() {
        if(mView!=null){
            mView.notifyAdapter();
        }
    }

    @Override
    public void onItemRemoved() {
        if(mView!=null){
            mView.notifyAdapter();
        }
    }

    @Override
    public void onItemEdited() {
        if(mView!=null){
            mView.notifyAdapter();
        }
    }

    @Override
    public void addTextMessage(String message ) {
        TextMessage m = new TextMessage();
        m.setContent(message);
        m.sentMessage();
        mView.hideKeyboard();
        mView.erase_EditTexy_Message();
    }

    @Override
    public void onDestroy() {
            mView = null ;
    }

    @Override
    public void onCreate(Person person) {
        mView.setAdapter(interactor.getPreviousMessages(this,person.getId()));
        mView.setTitleOfToolbar(person.getFirstName()+" "+person.getLastName());
    }
}
