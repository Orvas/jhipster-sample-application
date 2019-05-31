package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.TeeService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.TeeDTO;
import io.github.jhipster.application.service.dto.TeeCriteria;
import io.github.jhipster.application.service.TeeQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.Tee}.
 */
@RestController
@RequestMapping("/api")
public class TeeResource {

    private final Logger log = LoggerFactory.getLogger(TeeResource.class);

    private static final String ENTITY_NAME = "tee";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TeeService teeService;

    private final TeeQueryService teeQueryService;

    public TeeResource(TeeService teeService, TeeQueryService teeQueryService) {
        this.teeService = teeService;
        this.teeQueryService = teeQueryService;
    }

    /**
     * {@code POST  /tees} : Create a new tee.
     *
     * @param teeDTO the teeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new teeDTO, or with status {@code 400 (Bad Request)} if the tee has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tees")
    public ResponseEntity<TeeDTO> createTee(@Valid @RequestBody TeeDTO teeDTO) throws URISyntaxException {
        log.debug("REST request to save Tee : {}", teeDTO);
        if (teeDTO.getId() != null) {
            throw new BadRequestAlertException("A new tee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TeeDTO result = teeService.save(teeDTO);
        return ResponseEntity.created(new URI("/api/tees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tees} : Updates an existing tee.
     *
     * @param teeDTO the teeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated teeDTO,
     * or with status {@code 400 (Bad Request)} if the teeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the teeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tees")
    public ResponseEntity<TeeDTO> updateTee(@Valid @RequestBody TeeDTO teeDTO) throws URISyntaxException {
        log.debug("REST request to update Tee : {}", teeDTO);
        if (teeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TeeDTO result = teeService.save(teeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, teeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tees} : get all the tees.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tees in body.
     */
    @GetMapping("/tees")
    public ResponseEntity<List<TeeDTO>> getAllTees(TeeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get Tees by criteria: {}", criteria);
        Page<TeeDTO> page = teeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /tees/count} : count all the tees.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/tees/count")
    public ResponseEntity<Long> countTees(TeeCriteria criteria) {
        log.debug("REST request to count Tees by criteria: {}", criteria);
        return ResponseEntity.ok().body(teeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /tees/:id} : get the "id" tee.
     *
     * @param id the id of the teeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the teeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tees/{id}")
    public ResponseEntity<TeeDTO> getTee(@PathVariable Long id) {
        log.debug("REST request to get Tee : {}", id);
        Optional<TeeDTO> teeDTO = teeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(teeDTO);
    }

    /**
     * {@code DELETE  /tees/:id} : delete the "id" tee.
     *
     * @param id the id of the teeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tees/{id}")
    public ResponseEntity<Void> deleteTee(@PathVariable Long id) {
        log.debug("REST request to delete Tee : {}", id);
        teeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
