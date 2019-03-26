package com.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author jiHongYuan
 * @Title: UserInfo
 * @ProjectName TestSpring
 * @date 2019/3/2314:49
 */
public class UserInfo extends User implements UserDetails {
    private Set<Role> roles;

    public Set<Role> getRoleSet() {
        return roles;
    }

    public void setRoleSet(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role r : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + r.getRoleKey()));
        }
        return list;
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
