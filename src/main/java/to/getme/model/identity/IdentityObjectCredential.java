package to.getme.model.identity;

import org.jboss.seam.security.annotations.management.IdentityProperty;
import org.jboss.seam.security.annotations.management.PropertyType;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Holds credential values
 *
 * @author Shane Bryzak
 */
@Entity
public class IdentityObjectCredential implements Serializable {

    private Long id;
    private IdentityObject identityObject;
    private IdentityObjectCredentialType type;
    private String value;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "identity_object")
    public IdentityObject getIdentityObject() {
        return identityObject;
    }

    public void setIdentityObject(IdentityObject identityObject) {
        this.identityObject = identityObject;
    }

    @ManyToOne
    @IdentityProperty(PropertyType.TYPE)
    @JoinColumn(name = "identity_object_credential_type")
    public IdentityObjectCredentialType getType() {
        return type;
    }

    public void setType(IdentityObjectCredentialType type) {
        this.type = type;
    }

    @IdentityProperty(PropertyType.VALUE)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
