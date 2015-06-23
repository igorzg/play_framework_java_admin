package models;

import com.avaje.ebean.Expr;
import com.avaje.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import play.data.validation.Constraints;
import java.util.List;
import java.util.Set;

/**
 * User model
 */
@Entity
public class User extends Model {
    @Id
    public String id;
    @Constraints.Required
    public String name;
    @Constraints.Required
    public String surname;
    @Constraints.Required
    @Constraints.Min(6)
    public String username;
    @Constraints.Required
    @Constraints.Email
    public String email;
    @Constraints.Required
    public String password;

    public static Finder<Long, User> find = new Finder<Long, User>(
            Long.class, User.class
    );


    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public static List getAll() {
        return new Model.Finder(String.class, User.class).all();
    }

    public static User getUser(String username) {
        return User.find.where().or(Expr.eq("email", username), Expr.eq("username", username)).findUnique();
    }
    public static Boolean exists(String email, String username) {
        return User.find.where().or(Expr.eq("email", email), Expr.eq("username", username)).findRowCount() > 0;
    }

    public static Boolean haveUsers() {
        Integer count = User.find.findRowCount();
        return count > 0;
    }

}
