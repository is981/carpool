package to.getme.identity.restriction;

import to.getme.identity.annotations.*;
import org.jboss.seam.security.annotations.Secures;

import org.jboss.seam.security.Identity;

/**
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 */
public class RestrictionManager {

    @Secures
    @RootRestriction
    public boolean isRoot(Identity identity) {
        return identity.hasRole(RoleType.ROOT.getName(), "", "");
    }

    @Secures
    @CreateRestriction
    public boolean canCreate(Identity identity) {
        return identity.hasRole(RoleType.CREATE.getName(), "", "");
    }

    @Secures
    @DeleteRestriction
    public boolean canDelete(Identity identity) {
        return identity.hasRole(RoleType.DELETE.getName(), "", "");
    }

    @Secures
    @ReadRestriction
    public boolean canRead(Identity identity) {
        return identity.hasRole(RoleType.READ.getName(), "", "");
    }

    @Secures
    @UpdateRestriction
    public boolean canUpdate(Identity identity) {
        return identity.hasRole(RoleType.UPDATE.getName(), "", "");
    }
}
