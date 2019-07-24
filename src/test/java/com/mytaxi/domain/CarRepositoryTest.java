package com.mytaxi.domain;


import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.domainvalue.CarStatus;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findById() {
        Optional<CarDO> car = carRepository.findById(2L);
        Assertions.assertThat(car.get().getLicensePlate()).isEqualTo("car02");

    }

    @Test
    public void findByLicensePlate() {
        CarDO car = carRepository.findByLicensePlate("car01");
        Assertions.assertThat(car.getLicensePlate()).isEqualTo("car01");

    }

    @Test
    public void findByCarStatus() {
        List<CarDO> carList = carRepository.findByCarStatus(CarStatus.AVAILABLE);
        Assertions.assertThat(carList.size()).isEqualTo(4);

    }

}
