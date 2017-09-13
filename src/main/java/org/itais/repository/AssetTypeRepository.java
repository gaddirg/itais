package org.itais.repository;

import java.util.List;

import org.itais.domain.AssetType;
import org.springframework.data.repository.CrudRepository;

public interface AssetTypeRepository extends CrudRepository<AssetType, Long>
{

    AssetType findByType(String type);

	List<AssetType> findAllByOrderByType();

	AssetType findById(Long id);

}
