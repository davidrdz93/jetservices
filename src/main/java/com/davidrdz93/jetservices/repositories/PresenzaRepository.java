package com.davidrdz93.jetservices.repositories;

import com.davidrdz93.jetservices.entities.Presenza;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresenzaRepository extends CrudRepository<Presenza, Long>
{ }
