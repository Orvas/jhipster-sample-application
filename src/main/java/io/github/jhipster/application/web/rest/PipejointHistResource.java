package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.PipejointHistService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.PipejointHistDTO;
import io.github.jhipster.application.service.dto.PipejointHistCriteria;
import io.github.jhipster.application.service.PipejointHistQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.PipejointHist}.
 */
@RestController
@RequestMapping("/api")
public class PipejointHistResource {

    private final Logger log = LoggerFactory.getLogger(PipejointHistResource.class);

    private static final String ENTITY_NAME = "pipejointHist";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PipejointHistService pipejointHistService;

    private final PipejointHistQueryService pipejointHistQueryService;

    public PipejointHistResource(PipejointHistService pipejointHistService, PipejointHistQueryService pipejointHistQueryService) {
        this.pipejointHistService = pipejointHistService;
        this.pipejointHistQueryService = pipejointHistQueryService;
    }

    /**
     * {@code POST  /pipejoint-hists} : Create a new pipejointHist.
     *
     * @param pipejointHistDTO the pipejointHistDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pipejointHistDTO, or with status {@code 400 (Bad Request)} if the pipejointHist has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pipejoint-hists")
    public ResponseEntity<PipejointHistDTO> createPipejointHist(@Valid @RequestBody PipejointHistDTO pipejointHistDTO) throws URISyntaxException {
        log.debug("REST request to save PipejointHist : {}", pipejointHistDTO);
        if (pipejointHistDTO.getId() != null) {
            throw new BadRequestAlertException("A new pipejointHist cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PipejointHistDTO result = pipejointHistService.save(pipejointHistDTO);
        return ResponseEntity.created(new URI("/api/pipejoint-hists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pipejoint-hists} : Updates an existing pipejointHist.
     *
     * @param pipejointHistDTO the pipejointHistDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pipejointHistDTO,
     * or with status {@code 400 (Bad Request)} if the pipejointHistDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pipejointHistDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pipejoint-hists")
    public ResponseEntity<PipejointHistDTO> updatePipejointHist(@Valid @RequestBody PipejointHistDTO pipejointHistDTO) throws URISyntaxException {
        log.debug("REST request to update PipejointHist : {}", pipejointHistDTO);
        if (pipejointHistDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PipejointHistDTO result = pipejointHistService.save(pipejointHistDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, pipejointHistDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pipejoint-hists} : get all the pipejointHists.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pipejointHists in body.
     */
    @GetMapping("/pipejoint-hists")
    public ResponseEntity<List<PipejointHistDTO>> getAllPipejointHists(PipejointHistCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get PipejointHists by criteria: {}", criteria);
        Page<PipejointHistDTO> page = pipejointHistQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /pipejoint-hists/count} : count all the pipejointHists.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/pipejoint-hists/count")
    public ResponseEntity<Long> countPipejointHists(PipejointHistCriteria criteria) {
        log.debug("REST request to count PipejointHists by criteria: {}", criteria);
        return ResponseEntity.ok().body(pipejointHistQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /pipejoint-hists/:id} : get the "id" pipejointHist.
     *
     * @param id the id of the pipejointHistDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pipejointHistDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pipejoint-hists/{id}")
    public ResponseEntity<PipejointHistDTO> getPipejointHist(@PathVariable Long id) {
        log.debug("REST request to get PipejointHist : {}", id);
        Optional<PipejointHistDTO> pipejointHistDTO = pipejointHistService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pipejointHistDTO);
    }

    /**
     * {@code DELETE  /pipejoint-hists/:id} : delete the "id" pipejointHist.
     *
     * @param id the id of the pipejointHistDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pipejoint-hists/{id}")
    public ResponseEntity<Void> deletePipejointHist(@PathVariable Long id) {
        log.debug("REST request to delete PipejointHist : {}", id);
        pipejointHistService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
