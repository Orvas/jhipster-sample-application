package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.ListObjectStatus;
import io.github.jhipster.application.repository.ListObjectStatusRepository;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListObjectStatus}.
 */
@RestController
@RequestMapping("/api")
public class ListObjectStatusResource {

    private final Logger log = LoggerFactory.getLogger(ListObjectStatusResource.class);

    private static final String ENTITY_NAME = "listObjectStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListObjectStatusRepository listObjectStatusRepository;

    public ListObjectStatusResource(ListObjectStatusRepository listObjectStatusRepository) {
        this.listObjectStatusRepository = listObjectStatusRepository;
    }

    /**
     * {@code POST  /list-object-statuses} : Create a new listObjectStatus.
     *
     * @param listObjectStatus the listObjectStatus to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listObjectStatus, or with status {@code 400 (Bad Request)} if the listObjectStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-object-statuses")
    public ResponseEntity<ListObjectStatus> createListObjectStatus(@RequestBody ListObjectStatus listObjectStatus) throws URISyntaxException {
        log.debug("REST request to save ListObjectStatus : {}", listObjectStatus);
        if (listObjectStatus.getId() != null) {
            throw new BadRequestAlertException("A new listObjectStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListObjectStatus result = listObjectStatusRepository.save(listObjectStatus);
        return ResponseEntity.created(new URI("/api/list-object-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-object-statuses} : Updates an existing listObjectStatus.
     *
     * @param listObjectStatus the listObjectStatus to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listObjectStatus,
     * or with status {@code 400 (Bad Request)} if the listObjectStatus is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listObjectStatus couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-object-statuses")
    public ResponseEntity<ListObjectStatus> updateListObjectStatus(@RequestBody ListObjectStatus listObjectStatus) throws URISyntaxException {
        log.debug("REST request to update ListObjectStatus : {}", listObjectStatus);
        if (listObjectStatus.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListObjectStatus result = listObjectStatusRepository.save(listObjectStatus);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listObjectStatus.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-object-statuses} : get all the listObjectStatuses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listObjectStatuses in body.
     */
    @GetMapping("/list-object-statuses")
    public List<ListObjectStatus> getAllListObjectStatuses() {
        log.debug("REST request to get all ListObjectStatuses");
        return listObjectStatusRepository.findAll();
    }

    /**
     * {@code GET  /list-object-statuses/:id} : get the "id" listObjectStatus.
     *
     * @param id the id of the listObjectStatus to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listObjectStatus, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-object-statuses/{id}")
    public ResponseEntity<ListObjectStatus> getListObjectStatus(@PathVariable Long id) {
        log.debug("REST request to get ListObjectStatus : {}", id);
        Optional<ListObjectStatus> listObjectStatus = listObjectStatusRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(listObjectStatus);
    }

    /**
     * {@code DELETE  /list-object-statuses/:id} : delete the "id" listObjectStatus.
     *
     * @param id the id of the listObjectStatus to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-object-statuses/{id}")
    public ResponseEntity<Void> deleteListObjectStatus(@PathVariable Long id) {
        log.debug("REST request to delete ListObjectStatus : {}", id);
        listObjectStatusRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
