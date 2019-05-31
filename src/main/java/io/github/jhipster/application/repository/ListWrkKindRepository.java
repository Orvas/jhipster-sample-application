package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListWrkKind;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListWrkKind entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListWrkKindRepository extends JpaRepository<ListWrkKind, Long>, JpaSpecificationExecutor<ListWrkKind> {

}
