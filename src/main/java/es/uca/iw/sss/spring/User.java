package es.uca.iw.sss.spring;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@Entity
public class User implements Serializable, Cloneable, UserDetails {
@Id
@GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Este campo es obligatorio")
    private String firstName = "";
    @NotEmpty(message = "Este campo es obligatorio")
    private String lastName = "";
    @NotEmpty(message = "Este campo es obligatorio")
    @Column(unique = true)
    private String email = "";
    @NotEmpty(message = "Este campo es obligatorio")
    private String dni = "";
    @NotEmpty(message = "Este campo es obligatorio")
    @Column(unique = true)
    private String username = "";
    @NotEmpty(message = "Este campo es obligatorio")
    private String password = "";

    public User() {

    }
    public User(String firstname, String lastName, String email, String dni, String user, String password) {
        this.firstName = firstname;
        this.lastName = lastName;
        this.email = email;
        this.dni = dni;
        this.username = user;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return username;
    }

    public void setUser(String user) {
        this.username = user;
    }

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the value of status
     *
     * @return the value of status
     */
    public String getDni() {
        return dni;
    }

    /**
     * Set the value of status
     *
     * @param dni new value of DNI
     */
    public void setDni(String dni) {
        this.dni = dni;
    }



    /**
     * Get the value of lastName
     *
     * @return the value of lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the value of lastName
     *
     * @param lastName new value of lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the value of firstName
     *
     * @return the value of firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the value of firstName
     *
     * @param firstName new value of firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public boolean isPersisted() {
        return id != null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this.id == null) {
            return false;
        }

        if (obj instanceof User && obj.getClass().equals(getClass())) {
            return this.id.equals(((User) obj).id);
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + (id == null ? 0 : id.hashCode());
        return hash;
    }

    @Override
    public User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return "asd";
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}