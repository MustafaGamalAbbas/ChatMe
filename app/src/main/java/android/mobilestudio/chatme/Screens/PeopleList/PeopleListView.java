package android.mobilestudio.chatme.screens.peopleList;

import android.mobilestudio.chatme.models.Person;

import java.util.List;

/**
 * Created by pisoo on 7/30/2017.
 */

public interface PeopleListView {

    void setAdapter(List<Person> list);
    void notifyAdapter ();
    void returnToLoginScreen ();
    void toProfile(Person user);

}
