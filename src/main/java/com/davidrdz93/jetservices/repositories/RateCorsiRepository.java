package com.davidrdz93.jetservices.repositories;

import com.davidrdz93.jetservices.entities.RateCorsi;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.List;

public interface RateCorsiRepository extends CrudRepository<RateCorsi, Long>, JpaSpecificationExecutor
{
    Optional<List<RateCorsi>> findAllByIscrizioneId(Long iscrizioneId);
}
