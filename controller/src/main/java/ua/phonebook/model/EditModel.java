package ua.phonebook.model;

/**
 * Created by dexter on 21.04.16.
 */
// UserDTO
public class EditModel extends Contact {

    private String pass;
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
