package com.aatapi.service.aat;

import com.aatapi.dataaccessobject.AATRepository;
import com.aatapi.domainobject.AATDO;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some car specific things.
 * <p/>
 */
@Service
public class AATService
{
    private final AATRepository aatRepository;

    public AATService(final AATRepository aatRepository)
    {
        this.aatRepository = aatRepository;
    }

    public Iterable<AATDO> list() {
        return aatRepository.findAll();
    }

    public AATDO save(AATDO user) {
        return aatRepository.save(user);
    }

    public Iterable<AATDO> save(List<AATDO> objects) {
        objects.forEach(e -> {
            e.getOtherDownload().stream().forEach(u -> u.setAat(e));
        });
        return aatRepository.saveAll(objects);
    }
}
