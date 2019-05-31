package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.CpsRangeService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.CpsRangeDTO;
import io.github.jhipster.application.service.dto.CpsRangeCriteria;
import io.github.jhipster.application.service.CpsRangeQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.CpsRange}.
 */
@RestController
@RequestMapping("/api")
public class CpsRangeResource {

    private final Logger log = LoggerFactory.getLogger(CpsRangeResource.class);

    private static final String ENTITY_NAME = "cpsRange";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CpsRangeService cpsRangeService;

    private final CpsRangeQueryService cpsRangeQueryService;

    public CpsRangeResource(CpsRangeService cpsRangeService, CpsRangeQueryService cpsRangeQueryService) {
        this.cpsRangeService = cpsRangeService;
        this.cpsRangeQueryService = cpsRangeQueryService;
    }

    /**
     * {@code POST  /cps-ranges} : Create a new cpsRange.
     *
     * @param cpsRangeDTO the cpsRangeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cpsRangeDTO, or with status {@code 400 (Bad Request)} if the cpsRange has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cps-ranges")
    public ResponseEntity<CpsRangeDTO> createCpsRange(@Valid @RequestBody CpsRangeDTO cpsRangeDTO) throws URISyntaxException {
        log.debug("REST request to save CpsRange : {}", cpsRangeDTO);
        if (cpsRangeDTO.getId() != null) {
            throw new BadRequestAlertException("A new cpsRange cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CpsRangeDTO result = cpsRangeService.save(cpsRangeDTO);
        return ResponseEntity.created(new URI("/api/cps-ranges/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cps-ranges} : Updates an existing cpsRange.
     *
     * @param cpsRangeDTO the cpsRangeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cpsRangeDTO,
     * or with status {@code 400 (Bad Request)} if the cpsRangeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cpsRangeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cps-ranges")
    public ResponseEntity<CpsRangeDTO> updateCpsRange(@Valid @RequestBody CpsRangeDTO cpsRangeDTO) throws URISyntaxException {
        log.debug("REST request to update CpsRange : {}", cpsRangeDTO);
        if (cpsRangeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CpsRangeDTO result = cpsRangeService.save(cpsRangeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cpsRangeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cps-ranges} : get all the cpsRanges.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cpsRanges in body.
     */
    @GetMapping("/cps-ranges")
    public ResponseEntity<List<CpsRangeDTO>> getAllCpsRanges(CpsRangeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get CpsRanges by criteria: {}", criteria);
        Page<CpsRangeDTO> page = cpsRangeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /cps-ranges/count} : count all the cpsRanges.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/cps-ranges/count")
    public ResponseEntity<Long> countCpsRanges(CpsRangeCriteria criteria) {
        log.debug("REST request to count CpsRanges by criteria: {}", criteria);
        return ResponseEntity.ok().body(cpsRangeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /cps-ranges/:id} : get the "id" cpsRange.
     *
     * @param id the id of the cpsRangeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cpsRangeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cps-ranges/{id}")
    public ResponseEntity<CpsRangeDTO> getCpsRange(@PathVariable Long id) {
        log.debug("REST request to get CpsRange : {}", id);
        Optional<CpsRangeDTO> cpsRangeDTO = cpsRangeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cpsRangeDTO);
    }

    /**
     * {@code DELETE  /cps-ranges/:id} : delete the "id" cpsRange.
     *
     * @param id the id of the cpsRangeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cps-ranges/{id}")
    public ResponseEntity<Void> deleteCpsRange(@PathVariable Long id) {
        log.debug("REST request to delete CpsRange : {}", id);
        cpsRangeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
