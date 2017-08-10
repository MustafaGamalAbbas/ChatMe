package android.mobilestudio.chatme.screens.peopleList;

/**
 * Created by pisoo on 7/30/2017.
 */

public interface PeopleListPresenter {
    void onDestroy();

    void onCreate();

    void logoutCurrentUser();

    void moveToProfile();
}
