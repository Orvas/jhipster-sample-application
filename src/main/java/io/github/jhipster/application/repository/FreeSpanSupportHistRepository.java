package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.FreeSpanSupportHist;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FreeSpanSupportHist entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FreeSpanSupportHistRepository extends JpaRepository<FreeSpanSupportHist, Long>, JpaSpecificationExecutor<FreeSpanSupportHist> {

}
