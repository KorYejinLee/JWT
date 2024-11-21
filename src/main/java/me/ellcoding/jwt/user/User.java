package me.ellcoding.jwt.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.ellcoding.jwt.token.Token;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "login_id", length = 100, unique = true)
    private String loginId;

    @Column(name = "password", length = 256)
    private String password;

    @Column(name = "nickname", length = 100)
    private String nickname;

    @Column(name = "email", length = 255, unique = true)
    private String email;

    @Column(name = "name", length = 100, unique = true)
    private String name;

    @Column(name = "tel", length = 100)
    private String tel;

    @Column(name = "birth", length = 100)
    private String birth;

    @Column(name = "is_deleted", length = 100)
    private boolean isDeleted;

    @Column(name = "profile", length = 100)
    private String profile;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getUsername() {
        return ""; // Todo email로 받을지 name으로 받을지 정하기
    }

}
