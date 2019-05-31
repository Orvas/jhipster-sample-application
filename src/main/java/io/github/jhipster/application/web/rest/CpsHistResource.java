package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.CpsHistService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.CpsHistDTO;
import io.github.jhipster.application.service.dto.CpsHistCriteria;
import io.github.jhipster.application.service.CpsHistQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.CpsHist}.
 */
@RestController
@RequestMapping("/api")
public class CpsHistResource {

    private final Logger log = LoggerFactory.getLogger(CpsHistResource.class);

    private static final String ENTITY_NAME = "cpsHist";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CpsHistService cpsHistService;

    private final CpsHistQueryService cpsHistQueryService;

    public CpsHistResource(CpsHistService cpsHistService, CpsHistQueryService cpsHistQueryService) {
        this.cpsHistService = cpsHistService;
        this.cpsHistQueryService = cpsHistQueryService;
    }

    /**
     * {@code POST  /cps-hists} : Create a new cpsHist.
     *
     * @param cpsHistDTO the cpsHistDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cpsHistDTO, or with status {@code 400 (Bad Request)} if the cpsHist has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cps-hists")
    public ResponseEntity<CpsHistDTO> createCpsHist(@Valid @RequestBody CpsHistDTO cpsHistDTO) throws URISyntaxException {
        log.debug("REST request to save CpsHist : {}", cpsHistDTO);
        if (cpsHistDTO.getId() != null) {
            throw new BadRequestAlertException("A new cpsHist cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CpsHistDTO result = cpsHistService.save(cpsHistDTO);
        return ResponseEntity.created(new URI("/api/cps-hists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cps-hists} : Updates an existing cpsHist.
     *
     * @param cpsHistDTO the cpsHistDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cpsHistDTO,
     * or with status {@code 400 (Bad Request)} if the cpsHistDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cpsHistDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cps-hists")
    public ResponseEntity<CpsHistDTO> updateCpsHist(@Valid @RequestBody CpsHistDTO cpsHistDTO) throws URISyntaxException {
        log.debug("REST request to update CpsHist : {}", cpsHistDTO);
        if (cpsHistDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CpsHistDTO result = cpsHistService.save(cpsHistDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cpsHistDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cps-hists} : get all the cpsHists.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cpsHists in body.
     */
    @GetMapping("/cps-hists")
    public ResponseEntity<List<CpsHistDTO>> getAllCpsHists(CpsHistCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get CpsHists by criteria: {}", criteria);
        Page<CpsHistDTO> page = cpsHistQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /cps-hists/count} : count all the cpsHists.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/cps-hists/count")
    public ResponseEntity<Long> countCpsHists(CpsHistCriteria criteria) {
        log.debug("REST request to count CpsHists by criteria: {}", criteria);
        return ResponseEntity.ok().body(cpsHistQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /cps-hists/:id} : get the "id" cpsHist.
     *
     * @param id the id of the cpsHistDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cpsHistDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cps-hists/{id}")
    public ResponseEntity<CpsHistDTO> getCpsHist(@PathVariable Long id) {
        log.debug("REST request to get CpsHist : {}", id);
        Optional<CpsHistDTO> cpsHistDTO = cpsHistService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cpsHistDTO);
    }

    /**
     * {@code DELETE  /cps-hists/:id} : delete the "id" cpsHist.
     *
     * @param id the id of the cpsHistDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cps-hists/{id}")
    public ResponseEntity<Void> deleteCpsHist(@PathVariable Long id) {
        log.debug("REST request to delete CpsHist : {}", id);
        cpsHistService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
