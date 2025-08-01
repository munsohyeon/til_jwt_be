package kr.co.wikibook.gallery_jwt_jpa.config.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {
    private final int memberId;
    private final Collection<? extends GrantedAuthority> authorities;
    // securlity가 인과처리 할때 사용하는 방식
    public UserPrincipal(int memberId, List<String> roles) {
        this.memberId = memberId;
        this.authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        // this(memberId, roles.stream().map(SimpleGrantedAuthority::new).toList())
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }
}
