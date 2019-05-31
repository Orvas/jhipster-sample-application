package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.FreeSpanSupportHistService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.FreeSpanSupportHistDTO;
import io.github.jhipster.application.service.dto.FreeSpanSupportHistCriteria;
import io.github.jhipster.application.service.FreeSpanSupportHistQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.FreeSpanSupportHist}.
 */
@RestController
@RequestMapping("/api")
public class FreeSpanSupportHistResource {

    private final Logger log = LoggerFactory.getLogger(FreeSpanSupportHistResource.class);

    private static final String ENTITY_NAME = "freeSpanSupportHist";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FreeSpanSupportHistService freeSpanSupportHistService;

    private final FreeSpanSupportHistQueryService freeSpanSupportHistQueryService;

    public FreeSpanSupportHistResource(FreeSpanSupportHistService freeSpanSupportHistService, FreeSpanSupportHistQueryService freeSpanSupportHistQueryService) {
        this.freeSpanSupportHistService = freeSpanSupportHistService;
        this.freeSpanSupportHistQueryService = freeSpanSupportHistQueryService;
    }

    /**
     * {@code POST  /free-span-support-hists} : Create a new freeSpanSupportHist.
     *
     * @param freeSpanSupportHistDTO the freeSpanSupportHistDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new freeSpanSupportHistDTO, or with status {@code 400 (Bad Request)} if the freeSpanSupportHist has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/free-span-support-hists")
    public ResponseEntity<FreeSpanSupportHistDTO> createFreeSpanSupportHist(@Valid @RequestBody FreeSpanSupportHistDTO freeSpanSupportHistDTO) throws URISyntaxException {
        log.debug("REST request to save FreeSpanSupportHist : {}", freeSpanSupportHistDTO);
        if (freeSpanSupportHistDTO.getId() != null) {
            throw new BadRequestAlertException("A new freeSpanSupportHist cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FreeSpanSupportHistDTO result = freeSpanSupportHistService.save(freeSpanSupportHistDTO);
        return ResponseEntity.created(new URI("/api/free-span-support-hists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /free-span-support-hists} : Updates an existing freeSpanSupportHist.
     *
     * @param freeSpanSupportHistDTO the freeSpanSupportHistDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated freeSpanSupportHistDTO,
     * or with status {@code 400 (Bad Request)} if the freeSpanSupportHistDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the freeSpanSupportHistDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/free-span-support-hists")
    public ResponseEntity<FreeSpanSupportHistDTO> updateFreeSpanSupportHist(@Valid @RequestBody FreeSpanSupportHistDTO freeSpanSupportHistDTO) throws URISyntaxException {
        log.debug("REST request to update FreeSpanSupportHist : {}", freeSpanSupportHistDTO);
        if (freeSpanSupportHistDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FreeSpanSupportHistDTO result = freeSpanSupportHistService.save(freeSpanSupportHistDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, freeSpanSupportHistDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /free-span-support-hists} : get all the freeSpanSupportHists.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of freeSpanSupportHists in body.
     */
    @GetMapping("/free-span-support-hists")
    public ResponseEntity<List<FreeSpanSupportHistDTO>> getAllFreeSpanSupportHists(FreeSpanSupportHistCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get FreeSpanSupportHists by criteria: {}", criteria);
        Page<FreeSpanSupportHistDTO> page = freeSpanSupportHistQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /free-span-support-hists/count} : count all the freeSpanSupportHists.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/free-span-support-hists/count")
    public ResponseEntity<Long> countFreeSpanSupportHists(FreeSpanSupportHistCriteria criteria) {
        log.debug("REST request to count FreeSpanSupportHists by criteria: {}", criteria);
        return ResponseEntity.ok().body(freeSpanSupportHistQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /free-span-support-hists/:id} : get the "id" freeSpanSupportHist.
     *
     * @param id the id of the freeSpanSupportHistDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the freeSpanSupportHistDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/free-span-support-hists/{id}")
    public ResponseEntity<FreeSpanSupportHistDTO> getFreeSpanSupportHist(@PathVariable Long id) {
        log.debug("REST request to get FreeSpanSupportHist : {}", id);
        Optional<FreeSpanSupportHistDTO> freeSpanSupportHistDTO = freeSpanSupportHistService.findOne(id);
        return ResponseUtil.wrapOrNotFound(freeSpanSupportHistDTO);
    }

    /**
     * {@code DELETE  /free-span-support-hists/:id} : delete the "id" freeSpanSupportHist.
     *
     * @param id the id of the freeSpanSupportHistDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/free-span-support-hists/{id}")
    public ResponseEntity<Void> deleteFreeSpanSupportHist(@PathVariable Long id) {
        log.debug("REST request to delete FreeSpanSupportHist : {}", id);
        freeSpanSupportHistService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
