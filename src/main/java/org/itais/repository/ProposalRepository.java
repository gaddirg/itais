package org.itais.repository;

import org.itais.domain.Inventory;
import org.itais.domain.Office;
import org.itais.domain.Proposal;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
/**
 * 
 *defines Proposal repository extending CrudRepository defining create retrieve update and delete functionality
 */
public interface ProposalRepository extends CrudRepository<Proposal, Long>
{

    Proposal findById(Long id);
    Proposal findByTitle(String title);
    List<Proposal> findAllByOrderByCreatedOnDesc();

}
