package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListSafetyClass;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListSafetyClass entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListSafetyClassRepository extends JpaRepository<ListSafetyClass, Long>, JpaSpecificationExecutor<ListSafetyClass> {

}
