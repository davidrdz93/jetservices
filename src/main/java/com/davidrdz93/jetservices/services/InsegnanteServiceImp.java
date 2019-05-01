package com.davidrdz93.jetservices.services;

import com.davidrdz93.jetservices.entities.Insegnante;
import com.davidrdz93.jetservices.repositories.InsegnanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Component(value = "mainInsegnanteImp")
public class InsegnanteServiceImp implements InsegnanteService
{

    private InsegnanteRepository insegnanteRepository;

    @Autowired
    public InsegnanteServiceImp(InsegnanteRepository insegnanteRepository)
    {
        this.insegnanteRepository = insegnanteRepository;
    }

    @Override
    public List<Insegnante> getInsegnanti(String nome, String cognome, String email)
    {
        return this.insegnanteRepository.findAll(new Specification<Insegnante>() {
            @Override
            public Predicate toPredicate(Root<Insegnante> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<Predicate>();

                if (nome != null && !nome.equals(""))
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("nome"), "%" + nome + "%")));

                if (cognome != null && !cognome.equals(""))
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("cognome"), "%" + cognome + "%")));

                if (email != null && !email.equals(""))
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("email"), "%" + email + "%")));

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }
}
