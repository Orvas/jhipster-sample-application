package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.MetaList;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MetaList entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MetaListRepository extends JpaRepository<MetaList, Long>, JpaSpecificationExecutor<MetaList> {

}
