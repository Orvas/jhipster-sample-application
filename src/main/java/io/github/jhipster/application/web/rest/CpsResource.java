package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.CpsService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.CpsDTO;
import io.github.jhipster.application.service.dto.CpsCriteria;
import io.github.jhipster.application.service.CpsQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.Cps}.
 */
@RestController
@RequestMapping("/api")
public class CpsResource {

    private final Logger log = LoggerFactory.getLogger(CpsResource.class);

    private static final String ENTITY_NAME = "cps";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CpsService cpsService;

    private final CpsQueryService cpsQueryService;

    public CpsResource(CpsService cpsService, CpsQueryService cpsQueryService) {
        this.cpsService = cpsService;
        this.cpsQueryService = cpsQueryService;
    }

    /**
     * {@code POST  /cps} : Create a new cps.
     *
     * @param cpsDTO the cpsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cpsDTO, or with status {@code 400 (Bad Request)} if the cps has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cps")
    public ResponseEntity<CpsDTO> createCps(@Valid @RequestBody CpsDTO cpsDTO) throws URISyntaxException {
        log.debug("REST request to save Cps : {}", cpsDTO);
        if (cpsDTO.getId() != null) {
            throw new BadRequestAlertException("A new cps cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CpsDTO result = cpsService.save(cpsDTO);
        return ResponseEntity.created(new URI("/api/cps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cps} : Updates an existing cps.
     *
     * @param cpsDTO the cpsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cpsDTO,
     * or with status {@code 400 (Bad Request)} if the cpsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cpsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cps")
    public ResponseEntity<CpsDTO> updateCps(@Valid @RequestBody CpsDTO cpsDTO) throws URISyntaxException {
        log.debug("REST request to update Cps : {}", cpsDTO);
        if (cpsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CpsDTO result = cpsService.save(cpsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cpsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cps} : get all the cps.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cps in body.
     */
    @GetMapping("/cps")
    public ResponseEntity<List<CpsDTO>> getAllCps(CpsCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get Cps by criteria: {}", criteria);
        Page<CpsDTO> page = cpsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /cps/count} : count all the cps.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/cps/count")
    public ResponseEntity<Long> countCps(CpsCriteria criteria) {
        log.debug("REST request to count Cps by criteria: {}", criteria);
        return ResponseEntity.ok().body(cpsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /cps/:id} : get the "id" cps.
     *
     * @param id the id of the cpsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cpsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cps/{id}")
    public ResponseEntity<CpsDTO> getCps(@PathVariable Long id) {
        log.debug("REST request to get Cps : {}", id);
        Optional<CpsDTO> cpsDTO = cpsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cpsDTO);
    }

    /**
     * {@code DELETE  /cps/:id} : delete the "id" cps.
     *
     * @param id the id of the cpsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cps/{id}")
    public ResponseEntity<Void> deleteCps(@PathVariable Long id) {
        log.debug("REST request to delete Cps : {}", id);
        cpsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
