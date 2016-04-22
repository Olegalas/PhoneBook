package ua.phonebook.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dexter on 15.04.16.
 */
@Entity
@Table(name = "users")
public class User extends Login{

    @Size(min = 4, max = 20, message = "minimum 4 symbols, maximum 20 symbols")
    @NotEmpty(message = "Please enter your first name")
    @Column(nullable = false, length = 20)
    private String firstName;

    @Size(min = 4, max = 20, message = "minimum 4 symbols, maximum 20 symbols")
    @NotEmpty(message = "Please enter your last name")
    @Column(nullable = false, length = 20)
    private String lastName;

    @Column(nullable = false, unique = true)
    @Email(message = "Please enter your email correctly")
    @NotEmpty(message = "Please enter your email")
    private String email;

    @NotEmpty(message = "Please enter your number of mobile telephone")
    @Column(nullable = false)
    private String mobilePhone;

    @NotEmpty(message = "Please enter your number of home telephone")
    @Column(nullable = false)
    private String homePhone;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Contact> phoneBook = new ArrayList<>();

    public User() {
    }

    public User(EditModel form){

        login = form.login;
        pass = form.getPass();
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
