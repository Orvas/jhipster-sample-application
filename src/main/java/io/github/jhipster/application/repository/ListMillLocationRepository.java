package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListMillLocation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListMillLocation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListMillLocationRepository extends JpaRepository<ListMillLocation, Long>, JpaSpecificationExecutor<ListMillLocation> {

}
