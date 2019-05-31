package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListRiskConsequence;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListRiskConsequence entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListRiskConsequenceRepository extends JpaRepository<ListRiskConsequence, Long>, JpaSpecificationExecutor<ListRiskConsequence> {

}
