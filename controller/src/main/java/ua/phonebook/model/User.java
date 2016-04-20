package ua.phonebook.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dexter on 15.04.16.
 */
@Entity
@Table(name = "users")
public class User extends Login{

    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(nullable = false, length = 20)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String mobilePhone;

    @Column(nullable = false)
    private String homePhone;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Contact> phoneBook = new ArrayList<>();

    public User() {
    }

    public User(RegistrationForm form){

        login = form.login;
        pass = form.pass;
        firstName = form.getFirstName();
        lastName = form.getLastName();
        email = form.getEmail();
        mobilePhone = form.getMobilePhone();
        homePhone = form.getHomePhone();

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Contact> getPhoneBook() {
        return phoneBook;
    }

    public void setPhoneBook(List<Contact> phoneBook) {
        this.phoneBook = phoneBook;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobileTelephone='" + mobilePhone + '\'' +
                ", homeTelephone='" + homePhone + '\'' +
                ", phoneBook=" + phoneBook +
                '}';
    }
}
