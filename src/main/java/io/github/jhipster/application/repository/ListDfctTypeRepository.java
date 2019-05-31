package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListDfctType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListDfctType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListDfctTypeRepository extends JpaRepository<ListDfctType, Long>, JpaSpecificationExecutor<ListDfctType> {

}
