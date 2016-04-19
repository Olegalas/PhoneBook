package ua.phonebook.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dexter on 15.04.16.
 */
@Entity
@Table(name = "users")
public class User extends IdGenerate{

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String pass;

    private String firstName;

    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String mobileTelephone;

    private String homeTelephone;

    @ManyToOne
    @JoinColumn(name="friend_id", referencedColumnName = "id")
    private User toPhoneBook;

    @OneToMany(mappedBy = "toPhoneBook", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> phoneBook = new ArrayList<>();

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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

    public String getMobileTelephone() {
        return mobileTelephone;
    }

    public void setMobileTelephone(String mobileTelephone) {
        this.mobileTelephone = mobileTelephone;
    }

    public String getHomeTelephone() {
        return homeTelephone;
    }

    public void setHomeTelephone(String homeTelephone) {
        this.homeTelephone = homeTelephone;
    }

    public User getToPhoneBook() {
        return toPhoneBook;
    }

    public void setToPhoneBook(User toPhoneBook) {
        this.toPhoneBook = toPhoneBook;
    }

    public List<User> getPhoneBook() {
        return phoneBook;
    }

    public void setPhoneBook(List<User> phoneBook) {
        this.phoneBook = phoneBook;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobileTelephone='" + mobileTelephone + '\'' +
                ", homeTelephone='" + homeTelephone + '\'' +
                ", toPhoneBook=" + toPhoneBook +
                ", phoneBook=" + phoneBook +
                '}';
    }
}
