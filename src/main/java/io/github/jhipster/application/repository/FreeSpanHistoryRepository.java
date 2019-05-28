package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.FreeSpanHistory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FreeSpanHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FreeSpanHistoryRepository extends JpaRepository<FreeSpanHistory, Long> {

}
