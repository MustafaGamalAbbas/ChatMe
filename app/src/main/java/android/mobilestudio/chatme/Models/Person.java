package android.mobilestudio.chatme.models;

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
    private String Position ;
    private int Age ;
    private String phone;
    private Boolean active ;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String positoin) {
        Position = positoin;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

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
