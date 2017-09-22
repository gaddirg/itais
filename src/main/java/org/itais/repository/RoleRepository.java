package org.itais.repository;

import java.util.List;

import org.itais.domain.Inventory;
import org.itais.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * 
 *defines Role repository extending JpaRepository defining user roles
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Override
    void delete(Role role);

	List<Role> findAllByOrderByNameDesc();

	Role findById(long id);

    Role findByName(String name);
	
}
