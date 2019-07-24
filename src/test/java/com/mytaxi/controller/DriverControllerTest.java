package com.mytaxi.controller;

import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.service.driver.DriverService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DriverControllerTest {


    @Mock
    private DriverService driverService;

    private MockMvc mockMvc;

    DriverDTO driverDTO;
    DriverDO driverDO ;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        DriverController driverController = new DriverController(driverService);
        mockMvc = MockMvcBuilders.standaloneSetup(driverController)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();

        driverDTO = DriverDTO.newBuilder().createDriverDTO();
        driverDO = new DriverDO("driver01","driverpw01");

    }


    @Test
    public void createDriver() throws Exception {
        // Arrange
        // public DriverDO(Long id, String licensePlate, String manufacturer, Integer seatCount, Boolean convertible,
        // EngineType engineType, Double rating, Boolean deleted )
        //
        when(driverService.create(driverDO)).thenReturn(driverDO);

        driverDTO = DriverMapper.makeDriverDTO(driverDO);
        //System.out.println(driverDTO);
        // Act
        String resultContent = mockMvc.perform(post("/v1/drivers/")
                .content(driverDTO.toString()))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();

        // Asserts
        DriverDTO result = DriverMapper.makeDriverDTO(driverDO);
        assertDriverDTO(result, driverDTO);

        verify(driverService).create(driverDO);
    }


    @Test
    public void testCreateWithThrow() throws Exception {
        // Arrange

        DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);

        when(driverService.create(driverDO))
                .thenThrow(ConstraintsViolationException.class);

        // Act & Assert
        mockMvc.perform(post("/v1/drivers")
                .param("username", "123"))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testGetRocket() throws Exception {
        // Arrange
        when(driverService.find(eq(1L))).thenReturn(driverDO);

        // Act
        String content = mockMvc.perform(get("/v1/drivers/{id}", 1))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // Asserts
        DriverDTO result = DriverMapper.makeDriverDTO(driverDO);
        assertDriverDTO(result, driverDTO);

        verify(driverService).find(eq(1L));
    }


    private void assertDriverDTO(DriverDTO actual, DriverDTO expected) {
        Assertions.assertThat(actual)
                .extracting(DriverDTO::getId,
                        DriverDTO::getUsername,
                        DriverDTO::getOnlineStatus)
                .contains(expected.getId(),
                        expected.getUsername(),
                        expected.getOnlineStatus());
    }
}