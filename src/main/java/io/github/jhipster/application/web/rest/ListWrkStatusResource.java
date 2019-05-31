package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListWrkStatusService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListWrkStatusDTO;
import io.github.jhipster.application.service.dto.ListWrkStatusCriteria;
import io.github.jhipster.application.service.ListWrkStatusQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link io.github.jhipster.application.domain.ListWrkStatus}.
 */
@RestController
@RequestMapping("/api")
public class ListWrkStatusResource {

    private final Logger log = LoggerFactory.getLogger(ListWrkStatusResource.class);

    private static final String ENTITY_NAME = "listWrkStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListWrkStatusService listWrkStatusService;

    private final ListWrkStatusQueryService listWrkStatusQueryService;

    public ListWrkStatusResource(ListWrkStatusService listWrkStatusService, ListWrkStatusQueryService listWrkStatusQueryService) {
        this.listWrkStatusService = listWrkStatusService;
        this.listWrkStatusQueryService = listWrkStatusQueryService;
    }

    /**
     * {@code POST  /list-wrk-statuses} : Create a new listWrkStatus.
     *
     * @param listWrkStatusDTO the listWrkStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listWrkStatusDTO, or with status {@code 400 (Bad Request)} if the listWrkStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-wrk-statuses")
    public ResponseEntity<ListWrkStatusDTO> createListWrkStatus(@Valid @RequestBody ListWrkStatusDTO listWrkStatusDTO) throws URISyntaxException {
        log.debug("REST request to save ListWrkStatus : {}", listWrkStatusDTO);
        if (listWrkStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new listWrkStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListWrkStatusDTO result = listWrkStatusService.save(listWrkStatusDTO);
        return ResponseEntity.created(new URI("/api/list-wrk-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-wrk-statuses} : Updates an existing listWrkStatus.
     *
     * @param listWrkStatusDTO the listWrkStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listWrkStatusDTO,
     * or with status {@code 400 (Bad Request)} if the listWrkStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listWrkStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-wrk-statuses")
    public ResponseEntity<ListWrkStatusDTO> updateListWrkStatus(@Valid @RequestBody ListWrkStatusDTO listWrkStatusDTO) throws URISyntaxException {
        log.debug("REST request to update ListWrkStatus : {}", listWrkStatusDTO);
        if (listWrkStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListWrkStatusDTO result = listWrkStatusService.save(listWrkStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listWrkStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-wrk-statuses} : get all the listWrkStatuses.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listWrkStatuses in body.
     */
    @GetMapping("/list-wrk-statuses")
    public ResponseEntity<List<ListWrkStatusDTO>> getAllListWrkStatuses(ListWrkStatusCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListWrkStatuses by criteria: {}", criteria);
        Page<ListWrkStatusDTO> page = listWrkStatusQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-wrk-statuses/count} : count all the listWrkStatuses.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-wrk-statuses/count")
    public ResponseEntity<Long> countListWrkStatuses(ListWrkStatusCriteria criteria) {
        log.debug("REST request to count ListWrkStatuses by criteria: {}", criteria);
        return ResponseEntity.ok().body(listWrkStatusQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-wrk-statuses/:id} : get the "id" listWrkStatus.
     *
     * @param id the id of the listWrkStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listWrkStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-wrk-statuses/{id}")
    public ResponseEntity<ListWrkStatusDTO> getListWrkStatus(@PathVariable Long id) {
        log.debug("REST request to get ListWrkStatus : {}", id);
        Optional<ListWrkStatusDTO> listWrkStatusDTO = listWrkStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listWrkStatusDTO);
    }

    /**
     * {@code DELETE  /list-wrk-statuses/:id} : delete the "id" listWrkStatus.
     *
     * @param id the id of the listWrkStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-wrk-statuses/{id}")
    public ResponseEntity<Void> deleteListWrkStatus(@PathVariable Long id) {
        log.debug("REST request to delete ListWrkStatus : {}", id);
        listWrkStatusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
