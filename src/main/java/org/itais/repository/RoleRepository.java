package org.itais.repository;

import org.itais.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * 
 *defines Role repository extending JpaRepository defining user roles
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    @Override
    void delete(Role role);

}
