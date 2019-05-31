package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.PipelineHistService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.PipelineHistDTO;
import io.github.jhipster.application.service.dto.PipelineHistCriteria;
import io.github.jhipster.application.service.PipelineHistQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.PipelineHist}.
 */
@RestController
@RequestMapping("/api")
public class PipelineHistResource {

    private final Logger log = LoggerFactory.getLogger(PipelineHistResource.class);

    private static final String ENTITY_NAME = "pipelineHist";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PipelineHistService pipelineHistService;

    private final PipelineHistQueryService pipelineHistQueryService;

    public PipelineHistResource(PipelineHistService pipelineHistService, PipelineHistQueryService pipelineHistQueryService) {
        this.pipelineHistService = pipelineHistService;
        this.pipelineHistQueryService = pipelineHistQueryService;
    }

    /**
     * {@code POST  /pipeline-hists} : Create a new pipelineHist.
     *
     * @param pipelineHistDTO the pipelineHistDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pipelineHistDTO, or with status {@code 400 (Bad Request)} if the pipelineHist has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pipeline-hists")
    public ResponseEntity<PipelineHistDTO> createPipelineHist(@Valid @RequestBody PipelineHistDTO pipelineHistDTO) throws URISyntaxException {
        log.debug("REST request to save PipelineHist : {}", pipelineHistDTO);
        if (pipelineHistDTO.getId() != null) {
            throw new BadRequestAlertException("A new pipelineHist cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PipelineHistDTO result = pipelineHistService.save(pipelineHistDTO);
        return ResponseEntity.created(new URI("/api/pipeline-hists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pipeline-hists} : Updates an existing pipelineHist.
     *
     * @param pipelineHistDTO the pipelineHistDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pipelineHistDTO,
     * or with status {@code 400 (Bad Request)} if the pipelineHistDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pipelineHistDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pipeline-hists")
    public ResponseEntity<PipelineHistDTO> updatePipelineHist(@Valid @RequestBody PipelineHistDTO pipelineHistDTO) throws URISyntaxException {
        log.debug("REST request to update PipelineHist : {}", pipelineHistDTO);
        if (pipelineHistDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PipelineHistDTO result = pipelineHistService.save(pipelineHistDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, pipelineHistDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pipeline-hists} : get all the pipelineHists.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pipelineHists in body.
     */
    @GetMapping("/pipeline-hists")
    public ResponseEntity<List<PipelineHistDTO>> getAllPipelineHists(PipelineHistCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get PipelineHists by criteria: {}", criteria);
        Page<PipelineHistDTO> page = pipelineHistQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /pipeline-hists/count} : count all the pipelineHists.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/pipeline-hists/count")
    public ResponseEntity<Long> countPipelineHists(PipelineHistCriteria criteria) {
        log.debug("REST request to count PipelineHists by criteria: {}", criteria);
        return ResponseEntity.ok().body(pipelineHistQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /pipeline-hists/:id} : get the "id" pipelineHist.
     *
     * @param id the id of the pipelineHistDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pipelineHistDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pipeline-hists/{id}")
    public ResponseEntity<PipelineHistDTO> getPipelineHist(@PathVariable Long id) {
        log.debug("REST request to get PipelineHist : {}", id);
        Optional<PipelineHistDTO> pipelineHistDTO = pipelineHistService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pipelineHistDTO);
    }

    /**
     * {@code DELETE  /pipeline-hists/:id} : delete the "id" pipelineHist.
     *
     * @param id the id of the pipelineHistDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pipeline-hists/{id}")
    public ResponseEntity<Void> deletePipelineHist(@PathVariable Long id) {
        log.debug("REST request to delete PipelineHist : {}", id);
        pipelineHistService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
