package com.mytaxi.dataaccessobject;

import com.mytaxi.domainvalue.OnlineStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends CrudRepository<DriverDO, Long>, JpaSpecificationExecutor<DriverDO>
{
    DriverDO findByIdAndOnlineStatus(Long id, OnlineStatus onlineStatus);
    List<DriverDO> findByOnlineStatus(OnlineStatus onlineStatus);

}
