package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListEnvDirection;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListEnvDirection entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListEnvDirectionRepository extends JpaRepository<ListEnvDirection, Long>, JpaSpecificationExecutor<ListEnvDirection> {

}
