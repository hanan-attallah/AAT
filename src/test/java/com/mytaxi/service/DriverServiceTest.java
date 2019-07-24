package com.mytaxi.service;

import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.driver.DefaultDriverService;
import junitparams.JUnitParamsRunner;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * test the driver service
 */
@RunWith(JUnitParamsRunner.class)
public class DriverServiceTest {

    @InjectMocks
    private DefaultDriverService driverService;

    @Mock
    private DriverRepository driverRepository;

    @Mock
    private CarRepository carRepository;

    private ArgumentCaptor<DriverDO> driverCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        driverCaptor = ArgumentCaptor.forClass(DriverDO.class);
        driverService = new DefaultDriverService(driverRepository);
    }


    @Test
    public void create() throws ConstraintsViolationException {
        // Arrange
        DriverDO driverDO = mock(DriverDO.class);
        String boom = "driver01";
        when(driverRepository.save(any(DriverDO.class))).thenReturn(driverDO);

        // Act
        DriverDO result = driverService.create(driverDO);

        // Asserts
        Assertions.assertThat(result)
                .isNotNull()
                .isEqualTo(driverDO);

        ArgumentCaptor<DriverDO> captor = ArgumentCaptor.forClass(DriverDO.class);
        verify(driverRepository).save(captor.capture());

        Assertions.assertThat(captor.getValue())
                .extracting(DriverDO::getUsername)
                .contains(boom);
    }


    @Test(expected = EntityNotFoundException.class)
    public void getDriver_whenDriverNotFound() throws EntityNotFoundException {
        driverService.find(44L);
    }


    @Test
    public void getDriverById() throws EntityNotFoundException {
        // Arrange
        DriverDO driver = mock(DriverDO.class);
        when(driverRepository.findById(1L)).thenReturn(Optional.of(driver));

        System.out.println(driver.getId());

        // Act
        DriverDO result = driverService.find(1L);

        // Asserts
        Assertions.assertThat(result)
                .isNotNull()
                .isEqualTo(driver);

        verify(driverRepository).findById(eq(1L));
    }


    @Test
    public void testGetAllByStatus() {
        // Arrange
        DriverDO driver1 = mock(DriverDO.class);
        DriverDO driver2 = mock(DriverDO.class);
        DriverDO driver3 = mock(DriverDO.class);
        DriverDO driver4 = mock(DriverDO.class);
        when(driverRepository.findByOnlineStatus(OnlineStatus.ONLINE))
                .thenReturn(Arrays.asList(driver1, driver2, driver3, driver4));

        // Act
        List<DriverDO> rockets = driverService.find(OnlineStatus.ONLINE);

        // Asserts
        verify(driverRepository).findByOnlineStatus(OnlineStatus.ONLINE);

        Assertions.assertThat(rockets)
                .isNotNull()
                .hasSize(4)
                .contains(driver1, driver2, driver3, driver4);
    }

    @Test(expected = CarAlreadyInUseException.class)
    public void testSelectOnAlreadyUsedCar() throws CarAlreadyInUseException, EntityNotFoundException {
        CarDO carDO = mock(CarDO.class);
        when(carRepository.findById(eq(1L))).thenReturn(Optional.of(carDO));

        DriverDO driverDO = mock(DriverDO.class);
        when(driverRepository.findById(eq(1L))).thenReturn(Optional.of(driverDO));

         driverService.selectCar(driverDO.getId(),carDO.getId());
    }

}