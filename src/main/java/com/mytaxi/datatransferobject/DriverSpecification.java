package com.mytaxi.datatransferobject;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class DriverSpecification implements Specification<DriverDO> {

    private DriverDO driverDO;

    public DriverSpecification(DriverDO driverDO) {
        super();
        this.driverDO = driverDO;
    }

    public Predicate toPredicate(Root<DriverDO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

        Predicate predicate = cb.conjunction();

        if (driverDO.getId() != null) {
            predicate.getExpressions().add(cb.equal(root.get("id"), driverDO.getId()));
        }

        if (driverDO.getUsername() != null) {
            predicate.getExpressions().add(cb.like(root.get("username"), driverDO.getUsername()));
        }

        if (driverDO.getOnlineStatus() != null) {
            predicate.getExpressions().add(cb.equal(root.get("onlineStatus"), driverDO.getOnlineStatus()));
        }

        if (driverDO.getDeleted() != null) {
            predicate.getExpressions().add(cb.equal(root.get("deleted"), driverDO.getDeleted()));
        }

        if (driverDO.getTransientMap().size() > 0) {
            driverDO.getTransientMap().forEach((k,v)->{
                predicate.getExpressions().add(cb.equal(root.get("selectedCar").get(k.toString()), v));
            });
        }

        return predicate;
    }
}
