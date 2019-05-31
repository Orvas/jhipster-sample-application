package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.LaunchReceiver;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LaunchReceiver entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LaunchReceiverRepository extends JpaRepository<LaunchReceiver, Long>, JpaSpecificationExecutor<LaunchReceiver> {

}
