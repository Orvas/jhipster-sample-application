package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.FreeSpanHistService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.FreeSpanHistDTO;
import io.github.jhipster.application.service.dto.FreeSpanHistCriteria;
import io.github.jhipster.application.service.FreeSpanHistQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.FreeSpanHist}.
 */
@RestController
@RequestMapping("/api")
public class FreeSpanHistResource {

    private final Logger log = LoggerFactory.getLogger(FreeSpanHistResource.class);

    private static final String ENTITY_NAME = "freeSpanHist";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FreeSpanHistService freeSpanHistService;

    private final FreeSpanHistQueryService freeSpanHistQueryService;

    public FreeSpanHistResource(FreeSpanHistService freeSpanHistService, FreeSpanHistQueryService freeSpanHistQueryService) {
        this.freeSpanHistService = freeSpanHistService;
        this.freeSpanHistQueryService = freeSpanHistQueryService;
    }

    /**
     * {@code POST  /free-span-hists} : Create a new freeSpanHist.
     *
     * @param freeSpanHistDTO the freeSpanHistDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new freeSpanHistDTO, or with status {@code 400 (Bad Request)} if the freeSpanHist has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/free-span-hists")
    public ResponseEntity<FreeSpanHistDTO> createFreeSpanHist(@Valid @RequestBody FreeSpanHistDTO freeSpanHistDTO) throws URISyntaxException {
        log.debug("REST request to save FreeSpanHist : {}", freeSpanHistDTO);
        if (freeSpanHistDTO.getId() != null) {
            throw new BadRequestAlertException("A new freeSpanHist cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FreeSpanHistDTO result = freeSpanHistService.save(freeSpanHistDTO);
        return ResponseEntity.created(new URI("/api/free-span-hists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /free-span-hists} : Updates an existing freeSpanHist.
     *
     * @param freeSpanHistDTO the freeSpanHistDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated freeSpanHistDTO,
     * or with status {@code 400 (Bad Request)} if the freeSpanHistDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the freeSpanHistDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/free-span-hists")
    public ResponseEntity<FreeSpanHistDTO> updateFreeSpanHist(@Valid @RequestBody FreeSpanHistDTO freeSpanHistDTO) throws URISyntaxException {
        log.debug("REST request to update FreeSpanHist : {}", freeSpanHistDTO);
        if (freeSpanHistDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FreeSpanHistDTO result = freeSpanHistService.save(freeSpanHistDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, freeSpanHistDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /free-span-hists} : get all the freeSpanHists.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of freeSpanHists in body.
     */
    @GetMapping("/free-span-hists")
    public ResponseEntity<List<FreeSpanHistDTO>> getAllFreeSpanHists(FreeSpanHistCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get FreeSpanHists by criteria: {}", criteria);
        Page<FreeSpanHistDTO> page = freeSpanHistQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /free-span-hists/count} : count all the freeSpanHists.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/free-span-hists/count")
    public ResponseEntity<Long> countFreeSpanHists(FreeSpanHistCriteria criteria) {
        log.debug("REST request to count FreeSpanHists by criteria: {}", criteria);
        return ResponseEntity.ok().body(freeSpanHistQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /free-span-hists/:id} : get the "id" freeSpanHist.
     *
     * @param id the id of the freeSpanHistDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the freeSpanHistDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/free-span-hists/{id}")
    public ResponseEntity<FreeSpanHistDTO> getFreeSpanHist(@PathVariable Long id) {
        log.debug("REST request to get FreeSpanHist : {}", id);
        Optional<FreeSpanHistDTO> freeSpanHistDTO = freeSpanHistService.findOne(id);
        return ResponseUtil.wrapOrNotFound(freeSpanHistDTO);
    }

    /**
     * {@code DELETE  /free-span-hists/:id} : delete the "id" freeSpanHist.
     *
     * @param id the id of the freeSpanHistDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/free-span-hists/{id}")
    public ResponseEntity<Void> deleteFreeSpanHist(@PathVariable Long id) {
        log.debug("REST request to delete FreeSpanHist : {}", id);
        freeSpanHistService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
