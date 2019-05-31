package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListLongSeamWeldType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListLongSeamWeldType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListLongSeamWeldTypeRepository extends JpaRepository<ListLongSeamWeldType, Long>, JpaSpecificationExecutor<ListLongSeamWeldType> {

}
