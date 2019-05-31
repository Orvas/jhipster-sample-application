package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListNominalWallThickness;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListNominalWallThickness entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListNominalWallThicknessRepository extends JpaRepository<ListNominalWallThickness, Long>, JpaSpecificationExecutor<ListNominalWallThickness> {

}
