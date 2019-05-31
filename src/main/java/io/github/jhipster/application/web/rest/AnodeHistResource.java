package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.AnodeHistService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.AnodeHistDTO;
import io.github.jhipster.application.service.dto.AnodeHistCriteria;
import io.github.jhipster.application.service.AnodeHistQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.AnodeHist}.
 */
@RestController
@RequestMapping("/api")
public class AnodeHistResource {

    private final Logger log = LoggerFactory.getLogger(AnodeHistResource.class);

    private static final String ENTITY_NAME = "anodeHist";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AnodeHistService anodeHistService;

    private final AnodeHistQueryService anodeHistQueryService;

    public AnodeHistResource(AnodeHistService anodeHistService, AnodeHistQueryService anodeHistQueryService) {
        this.anodeHistService = anodeHistService;
        this.anodeHistQueryService = anodeHistQueryService;
    }

    /**
     * {@code POST  /anode-hists} : Create a new anodeHist.
     *
     * @param anodeHistDTO the anodeHistDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new anodeHistDTO, or with status {@code 400 (Bad Request)} if the anodeHist has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/anode-hists")
    public ResponseEntity<AnodeHistDTO> createAnodeHist(@Valid @RequestBody AnodeHistDTO anodeHistDTO) throws URISyntaxException {
        log.debug("REST request to save AnodeHist : {}", anodeHistDTO);
        if (anodeHistDTO.getId() != null) {
            throw new BadRequestAlertException("A new anodeHist cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AnodeHistDTO result = anodeHistService.save(anodeHistDTO);
        return ResponseEntity.created(new URI("/api/anode-hists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /anode-hists} : Updates an existing anodeHist.
     *
     * @param anodeHistDTO the anodeHistDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated anodeHistDTO,
     * or with status {@code 400 (Bad Request)} if the anodeHistDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the anodeHistDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/anode-hists")
    public ResponseEntity<AnodeHistDTO> updateAnodeHist(@Valid @RequestBody AnodeHistDTO anodeHistDTO) throws URISyntaxException {
        log.debug("REST request to update AnodeHist : {}", anodeHistDTO);
        if (anodeHistDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AnodeHistDTO result = anodeHistService.save(anodeHistDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, anodeHistDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /anode-hists} : get all the anodeHists.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of anodeHists in body.
     */
    @GetMapping("/anode-hists")
    public ResponseEntity<List<AnodeHistDTO>> getAllAnodeHists(AnodeHistCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get AnodeHists by criteria: {}", criteria);
        Page<AnodeHistDTO> page = anodeHistQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /anode-hists/count} : count all the anodeHists.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/anode-hists/count")
    public ResponseEntity<Long> countAnodeHists(AnodeHistCriteria criteria) {
        log.debug("REST request to count AnodeHists by criteria: {}", criteria);
        return ResponseEntity.ok().body(anodeHistQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /anode-hists/:id} : get the "id" anodeHist.
     *
     * @param id the id of the anodeHistDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the anodeHistDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/anode-hists/{id}")
    public ResponseEntity<AnodeHistDTO> getAnodeHist(@PathVariable Long id) {
        log.debug("REST request to get AnodeHist : {}", id);
        Optional<AnodeHistDTO> anodeHistDTO = anodeHistService.findOne(id);
        return ResponseUtil.wrapOrNotFound(anodeHistDTO);
    }

    /**
     * {@code DELETE  /anode-hists/:id} : delete the "id" anodeHist.
     *
     * @param id the id of the anodeHistDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/anode-hists/{id}")
    public ResponseEntity<Void> deleteAnodeHist(@PathVariable Long id) {
        log.debug("REST request to delete AnodeHist : {}", id);
        anodeHistService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
