package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListTeeType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListTeeType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListTeeTypeRepository extends JpaRepository<ListTeeType, Long>, JpaSpecificationExecutor<ListTeeType> {

}
