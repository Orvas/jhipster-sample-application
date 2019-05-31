package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListBendManufacturer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListBendManufacturer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListBendManufacturerRepository extends JpaRepository<ListBendManufacturer, Long>, JpaSpecificationExecutor<ListBendManufacturer> {

}
