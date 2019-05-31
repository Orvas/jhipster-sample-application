package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.AnodeHist;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AnodeHist entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnodeHistRepository extends JpaRepository<AnodeHist, Long>, JpaSpecificationExecutor<AnodeHist> {

}
