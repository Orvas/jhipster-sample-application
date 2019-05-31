package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListWrkPurposeService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListWrkPurposeDTO;
import io.github.jhipster.application.service.dto.ListWrkPurposeCriteria;
import io.github.jhipster.application.service.ListWrkPurposeQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListWrkPurpose}.
 */
@RestController
@RequestMapping("/api")
public class ListWrkPurposeResource {

    private final Logger log = LoggerFactory.getLogger(ListWrkPurposeResource.class);

    private static final String ENTITY_NAME = "listWrkPurpose";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListWrkPurposeService listWrkPurposeService;

    private final ListWrkPurposeQueryService listWrkPurposeQueryService;

    public ListWrkPurposeResource(ListWrkPurposeService listWrkPurposeService, ListWrkPurposeQueryService listWrkPurposeQueryService) {
        this.listWrkPurposeService = listWrkPurposeService;
        this.listWrkPurposeQueryService = listWrkPurposeQueryService;
    }

    /**
     * {@code POST  /list-wrk-purposes} : Create a new listWrkPurpose.
     *
     * @param listWrkPurposeDTO the listWrkPurposeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listWrkPurposeDTO, or with status {@code 400 (Bad Request)} if the listWrkPurpose has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-wrk-purposes")
    public ResponseEntity<ListWrkPurposeDTO> createListWrkPurpose(@Valid @RequestBody ListWrkPurposeDTO listWrkPurposeDTO) throws URISyntaxException {
        log.debug("REST request to save ListWrkPurpose : {}", listWrkPurposeDTO);
        if (listWrkPurposeDTO.getId() != null) {
            throw new BadRequestAlertException("A new listWrkPurpose cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListWrkPurposeDTO result = listWrkPurposeService.save(listWrkPurposeDTO);
        return ResponseEntity.created(new URI("/api/list-wrk-purposes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-wrk-purposes} : Updates an existing listWrkPurpose.
     *
     * @param listWrkPurposeDTO the listWrkPurposeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listWrkPurposeDTO,
     * or with status {@code 400 (Bad Request)} if the listWrkPurposeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listWrkPurposeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-wrk-purposes")
    public ResponseEntity<ListWrkPurposeDTO> updateListWrkPurpose(@Valid @RequestBody ListWrkPurposeDTO listWrkPurposeDTO) throws URISyntaxException {
        log.debug("REST request to update ListWrkPurpose : {}", listWrkPurposeDTO);
        if (listWrkPurposeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListWrkPurposeDTO result = listWrkPurposeService.save(listWrkPurposeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listWrkPurposeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-wrk-purposes} : get all the listWrkPurposes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listWrkPurposes in body.
     */
    @GetMapping("/list-wrk-purposes")
    public ResponseEntity<List<ListWrkPurposeDTO>> getAllListWrkPurposes(ListWrkPurposeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListWrkPurposes by criteria: {}", criteria);
        Page<ListWrkPurposeDTO> page = listWrkPurposeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-wrk-purposes/count} : count all the listWrkPurposes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-wrk-purposes/count")
    public ResponseEntity<Long> countListWrkPurposes(ListWrkPurposeCriteria criteria) {
        log.debug("REST request to count ListWrkPurposes by criteria: {}", criteria);
        return ResponseEntity.ok().body(listWrkPurposeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-wrk-purposes/:id} : get the "id" listWrkPurpose.
     *
     * @param id the id of the listWrkPurposeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listWrkPurposeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-wrk-purposes/{id}")
    public ResponseEntity<ListWrkPurposeDTO> getListWrkPurpose(@PathVariable Long id) {
        log.debug("REST request to get ListWrkPurpose : {}", id);
        Optional<ListWrkPurposeDTO> listWrkPurposeDTO = listWrkPurposeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listWrkPurposeDTO);
    }

    /**
     * {@code DELETE  /list-wrk-purposes/:id} : delete the "id" listWrkPurpose.
     *
     * @param id the id of the listWrkPurposeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-wrk-purposes/{id}")
    public ResponseEntity<Void> deleteListWrkPurpose(@PathVariable Long id) {
        log.debug("REST request to delete ListWrkPurpose : {}", id);
        listWrkPurposeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
