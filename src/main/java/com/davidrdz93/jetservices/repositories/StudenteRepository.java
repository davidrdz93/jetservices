package com.davidrdz93.jetservices.repositories;

import com.davidrdz93.jetservices.entities.Studente;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudenteRepository extends CrudRepository<Studente, Long>, JpaSpecificationExecutor
{}
