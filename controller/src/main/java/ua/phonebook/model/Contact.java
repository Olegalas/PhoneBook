package ua.phonebook.model;

import javax.persistence.*;

/**
 * Created by dexter on 19.04.16.
 */
// sometime object of Contact class will be UserDTO
@Entity
@Table(name = "contacts")
public class Contact extends IdGenerate{

    @Column(unique = true)
    private String login;

    private String pass;

    private String firstName;

    private String lastName;

    private String email;

    private String mobileTelephone;

    private String homeTelephone;

    @ManyToOne
    @JoinColumn(name="contact_id", referencedColumnName = "id")
    private User userId;

    public Contact() {
    }

    public Contact(User user){

        setId(user.getId());
        login = user.getLogin();
        pass = user.getPass();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        mobileTelephone = user.getMobileTelephone();
        homeTelephone = user.getHomeTelephone();

    }

    public Contact(String idUser){
        setId(Integer.parseInt(idUser));
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + getId() +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobileTelephone='" + mobileTelephone + '\'' +
                ", homeTelephone='" + homeTelephone + '\'' +
                '}';
    }
}
