package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListPipejointSpecification;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListPipejointSpecification entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListPipejointSpecificationRepository extends JpaRepository<ListPipejointSpecification, Long>, JpaSpecificationExecutor<ListPipejointSpecification> {

}
