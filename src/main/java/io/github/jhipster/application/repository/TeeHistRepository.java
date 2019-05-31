package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.TeeHist;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TeeHist entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TeeHistRepository extends JpaRepository<TeeHist, Long>, JpaSpecificationExecutor<TeeHist> {

}
