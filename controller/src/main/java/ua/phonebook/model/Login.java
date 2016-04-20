package ua.phonebook.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by dexter on 20.04.16.
 */
@MappedSuperclass
public class Login extends IdGenerate {

    @Column(nullable = false, unique = true, length = 20)
    protected String login;
    @Column(nullable = false, length = 20)
    protected String pass;

    public Login() {
    }

    public Login(String login, String pass) {
        this.login = login;
        this.pass = pass;
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

    @Override
    public String toString() {
        return "Login{" +
                "login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
