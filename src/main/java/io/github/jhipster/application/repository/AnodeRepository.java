package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Anode;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Anode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnodeRepository extends JpaRepository<Anode, Long>, JpaSpecificationExecutor<Anode> {

}
