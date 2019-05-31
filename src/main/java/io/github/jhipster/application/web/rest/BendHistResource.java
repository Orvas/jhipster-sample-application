package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.BendHistService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.BendHistDTO;
import io.github.jhipster.application.service.dto.BendHistCriteria;
import io.github.jhipster.application.service.BendHistQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.BendHist}.
 */
@RestController
@RequestMapping("/api")
public class BendHistResource {

    private final Logger log = LoggerFactory.getLogger(BendHistResource.class);

    private static final String ENTITY_NAME = "bendHist";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BendHistService bendHistService;

    private final BendHistQueryService bendHistQueryService;

    public BendHistResource(BendHistService bendHistService, BendHistQueryService bendHistQueryService) {
        this.bendHistService = bendHistService;
        this.bendHistQueryService = bendHistQueryService;
    }

    /**
     * {@code POST  /bend-hists} : Create a new bendHist.
     *
     * @param bendHistDTO the bendHistDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bendHistDTO, or with status {@code 400 (Bad Request)} if the bendHist has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bend-hists")
    public ResponseEntity<BendHistDTO> createBendHist(@Valid @RequestBody BendHistDTO bendHistDTO) throws URISyntaxException {
        log.debug("REST request to save BendHist : {}", bendHistDTO);
        if (bendHistDTO.getId() != null) {
            throw new BadRequestAlertException("A new bendHist cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BendHistDTO result = bendHistService.save(bendHistDTO);
        return ResponseEntity.created(new URI("/api/bend-hists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bend-hists} : Updates an existing bendHist.
     *
     * @param bendHistDTO the bendHistDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bendHistDTO,
     * or with status {@code 400 (Bad Request)} if the bendHistDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bendHistDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bend-hists")
    public ResponseEntity<BendHistDTO> updateBendHist(@Valid @RequestBody BendHistDTO bendHistDTO) throws URISyntaxException {
        log.debug("REST request to update BendHist : {}", bendHistDTO);
        if (bendHistDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BendHistDTO result = bendHistService.save(bendHistDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bendHistDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bend-hists} : get all the bendHists.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bendHists in body.
     */
    @GetMapping("/bend-hists")
    public ResponseEntity<List<BendHistDTO>> getAllBendHists(BendHistCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get BendHists by criteria: {}", criteria);
        Page<BendHistDTO> page = bendHistQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /bend-hists/count} : count all the bendHists.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/bend-hists/count")
    public ResponseEntity<Long> countBendHists(BendHistCriteria criteria) {
        log.debug("REST request to count BendHists by criteria: {}", criteria);
        return ResponseEntity.ok().body(bendHistQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /bend-hists/:id} : get the "id" bendHist.
     *
     * @param id the id of the bendHistDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bendHistDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bend-hists/{id}")
    public ResponseEntity<BendHistDTO> getBendHist(@PathVariable Long id) {
        log.debug("REST request to get BendHist : {}", id);
        Optional<BendHistDTO> bendHistDTO = bendHistService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bendHistDTO);
    }

    /**
     * {@code DELETE  /bend-hists/:id} : delete the "id" bendHist.
     *
     * @param id the id of the bendHistDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bend-hists/{id}")
    public ResponseEntity<Void> deleteBendHist(@PathVariable Long id) {
        log.debug("REST request to delete BendHist : {}", id);
        bendHistService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
