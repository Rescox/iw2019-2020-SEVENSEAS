package es.uca.iw.sss.spring;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class User implements Serializable, Cloneable, UserDetails {
@Id
@GeneratedValue(strategy =  GenerationType.AUTO)
@Column(name = "id", unique = true, nullable = false)
    private Long id;
    @NotEmpty(message = "This field is required")
    private String firstName = "";
    @NotEmpty(message = "This field is required")
    private String lastName = "";
    @NotEmpty(message = "This field is required")
    @Column(unique = true)
    private String email = "";
    @NotEmpty(message = "This field is required")
    private String dni = "";
    @NotEmpty(message = "This field is required")
    @Column(unique = true)
    private String username = "";
    @NotEmpty(message = "This field is required")
    private String password = "";
    @ManyToOne
    private Ship ship;
    //Poner la clave foranea hacia account
    private String role = "";

    public User() {

    }

    public User(String firstName, String lastName, String email, String dni, String user, String password) {
        this.firstName = firstName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        list.add(new SimpleGrantedAuthority(role));
        return list;

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
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getRole() {
        return role;
    }

    public void  setRole(String role) {
        this.role = role;
    }
}