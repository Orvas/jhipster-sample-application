package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListValveFunction;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListValveFunction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListValveFunctionRepository extends JpaRepository<ListValveFunction, Long>, JpaSpecificationExecutor<ListValveFunction> {

}
