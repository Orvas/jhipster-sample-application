package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListWrkStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListWrkStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListWrkStatusRepository extends JpaRepository<ListWrkStatus, Long>, JpaSpecificationExecutor<ListWrkStatus> {

}
