package components;

import controllers.routes;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by igi on 15/06/15.
 */
public class Authenticator extends Security.Authenticator {
    @Override
    public String getUsername(Http.Context context) {
        return context.session().get("email");
    }

    @Override
    public Result onUnauthorized(Http.Context context) {
        return redirect(routes.Auth.login());
    }
}
