package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListPipeSpecification;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListPipeSpecification entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListPipeSpecificationRepository extends JpaRepository<ListPipeSpecification, Long>, JpaSpecificationExecutor<ListPipeSpecification> {

}
