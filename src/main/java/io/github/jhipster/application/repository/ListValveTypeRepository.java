package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListValveType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListValveType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListValveTypeRepository extends JpaRepository<ListValveType, Long>, JpaSpecificationExecutor<ListValveType> {

}
