package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListTeeManufacturer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListTeeManufacturer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListTeeManufacturerRepository extends JpaRepository<ListTeeManufacturer, Long>, JpaSpecificationExecutor<ListTeeManufacturer> {

}
