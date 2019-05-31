package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.DisplacementHistService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.DisplacementHistDTO;
import io.github.jhipster.application.service.dto.DisplacementHistCriteria;
import io.github.jhipster.application.service.DisplacementHistQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.DisplacementHist}.
 */
@RestController
@RequestMapping("/api")
public class DisplacementHistResource {

    private final Logger log = LoggerFactory.getLogger(DisplacementHistResource.class);

    private static final String ENTITY_NAME = "displacementHist";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DisplacementHistService displacementHistService;

    private final DisplacementHistQueryService displacementHistQueryService;

    public DisplacementHistResource(DisplacementHistService displacementHistService, DisplacementHistQueryService displacementHistQueryService) {
        this.displacementHistService = displacementHistService;
        this.displacementHistQueryService = displacementHistQueryService;
    }

    /**
     * {@code POST  /displacement-hists} : Create a new displacementHist.
     *
     * @param displacementHistDTO the displacementHistDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new displacementHistDTO, or with status {@code 400 (Bad Request)} if the displacementHist has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/displacement-hists")
    public ResponseEntity<DisplacementHistDTO> createDisplacementHist(@Valid @RequestBody DisplacementHistDTO displacementHistDTO) throws URISyntaxException {
        log.debug("REST request to save DisplacementHist : {}", displacementHistDTO);
        if (displacementHistDTO.getId() != null) {
            throw new BadRequestAlertException("A new displacementHist cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DisplacementHistDTO result = displacementHistService.save(displacementHistDTO);
        return ResponseEntity.created(new URI("/api/displacement-hists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /displacement-hists} : Updates an existing displacementHist.
     *
     * @param displacementHistDTO the displacementHistDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated displacementHistDTO,
     * or with status {@code 400 (Bad Request)} if the displacementHistDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the displacementHistDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/displacement-hists")
    public ResponseEntity<DisplacementHistDTO> updateDisplacementHist(@Valid @RequestBody DisplacementHistDTO displacementHistDTO) throws URISyntaxException {
        log.debug("REST request to update DisplacementHist : {}", displacementHistDTO);
        if (displacementHistDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DisplacementHistDTO result = displacementHistService.save(displacementHistDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, displacementHistDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /displacement-hists} : get all the displacementHists.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of displacementHists in body.
     */
    @GetMapping("/displacement-hists")
    public ResponseEntity<List<DisplacementHistDTO>> getAllDisplacementHists(DisplacementHistCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get DisplacementHists by criteria: {}", criteria);
        Page<DisplacementHistDTO> page = displacementHistQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /displacement-hists/count} : count all the displacementHists.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/displacement-hists/count")
    public ResponseEntity<Long> countDisplacementHists(DisplacementHistCriteria criteria) {
        log.debug("REST request to count DisplacementHists by criteria: {}", criteria);
        return ResponseEntity.ok().body(displacementHistQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /displacement-hists/:id} : get the "id" displacementHist.
     *
     * @param id the id of the displacementHistDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the displacementHistDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/displacement-hists/{id}")
    public ResponseEntity<DisplacementHistDTO> getDisplacementHist(@PathVariable Long id) {
        log.debug("REST request to get DisplacementHist : {}", id);
        Optional<DisplacementHistDTO> displacementHistDTO = displacementHistService.findOne(id);
        return ResponseUtil.wrapOrNotFound(displacementHistDTO);
    }

    /**
     * {@code DELETE  /displacement-hists/:id} : delete the "id" displacementHist.
     *
     * @param id the id of the displacementHistDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/displacement-hists/{id}")
    public ResponseEntity<Void> deleteDisplacementHist(@PathVariable Long id) {
        log.debug("REST request to delete DisplacementHist : {}", id);
        displacementHistService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
