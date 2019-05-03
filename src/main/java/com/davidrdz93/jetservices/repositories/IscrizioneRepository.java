package com.davidrdz93.jetservices.repositories;

import com.davidrdz93.jetservices.entities.Iscrizione;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.List;

public interface IscrizioneRepository extends CrudRepository<Iscrizione, Long>, JpaSpecificationExecutor
{
    Optional<Iscrizione> findByStudenteId(Long studenteId);

    List<Iscrizione> findByCorsoId(Long corsoId);

    Optional<Iscrizione> findByInsegnanteId(Long insegnateId);
}
