package com.mytaxi.controller;

import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Matchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CarService carService;

//    @Test
//    public void getReservation_shouldReturnReservationInfo() {
//        try {
//            mockMvc.perform(MockMvcRequestBuilders.get("/v1/cars/1"))
//                    .andExpect(MockMvcResultMatchers.status().isOk())
//                    .andExpect(MockMvcResultMatchers.jsonPath("carId").value("1"));
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

    @Test
    public void getCar_withData() {
        try {

            CarDO carDO = new CarDO( "9090", "BMW", 4, false, EngineType.ELECTRIC, 4.0);
            carDO.setId(1L);

            BDDMockito.given(carService.find(ArgumentMatchers.anyLong())).willReturn(carDO);
            mockMvc.perform(MockMvcRequestBuilders.get("/v1/cars/1"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.jsonPath("id").value(1L))
                    .andExpect(MockMvcResultMatchers.jsonPath("licensePlate").value("9090"))
                    .andExpect(MockMvcResultMatchers.jsonPath("manufacturer").value("BMW"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getCar_entityNotFound() throws Exception {
        BDDMockito.given(carService.find(ArgumentMatchers.anyLong()))
                .willThrow(new EntityNotFoundException("Could not find entity with id"));

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/cars/100"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/cars/RRR"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void deleteCar_exitRecord() {
        try {
            CarDO carDO = new CarDO( "9090", "BMW", 4, false, EngineType.ELECTRIC, 4.0);
            carDO.setId(1L);
            BDDMockito.doNothing().when(carService).delete(ArgumentMatchers.anyLong());
            mockMvc.perform(MockMvcRequestBuilders.delete("/v1/cars/1"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isOk());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteCar_notFound() {
        try {
            BDDMockito.willThrow(new EntityNotFoundException("Could not find entity with id")).willDoNothing().given(carService).delete(ArgumentMatchers.anyLong());
            mockMvc.perform(MockMvcRequestBuilders.delete("/v1/cars/100"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Mock
//    private AATService carService;


//    CarDTO carDTO;
//    CarDO carDO ;
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        CarController carController = new CarController(carService);
//        mockMvc = MockMvcBuilders.standaloneSetup(carController)
//                .alwaysDo(MockMvcResultHandlers.print())
//                .build();
//
//        carDTO = CarDTO.newBuilder().createCarDTO();
//        carDO = new CarDO("car01","Ford",4,false, EngineType.GAS,8.0);
//
//    }
//
//
//    @Test
//    public void createCar() throws Exception {
//
//        when(carService.create(carDO)).thenReturn(carDO);
//
//        carDTO = CarMapper.makeCarDTO(carDO);
//        // Act
//        String resultContent = mockMvc.perform(post("/v1/cars/")
//                .param(carDTO.toString()))
//                .andExpect(status().isCreated())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andReturn().getResponse().getContentAsString();
//
//        // Asserts
//        CarDTO result = CarMapper.makeCarDTO(carDO);
//        assertCarDTO(result, carDTO);
//
//        verify(carService).create(carDO);
//    }
//
//
//    @Test
//    public void testCreateWithThrow() throws Exception {
//        // Arrange
//
//        CarDO carDO = CarMapper.makeCarDO(carDTO);
//
//        when(carService.create(carDO))
//                .thenThrow(ConstraintsViolationException.class);
//
//        // Act & Assert
//        mockMvc.perform(post("/v1/cars")
//                .param("LicensePlate", "123"))
//                .andExpect(status().isBadRequest());
//    }
//
//
//    @Test
//    public void testGetRocket() throws Exception {
//        // Arrange
//        when(carService.find(eq(1L))).thenReturn(carDO);
//
//        // Act
//        String content = mockMvc.perform(get("/v1/cars/{id}", 1))
//                .andExpect(status().isOk())
//                .andReturn().getResponse().getContentAsString();
//
//        // Asserts
//        CarDTO result = CarMapper.makeCarDTO(carDO);
//        assertCarDTO(result, carDTO);
//
//        verify(carService).find(eq(1L));
//    }
//
//
//    private void assertCarDTO(CarDTO actual, CarDTO expected) {
//        Assertions.assertThat(actual)
//                .extracting(CarDTO::getId,
//                        CarDTO::getLicensePlate,
//                        CarDTO::getConvertible)
//                .contains(expected.getId(),
//                        expected.getLicensePlate(),
//                        expected.getConvertible());
//    }
}