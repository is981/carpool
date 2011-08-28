package to.getme.identity.authentication;

import to.getme.model.identity.User;

/**
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 */
public class IdentityUser implements org.picketlink.idm.api.User {

    User user;

    public IdentityUser(User user) {
        this.user = user;
    }

    public String getId() {
        return user.getId().toString();
    }

    public String getKey() {
        return getId();
    }
}
