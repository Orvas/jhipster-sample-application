package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListBoundaryCondUcase;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListBoundaryCondUcase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListBoundaryCondUcaseRepository extends JpaRepository<ListBoundaryCondUcase, Long>, JpaSpecificationExecutor<ListBoundaryCondUcase> {

}
