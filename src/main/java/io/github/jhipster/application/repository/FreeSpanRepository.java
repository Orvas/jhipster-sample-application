package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.FreeSpan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FreeSpan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FreeSpanRepository extends JpaRepository<FreeSpan, Long>, JpaSpecificationExecutor<FreeSpan> {

}
