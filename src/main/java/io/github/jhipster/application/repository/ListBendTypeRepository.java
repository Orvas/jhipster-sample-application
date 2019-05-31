package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListBendType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListBendType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListBendTypeRepository extends JpaRepository<ListBendType, Long>, JpaSpecificationExecutor<ListBendType> {

}
