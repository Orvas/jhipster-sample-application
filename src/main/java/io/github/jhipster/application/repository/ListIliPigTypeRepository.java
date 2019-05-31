package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListIliPigType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListIliPigType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListIliPigTypeRepository extends JpaRepository<ListIliPigType, Long>, JpaSpecificationExecutor<ListIliPigType> {

}
