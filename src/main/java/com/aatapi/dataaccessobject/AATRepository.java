package com.aatapi.dataaccessobject;

import com.aatapi.domainobject.AATDO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Database Access Object for car table.
 * <p/>
 */
public interface AATRepository extends CrudRepository<AATDO, Long>
{
}
