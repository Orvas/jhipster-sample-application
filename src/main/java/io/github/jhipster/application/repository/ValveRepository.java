package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Valve;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Valve entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ValveRepository extends JpaRepository<Valve, Long>, JpaSpecificationExecutor<Valve> {

}
