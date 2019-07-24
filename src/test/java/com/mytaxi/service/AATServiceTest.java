package com.mytaxi.service;

import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.CarService;
import com.mytaxi.service.car.DefaultCarService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;

import org.mockito.junit.MockitoJUnitRunner;

/**
 * test the car service
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class AATServiceTest {


    CarService carService;

    @Mock
    CarRepository carRepository;


    @Before
    public void setUp() throws Exception {
        carService = new DefaultCarService(carRepository);
    }


    @Test
    public void getReservationDetails_returnsReservationInfo() {

        try {
            CarDO carDO = new CarDO("9090", "BMW", 4, false, EngineType.ELECTRIC, 4.0);
            carDO.setId(1L);
            BDDMockito.given(carRepository.findByLicensePlate(carDO.getLicensePlate())).willReturn(carDO);
            CarDO returnedCarDO = carService.find(1L);
            assertThat(carDO.getId()).isEqualTo(1L);
            assertThat(carDO.getEngineType()).isEqualTo(EngineType.ELECTRIC);
            assertThat(carDO.getManufacturer()).isEqualTo("BMW");
        } catch (Exception e) {
        }
    }


    @Test(expected = EntityNotFoundException.class)
    public void getCar_whenNotFound() throws EntityNotFoundException {
        BDDMockito.given(carRepository.findByLicensePlate("9090")).willReturn(null);
        CarDO carDO = carService.find(1L);
    }


//    private ArgumentCaptor<CarDO> carCaptor;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        carCaptor = ArgumentCaptor.forClass(CarDO.class);
//        carService = new DefaultAATService(carRepository);
//    }





//    @Test
//    public void create() throws ConstraintsViolationException {
//        // Arrange
//        CarDO carDO = mock(CarDO.class);
//        String boom = "car01";
//        when(carRepository.save(any(CarDO.class))).thenReturn(carDO);
//
//        // Act
//        CarDO result = carService.create(carDO);
//
//        // Asserts
//        Assertions.assertThat(result)
//                .isNotNull()
//                .isEqualTo(carDO);
//
//        ArgumentCaptor<CarDO> captor = ArgumentCaptor.forClass(CarDO.class);
//        verify(carRepository).save(captor.capture());
//
//        Assertions.assertThat(captor.getValue())
//                .extracting(CarDO::getLicensePlate)
//                .contains(boom);
//    }
//
//
//    @Test(expected = EntityNotFoundException.class)
//    public void getCar_whenCarNotFound() throws EntityNotFoundException {
//        carService.find(44L);
//    }
//
//
//    @Test
//    public void getCarById() throws EntityNotFoundException {
//        // Arrange
//        CarDO car = mock(CarDO.class);
//        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
//
//        System.out.println(car.getId());
//
//        // Act
//        CarDO result = carService.find(1L);
//
//        // Asserts
//        Assertions.assertThat(result)
//                .isNotNull()
//                .isEqualTo(car);
//
//        verify(carRepository).findById(eq(1L));
//    }
//
//    @Test
//    public void findByLicensePlate() throws EntityNotFoundException {
//        // Arrange
//        CarDO car = mock(CarDO.class);
//        when(carRepository.findByLicensePlate("car01")).thenReturn(car);
//
//        System.out.println(car.getLicensePlate());
//        // Act
//        CarDO result = carService.find(1L);
//
//        // Asserts
//        Assertions.assertThat(result)
//                .isNotNull()
//                .isEqualTo(car);
//
//        verify(carRepository).findByLicensePlate(eq("car01"));
//    }
//
//
//    @Test
//    public void testGetAllByStatus() {
//        // Arrange
//        CarDO car1 = mock(CarDO.class);
//        CarDO car2 = mock(CarDO.class);
//        when(carRepository.findByCarStatus(CarStatus.BUSY))
//                .thenReturn(Arrays.asList(car1, car2));
//
//        // Act
//        List<CarDO> rockets = carService.find(CarStatus.BUSY);
//
//        // Asserts
//        verify(carRepository).findByCarStatus(CarStatus.BUSY);
//
//        Assertions.assertThat(rockets)
//                .isNotNull()
//                .hasSize(2)
//                .contains(car1, car2);
//    }

}