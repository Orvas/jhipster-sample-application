package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListValveSpecification;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListValveSpecification entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListValveSpecificationRepository extends JpaRepository<ListValveSpecification, Long>, JpaSpecificationExecutor<ListValveSpecification> {

}
