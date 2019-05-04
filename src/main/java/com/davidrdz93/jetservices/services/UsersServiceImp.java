package com.davidrdz93.jetservices.services;

import com.davidrdz93.jetservices.entities.User;
import com.davidrdz93.jetservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Service(value = "mainUsersImp")
public class UsersServiceImp implements UserService
{
    private UserRepository userRepository;

    @Autowired
    public UsersServiceImp(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public List<User> retrieveUsers(String nome, String cognome, String username)
    {
        return this.userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<Predicate>();

                if (nome != null && !nome.equals(""))
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("nome"), "%" + nome + "%")));

                if (cognome != null && !cognome.equals(""))
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("cognome"), "%" + cognome + "%")));

                if (username != null && !username.equals(""))
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("username"), "%" + username + "%")));

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }

}
