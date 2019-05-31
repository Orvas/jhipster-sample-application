package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListPipelineLocation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListPipelineLocation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListPipelineLocationRepository extends JpaRepository<ListPipelineLocation, Long>, JpaSpecificationExecutor<ListPipelineLocation> {

}
