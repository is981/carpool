package to.getme.model.identity;

import org.jboss.seam.security.annotations.management.IdentityProperty;
import org.jboss.seam.security.annotations.management.PropertyType;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Contains relationships between identities
 *
 * @author Shane Bryzak
 */
@Entity
public class IdentityObjectRelationship implements Serializable {

    private Long id;
    private String name;
    private IdentityObjectRelationshipType relationshipType;
    private IdentityObject from;
    private IdentityObject to;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @IdentityProperty(PropertyType.NAME)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @IdentityProperty(PropertyType.TYPE)
    @JoinColumn(name = "identity_object_relationship_type")
    public IdentityObjectRelationshipType getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(IdentityObjectRelationshipType relationshipType) {
        this.relationshipType = relationshipType;
    }

    @ManyToOne
    @IdentityProperty(PropertyType.RELATIONSHIP_FROM)
    @JoinColumn(name = "identity_object_from")
    public IdentityObject getFrom() {
        return from;
    }

    public void setFrom(IdentityObject from) {
        this.from = from;
    }

    @ManyToOne
    @IdentityProperty(PropertyType.RELATIONSHIP_TO)
    @JoinColumn(name = "identity_object_to")
    public IdentityObject getTo() {
        return to;
    }

    public void setTo(IdentityObject to) {
        this.to = to;
    }
}
