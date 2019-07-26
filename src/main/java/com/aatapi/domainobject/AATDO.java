package com.aatapi.domainobject;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "aat")
@Getter
@Setter
@JsonAutoDetect
@AllArgsConstructor
public class AATDO
{
    @JsonIgnore
    @Id
    @GeneratedValue
    private Long _id;

    private String id;

    private String title;

    @Embedded
    private Description description;

    private String website;

    private String image;

    @Embedded
    private GeoCoordinate location;

    @JsonProperty("other_download")
    @OneToMany(mappedBy = "aat", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Details> otherDownload = new ArrayList<>();

    private Boolean deleted = false;

    private AATDO()
    {
    }

}
