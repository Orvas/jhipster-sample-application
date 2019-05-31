package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListBucklarrType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListBucklarrType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListBucklarrTypeRepository extends JpaRepository<ListBucklarrType, Long>, JpaSpecificationExecutor<ListBucklarrType> {

}
