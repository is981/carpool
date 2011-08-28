package to.getme.model.identity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(schema = "public", name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;

	@NotNull
    @NotEmpty
    @Size(min = 8)
    private String username;

	@NotNull
    @NotEmpty
	private String name;

	@NotNull
    @NotEmpty
	private String password;

	@NotNull
    @NotEmpty
	private String salt;

	@NotNull
    @NotEmpty
	@Email
	private String email;

    @NotNull
    private Boolean active = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
      return username;
    }

	public void setName(String name) {
		this.name = name;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    public String getSalt() {
        return salt;
    }

    public void setSalt(final String salt) {
        this.salt = salt;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
	public String toString() {
		return "User (username = " + username + ", name = " + name + ")";
	}
}