package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.CpsHist;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CpsHist entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CpsHistRepository extends JpaRepository<CpsHist, Long>, JpaSpecificationExecutor<CpsHist> {

}
