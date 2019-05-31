package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListRiskProbabilityService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListRiskProbabilityDTO;
import io.github.jhipster.application.service.dto.ListRiskProbabilityCriteria;
import io.github.jhipster.application.service.ListRiskProbabilityQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListRiskProbability}.
 */
@RestController
@RequestMapping("/api")
public class ListRiskProbabilityResource {

    private final Logger log = LoggerFactory.getLogger(ListRiskProbabilityResource.class);

    private static final String ENTITY_NAME = "listRiskProbability";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListRiskProbabilityService listRiskProbabilityService;

    private final ListRiskProbabilityQueryService listRiskProbabilityQueryService;

    public ListRiskProbabilityResource(ListRiskProbabilityService listRiskProbabilityService, ListRiskProbabilityQueryService listRiskProbabilityQueryService) {
        this.listRiskProbabilityService = listRiskProbabilityService;
        this.listRiskProbabilityQueryService = listRiskProbabilityQueryService;
    }

    /**
     * {@code POST  /list-risk-probabilities} : Create a new listRiskProbability.
     *
     * @param listRiskProbabilityDTO the listRiskProbabilityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listRiskProbabilityDTO, or with status {@code 400 (Bad Request)} if the listRiskProbability has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-risk-probabilities")
    public ResponseEntity<ListRiskProbabilityDTO> createListRiskProbability(@Valid @RequestBody ListRiskProbabilityDTO listRiskProbabilityDTO) throws URISyntaxException {
        log.debug("REST request to save ListRiskProbability : {}", listRiskProbabilityDTO);
        if (listRiskProbabilityDTO.getId() != null) {
            throw new BadRequestAlertException("A new listRiskProbability cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListRiskProbabilityDTO result = listRiskProbabilityService.save(listRiskProbabilityDTO);
        return ResponseEntity.created(new URI("/api/list-risk-probabilities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-risk-probabilities} : Updates an existing listRiskProbability.
     *
     * @param listRiskProbabilityDTO the listRiskProbabilityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listRiskProbabilityDTO,
     * or with status {@code 400 (Bad Request)} if the listRiskProbabilityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listRiskProbabilityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-risk-probabilities")
    public ResponseEntity<ListRiskProbabilityDTO> updateListRiskProbability(@Valid @RequestBody ListRiskProbabilityDTO listRiskProbabilityDTO) throws URISyntaxException {
        log.debug("REST request to update ListRiskProbability : {}", listRiskProbabilityDTO);
        if (listRiskProbabilityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListRiskProbabilityDTO result = listRiskProbabilityService.save(listRiskProbabilityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listRiskProbabilityDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-risk-probabilities} : get all the listRiskProbabilities.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listRiskProbabilities in body.
     */
    @GetMapping("/list-risk-probabilities")
    public ResponseEntity<List<ListRiskProbabilityDTO>> getAllListRiskProbabilities(ListRiskProbabilityCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListRiskProbabilities by criteria: {}", criteria);
        Page<ListRiskProbabilityDTO> page = listRiskProbabilityQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-risk-probabilities/count} : count all the listRiskProbabilities.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-risk-probabilities/count")
    public ResponseEntity<Long> countListRiskProbabilities(ListRiskProbabilityCriteria criteria) {
        log.debug("REST request to count ListRiskProbabilities by criteria: {}", criteria);
        return ResponseEntity.ok().body(listRiskProbabilityQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-risk-probabilities/:id} : get the "id" listRiskProbability.
     *
     * @param id the id of the listRiskProbabilityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listRiskProbabilityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-risk-probabilities/{id}")
    public ResponseEntity<ListRiskProbabilityDTO> getListRiskProbability(@PathVariable Long id) {
        log.debug("REST request to get ListRiskProbability : {}", id);
        Optional<ListRiskProbabilityDTO> listRiskProbabilityDTO = listRiskProbabilityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listRiskProbabilityDTO);
    }

    /**
     * {@code DELETE  /list-risk-probabilities/:id} : delete the "id" listRiskProbability.
     *
     * @param id the id of the listRiskProbabilityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-risk-probabilities/{id}")
    public ResponseEntity<Void> deleteListRiskProbability(@PathVariable Long id) {
        log.debug("REST request to delete ListRiskProbability : {}", id);
        listRiskProbabilityService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
