package android.mobilestudio.chatme.screens.peopleList;

import android.mobilestudio.chatme.models.Person;

import java.util.List;

/**
 * Created by pisoo on 7/30/2017.
 */

public interface PeopleListInteractor {

     interface OnGetFinishedListener{
        void  OnItemAdded () ;
         void onItemRemoved ();
         void onItemEdited ();
         void onGetPerson(Person person) ;
     }
    List<Person> getListOfPerson (OnGetFinishedListener listener) ;
    void Logout();
    Person getPerson(OnGetFinishedListener listener  );

}
