package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListClcType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListClcType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListClcTypeRepository extends JpaRepository<ListClcType, Long>, JpaSpecificationExecutor<ListClcType> {

}
