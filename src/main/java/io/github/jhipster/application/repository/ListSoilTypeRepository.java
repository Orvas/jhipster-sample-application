package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListSoilType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListSoilType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListSoilTypeRepository extends JpaRepository<ListSoilType, Long>, JpaSpecificationExecutor<ListSoilType> {

}
