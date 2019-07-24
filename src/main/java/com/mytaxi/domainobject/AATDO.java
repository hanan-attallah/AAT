package com.mytaxi.domainobject;

import com.mytaxi.domainvalue.CarStatus;
import com.mytaxi.domainvalue.EngineType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
@Table(
    name = "car",
    uniqueConstraints = @UniqueConstraint(name = "uc_licensePlate", columnNames = {"licensePlate"})
)
public class AATDO
{

    @Id
    @GeneratedValue
    private Long id;


    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(nullable = false)
    @NotNull(message = "licensePlate can not be null!")
    private String licensePlate;

    @Column(nullable = false)
    @NotNull(message = "manufacturer can not be null!")
    private String manufacturer;

    private Double rating=0.0;

    @Column(nullable = false)
    @NotNull(message = "seatCount can not be null!")
    private Integer seatCount;

    @Column(nullable = false)
    private Boolean convertible = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "EngineType can not be null!")
    private EngineType engineType;

    @Column(nullable = false)
    private Boolean deleted = false;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarStatus carStatus = CarStatus.AVAILABLE;

    private AATDO()
    {
    }

    public AATDO(String licensePlate, String manufacturer, Integer seatCount, Boolean convertible, EngineType engineType, Double rating )
    {
        this.licensePlate = licensePlate;
        this.manufacturer = manufacturer;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.engineType = engineType;
        this.rating = rating;
        this.deleted = false;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Boolean getDeleted()
    {
        return deleted;
    }

    public void setDeleted(Boolean deleted)
    {
        this.deleted = deleted;
    }

    public CarStatus getCarStatus()
    {
        return carStatus;
    }

    public void setCarStatus(CarStatus carStatus)
    {
        this.carStatus = carStatus;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }

    public Boolean getConvertible() {
        return convertible;
    }

    public void setConvertible(Boolean convertible) {
        this.convertible = convertible;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

}
