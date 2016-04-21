package ua.phonebook.model;

import javax.persistence.*;

/**
 * Created by dexter on 19.04.16.
 */
@Entity
@Table(name = "contacts")
public class Contact extends IdGenerate{

    @Column(nullable = false, unique = true, length = 20)
    protected String login;

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

    @ManyToOne
    @JoinColumn(name="contact_id", referencedColumnName = "id")
    private User userId;

    public Contact() {
    }

    public Contact(Contact contact, User user){

        login = contact.login;
        firstName = contact.firstName;
        lastName = contact.lastName;
        email = contact.email;
        mobilePhone = contact.mobilePhone;
        homePhone = contact.mobilePhone;
        userId = user;

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Contact contact = (Contact) o;

        return login != null ? login.equals(contact.login) : contact.login == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (login != null ? login.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", userId=" + userId +
                '}';
    }
}
