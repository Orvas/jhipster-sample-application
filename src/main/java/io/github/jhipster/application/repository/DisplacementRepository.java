package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Displacement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Displacement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DisplacementRepository extends JpaRepository<Displacement, Long>, JpaSpecificationExecutor<Displacement> {

}
