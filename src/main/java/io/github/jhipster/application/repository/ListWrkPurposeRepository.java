package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListWrkPurpose;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListWrkPurpose entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListWrkPurposeRepository extends JpaRepository<ListWrkPurpose, Long>, JpaSpecificationExecutor<ListWrkPurpose> {

}
