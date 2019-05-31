package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.DisplacementHist;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DisplacementHist entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DisplacementHistRepository extends JpaRepository<DisplacementHist, Long>, JpaSpecificationExecutor<DisplacementHist> {

}
