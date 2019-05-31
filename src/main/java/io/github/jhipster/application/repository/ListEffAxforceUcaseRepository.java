package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListEffAxforceUcase;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListEffAxforceUcase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListEffAxforceUcaseRepository extends JpaRepository<ListEffAxforceUcase, Long>, JpaSpecificationExecutor<ListEffAxforceUcase> {

}
