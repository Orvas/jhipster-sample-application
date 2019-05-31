package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ValveHist;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ValveHist entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ValveHistRepository extends JpaRepository<ValveHist, Long>, JpaSpecificationExecutor<ValveHist> {

}
