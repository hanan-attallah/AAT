package com.mytaxi.domain;

import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.domainvalue.OnlineStatus;
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
public class DriverRepositoryTest {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findById() {
        Optional<DriverDO> car = driverRepository.findById(2L);
        Assertions.assertThat(car.get().getUsername()).isEqualTo("driver02");

    }

    @Test
    public void findByCarStatus() {
        List<DriverDO> driverList = driverRepository.findByOnlineStatus(OnlineStatus.ONLINE);
        Assertions.assertThat(driverList.size()).isEqualTo(4);

    }

}
