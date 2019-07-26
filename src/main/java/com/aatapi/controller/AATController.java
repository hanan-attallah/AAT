package com.aatapi.controller;

import com.aatapi.controller.mapper.AATMapper;
import com.aatapi.datatransferobject.AATDTO;
import com.aatapi.domainobject.Details;
import com.aatapi.service.aat.AATService;
import com.aatapi.service.aat.DetailsService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * All operations with a AAT will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/aat")
public class AATController
{

    private final AATService aatService;
    private final DetailsService detailsService;

    @Autowired
    public AATController(final AATService aatService, final DetailsService detailsService)
    {
        this.aatService = aatService;
        this.detailsService = detailsService;
    }

    @GetMapping("/list")
    public List<AATDTO> list()
    {
        return AATMapper.makeAATDTOList(Lists.newArrayList(aatService.list()));
    }



    @GetMapping("/list2")
    public Iterable<Details> list2() {
        return detailsService.list();
    }
}
