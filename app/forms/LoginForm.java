package forms;
import models.*;
import play.data.validation.Constraints;
import java.util.Map;


/**
 * Created by igi on 15/06/15.
 */
public class LoginForm {
    @Constraints.Required
    public String username;
    @Constraints.Required
    public String password;

    /**
     * Authenticate user
     * @param formData submitted form
     * @return boolean if user is authenticated
     */
    public static Boolean authenticate(Map<String, String> formData) {
        String username = formData.get("username");
        String password = formData.get("password");
        User user = User.getUser(username);
        return user != null && user.getPassword().equals(password);
    }
}
