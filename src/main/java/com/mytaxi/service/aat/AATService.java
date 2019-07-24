package com.mytaxi.service.aat;

import com.mytaxi.domainvalue.CarStatus;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

import java.util.List;

public interface AATService
{

    CarDO find(Long carId) throws EntityNotFoundException;

    CarDO create(CarDO carDO) throws ConstraintsViolationException;

    void delete(Long carId) throws EntityNotFoundException;

    void updateStatus(long carId, String carStatus) throws EntityNotFoundException;

    List<CarDO> find(CarStatus carStatus);

}
