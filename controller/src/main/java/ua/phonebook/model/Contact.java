package ua.phonebook.model;

import javax.persistence.*;

/**
 * Created by dexter on 19.04.16.
 */
// sometime object of Contact class will be UserDTO
@Entity
@Table(name = "contacts")
public class Contact extends Login{

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

    public Contact(User user){

        setId(user.getId());
        login = user.getLogin();
        pass = user.getPass();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        mobilePhone = user.getMobilePhone();
        homePhone = user.getHomePhone();

    }

    public Contact(String idUser){
        setId(Integer.parseInt(idUser));
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
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", userId=" + userId +
                '}';
    }
}
