package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.TeeHistService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.TeeHistDTO;
import io.github.jhipster.application.service.dto.TeeHistCriteria;
import io.github.jhipster.application.service.TeeHistQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.TeeHist}.
 */
@RestController
@RequestMapping("/api")
public class TeeHistResource {

    private final Logger log = LoggerFactory.getLogger(TeeHistResource.class);

    private static final String ENTITY_NAME = "teeHist";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TeeHistService teeHistService;

    private final TeeHistQueryService teeHistQueryService;

    public TeeHistResource(TeeHistService teeHistService, TeeHistQueryService teeHistQueryService) {
        this.teeHistService = teeHistService;
        this.teeHistQueryService = teeHistQueryService;
    }

    /**
     * {@code POST  /tee-hists} : Create a new teeHist.
     *
     * @param teeHistDTO the teeHistDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new teeHistDTO, or with status {@code 400 (Bad Request)} if the teeHist has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tee-hists")
    public ResponseEntity<TeeHistDTO> createTeeHist(@Valid @RequestBody TeeHistDTO teeHistDTO) throws URISyntaxException {
        log.debug("REST request to save TeeHist : {}", teeHistDTO);
        if (teeHistDTO.getId() != null) {
            throw new BadRequestAlertException("A new teeHist cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TeeHistDTO result = teeHistService.save(teeHistDTO);
        return ResponseEntity.created(new URI("/api/tee-hists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tee-hists} : Updates an existing teeHist.
     *
     * @param teeHistDTO the teeHistDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated teeHistDTO,
     * or with status {@code 400 (Bad Request)} if the teeHistDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the teeHistDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tee-hists")
    public ResponseEntity<TeeHistDTO> updateTeeHist(@Valid @RequestBody TeeHistDTO teeHistDTO) throws URISyntaxException {
        log.debug("REST request to update TeeHist : {}", teeHistDTO);
        if (teeHistDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TeeHistDTO result = teeHistService.save(teeHistDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, teeHistDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tee-hists} : get all the teeHists.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of teeHists in body.
     */
    @GetMapping("/tee-hists")
    public ResponseEntity<List<TeeHistDTO>> getAllTeeHists(TeeHistCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get TeeHists by criteria: {}", criteria);
        Page<TeeHistDTO> page = teeHistQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /tee-hists/count} : count all the teeHists.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/tee-hists/count")
    public ResponseEntity<Long> countTeeHists(TeeHistCriteria criteria) {
        log.debug("REST request to count TeeHists by criteria: {}", criteria);
        return ResponseEntity.ok().body(teeHistQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /tee-hists/:id} : get the "id" teeHist.
     *
     * @param id the id of the teeHistDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the teeHistDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tee-hists/{id}")
    public ResponseEntity<TeeHistDTO> getTeeHist(@PathVariable Long id) {
        log.debug("REST request to get TeeHist : {}", id);
        Optional<TeeHistDTO> teeHistDTO = teeHistService.findOne(id);
        return ResponseUtil.wrapOrNotFound(teeHistDTO);
    }

    /**
     * {@code DELETE  /tee-hists/:id} : delete the "id" teeHist.
     *
     * @param id the id of the teeHistDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tee-hists/{id}")
    public ResponseEntity<Void> deleteTeeHist(@PathVariable Long id) {
        log.debug("REST request to delete TeeHist : {}", id);
        teeHistService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
