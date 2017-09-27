package org.itais.repository;

import java.util.List;

import org.itais.domain.AssetStatus;
import org.springframework.data.repository.CrudRepository;

public interface AssetStatusRepository extends CrudRepository<AssetStatus, Long>
{

	AssetStatus findByStatus(String status);

	List<AssetStatus> findAllByOrderByStatus();

	AssetStatus findById(Long id);

}
