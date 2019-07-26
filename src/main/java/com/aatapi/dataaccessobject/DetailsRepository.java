package com.aatapi.dataaccessobject;

import com.aatapi.domainobject.AATDO;
import com.aatapi.domainobject.Details;
import org.springframework.data.repository.CrudRepository;

/**
 * Database Access Object for car table.
 * <p/>
 */
public interface DetailsRepository extends CrudRepository<Details, Long>
{
}
