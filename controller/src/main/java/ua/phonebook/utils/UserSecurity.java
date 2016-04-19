package ua.phonebook.utils;

import org.springframework.ui.Model;
import ua.phonebook.model.User;

/**
 * Created by dexter on 19.04.16.
 */
public class UserSecurity {


    // true if user don't pass
    public static boolean userCheck(String id, Model model){
        User user = (User) model.asMap().get("user");
        return (user == null || !String.valueOf(user.getId()).equals(id));
    }
}
