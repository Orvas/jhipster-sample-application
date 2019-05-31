package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListInternalPressUcase;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListInternalPressUcase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListInternalPressUcaseRepository extends JpaRepository<ListInternalPressUcase, Long>, JpaSpecificationExecutor<ListInternalPressUcase> {

}
