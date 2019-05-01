package com.davidrdz93.jetservices.repositories;

import com.davidrdz93.jetservices.entities.Insegnante;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface InsegnanteRepository extends CrudRepository<Insegnante, Long>, JpaSpecificationExecutor
{ }
