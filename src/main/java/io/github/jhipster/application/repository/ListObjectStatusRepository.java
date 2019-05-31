package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListObjectStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListObjectStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListObjectStatusRepository extends JpaRepository<ListObjectStatus, Long>, JpaSpecificationExecutor<ListObjectStatus> {

}
