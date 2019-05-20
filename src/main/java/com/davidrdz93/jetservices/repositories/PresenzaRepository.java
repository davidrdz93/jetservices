package com.davidrdz93.jetservices.repositories;

import com.davidrdz93.jetservices.entities.Presenza;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PresenzaRepository extends CrudRepository<Presenza, Long>
{
    Optional<List<Presenza>> findByLezioneId(Long idRegistroLezione);

    void deletePresenzaByLezioneIdAndStudenteId(Long lezioneId, Long StudenteId);

    void deletePresenzaByLezioneId(Long idLezione);

    Optional<List<Presenza>> findByStudenteId(Long idStudente);
}
