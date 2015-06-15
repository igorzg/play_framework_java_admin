package forms;
import models.*;
import play.data.validation.Constraints;
import java.util.Map;


/**
 * Created by igi on 15/06/15.
 */
public class LoginForm {
    @Constraints.Required
    @Constraints.Email
    public String email;
    @Constraints.Required
    public String password;

    /**
     * Authenticate user
     * @param formData submitted form
     * @return boolean if user is authenticated
     */
    public static Boolean authenticate(Map<String, String> formData) {
        String email = formData.get("email");
        String password = formData.get("password");
        User user = User.getUserByEmail(email);
        return user != null && user.getPassword().equals(password);
    }
}
