package org.itais.repository;

import org.itais.domain.Office;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OfficeRepository extends CrudRepository<Office, Long>
{

    Office findById(Long id);
    Office findByName(String name);
    List<Office> findAllByOrderByNameAsc();
//    List<Office> findAllByOwnerAndStatusOrderByCreatedOnDesc(User owner, Boolean status);
    List<Office> findAllByStatusOrderByCreatedOnDesc(Boolean status);

}

