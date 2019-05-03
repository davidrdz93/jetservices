package com.davidrdz93.jetservices.services;

import com.davidrdz93.jetservices.entities.Corso;
import com.davidrdz93.jetservices.entities.RegistroLezione;
import com.davidrdz93.jetservices.exceptions.NotFound404Exception;
import com.davidrdz93.jetservices.repositories.CorsoRepository;
import com.davidrdz93.jetservices.repositories.RegistroLezioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component(value = "mainCorsoImp")
public class CorsoServiceImp implements CorsoService
{
    private CorsoRepository corsoRepository;
    private RegistroLezioneRepository registroLezioneRepository;

    @Autowired
    public CorsoServiceImp(CorsoRepository corsoRepository,
                           RegistroLezioneRepository registroLezioneRepository)
    {
        this.corsoRepository = corsoRepository;
        this.registroLezioneRepository = registroLezioneRepository;
    }

    @Override
    public List<Corso> retrieveCorsi(String nome,
                                     String lingua,
                                     String livello,
                                     Date dataInizioDa,
                                     Date dataInizioA,
                                     Date dataFineDa,
                                     Date dataFineA,
                                     boolean completato)
    {
        return this.corsoRepository.findAll(new Specification<Corso>(){
            @Override
            public Predicate toPredicate(Root<Corso> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<Predicate>();

                if (nome != null && !nome.equals(""))
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("nome"), "%" + nome + "%")));

                if (lingua != null && !lingua.equals(""))
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("lingua"), "%" + lingua + "%")));

                if (livello != null && !livello.equals(""))
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("livello"), "%" + livello + "%")));

                if (dataInizioA != null || dataInizioDa != null)
                {
                    if (dataInizioDa != null && dataInizioA == null)
                        predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("dataInizio"), dataInizioDa)));

                    else if (dataInizioDa == null && dataInizioA != null)
                        predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("dataInizio"), dataInizioA)));

                    else
                        predicates.add(criteriaBuilder.and(criteriaBuilder.between(root.get("dataInizio"), dataInizioDa, dataInizioA)));
                }

                if (dataFineDa != null || dataFineA != null)
                {
                    if (dataFineDa != null && dataFineA == null)
                        predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("dataFine"), dataFineDa)));

                    else if (dataFineDa == null && dataFineA != null)
                        predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("dataFine"), dataFineA)));

                    else
                        predicates.add(criteriaBuilder.and(criteriaBuilder.between(root.get("dataFine"), dataFineDa, dataFineA)));
                }

                if (completato)
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("complete"), true)));
                else
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("complete"), false)));

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }

    @Override
    public double oreResidue(Long corsoId)
    {
        double oreConsumate = 0d;
        double oreTotali = this.corsoRepository.findById(corsoId)
                .map(Corso::getNumeroOre)
                .orElseThrow(() -> new NotFound404Exception());

        List<RegistroLezione> lezioni = this.registroLezioneRepository.findByCorsoId(corsoId);

        for (RegistroLezione registroLezione: lezioni)
            oreConsumate += registroLezione.getOre() + ((double) registroLezione.getMinuti())/60;

        oreConsumate = Math.round(oreConsumate*100d)/100d; // arrotondo a due decimali

        return oreTotali - oreConsumate;
    }
}
