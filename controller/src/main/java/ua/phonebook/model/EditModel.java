package ua.phonebook.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by dexter on 21.04.16.
 */
// UserDTO
public class EditModel extends Contact {

    @Size(min = 4, max = 20, message = "minimum 4 symbols, maximum 20 symbols")
    private String pass;
    @Size(min = 4, max = 20, message = "minimum 4 symbols, maximum 20 symbols")
    private String newPass;
    private String rePass;

    public EditModel() {
    }

    public EditModel(String id){
        setId(Integer.parseInt(id));
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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

}
