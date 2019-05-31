package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListSteelGrade;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListSteelGrade entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListSteelGradeRepository extends JpaRepository<ListSteelGrade, Long>, JpaSpecificationExecutor<ListSteelGrade> {

}
