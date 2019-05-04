package com.davidrdz93.jetservices.services;

import com.davidrdz93.jetservices.entities.RegistroLezione;
import com.davidrdz93.jetservices.repositories.PresenzaRepository;
import com.davidrdz93.jetservices.repositories.RegistroLezioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service(value = "mainRegistroLezioniImp")
public class RegistroLezioniServiceImp implements RegistroLezioniService
{

    private RegistroLezioneRepository registroLezioneRepository;
    private PresenzaRepository presenzaRepository;

    @Autowired
    public RegistroLezioniServiceImp(RegistroLezioneRepository registroLezioneRepository,
                                     PresenzaRepository presenzaRepository)
    {
        this.registroLezioneRepository = registroLezioneRepository;
        this.presenzaRepository = presenzaRepository;
    }


    @Override
    public List<RegistroLezione> findLezioni(Long corsoId, Long insegnanteId, Date dataDa, Date dataA)
    {
        return this.registroLezioneRepository.findAll(new Specification<RegistroLezione>() {
            @Override
            public Predicate toPredicate(Root<RegistroLezione> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<Predicate>();

                if (corsoId != null)
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("corso"), corsoId)));

                if (insegnanteId != null)
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("insegnante"), insegnanteId)));

                if (dataDa != null || dataA != null)
                {
                    if (dataDa != null && dataA == null)
                        predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("data"), dataDa)));

                    else if (dataDa == null && dataA != null)
                        predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("data"), dataA)));

                    else
                        predicates.add(criteriaBuilder.and(criteriaBuilder.between(root.get("data"), dataDa, dataA)));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }

    @Override
    @Transactional
    public void deleteLezioneAndPresenze(Long idLezione)
    {
        this.presenzaRepository.deletePresenzaByLezioneId(idLezione);
        this.registroLezioneRepository.deleteById(idLezione);
    }
}
