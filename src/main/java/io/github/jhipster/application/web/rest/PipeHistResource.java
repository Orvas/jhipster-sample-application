package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.PipeHistService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.PipeHistDTO;
import io.github.jhipster.application.service.dto.PipeHistCriteria;
import io.github.jhipster.application.service.PipeHistQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.PipeHist}.
 */
@RestController
@RequestMapping("/api")
public class PipeHistResource {

    private final Logger log = LoggerFactory.getLogger(PipeHistResource.class);

    private static final String ENTITY_NAME = "pipeHist";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PipeHistService pipeHistService;

    private final PipeHistQueryService pipeHistQueryService;

    public PipeHistResource(PipeHistService pipeHistService, PipeHistQueryService pipeHistQueryService) {
        this.pipeHistService = pipeHistService;
        this.pipeHistQueryService = pipeHistQueryService;
    }

    /**
     * {@code POST  /pipe-hists} : Create a new pipeHist.
     *
     * @param pipeHistDTO the pipeHistDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pipeHistDTO, or with status {@code 400 (Bad Request)} if the pipeHist has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pipe-hists")
    public ResponseEntity<PipeHistDTO> createPipeHist(@Valid @RequestBody PipeHistDTO pipeHistDTO) throws URISyntaxException {
        log.debug("REST request to save PipeHist : {}", pipeHistDTO);
        if (pipeHistDTO.getId() != null) {
            throw new BadRequestAlertException("A new pipeHist cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PipeHistDTO result = pipeHistService.save(pipeHistDTO);
        return ResponseEntity.created(new URI("/api/pipe-hists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pipe-hists} : Updates an existing pipeHist.
     *
     * @param pipeHistDTO the pipeHistDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pipeHistDTO,
     * or with status {@code 400 (Bad Request)} if the pipeHistDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pipeHistDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pipe-hists")
    public ResponseEntity<PipeHistDTO> updatePipeHist(@Valid @RequestBody PipeHistDTO pipeHistDTO) throws URISyntaxException {
        log.debug("REST request to update PipeHist : {}", pipeHistDTO);
        if (pipeHistDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PipeHistDTO result = pipeHistService.save(pipeHistDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, pipeHistDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pipe-hists} : get all the pipeHists.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pipeHists in body.
     */
    @GetMapping("/pipe-hists")
    public ResponseEntity<List<PipeHistDTO>> getAllPipeHists(PipeHistCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get PipeHists by criteria: {}", criteria);
        Page<PipeHistDTO> page = pipeHistQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /pipe-hists/count} : count all the pipeHists.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/pipe-hists/count")
    public ResponseEntity<Long> countPipeHists(PipeHistCriteria criteria) {
        log.debug("REST request to count PipeHists by criteria: {}", criteria);
        return ResponseEntity.ok().body(pipeHistQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /pipe-hists/:id} : get the "id" pipeHist.
     *
     * @param id the id of the pipeHistDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pipeHistDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pipe-hists/{id}")
    public ResponseEntity<PipeHistDTO> getPipeHist(@PathVariable Long id) {
        log.debug("REST request to get PipeHist : {}", id);
        Optional<PipeHistDTO> pipeHistDTO = pipeHistService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pipeHistDTO);
    }

    /**
     * {@code DELETE  /pipe-hists/:id} : delete the "id" pipeHist.
     *
     * @param id the id of the pipeHistDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pipe-hists/{id}")
    public ResponseEntity<Void> deletePipeHist(@PathVariable Long id) {
        log.debug("REST request to delete PipeHist : {}", id);
        pipeHistService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
