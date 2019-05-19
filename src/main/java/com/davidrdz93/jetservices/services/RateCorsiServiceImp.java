package com.davidrdz93.jetservices.services;

import com.davidrdz93.jetservices.entities.RateCorsi;
import com.davidrdz93.jetservices.repositories.RateCorsiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service(value = "mainRateCorsiImp")
public class RateCorsiServiceImp implements RateCorsiService
{

    private RateCorsiRepository rateCorsiRepository;

    @Autowired
    public RateCorsiServiceImp(RateCorsiRepository rateCorsiRepository)
    {
        this.rateCorsiRepository = rateCorsiRepository;
    }

    @Override
    public List<RateCorsi> getRateCorsi(Date dataScadenzaDa, Date dataScadenzaA) {
        return this.rateCorsiRepository.findAll(new Specification<RateCorsi>() {
            @Override
            public Predicate toPredicate(Root<RateCorsi> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<Predicate>();

                if (dataScadenzaDa != null || dataScadenzaA != null)
                {
                    if (dataScadenzaA == null)
                        predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("dataScadenza"), dataScadenzaDa)));

                    else if (dataScadenzaDa == null)
                        predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("dataScadenza"), dataScadenzaA)));

                    else
                        predicates.add(criteriaBuilder.and(criteriaBuilder.between(root.get("dataScadenza"), dataScadenzaDa, dataScadenzaA)));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }
}
