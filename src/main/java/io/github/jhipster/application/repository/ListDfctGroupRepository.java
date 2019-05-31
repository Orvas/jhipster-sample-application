package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListDfctGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListDfctGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListDfctGroupRepository extends JpaRepository<ListDfctGroup, Long>, JpaSpecificationExecutor<ListDfctGroup> {

}
