package org.itais.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.itais.domain.Role;
import org.itais.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails
{

    private static final long serialVersionUID = 3185970362329652822L;

    private User user;

    public UserDetailsImpl(User user)
    {
	this.user = user;
    }

    @Override
    public String getPassword()
    {
	return user.getPassword();
    }

    @Override
    public String getUsername()
    {
	return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired()
    {
	return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
	return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
	return true;
    }

    @Override
    public boolean isEnabled()
    {
	return true;
    }
    
    public long getId()
    {
	return user.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
	Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	Collection<Role> roles = user.getRoles();
	for (Role role : roles)
	{
	    authorities.add(new SimpleGrantedAuthority(role.getName()));
	}
	return authorities;
    }
    
    public User getUser()
    {
	return user;
    }
}
