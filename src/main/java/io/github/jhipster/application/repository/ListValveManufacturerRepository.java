package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListValveManufacturer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListValveManufacturer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListValveManufacturerRepository extends JpaRepository<ListValveManufacturer, Long>, JpaSpecificationExecutor<ListValveManufacturer> {

}
