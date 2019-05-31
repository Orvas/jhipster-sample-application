package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListEnvPoint;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListEnvPoint entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListEnvPointRepository extends JpaRepository<ListEnvPoint, Long>, JpaSpecificationExecutor<ListEnvPoint> {

}
