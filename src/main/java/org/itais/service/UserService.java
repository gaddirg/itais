package org.itais.service;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.itais.domain.User;

public interface UserService
{

    public User findByEmail(String email);
    public User findById(Long id);
    public List<User> list();
    public User save(User user);
	public void delete(Long id);
	boolean isAccountOwner(String currUser, String email);

    
}
