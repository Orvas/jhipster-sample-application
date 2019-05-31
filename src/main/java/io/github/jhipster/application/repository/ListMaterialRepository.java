package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListMaterial;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListMaterial entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListMaterialRepository extends JpaRepository<ListMaterial, Long>, JpaSpecificationExecutor<ListMaterial> {

}
