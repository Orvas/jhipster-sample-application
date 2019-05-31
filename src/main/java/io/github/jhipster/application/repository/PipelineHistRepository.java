package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.PipelineHist;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PipelineHist entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PipelineHistRepository extends JpaRepository<PipelineHist, Long>, JpaSpecificationExecutor<PipelineHist> {

}
