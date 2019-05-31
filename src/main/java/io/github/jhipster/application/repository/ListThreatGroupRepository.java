package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ListThreatGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListThreatGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListThreatGroupRepository extends JpaRepository<ListThreatGroup, Long>, JpaSpecificationExecutor<ListThreatGroup> {

}
