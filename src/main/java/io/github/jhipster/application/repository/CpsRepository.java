package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Cps;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Cps entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CpsRepository extends JpaRepository<Cps, Long>, JpaSpecificationExecutor<Cps> {

}
