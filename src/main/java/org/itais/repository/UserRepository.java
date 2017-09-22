package org.itais.repository;

import org.itais.domain.Office;
import org.itais.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;
/**
 * 
 *defines User repository interface extending  CrudRepository defining create retrieve update and delete functionality
 */
public interface UserRepository extends CrudRepository<User, Long>
{

    User findByEmail(String email);
    List<User> findAllByOrderByIdAsc();
}
