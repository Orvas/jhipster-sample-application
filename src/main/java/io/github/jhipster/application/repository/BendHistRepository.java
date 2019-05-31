package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.BendHist;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BendHist entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BendHistRepository extends JpaRepository<BendHist, Long>, JpaSpecificationExecutor<BendHist> {

}
