package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListBucklarrSpecification;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListBucklarrSpecification entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListBucklarrSpecificationRepository extends JpaRepository<ListBucklarrSpecification, Long>, JpaSpecificationExecutor<ListBucklarrSpecification> {

}
