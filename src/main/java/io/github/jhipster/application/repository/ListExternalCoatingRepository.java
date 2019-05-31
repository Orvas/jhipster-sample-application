package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListExternalCoating;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListExternalCoating entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListExternalCoatingRepository extends JpaRepository<ListExternalCoating, Long>, JpaSpecificationExecutor<ListExternalCoating> {

}
