package ua.phonebook.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by dexter on 19.04.16.
 */
@Entity
@Table(name = "contacts")
public class Contact extends IdGenerate{

    @Pattern(regexp = "[a-zA-Z0-9]*", message = "Only latins symbols and numbers")
    @Column(nullable = false, unique = true, length = 20)
    @Size(min = 4, max = 20, message = "minimum 4 symbols, maximum 20 symbols")
    @NotEmpty(message = "Please enter your login")
    protected String login;

    @Pattern(regexp = "[a-zA-Z0-9]*", message = "Only latins symbols and numbers")
    @Size(min = 4, max = 20, message = "minimum 4 symbols, maximum 20 symbols")
    @NotEmpty(message = "Please enter your first name")
    @Column(nullable = false, length = 20)
    private String firstName;

    @Pattern(regexp = "[a-zA-Z0-9]*", message = "Only latins symbols and numbers")
    @Size(min = 4, max = 20, message = "minimum 4 symbols, maximum 20 symbols")
    @NotEmpty(message = "Please enter your last name")
    @Column(nullable = false, length = 20)
    private String lastName;

    @Column(nullable = false, unique = true)
    @Email(message = "Please enter your email correctly")
    @NotEmpty(message = "Please enter your email")
    private String email;

    @Pattern(regexp = "\\+\\d{2}\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}", message = "Format +38(0__)___-__-__")
    @NotEmpty(message = "Please enter your number of mobile telephone")
    @Column(nullable = false)
    private String mobilePhone;

    @Pattern(regexp = "\\+\\d{2}\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}", message = "Format +38(0__)___-__-__")
    @NotEmpty(message = "Please enter your number of home telephone")
    @Column(nullable = false)
    private String homePhone;

    @ManyToOne
    @JoinColumn(name="contact_id", referencedColumnName = "id")
    private User userId;

    public Contact() {
    }

    public Contact(Contact contact, User user){

        setId(contact.getId());
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
                '}';
    }
}
