package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListBucklarrManufacturer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListBucklarrManufacturer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListBucklarrManufacturerRepository extends JpaRepository<ListBucklarrManufacturer, Long>, JpaSpecificationExecutor<ListBucklarrManufacturer> {

}
