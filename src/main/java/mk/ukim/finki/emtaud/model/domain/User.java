package mk.ukim.finki.emtaud.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.emtaud.model.enumerations.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@Table(name = "shop_users")
public class User implements UserDetails {


    @Id
    private String username;

    @JsonIgnore
    private String password;

    private String name;

    private String surname;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    @Enumerated(EnumType.STRING)
    private Role role;

    // default:
    // to-one -> FetchType.EAGER
    // to-many -> FetchType.LAZY
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ShoppingCart> carts;

    public User() {
    }

    public User(String username, String password, String name, String surname, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public User(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = Role.ROLE_USER;
    }

    public User(UserDetails userDetails) {
        this.username = userDetails.getUsername();
        this.password = userDetails.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList((GrantedAuthority) this.role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }


}
