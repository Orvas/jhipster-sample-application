package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListClcLvl;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListClcLvl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListClcLvlRepository extends JpaRepository<ListClcLvl, Long>, JpaSpecificationExecutor<ListClcLvl> {

}
