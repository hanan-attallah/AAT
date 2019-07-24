package com.mytaxi.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mytaxi.domainvalue.CarStatus;
import com.mytaxi.domainvalue.EngineType;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AATDTO
{
    @JsonIgnore
    private Long id;

    @NotNull(message = "licensePlate can not be null!")
    private String licensePlate;

    private Double rating;

    @NotNull(message = "seatCount can not be null!")
    private Integer seatCount;

    @NotNull(message = "manufacturer can not be null!")
    private String manufacturer;

    private Boolean convertible = false;

    private CarStatus carStatus;

    @NotNull(message = "EngineType can not be null!")
    private EngineType engineType;


    private AATDTO()
    {
    }


    private AATDTO(Long id, String licensePlate, String manufacturer, Integer seatCount, Boolean convertible, EngineType engineType, Double rating )
    {
        this.id = id;
        this.licensePlate = licensePlate;
        this.manufacturer = manufacturer;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.engineType = engineType;
        this.rating = rating;
        this.engineType = EngineType.GAS;
    }


    public static CarDTOBuilder newBuilder()
    {
        return new CarDTOBuilder();
    }


    @JsonProperty
    public Long getId()
    {
        return id;
    }

    public String getLicensePlate() { return licensePlate; }

    public Double getRating() {
        return rating;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Boolean getConvertible() {
        return convertible;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public CarStatus getCarStatus() { return carStatus; }


    public static class CarDTOBuilder
    {
        private Long id;

        private String licensePlate;

        private Double rating;

        private Integer seatCount;

        private String manufacturer;

        private Boolean convertible = false;

        private EngineType engineType;

        private CarStatus carStatus;

        public CarDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public CarDTOBuilder setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public CarDTOBuilder setRating(Double rating) {
            this.rating = rating;
            return this;
        }

        public CarDTOBuilder setSeatCount(Integer seatCount) {
            this.seatCount = seatCount;
            return this;
        }

        public CarDTOBuilder setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public CarDTOBuilder setConvertible(Boolean convertible) {
            this.convertible = convertible;
            return this;
        }

        public CarDTOBuilder setEngineType(EngineType engineType) {
            this.engineType = engineType;
            return this;
        }

        public CarDTOBuilder setCarStatus(CarStatus carStatus) {
            this.carStatus = carStatus;
            return this;
        }

        public AATDTO createCarDTO()
        {
            //todo read abt buider design betern
            return new AATDTO(id, licensePlate, manufacturer, seatCount, convertible, engineType, rating);
        }

    }
}
