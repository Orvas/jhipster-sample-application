package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.BuckleArrestorHistService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.BuckleArrestorHistDTO;
import io.github.jhipster.application.service.dto.BuckleArrestorHistCriteria;
import io.github.jhipster.application.service.BuckleArrestorHistQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.BuckleArrestorHist}.
 */
@RestController
@RequestMapping("/api")
public class BuckleArrestorHistResource {

    private final Logger log = LoggerFactory.getLogger(BuckleArrestorHistResource.class);

    private static final String ENTITY_NAME = "buckleArrestorHist";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BuckleArrestorHistService buckleArrestorHistService;

    private final BuckleArrestorHistQueryService buckleArrestorHistQueryService;

    public BuckleArrestorHistResource(BuckleArrestorHistService buckleArrestorHistService, BuckleArrestorHistQueryService buckleArrestorHistQueryService) {
        this.buckleArrestorHistService = buckleArrestorHistService;
        this.buckleArrestorHistQueryService = buckleArrestorHistQueryService;
    }

    /**
     * {@code POST  /buckle-arrestor-hists} : Create a new buckleArrestorHist.
     *
     * @param buckleArrestorHistDTO the buckleArrestorHistDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new buckleArrestorHistDTO, or with status {@code 400 (Bad Request)} if the buckleArrestorHist has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/buckle-arrestor-hists")
    public ResponseEntity<BuckleArrestorHistDTO> createBuckleArrestorHist(@Valid @RequestBody BuckleArrestorHistDTO buckleArrestorHistDTO) throws URISyntaxException {
        log.debug("REST request to save BuckleArrestorHist : {}", buckleArrestorHistDTO);
        if (buckleArrestorHistDTO.getId() != null) {
            throw new BadRequestAlertException("A new buckleArrestorHist cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BuckleArrestorHistDTO result = buckleArrestorHistService.save(buckleArrestorHistDTO);
        return ResponseEntity.created(new URI("/api/buckle-arrestor-hists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /buckle-arrestor-hists} : Updates an existing buckleArrestorHist.
     *
     * @param buckleArrestorHistDTO the buckleArrestorHistDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated buckleArrestorHistDTO,
     * or with status {@code 400 (Bad Request)} if the buckleArrestorHistDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the buckleArrestorHistDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/buckle-arrestor-hists")
    public ResponseEntity<BuckleArrestorHistDTO> updateBuckleArrestorHist(@Valid @RequestBody BuckleArrestorHistDTO buckleArrestorHistDTO) throws URISyntaxException {
        log.debug("REST request to update BuckleArrestorHist : {}", buckleArrestorHistDTO);
        if (buckleArrestorHistDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BuckleArrestorHistDTO result = buckleArrestorHistService.save(buckleArrestorHistDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, buckleArrestorHistDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /buckle-arrestor-hists} : get all the buckleArrestorHists.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of buckleArrestorHists in body.
     */
    @GetMapping("/buckle-arrestor-hists")
    public ResponseEntity<List<BuckleArrestorHistDTO>> getAllBuckleArrestorHists(BuckleArrestorHistCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get BuckleArrestorHists by criteria: {}", criteria);
        Page<BuckleArrestorHistDTO> page = buckleArrestorHistQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /buckle-arrestor-hists/count} : count all the buckleArrestorHists.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/buckle-arrestor-hists/count")
    public ResponseEntity<Long> countBuckleArrestorHists(BuckleArrestorHistCriteria criteria) {
        log.debug("REST request to count BuckleArrestorHists by criteria: {}", criteria);
        return ResponseEntity.ok().body(buckleArrestorHistQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /buckle-arrestor-hists/:id} : get the "id" buckleArrestorHist.
     *
     * @param id the id of the buckleArrestorHistDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the buckleArrestorHistDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/buckle-arrestor-hists/{id}")
    public ResponseEntity<BuckleArrestorHistDTO> getBuckleArrestorHist(@PathVariable Long id) {
        log.debug("REST request to get BuckleArrestorHist : {}", id);
        Optional<BuckleArrestorHistDTO> buckleArrestorHistDTO = buckleArrestorHistService.findOne(id);
        return ResponseUtil.wrapOrNotFound(buckleArrestorHistDTO);
    }

    /**
     * {@code DELETE  /buckle-arrestor-hists/:id} : delete the "id" buckleArrestorHist.
     *
     * @param id the id of the buckleArrestorHistDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/buckle-arrestor-hists/{id}")
    public ResponseEntity<Void> deleteBuckleArrestorHist(@PathVariable Long id) {
        log.debug("REST request to delete BuckleArrestorHist : {}", id);
        buckleArrestorHistService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
