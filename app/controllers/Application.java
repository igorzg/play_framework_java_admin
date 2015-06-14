package controllers;

import models.User;
import play.data.Form;
import play.db.ebean.Model;
import play.mvc.*;

import views.html.*;
import java.util.List;

import static play.libs.Json.toJson;

public class Application extends Core {

    public static Result index() {
        Boolean haveUsers = models.User.haveUsers();
        if (!haveUsers) {
            return redirect(routes.User.create());
        }
        return ok("result");
    }

}
