package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Tee;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Tee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TeeRepository extends JpaRepository<Tee, Long>, JpaSpecificationExecutor<Tee> {

}
