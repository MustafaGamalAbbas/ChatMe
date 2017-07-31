package android.mobilestudio.chatme.Screens.PeopleList;

/**
 * Created by pisoo on 7/30/2017.
 */

public class PeopleListPresenteImpl implements PeopleListPresenter , PeopleListInteractor.OnGetFinishedListener{
    PeopleListView mView ;
    PeopleListInteractor interactor ;
     public  PeopleListPresenteImpl (PeopleListView view ){
         mView = view ;
         interactor  = new PeopleListInteractorImpl() ;
     }
    @Override
    public void onDestroy() {
        mView = null ;
    }

    @Override
    public void onCreate() {
       // interactor.getListOfPerson();
        mView.setAdapter(interactor.getListOfPerson(this));
    }

    @Override
    public void logoutCurrentUser() {
        interactor.Logout();
        mView.returnToLoginScreen();
    }

    @Override
    public void OnItemAdded() {
            mView.notifyAdapter();
    }

    @Override
    public void onItemRemoved() {
        mView.notifyAdapter();
    }

    @Override
    public void onItemEdited() {
        mView.notifyAdapter();

    }
}
