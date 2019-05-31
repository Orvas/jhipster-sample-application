package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.LaunchReceiverHist;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LaunchReceiverHist entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LaunchReceiverHistRepository extends JpaRepository<LaunchReceiverHist, Long>, JpaSpecificationExecutor<LaunchReceiverHist> {

}
