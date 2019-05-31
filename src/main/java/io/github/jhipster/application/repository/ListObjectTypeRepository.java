package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListObjectType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListObjectType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListObjectTypeRepository extends JpaRepository<ListObjectType, Long>, JpaSpecificationExecutor<ListObjectType> {

}
