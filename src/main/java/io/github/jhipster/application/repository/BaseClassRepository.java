package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.BaseClass;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BaseClass entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BaseClassRepository extends JpaRepository<BaseClass, Long>, JpaSpecificationExecutor<BaseClass> {

}
