package org.itais.service;

import org.itais.domain.Office;
import org.itais.domain.User;
import org.itais.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService, UserDetailsService
{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository)
    {
	this.userRepository = userRepository;
    }

    @Override
    public User findByEmail(String email)
    {
	return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
	User user = findByEmail(username);
	if (user == null)
	{
	    throw new UsernameNotFoundException(username);
	}

	return new UserDetailsImpl(user);
    }
    
    @Override
    public List<User> list()
    {
	return userRepository.findAllByOrderByIdAsc();
    }

    @Override
    public User save(User user)
    {
	return userRepository.save(user);
    }

	@Override
	public User findById(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		userRepository.delete(id);
	}

	@Override
	public boolean isAccountOwner(String currUser, String email) {
		if (currUser == email)
			return true;
		return false;
	}
	
}
