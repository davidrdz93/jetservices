package com.davidrdz93.jetservices.services;

import com.davidrdz93.jetservices.entities.Corso;
import com.davidrdz93.jetservices.entities.RegistroLezione;
import com.davidrdz93.jetservices.exceptions.NotFound404Exception;
import com.davidrdz93.jetservices.repositories.CorsoRepository;
import com.davidrdz93.jetservices.repositories.RegistroLezioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service(value = "mainCorsoImp")
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
                                     String livello)
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
