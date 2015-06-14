package models;

import play.api.data.validation.ValidationError;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints;

import java.util.ArrayList;
import java.util.List;

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

    public static Boolean exists(String email) {
        return User.find.where().eq("email", email).findRowCount() > 0;
    }

    public static Boolean haveUsers() {
        Integer count = User.find.findRowCount();
        return count > 0;
    }

}
