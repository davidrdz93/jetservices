package com.davidrdz93.jetservices.services;

import com.davidrdz93.jetservices.entities.Iscrizione;
import com.davidrdz93.jetservices.repositories.IscrizioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service(value = "mainIscrizioneImp")
public class IscrizioneServiceImp implements IscrizioneService
{

    private IscrizioneRepository iscrizioneRepository;

    @Autowired
    public IscrizioneServiceImp(IscrizioneRepository iscrizioneRepository)
    {
        this.iscrizioneRepository = iscrizioneRepository;
    }

    @Override
    public List<Iscrizione> getIscrizioni(Long idCorso,
                                          Long idInsegnante,
                                          Long idStudente,
                                          Date dataIscrizioneDa,
                                          Date dateIscrizioneA,
                                          Date dataIscrizioneFineDa,
                                          Date dateIscrizioneFineA)
    {
        return this.iscrizioneRepository.findAll(new Specification<Iscrizione>() {
            @Override
            public Predicate toPredicate(Root<Iscrizione> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {


                List<Predicate> predicates = new ArrayList<Predicate>();

                if (idCorso != null)
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("corso"), idCorso)));

                if (idInsegnante != null)
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("insegnante"), idInsegnante)));

                if (idStudente != null)
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("studente"), idStudente)));

                if (dataIscrizioneDa != null || dateIscrizioneA != null)
                {
                    if (dataIscrizioneDa != null && dateIscrizioneA == null)
                        predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("dataIscrizione"), dataIscrizioneDa)));

                    else if (dataIscrizioneDa == null && dateIscrizioneA != null)
                        predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("dataIscrizione"), dateIscrizioneA)));

                    else
                        predicates.add(criteriaBuilder.and(criteriaBuilder.between(root.get("dataIscrizione"), dataIscrizioneDa, dateIscrizioneA)));
                }

                if (dataIscrizioneFineDa != null || dateIscrizioneFineA != null)
                {
                    if (dataIscrizioneFineDa != null && dateIscrizioneFineA == null)
                        predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("dataFineValidita"), dataIscrizioneFineDa)));

                    else if (dataIscrizioneFineDa == null && dateIscrizioneFineA != null)
                        predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("dataFineValidita"), dateIscrizioneFineA)));

                    else
                        predicates.add(criteriaBuilder.and(criteriaBuilder.between(root.get("dataFineValidita"), dataIscrizioneFineDa, dateIscrizioneFineA)));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }
}
