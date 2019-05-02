package com.davidrdz93.jetservices.repositories;

import com.davidrdz93.jetservices.entities.RegistroLezione;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistroLezioneRepository extends CrudRepository<RegistroLezione, Long>
{
    Optional<RegistroLezione> findByCorsoId(Long idCorso);
}
