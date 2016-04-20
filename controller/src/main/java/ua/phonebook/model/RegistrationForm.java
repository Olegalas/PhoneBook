package ua.phonebook.model;

/**
 * Created by dexter on 20.04.16.
 */
public class RegistrationForm extends Contact {

    private String newPass;
    private String rePass;

    public RegistrationForm() {
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getRePass() {
        return rePass;
    }

    public void setRePass(String rePass) {
        this.rePass = rePass;
    }

    @Override
    public String toString() {
        return "RegistrationForm{" +
                "newPass='" + newPass + '\'' +
                ", rePass='" + rePass + '\'' +
                '}';
    }
}
