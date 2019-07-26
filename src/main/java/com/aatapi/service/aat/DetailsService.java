package com.aatapi.service.aat;

import com.aatapi.dataaccessobject.AATRepository;
import com.aatapi.dataaccessobject.DetailsRepository;
import com.aatapi.domainobject.AATDO;
import com.aatapi.domainobject.Details;
import com.aatapi.exception.ConstraintsViolationException;
import com.aatapi.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some car specific things.
 * <p/>
 */
@Service
public class DetailsService
{
    private final DetailsRepository detailsRepository;

    public DetailsService(final DetailsRepository detailsRepository)
    {
        this.detailsRepository = detailsRepository;
    }

    public Iterable<Details> list() {
        return detailsRepository.findAll();
    }

    public Details save(Details user) {
        return detailsRepository.save(user);
    }

    public Iterable<Details> save(List<Details> users) {
        return detailsRepository.saveAll(users);
    }

}
