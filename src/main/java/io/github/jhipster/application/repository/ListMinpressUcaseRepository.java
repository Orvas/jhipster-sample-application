package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListMinpressUcase;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListMinpressUcase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListMinpressUcaseRepository extends JpaRepository<ListMinpressUcase, Long>, JpaSpecificationExecutor<ListMinpressUcase> {

}
