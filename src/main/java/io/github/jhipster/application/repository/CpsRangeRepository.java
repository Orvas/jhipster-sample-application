package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.CpsRange;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CpsRange entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CpsRangeRepository extends JpaRepository<CpsRange, Long>, JpaSpecificationExecutor<CpsRange> {

}
