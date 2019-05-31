package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Pipejoint;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Pipejoint entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PipejointRepository extends JpaRepository<Pipejoint, Long>, JpaSpecificationExecutor<Pipejoint> {

}
