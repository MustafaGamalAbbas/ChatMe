package android.mobilestudio.chatme.Screens.PeopleList;

import android.mobilestudio.chatme.Models.Person;

import java.util.List;

/**
 * Created by pisoo on 7/30/2017.
 */

public interface PeopleListView {

    void setAdapter(List<Person> list);
    void notifyAdapter ();
    void returnToLoginScreen ();

}
