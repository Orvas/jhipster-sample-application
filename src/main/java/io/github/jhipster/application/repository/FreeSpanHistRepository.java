package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.FreeSpanHist;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FreeSpanHist entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FreeSpanHistRepository extends JpaRepository<FreeSpanHist, Long>, JpaSpecificationExecutor<FreeSpanHist> {

}
