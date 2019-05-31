package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListThreat;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListThreat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListThreatRepository extends JpaRepository<ListThreat, Long>, JpaSpecificationExecutor<ListThreat> {

}
