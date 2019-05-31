package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.FreeSpanService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.FreeSpanDTO;
import io.github.jhipster.application.service.dto.FreeSpanCriteria;
import io.github.jhipster.application.service.FreeSpanQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.FreeSpan}.
 */
@RestController
@RequestMapping("/api")
public class FreeSpanResource {

    private final Logger log = LoggerFactory.getLogger(FreeSpanResource.class);

    private static final String ENTITY_NAME = "freeSpan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FreeSpanService freeSpanService;

    private final FreeSpanQueryService freeSpanQueryService;

    public FreeSpanResource(FreeSpanService freeSpanService, FreeSpanQueryService freeSpanQueryService) {
        this.freeSpanService = freeSpanService;
        this.freeSpanQueryService = freeSpanQueryService;
    }

    /**
     * {@code POST  /free-spans} : Create a new freeSpan.
     *
     * @param freeSpanDTO the freeSpanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new freeSpanDTO, or with status {@code 400 (Bad Request)} if the freeSpan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/free-spans")
    public ResponseEntity<FreeSpanDTO> createFreeSpan(@Valid @RequestBody FreeSpanDTO freeSpanDTO) throws URISyntaxException {
        log.debug("REST request to save FreeSpan : {}", freeSpanDTO);
        if (freeSpanDTO.getId() != null) {
            throw new BadRequestAlertException("A new freeSpan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FreeSpanDTO result = freeSpanService.save(freeSpanDTO);
        return ResponseEntity.created(new URI("/api/free-spans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /free-spans} : Updates an existing freeSpan.
     *
     * @param freeSpanDTO the freeSpanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated freeSpanDTO,
     * or with status {@code 400 (Bad Request)} if the freeSpanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the freeSpanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/free-spans")
    public ResponseEntity<FreeSpanDTO> updateFreeSpan(@Valid @RequestBody FreeSpanDTO freeSpanDTO) throws URISyntaxException {
        log.debug("REST request to update FreeSpan : {}", freeSpanDTO);
        if (freeSpanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FreeSpanDTO result = freeSpanService.save(freeSpanDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, freeSpanDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /free-spans} : get all the freeSpans.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of freeSpans in body.
     */
    @GetMapping("/free-spans")
    public ResponseEntity<List<FreeSpanDTO>> getAllFreeSpans(FreeSpanCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get FreeSpans by criteria: {}", criteria);
        Page<FreeSpanDTO> page = freeSpanQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /free-spans/count} : count all the freeSpans.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/free-spans/count")
    public ResponseEntity<Long> countFreeSpans(FreeSpanCriteria criteria) {
        log.debug("REST request to count FreeSpans by criteria: {}", criteria);
        return ResponseEntity.ok().body(freeSpanQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /free-spans/:id} : get the "id" freeSpan.
     *
     * @param id the id of the freeSpanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the freeSpanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/free-spans/{id}")
    public ResponseEntity<FreeSpanDTO> getFreeSpan(@PathVariable Long id) {
        log.debug("REST request to get FreeSpan : {}", id);
        Optional<FreeSpanDTO> freeSpanDTO = freeSpanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(freeSpanDTO);
    }

    /**
     * {@code DELETE  /free-spans/:id} : delete the "id" freeSpan.
     *
     * @param id the id of the freeSpanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/free-spans/{id}")
    public ResponseEntity<Void> deleteFreeSpan(@PathVariable Long id) {
        log.debug("REST request to delete FreeSpan : {}", id);
        freeSpanService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
