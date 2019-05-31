package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.PipelineSection;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PipelineSection entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PipelineSectionRepository extends JpaRepository<PipelineSection, Long>, JpaSpecificationExecutor<PipelineSection> {

}
