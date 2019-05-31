package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListAnodeBraceleteType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListAnodeBraceleteType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListAnodeBraceleteTypeRepository extends JpaRepository<ListAnodeBraceleteType, Long>, JpaSpecificationExecutor<ListAnodeBraceleteType> {

}
