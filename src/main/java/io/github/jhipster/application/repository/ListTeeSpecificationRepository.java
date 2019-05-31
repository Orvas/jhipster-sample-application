package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListTeeSpecification;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListTeeSpecification entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListTeeSpecificationRepository extends JpaRepository<ListTeeSpecification, Long>, JpaSpecificationExecutor<ListTeeSpecification> {

}
