package controllers;

import components.Authenticator;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by igi on 10/06/15.
 */
@Security.Authenticated(Authenticator.class)
public class Core extends Controller {

    public static Result index() {
        return ok("result");
    }
}
