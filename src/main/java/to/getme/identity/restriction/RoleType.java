package to.getme.identity.restriction;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 */
public enum RoleType implements org.picketlink.idm.api.RoleType {
    ROOT("Root"),
    CREATE("Create"),
    DELETE("Delete"),
    UPDATE("Update"),
    READ("Read");

    private String name;

    RoleType(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public Set<String> getNames() {
        Set<String> ret = new HashSet<String>();
        try {
            for (Field i : this.getClass().getFields()) {
                if (i.isEnumConstant()) {
                        ret.add(((RoleType)i.get(i.getName())).getName());
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
