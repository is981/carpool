package to.getme.identity.restriction;

/**
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 */
public enum GroupType {
    DOMAIN("domain");

    private String name;

    private GroupType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
