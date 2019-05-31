package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Pipeline;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Pipeline entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PipelineRepository extends JpaRepository<Pipeline, Long>, JpaSpecificationExecutor<Pipeline> {

}
