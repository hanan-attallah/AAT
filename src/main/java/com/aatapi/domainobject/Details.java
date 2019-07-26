package com.aatapi.domainobject;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "other_download")
@Getter
@Setter
@JsonAutoDetect
public class Details
{
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String status;

    @JsonProperty("media_type")
    private String mediaType;

    @JsonProperty("access_url")
    private String accessUrl;

    @Column(length=10485760)
    private String description;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "aat_id")
    private AATDO aat;
}
