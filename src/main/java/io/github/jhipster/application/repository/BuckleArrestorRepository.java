package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.BuckleArrestor;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BuckleArrestor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BuckleArrestorRepository extends JpaRepository<BuckleArrestor, Long>, JpaSpecificationExecutor<BuckleArrestor> {

}
