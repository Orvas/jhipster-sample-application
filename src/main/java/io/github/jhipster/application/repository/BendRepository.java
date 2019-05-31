package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Bend;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Bend entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BendRepository extends JpaRepository<Bend, Long>, JpaSpecificationExecutor<Bend> {

}
