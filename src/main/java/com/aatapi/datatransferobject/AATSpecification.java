package com.aatapi.datatransferobject;

import com.aatapi.domainobject.AATDO;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AATSpecification implements Specification<AATDO> {

    private AATDO aatDO;

    public AATSpecification(AATDO aatDO) {
        super();
        this.aatDO = aatDO;
    }

    public Predicate toPredicate(Root<AATDO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

        Predicate predicate = cb.conjunction();

        if (aatDO.getId() != null) {
            predicate.getExpressions().add(cb.equal(root.get("id"), aatDO.getId()));
        }

        if (aatDO.getTitle() != null) {
            predicate.getExpressions().add(cb.like(root.get("title"), aatDO.getTitle()));
        }

        if (aatDO.getDescription() != null) {
            predicate.getExpressions().add(cb.equal(root.get("description"), aatDO.getDescription()));
        }

        if (aatDO.getDeleted() != null) {
            predicate.getExpressions().add(cb.equal(root.get("deleted"), aatDO.getDeleted()));
        }

//        if (aatDO.getTransientMap().size() > 0) {
//            aatDO.getTransientMap().forEach((k,v)->{
//                predicate.getExpressions().add(cb.equal(root.get("selectedCar").get(k.toString()), v));
//            });
//        }

        return predicate;
    }
}
