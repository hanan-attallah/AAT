package com.aatapi.datatransferobject;

import com.aatapi.domainobject.Description;
import com.aatapi.domainobject.Details;
import com.aatapi.domainobject.GeoCoordinate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class AATDTO
{
    @JsonIgnore
    private Long _id;

    private String id;

    private String title;

    private Description description;

    private String website;

    private String image;

    private GeoCoordinate location;

    private List<Details> otherDownload = new ArrayList<>();

    private AATDTO()
    {
    }

    public AATDTO(Long _id, String id, String title, Description description, String website, String image, GeoCoordinate location, List<Details> otherDownload)
    {
        this._id = _id;
        this.id = id;
        this.title = title;
        this.description = description;
        this.website = website;
        this.image = image;
        this.location = location;
        this.otherDownload = otherDownload;
    }


    public static AATDTOBuilder newBuilder()
    {
        return new AATDTOBuilder();
    }

    @JsonProperty
    public Long get_id() {
        return _id;
    }

    public static class AATDTOBuilder
    {
        private Long _id;

        private String id;

        private String title;

        private Description description;

        private String website;

        private String image;

        private GeoCoordinate location;

        @JsonProperty("other_download")
        private List<Details> otherDownload = new ArrayList<>();

        public AATDTOBuilder set_Id(Long _id)
        {
            this._id = _id;
            return this;
        }

        public AATDTOBuilder setId(String id)
        {
            this.id = id;
            return this;
        }

        public AATDTOBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public AATDTOBuilder setDescription(Description description) {
            this.description = description;
            return this;
        }

        public AATDTOBuilder setWebsite(String link) {
            this.website = link;
            return this;
        }

        public AATDTOBuilder setImage(String image) {
            this.image = image;
            return this;
        }

        public AATDTOBuilder setLocation(GeoCoordinate location) {
            this.location = location;
            return this;
        }

        public AATDTOBuilder setOtherDownload(List<Details> otherDownload) {
            this.otherDownload = otherDownload;
            return this;
        }

        public AATDTO createAATDTO()
        {
            //use read abt builder design pattern
            return new AATDTO(_id, id, title, description, website, image, location, otherDownload);
        }

    }
}
