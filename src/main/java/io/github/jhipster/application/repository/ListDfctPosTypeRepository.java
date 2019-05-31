package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListDfctPosType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListDfctPosType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListDfctPosTypeRepository extends JpaRepository<ListDfctPosType, Long>, JpaSpecificationExecutor<ListDfctPosType> {

}
