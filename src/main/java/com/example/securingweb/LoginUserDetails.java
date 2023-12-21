package com.example.securingweb;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.tweet.entities.LoginUser;

public class LoginUserDetails implements UserDetails {

    private final LoginUser loginUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public LoginUserDetails(LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    @Override
    public String getUsername() {
        return loginUser.getEmail();
    }

    @Override
    public String getPassword() {
        return loginUser.getPassword();
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
