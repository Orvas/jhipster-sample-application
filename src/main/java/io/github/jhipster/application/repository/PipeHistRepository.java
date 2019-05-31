package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.PipeHist;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PipeHist entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PipeHistRepository extends JpaRepository<PipeHist, Long>, JpaSpecificationExecutor<PipeHist> {

}
