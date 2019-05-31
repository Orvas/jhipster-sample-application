package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListPipejointType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListPipejointType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListPipejointTypeRepository extends JpaRepository<ListPipejointType, Long>, JpaSpecificationExecutor<ListPipejointType> {

}
