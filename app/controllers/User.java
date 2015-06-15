package controllers;

import play.data.validation.ValidationError;
import play.data.Form;
import play.mvc.*;
import play.i18n.Messages;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import views.html.user.*;
/**
 * Represents logic for User CRUD
 */
public class User extends Core {
    
    /**
     * Display User create form
     * @return
     */
    public static Result create() {
        String method = request().method();
        String message = null;
        Map<String, List<ValidationError>> errors = new HashMap<>();
        Map<String, String> formData = new HashMap<>();

        if (method.equals("POST")) {

            Form<models.User> userForm = Form.form(models.User.class);
            Form<models.User> submitedForm = userForm.bindFromRequest();
            formData = submitedForm.data();


            if (!formData.get("password").equals(formData.get("repeat_password"))) {
                submitedForm.reject(new ValidationError("password", Messages.get("error.repeat_password")));
            } else if (models.User.exists(formData.get("email"))) {
                submitedForm.reject(
                        new ValidationError(
                                "email",
                                Messages.get(
                                        "error.user_email_exists",
                                        Messages.get("user.create.email")
                                )
                        )
                );
            } else if (formData.get("repeat_password").isEmpty()) {
                submitedForm.reject(
                        new ValidationError(
                                "repeat_password",
                                Messages.get(
                                        "error.required",
                                        Messages.get("user.create.repeat_password")
                                )
                        )
                );
            }

            if (!submitedForm.hasErrors()) {
                message = Messages.get("user.create.success");
                models.User user = submitedForm.get();
                user.save();
            } else {
                message = Messages.get("error.general");
                errors = submitedForm.errors();
            }
        }

        return ok(create.render(errors, formData, message));
    }

}
