package controllers;

import models.User;
import play.data.DynamicForm;
import play.data.Form;
import play.data.validation.ValidationError;
import forms.*;
import play.i18n.Messages;
import play.mvc.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Auth extends Controller {
    /**
     * Create default user if there is no user in db
     * @return
     */
    public static Boolean checkUserCreated() {
        Boolean haveUsers = models.User.haveUsers();
        if (!haveUsers) {
            User user = new models.User();
            user.email = "admin@igorivanovic.info";
            user.username = "admin";
            user.name = "Igor";
            user.surname = "Ivanovic";
            user.password = "admin";
            user.save();
            return true;
        }
        return false;
    }
    /**
     * Login User
     * @return
     */
    public static Result login() {
        if (checkUserCreated()) {
            return redirect(routes.Auth.login());
        }
        String method = request().method();
        Map<String, List<ValidationError>> errors = new HashMap<>();
        Map<String, String> formData = new HashMap<>();

        if (method.equals("POST")) {
            Form loginForm = Form.form(LoginForm.class);
            Form submitedForm = loginForm.bindFromRequest();
            formData = submitedForm.data();
            errors = submitedForm.errors();

            System.out.print(formData.toString());
            System.out.print(errors.toString());

            if (!submitedForm.hasErrors() && LoginForm.authenticate(formData)) {
                session("username", formData.get("username"));
                return redirect(routes.Core.index());
            }

        }

        return ok(views.html.auth.login.render(errors, formData));
    }

    /**
     * Logout and clean the session.
     *
     * @return Index page
     */
    public static Result logout() {
        session().clear();
        flash("success", Messages.get("user.logout"));
        return redirect(routes.Auth.login());
    }
}
