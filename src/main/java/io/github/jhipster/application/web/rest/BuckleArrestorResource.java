package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.BuckleArrestorService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.BuckleArrestorDTO;
import io.github.jhipster.application.service.dto.BuckleArrestorCriteria;
import io.github.jhipster.application.service.BuckleArrestorQueryService;

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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link io.github.jhipster.application.domain.BuckleArrestor}.
 */
@RestController
@RequestMapping("/api")
public class BuckleArrestorResource {

    private final Logger log = LoggerFactory.getLogger(BuckleArrestorResource.class);

    private static final String ENTITY_NAME = "buckleArrestor";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BuckleArrestorService buckleArrestorService;

    private final BuckleArrestorQueryService buckleArrestorQueryService;

    public BuckleArrestorResource(BuckleArrestorService buckleArrestorService, BuckleArrestorQueryService buckleArrestorQueryService) {
        this.buckleArrestorService = buckleArrestorService;
        this.buckleArrestorQueryService = buckleArrestorQueryService;
    }

    /**
     * {@code POST  /buckle-arrestors} : Create a new buckleArrestor.
     *
     * @param buckleArrestorDTO the buckleArrestorDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new buckleArrestorDTO, or with status {@code 400 (Bad Request)} if the buckleArrestor has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/buckle-arrestors")
    public ResponseEntity<BuckleArrestorDTO> createBuckleArrestor(@Valid @RequestBody BuckleArrestorDTO buckleArrestorDTO) throws URISyntaxException {
        log.debug("REST request to save BuckleArrestor : {}", buckleArrestorDTO);
        if (buckleArrestorDTO.getId() != null) {
            throw new BadRequestAlertException("A new buckleArrestor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BuckleArrestorDTO result = buckleArrestorService.save(buckleArrestorDTO);
        return ResponseEntity.created(new URI("/api/buckle-arrestors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /buckle-arrestors} : Updates an existing buckleArrestor.
     *
     * @param buckleArrestorDTO the buckleArrestorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated buckleArrestorDTO,
     * or with status {@code 400 (Bad Request)} if the buckleArrestorDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the buckleArrestorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/buckle-arrestors")
    public ResponseEntity<BuckleArrestorDTO> updateBuckleArrestor(@Valid @RequestBody BuckleArrestorDTO buckleArrestorDTO) throws URISyntaxException {
        log.debug("REST request to update BuckleArrestor : {}", buckleArrestorDTO);
        if (buckleArrestorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BuckleArrestorDTO result = buckleArrestorService.save(buckleArrestorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, buckleArrestorDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /buckle-arrestors} : get all the buckleArrestors.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of buckleArrestors in body.
     */
    @GetMapping("/buckle-arrestors")
    public ResponseEntity<List<BuckleArrestorDTO>> getAllBuckleArrestors(BuckleArrestorCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get BuckleArrestors by criteria: {}", criteria);
        Page<BuckleArrestorDTO> page = buckleArrestorQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /buckle-arrestors/count} : count all the buckleArrestors.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/buckle-arrestors/count")
    public ResponseEntity<Long> countBuckleArrestors(BuckleArrestorCriteria criteria) {
        log.debug("REST request to count BuckleArrestors by criteria: {}", criteria);
        return ResponseEntity.ok().body(buckleArrestorQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /buckle-arrestors/:id} : get the "id" buckleArrestor.
     *
     * @param id the id of the buckleArrestorDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the buckleArrestorDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/buckle-arrestors/{id}")
    public ResponseEntity<BuckleArrestorDTO> getBuckleArrestor(@PathVariable Long id) {
        log.debug("REST request to get BuckleArrestor : {}", id);
        Optional<BuckleArrestorDTO> buckleArrestorDTO = buckleArrestorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(buckleArrestorDTO);
    }

    /**
     * {@code DELETE  /buckle-arrestors/:id} : delete the "id" buckleArrestor.
     *
     * @param id the id of the buckleArrestorDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/buckle-arrestors/{id}")
    public ResponseEntity<Void> deleteBuckleArrestor(@PathVariable Long id) {
        log.debug("REST request to delete BuckleArrestor : {}", id);
        buckleArrestorService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
