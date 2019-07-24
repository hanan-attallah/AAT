package com.mytaxi.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AATMapper {
    public static CarDO makeCarDO(CarDTO carDTO) {
        return new CarDO(carDTO.getLicensePlate(), carDTO.getManufacturer(), carDTO.getSeatCount(), carDTO.getConvertible(), carDTO.getEngineType(), carDTO.getRating());
    }

    public static CarDTO makeCarDTO(CarDO carDO) {
        CarDTO.CarDTOBuilder carDTOBuilder = CarDTO.newBuilder()
                .setId(carDO.getId())
                .setLicensePlate(carDO.getLicensePlate())
                .setManufacturer(carDO.getManufacturer())
                .setSeatCount(carDO.getSeatCount())
                .setConvertible(carDO.getConvertible())
                .setEngineType(carDO.getEngineType())
                .setRating(carDO.getRating());

        return carDTOBuilder.createCarDTO();
    }


    public static List<CarDTO> makeCarDTOList(Collection<CarDO> cars) {
        return cars.stream()
                .map(AATMapper::makeCarDTO)
                .collect(Collectors.toList());
    }
}
