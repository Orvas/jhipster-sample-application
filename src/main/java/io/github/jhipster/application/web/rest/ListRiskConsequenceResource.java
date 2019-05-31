package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListRiskConsequenceService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListRiskConsequenceDTO;
import io.github.jhipster.application.service.dto.ListRiskConsequenceCriteria;
import io.github.jhipster.application.service.ListRiskConsequenceQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListRiskConsequence}.
 */
@RestController
@RequestMapping("/api")
public class ListRiskConsequenceResource {

    private final Logger log = LoggerFactory.getLogger(ListRiskConsequenceResource.class);

    private static final String ENTITY_NAME = "listRiskConsequence";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListRiskConsequenceService listRiskConsequenceService;

    private final ListRiskConsequenceQueryService listRiskConsequenceQueryService;

    public ListRiskConsequenceResource(ListRiskConsequenceService listRiskConsequenceService, ListRiskConsequenceQueryService listRiskConsequenceQueryService) {
        this.listRiskConsequenceService = listRiskConsequenceService;
        this.listRiskConsequenceQueryService = listRiskConsequenceQueryService;
    }

    /**
     * {@code POST  /list-risk-consequences} : Create a new listRiskConsequence.
     *
     * @param listRiskConsequenceDTO the listRiskConsequenceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listRiskConsequenceDTO, or with status {@code 400 (Bad Request)} if the listRiskConsequence has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-risk-consequences")
    public ResponseEntity<ListRiskConsequenceDTO> createListRiskConsequence(@Valid @RequestBody ListRiskConsequenceDTO listRiskConsequenceDTO) throws URISyntaxException {
        log.debug("REST request to save ListRiskConsequence : {}", listRiskConsequenceDTO);
        if (listRiskConsequenceDTO.getId() != null) {
            throw new BadRequestAlertException("A new listRiskConsequence cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListRiskConsequenceDTO result = listRiskConsequenceService.save(listRiskConsequenceDTO);
        return ResponseEntity.created(new URI("/api/list-risk-consequences/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-risk-consequences} : Updates an existing listRiskConsequence.
     *
     * @param listRiskConsequenceDTO the listRiskConsequenceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listRiskConsequenceDTO,
     * or with status {@code 400 (Bad Request)} if the listRiskConsequenceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listRiskConsequenceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-risk-consequences")
    public ResponseEntity<ListRiskConsequenceDTO> updateListRiskConsequence(@Valid @RequestBody ListRiskConsequenceDTO listRiskConsequenceDTO) throws URISyntaxException {
        log.debug("REST request to update ListRiskConsequence : {}", listRiskConsequenceDTO);
        if (listRiskConsequenceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListRiskConsequenceDTO result = listRiskConsequenceService.save(listRiskConsequenceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listRiskConsequenceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-risk-consequences} : get all the listRiskConsequences.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listRiskConsequences in body.
     */
    @GetMapping("/list-risk-consequences")
    public ResponseEntity<List<ListRiskConsequenceDTO>> getAllListRiskConsequences(ListRiskConsequenceCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListRiskConsequences by criteria: {}", criteria);
        Page<ListRiskConsequenceDTO> page = listRiskConsequenceQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-risk-consequences/count} : count all the listRiskConsequences.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-risk-consequences/count")
    public ResponseEntity<Long> countListRiskConsequences(ListRiskConsequenceCriteria criteria) {
        log.debug("REST request to count ListRiskConsequences by criteria: {}", criteria);
        return ResponseEntity.ok().body(listRiskConsequenceQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-risk-consequences/:id} : get the "id" listRiskConsequence.
     *
     * @param id the id of the listRiskConsequenceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listRiskConsequenceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-risk-consequences/{id}")
    public ResponseEntity<ListRiskConsequenceDTO> getListRiskConsequence(@PathVariable Long id) {
        log.debug("REST request to get ListRiskConsequence : {}", id);
        Optional<ListRiskConsequenceDTO> listRiskConsequenceDTO = listRiskConsequenceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listRiskConsequenceDTO);
    }

    /**
     * {@code DELETE  /list-risk-consequences/:id} : delete the "id" listRiskConsequence.
     *
     * @param id the id of the listRiskConsequenceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-risk-consequences/{id}")
    public ResponseEntity<Void> deleteListRiskConsequence(@PathVariable Long id) {
        log.debug("REST request to delete ListRiskConsequence : {}", id);
        listRiskConsequenceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
