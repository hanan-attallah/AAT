package com.aatapi.controller.mapper;

import com.aatapi.datatransferobject.AATDTO;
import com.aatapi.domainobject.AATDO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AATMapper {
    public static AATDO makeAATDO(AATDTO aatDTO) {
        return new AATDO(aatDTO.get_id(),aatDTO.getId(),aatDTO.getTitle(), aatDTO.getDescription(),
                aatDTO.getWebsite(), aatDTO.getImage(), aatDTO.getLocation(), aatDTO.getOtherDownload(), true);
    }

    public static AATDTO makeAATDTO(AATDO aatDO) {
        AATDTO.AATDTOBuilder aatDTOBuilder = AATDTO.newBuilder()
                .setId(aatDO.getId())
                .setTitle(aatDO.getTitle())
                .setDescription(aatDO.getDescription())
                .setWebsite(aatDO.getWebsite())
                .setImage(aatDO.getImage())
                .setLocation(aatDO.getLocation())
                .setOtherDownload(aatDO.getOtherDownload());

        return aatDTOBuilder.createAATDTO();
    }

    public static List<AATDTO> makeAATDTOList(Collection<AATDO> aats) {
        return aats.stream()
                .map(AATMapper::makeAATDTO)
                .collect(Collectors.toList());
    }
}
