package com.davidrdz93.jetservices.repositories;

import com.davidrdz93.jetservices.entities.Iscrizione;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IscrizioneRepository extends CrudRepository<Iscrizione, Long>, JpaSpecificationExecutor
{
    Optional<Iscrizione> findByStudenteId(Long studenteId);

    Optional<Iscrizione> findByCorsoId(Long corsoId);

    Optional<Iscrizione> findByInsegnanteId(Long insegnateId);
}
