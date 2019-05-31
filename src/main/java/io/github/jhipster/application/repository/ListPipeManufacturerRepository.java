package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListPipeManufacturer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListPipeManufacturer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListPipeManufacturerRepository extends JpaRepository<ListPipeManufacturer, Long>, JpaSpecificationExecutor<ListPipeManufacturer> {

}
