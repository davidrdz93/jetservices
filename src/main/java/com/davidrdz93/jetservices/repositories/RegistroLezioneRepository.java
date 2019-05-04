package com.davidrdz93.jetservices.repositories;

import com.davidrdz93.jetservices.entities.RegistroLezione;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroLezioneRepository extends CrudRepository<RegistroLezione, Long>, JpaSpecificationExecutor
{
    List<RegistroLezione> findByCorsoId(Long idCorso);
}
