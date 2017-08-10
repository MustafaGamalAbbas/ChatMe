package android.mobilestudio.chatme.Screens.PeopleList;

import android.mobilestudio.chatme.Authentication.Login.LoginInteractor;
import android.mobilestudio.chatme.Models.Person;

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
