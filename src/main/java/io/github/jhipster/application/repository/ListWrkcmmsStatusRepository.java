package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListWrkcmmsStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListWrkcmmsStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListWrkcmmsStatusRepository extends JpaRepository<ListWrkcmmsStatus, Long>, JpaSpecificationExecutor<ListWrkcmmsStatus> {

}
