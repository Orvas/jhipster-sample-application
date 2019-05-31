package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListInternalCoating;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListInternalCoating entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListInternalCoatingRepository extends JpaRepository<ListInternalCoating, Long>, JpaSpecificationExecutor<ListInternalCoating> {

}
