package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.BuckleArrestorHist;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BuckleArrestorHist entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BuckleArrestorHistRepository extends JpaRepository<BuckleArrestorHist, Long>, JpaSpecificationExecutor<BuckleArrestorHist> {

}
