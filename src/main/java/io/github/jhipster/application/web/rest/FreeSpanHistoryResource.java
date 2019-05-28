package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.FreeSpanHistory;
import io.github.jhipster.application.repository.FreeSpanHistoryRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link io.github.jhipster.application.domain.FreeSpanHistory}.
 */
@RestController
@RequestMapping("/api")
public class FreeSpanHistoryResource {

    private final Logger log = LoggerFactory.getLogger(FreeSpanHistoryResource.class);

    private static final String ENTITY_NAME = "freeSpanHistory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FreeSpanHistoryRepository freeSpanHistoryRepository;

    public FreeSpanHistoryResource(FreeSpanHistoryRepository freeSpanHistoryRepository) {
        this.freeSpanHistoryRepository = freeSpanHistoryRepository;
    }

    /**
     * {@code POST  /free-span-histories} : Create a new freeSpanHistory.
     *
     * @param freeSpanHistory the freeSpanHistory to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new freeSpanHistory, or with status {@code 400 (Bad Request)} if the freeSpanHistory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/free-span-histories")
    public ResponseEntity<FreeSpanHistory> createFreeSpanHistory(@RequestBody FreeSpanHistory freeSpanHistory) throws URISyntaxException {
        log.debug("REST request to save FreeSpanHistory : {}", freeSpanHistory);
        if (freeSpanHistory.getId() != null) {
            throw new BadRequestAlertException("A new freeSpanHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FreeSpanHistory result = freeSpanHistoryRepository.save(freeSpanHistory);
        return ResponseEntity.created(new URI("/api/free-span-histories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /free-span-histories} : Updates an existing freeSpanHistory.
     *
     * @param freeSpanHistory the freeSpanHistory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated freeSpanHistory,
     * or with status {@code 400 (Bad Request)} if the freeSpanHistory is not valid,
     * or with status {@code 500 (Internal Server Error)} if the freeSpanHistory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/free-span-histories")
    public ResponseEntity<FreeSpanHistory> updateFreeSpanHistory(@RequestBody FreeSpanHistory freeSpanHistory) throws URISyntaxException {
        log.debug("REST request to update FreeSpanHistory : {}", freeSpanHistory);
        if (freeSpanHistory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FreeSpanHistory result = freeSpanHistoryRepository.save(freeSpanHistory);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, freeSpanHistory.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /free-span-histories} : get all the freeSpanHistories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of freeSpanHistories in body.
     */
    @GetMapping("/free-span-histories")
    public List<FreeSpanHistory> getAllFreeSpanHistories() {
        log.debug("REST request to get all FreeSpanHistories");
        return freeSpanHistoryRepository.findAll();
    }

    /**
     * {@code GET  /free-span-histories/:id} : get the "id" freeSpanHistory.
     *
     * @param id the id of the freeSpanHistory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the freeSpanHistory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/free-span-histories/{id}")
    public ResponseEntity<FreeSpanHistory> getFreeSpanHistory(@PathVariable Long id) {
        log.debug("REST request to get FreeSpanHistory : {}", id);
        Optional<FreeSpanHistory> freeSpanHistory = freeSpanHistoryRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(freeSpanHistory);
    }

    /**
     * {@code DELETE  /free-span-histories/:id} : delete the "id" freeSpanHistory.
     *
     * @param id the id of the freeSpanHistory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/free-span-histories/{id}")
    public ResponseEntity<Void> deleteFreeSpanHistory(@PathVariable Long id) {
        log.debug("REST request to delete FreeSpanHistory : {}", id);
        freeSpanHistoryRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
