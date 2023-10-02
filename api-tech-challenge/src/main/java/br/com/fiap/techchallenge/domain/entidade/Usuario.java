package br.com.fiap.techchallenge.domain.entidade;

import br.com.fiap.techchallenge.infra.login.UserRole;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column
    private UserRole role;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private Set<Consumidor> consumidores;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private Set<Eletrodomestico> eletrodomesticos;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private Set<Endereco> enderecos;

    public Usuario(String login, String password, UserRole userRole) {
        this.login = login;
        this.password = password;
        this.role = userRole;
    }
    public Usuario(){}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));

        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
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

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", consumidores=" + consumidores +
                ", eletrodomesticos=" + eletrodomesticos +
                ", enderecos=" + enderecos +
                '}';
    }
}
