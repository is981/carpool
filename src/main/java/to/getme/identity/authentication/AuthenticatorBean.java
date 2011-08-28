package to.getme.identity.authentication;

import to.getme.model.identity.User;
import org.jboss.seam.security.Authenticator;
import org.jboss.seam.security.BaseAuthenticator;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;
import org.picketlink.idm.impl.api.PasswordCredential;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named("authenticator")
@Stateless
public class AuthenticatorBean extends BaseAuthenticator implements Authenticator {

    @Inject
    private transient Logger logger;

    @Inject
    private EntityManager em;

    @Inject
    private Identity identity;

    @Inject
    private Credentials credentials;

    public void authenticate() {
        List<User> results = em.createQuery("select u from User u where u.username=:username").setParameter("username", credentials.getUsername()).getResultList();

        if (results.isEmpty()) {
            logger.log(Level.INFO, "User " + credentials.getUsername() + " does not exist");
            setStatus(AuthenticationStatus.FAILURE);
            return;
        } else if (results.size() > 1) {
            throw new IllegalStateException("Multiple users with username " + credentials.getUsername());
        }

        final User user = results.get(0);

        if (user.isActive()) {
            if (checkPassword(user)) {
                    setUser(new IdentityUser(user));
                    logger.log(Level.INFO, credentials.getUsername() + " logged in");
                    setStatus(AuthenticationStatus.SUCCESS);
                    return;
            } else {
                logger.log(Level.INFO, "Incorrect password for " + credentials.getUsername());
            }
        } else {
            logger.log(Level.WARNING, "Disabled user attempting to login " + credentials.getUsername());
        }
        setStatus(AuthenticationStatus.FAILURE);
    }

    private Boolean checkPassword(User user) {
        final String saltedPassword = user.getSalt() + ((PasswordCredential) credentials.getCredential()).getValue();
        final String sha512 = Hashing.hash(saltedPassword);
        return sha512.equals(user.getPassword());
    }

    private void assignRoles(User user) {

    }
}

