package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListClayType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListClayType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListClayTypeRepository extends JpaRepository<ListClayType, Long>, JpaSpecificationExecutor<ListClayType> {

}
