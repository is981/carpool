package to.getme.model.identity;

import org.jboss.seam.security.annotations.management.IdentityProperty;
import org.jboss.seam.security.annotations.management.PropertyType;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Stores user attributes
 *
 * @author Shane Bryzak
 */
@Entity
public class IdentityObjectAttribute implements Serializable {

    private Integer attributeId;
    private IdentityObject identityObject;
    private String name;
    private String value;

    @Id
    @GeneratedValue
    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer id) {
        this.attributeId = id;
    }

    @ManyToOne
    @JoinColumn(name = "identity_object")
    public IdentityObject getIdentityObject() {
        return identityObject;
    }

    public void setIdentityObject(IdentityObject identityObject) {
        this.identityObject = identityObject;
    }

    @IdentityProperty(PropertyType.NAME)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @IdentityProperty(PropertyType.VALUE)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
