package org.itais.repository;

import org.itais.domain.Inventory;
import org.itais.domain.Office;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;
/**
 * 
 *defines CFP repository extending super class CRUD repository defining create retreive update and delete functionality.
 */
public interface InventoryRepository extends CrudRepository<Inventory, Long>
{

    Inventory findById(Long id);
    Inventory findBySerialNumber(String serialNumber);
    List<Inventory> findAllByOrderByCreatedOnDesc();
    List<Inventory> findByWarrantyExpirationDateBetween(Date date1, Date date2);
    List<Inventory> findByWarrantyExpirationDateBefore(Date date);
    List<Inventory> findByWarrantyExpirationDateBeforeAndOffice(Date date, Office office);
    List<Inventory> findByWarrantyExpirationDateBetweenAndOffice(Date date1, Date date2, Office office);
    List<Inventory> findByOffice(Office office);
}
