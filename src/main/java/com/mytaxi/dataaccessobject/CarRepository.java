package com.mytaxi.dataaccessobject;

import com.mytaxi.domainvalue.CarStatus;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

/**
 * Database Access Object for car table.
 * <p/>
 */
public interface CarRepository extends CrudRepository<CarDO, Long>
{
    CarDO findByLicensePlate(String licensePlate);
    List<CarDO> findByCarStatus(CarStatus carStatus);
}
