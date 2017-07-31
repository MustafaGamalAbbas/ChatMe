package android.mobilestudio.chatme.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by pisoo on 6/29/2017.
 */

public class Person implements Serializable{
    private String Id=""  ;
    private String FirstName ;
    private String LastName ;
    private String Email;
    private String Password;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPassword() {
        return Password;
    }

    public Person() {
    }

    public void setPassword(String password) {
        Password = password;
    }
    private String Gender ;
    private String BirthDate ;


    public Person(String birthDate, String email, String firstName, String gender, String group, String lastName, String password, int score) {
        BirthDate = birthDate;
        Email = email;
        FirstName = firstName;
        Gender = gender;
         LastName = lastName;
        Password = password;
    }
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }

}
