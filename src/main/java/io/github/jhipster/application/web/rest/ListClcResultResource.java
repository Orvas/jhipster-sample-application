package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListClcResultService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListClcResultDTO;
import io.github.jhipster.application.service.dto.ListClcResultCriteria;
import io.github.jhipster.application.service.ListClcResultQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListClcResult}.
 */
@RestController
@RequestMapping("/api")
public class ListClcResultResource {

    private final Logger log = LoggerFactory.getLogger(ListClcResultResource.class);

    private static final String ENTITY_NAME = "listClcResult";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListClcResultService listClcResultService;

    private final ListClcResultQueryService listClcResultQueryService;

    public ListClcResultResource(ListClcResultService listClcResultService, ListClcResultQueryService listClcResultQueryService) {
        this.listClcResultService = listClcResultService;
        this.listClcResultQueryService = listClcResultQueryService;
    }

    /**
     * {@code POST  /list-clc-results} : Create a new listClcResult.
     *
     * @param listClcResultDTO the listClcResultDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listClcResultDTO, or with status {@code 400 (Bad Request)} if the listClcResult has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-clc-results")
    public ResponseEntity<ListClcResultDTO> createListClcResult(@Valid @RequestBody ListClcResultDTO listClcResultDTO) throws URISyntaxException {
        log.debug("REST request to save ListClcResult : {}", listClcResultDTO);
        if (listClcResultDTO.getId() != null) {
            throw new BadRequestAlertException("A new listClcResult cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListClcResultDTO result = listClcResultService.save(listClcResultDTO);
        return ResponseEntity.created(new URI("/api/list-clc-results/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-clc-results} : Updates an existing listClcResult.
     *
     * @param listClcResultDTO the listClcResultDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listClcResultDTO,
     * or with status {@code 400 (Bad Request)} if the listClcResultDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listClcResultDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-clc-results")
    public ResponseEntity<ListClcResultDTO> updateListClcResult(@Valid @RequestBody ListClcResultDTO listClcResultDTO) throws URISyntaxException {
        log.debug("REST request to update ListClcResult : {}", listClcResultDTO);
        if (listClcResultDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListClcResultDTO result = listClcResultService.save(listClcResultDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listClcResultDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-clc-results} : get all the listClcResults.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listClcResults in body.
     */
    @GetMapping("/list-clc-results")
    public ResponseEntity<List<ListClcResultDTO>> getAllListClcResults(ListClcResultCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListClcResults by criteria: {}", criteria);
        Page<ListClcResultDTO> page = listClcResultQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-clc-results/count} : count all the listClcResults.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-clc-results/count")
    public ResponseEntity<Long> countListClcResults(ListClcResultCriteria criteria) {
        log.debug("REST request to count ListClcResults by criteria: {}", criteria);
        return ResponseEntity.ok().body(listClcResultQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-clc-results/:id} : get the "id" listClcResult.
     *
     * @param id the id of the listClcResultDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listClcResultDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-clc-results/{id}")
    public ResponseEntity<ListClcResultDTO> getListClcResult(@PathVariable Long id) {
        log.debug("REST request to get ListClcResult : {}", id);
        Optional<ListClcResultDTO> listClcResultDTO = listClcResultService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listClcResultDTO);
    }

    /**
     * {@code DELETE  /list-clc-results/:id} : delete the "id" listClcResult.
     *
     * @param id the id of the listClcResultDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-clc-results/{id}")
    public ResponseEntity<Void> deleteListClcResult(@PathVariable Long id) {
        log.debug("REST request to delete ListClcResult : {}", id);
        listClcResultService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
