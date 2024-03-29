package com.davidrdz93.jetservices.services;

import com.davidrdz93.jetservices.entities.Iscrizione;
import com.davidrdz93.jetservices.entities.Studente;
import com.davidrdz93.jetservices.repositories.IscrizioneRepository;
import com.davidrdz93.jetservices.repositories.StudenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "mainStudentiImp")
public class StudentiServiceImp implements StudentiService
{
    private StudenteRepository studenteRepository;
    private IscrizioneRepository iscrizioneRepository;

    @Autowired
    public StudentiServiceImp(StudenteRepository studenteRepository,
                              IscrizioneRepository iscrizioneRepository)
    {
        this.studenteRepository = studenteRepository;
        this.iscrizioneRepository = iscrizioneRepository;
    }


    @Override
    public List<Studente> retrieveStudenti(String nome, String cognome, String cf) {

        return this.studenteRepository.findAll(new Specification<Studente>() {
            @Override
            public Predicate toPredicate(Root<Studente> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<Predicate>();

                if (nome != null && !nome.equals(""))
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("nome"), "%" + nome + "%")));

                if (cognome != null && !cognome.equals(""))
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("cognome"), "%" + cognome + "%")));

                if (cf != null && !cf.equals(""))
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("cf"), "%" + cf + "%")));

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });


    }

    @Override
    public List<Studente> findIscrittiByCorsoId(Long idCorso)
    {
        List<Studente> studenti = new ArrayList<Studente>();
        List<Iscrizione> iscrizioni = new ArrayList<Iscrizione>();

        iscrizioni = this.iscrizioneRepository.findByCorsoId(idCorso);
        iscrizioni
                .stream()
                .filter(iscrizione -> iscrizione.getDataFineValidita() == null)
                .forEach(iscrizione -> studenti.add(iscrizione.getStudente()));

        return studenti;
    }

    @Override
    public List<Studente> findStudentiByNomeCognome(String nomeCognome)
    {
        if (nomeCognome != null)
        {
            String[] parts = nomeCognome.split(" ");
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (parts.length == 1) // fai query in or con nome o cognome
                return this.studenteRepository.findAll(new Specification<Studente>() {
                    @Override
                    public Predicate toPredicate(Root<Studente> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder)
                    {
                        predicates.add(criteriaBuilder.or(criteriaBuilder.like(root.get("nome"), "%" + parts[0] + "%")));
                        predicates.add(criteriaBuilder.or(criteriaBuilder.like(root.get("cognome"), "%" + parts[0] + "%")));
                        return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
                    }
                });

            else // come primo elemento prendo il nome, come secondo elemento prendo il cognome
                return this.studenteRepository.findAll(new Specification() {
                    @Override
                    public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder)
                    {
                        predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("nome"), "%" + parts[0] + "%")));
                        predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("cognome"), "%" + parts[1] + "%")));
                        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                    }
                });

        }
        return null;
    }
}
