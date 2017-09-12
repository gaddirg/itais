package org.itais.repository;

import org.itais.domain.Office;
import org.itais.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
/**
 *defines Repo repository extending super class CrudRepository defining create retrieve update and delete functionality
 */
public interface OfficeRepository extends CrudRepository<Office, Long>
{

    Office findById(Long id);
    Office findByName(String name);
    List<Office> findAllByOrderByCreatedOnDesc();
//    List<Office> findAllByOwnerAndStatusOrderByCreatedOnDesc(User owner, Boolean status);
    List<Office> findAllByStatusOrderByCreatedOnDesc(Boolean status);

}
