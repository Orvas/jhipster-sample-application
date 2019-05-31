package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListClcKind;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListClcKind entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListClcKindRepository extends JpaRepository<ListClcKind, Long>, JpaSpecificationExecutor<ListClcKind> {

}
