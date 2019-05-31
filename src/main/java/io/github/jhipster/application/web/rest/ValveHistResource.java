package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ValveHistService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ValveHistDTO;
import io.github.jhipster.application.service.dto.ValveHistCriteria;
import io.github.jhipster.application.service.ValveHistQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ValveHist}.
 */
@RestController
@RequestMapping("/api")
public class ValveHistResource {

    private final Logger log = LoggerFactory.getLogger(ValveHistResource.class);

    private static final String ENTITY_NAME = "valveHist";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ValveHistService valveHistService;

    private final ValveHistQueryService valveHistQueryService;

    public ValveHistResource(ValveHistService valveHistService, ValveHistQueryService valveHistQueryService) {
        this.valveHistService = valveHistService;
        this.valveHistQueryService = valveHistQueryService;
    }

    /**
     * {@code POST  /valve-hists} : Create a new valveHist.
     *
     * @param valveHistDTO the valveHistDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new valveHistDTO, or with status {@code 400 (Bad Request)} if the valveHist has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/valve-hists")
    public ResponseEntity<ValveHistDTO> createValveHist(@Valid @RequestBody ValveHistDTO valveHistDTO) throws URISyntaxException {
        log.debug("REST request to save ValveHist : {}", valveHistDTO);
        if (valveHistDTO.getId() != null) {
            throw new BadRequestAlertException("A new valveHist cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ValveHistDTO result = valveHistService.save(valveHistDTO);
        return ResponseEntity.created(new URI("/api/valve-hists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /valve-hists} : Updates an existing valveHist.
     *
     * @param valveHistDTO the valveHistDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated valveHistDTO,
     * or with status {@code 400 (Bad Request)} if the valveHistDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the valveHistDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/valve-hists")
    public ResponseEntity<ValveHistDTO> updateValveHist(@Valid @RequestBody ValveHistDTO valveHistDTO) throws URISyntaxException {
        log.debug("REST request to update ValveHist : {}", valveHistDTO);
        if (valveHistDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ValveHistDTO result = valveHistService.save(valveHistDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, valveHistDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /valve-hists} : get all the valveHists.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of valveHists in body.
     */
    @GetMapping("/valve-hists")
    public ResponseEntity<List<ValveHistDTO>> getAllValveHists(ValveHistCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ValveHists by criteria: {}", criteria);
        Page<ValveHistDTO> page = valveHistQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /valve-hists/count} : count all the valveHists.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/valve-hists/count")
    public ResponseEntity<Long> countValveHists(ValveHistCriteria criteria) {
        log.debug("REST request to count ValveHists by criteria: {}", criteria);
        return ResponseEntity.ok().body(valveHistQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /valve-hists/:id} : get the "id" valveHist.
     *
     * @param id the id of the valveHistDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the valveHistDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/valve-hists/{id}")
    public ResponseEntity<ValveHistDTO> getValveHist(@PathVariable Long id) {
        log.debug("REST request to get ValveHist : {}", id);
        Optional<ValveHistDTO> valveHistDTO = valveHistService.findOne(id);
        return ResponseUtil.wrapOrNotFound(valveHistDTO);
    }

    /**
     * {@code DELETE  /valve-hists/:id} : delete the "id" valveHist.
     *
     * @param id the id of the valveHistDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/valve-hists/{id}")
    public ResponseEntity<Void> deleteValveHist(@PathVariable Long id) {
        log.debug("REST request to delete ValveHist : {}", id);
        valveHistService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
