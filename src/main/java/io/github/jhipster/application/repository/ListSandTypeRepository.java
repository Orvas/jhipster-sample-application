package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListSandType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListSandType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListSandTypeRepository extends JpaRepository<ListSandType, Long>, JpaSpecificationExecutor<ListSandType> {

}
